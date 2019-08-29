# java12-nio-non-blocking-polling-server-workshop 

_Reference_: https://www.udemy.com/java-non-blocking-io-with-javanio-and-design-patterns/  
_Reference_: https://github.com/kabutz/Transmogrifier  
_Reference_: http://www.java2s.com/Tutorials/Java/Socket/How_to_use_Java_SocketChannel_create_a_HTTP_client.htm  
_Reference_: https://www.youtube.com/watch?v=3m9RN4aDh08

# introduction
* current JVMs run bytecode at speeds approaching that of natively compiled code or even better (dynamic 
runtime optimizations), so
    * applications are no longer CPU bound (spending most of their time executing code) 
    * they are I/O bound (waiting for data transfers)
* how to adjust number of threads
    * CPU intense tasks - adding more threads than cores will have harmful effect on performance
        * suppose that processor works at full power and we force it to do context switches
    * I/O waiting - adding more threads could be beneficial - context switches are not so harmful
    if thread is only waiting
    * `Nthreads = NCPU * UCPU * (1 + W/C)`
        * NCPU is the number of cores, available through 
        `Runtime.getRuntime().availableProcessors()`
        * UCPU is the target CPU utilization (between 0 and 1)
        * W/C is the ratio of wait time to compute time
* operating system vs Java stream-based I/O model
    * operating system wants to move data in large chunks (buffers)
    * I/O classes of the JVM operates on small pieces — single bytes, or lines of text
    * operating system delivers buffers full of data -> stream classes of `java.io` breaks it down into little pieces
    * NIO provides similar concepts to operating system buffers - `ByteBuffer` object
    * `RandomAccessFile` with array-based `read( )` and `write( )` are pretty close to the underlying 
    operating-system calls (although at least one buffer copy)

# Buffer Handling
* buffers, and how buffers are handled, are the basis of all I/O
* "input/output" means nothing more than moving data in and out of buffers
* processes perform I/O by requesting operating system to:
    * write: drain data from a buffer 
    * read: fill buffer with data
* steps:
    1. process requests that its buffer be filled by making the `read()` system call
    1. kernel issuing a command to the disk controller hardware to fetch the data from disk
    1. disk controller writes the data directly into a kernel memory buffer by DMA (direct memory access)
    1. kernel copies the data from the temporary buffer to the buffer specified by the process
* kernel space is where the operating system lives
    * communication with device controllers
    * all I/O flows through kernel space
* why disk controller not send directly to the buffer in user space?
    * user space (where process lives) is a unprivileged area: code executing there cannot directly access 
    hardware devices
    * block-oriented hardware devices such as disk controllers operate on fixed-size data blocks 
    * user process may be requesting an oddly sized chunk of data 
    * kernel plays the role of intermediary, breaking down and reassembling data as it moves between 
    user space and storage devices
* virtual memory means that artificial, or virtual, addresses are used in place of physical 
(hardware RAM) memory addresses (simulates RAM)
    * more than one virtual address can refer to the same physical memory location
    * virtual memory space can be larger than the actual hardware memory available
    * eliminates copies between kernel and user space by mapping a kernel space address to the same 
    physical address as a virtual address in user space, the DMA hardware (which can access only physical 
    memory addresses) can fill a buffer that is simultaneously visible to both the kernel and a user space process
    
# Channels
* is a conduit to an I/O service (a hardware device, a file or socket) and provides methods for 
interacting with that service
* socket channel objects are bidirectional
* allows partial transfer - until buffer's `hasRemaining( )` method returns false
* cannot be reused - represents a specific connection to a specific I/O service and encapsulates 
the state of that connection 
* when a channel is closed - connection is lost

# Socket Channels
* models network sockets
* can operate in nonblocking mode and are selectable
* it's no longer necessary to dedicate a thread to each socket connection 
* it's possible to perform readiness selection of socket channels using a `Selector` object
* `SocketChannel`, `ServerSocketChannel` create a peer socket object when they are instantiated
    * classes from `java.net`: `Socket`, `ServerSocket` have been updated to be aware of channels
* Socket channels delegate protocol operations to the peer socket object
    * `ServerSocketChannel` doesn't have a `bind()` method, we have to fetch the peer
      socket and use it to bind to a port to begin listening for connections
* readiness selection - channel can be queried to determine if it's ready to perform an operation of interest, 
such as reading or writing
* sockets are stream-oriented, not packet-oriented
    * bytes sent will arrive in the same order, but
    * sender may write N bytes to a socket, and the receiver gets only K when invoking `read()` 
    - remaining part may still be in transit
