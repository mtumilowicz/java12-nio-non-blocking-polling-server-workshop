package workshop;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by mtumilowicz on 2019-07-31.
 */
public abstract class Step4_NonBlockingServerWorkshop {
    protected final int port;

    public Step4_NonBlockingServerWorkshop(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        log("Creating server socket on port " + port);
        // open server socket channel, hint: ServerSocketChannel.open();
        ServerSocketChannel ssc = null;
        // bind to the localhost:port, hint: bind, new InetSocketAddress("localhost", port)
        // configure non blocking, hint: configureBlocking(false)
        log("Created server socket on port " + port);

        processSockets(ssc);
    }

    protected abstract void processSockets(ServerSocketChannel ssc) throws IOException;

    private void log(String message) {
        System.out.println(message);
    }
}
