package workshop;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by mtumilowicz on 2019-07-21.
 */
public class Step2_ClientConnectionWorkshop implements Runnable {
    private final SocketChannel client;
    private final ByteBuffer buf;

    Step2_ClientConnectionWorkshop(SocketChannel client) {
        this.client = client;
        this.buf = ByteBuffer.allocateDirect(80);
    }

    @Override
    public void run() {
        try {
            throw new IOException(); // delete it
            // read from client (socket channel) into buffer, hint: client.read(buf)
            // if something was read, write it back to the client (socket channel), hint: read > 0, writeBufferToClient
            // if end (read == -1) - close the client (socket channel), hint: closeClientIfEnd(read)
        } catch (IOException exception) {
            // workshops
        }
    }

    private void closeClientIfEnd(int read) throws IOException {
        // hint: close means read == -1, client.close()
    }

    private void writeBufferToClient() throws IOException {
        // flip buffer, hint: buf.flip()
        // implement general purpose method BufferTransformerWorkshop.transformBytes(buf, UnaryOperator.identity())
        // write back to the client, hint: client.write(buf)
    }
}