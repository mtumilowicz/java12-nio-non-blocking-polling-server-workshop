package answer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by mtumilowicz on 2019-07-31.
 */
public abstract class NonBlockingServerAnswer {
    protected final int port;

    public NonBlockingServerAnswer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        log("Creating server socket on port " + port);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", port));
        ssc.configureBlocking(false);
        log("Created server socket on port " + port);

        processSockets(ssc);
    }

    protected abstract void processSockets(ServerSocketChannel ssc) throws IOException;

    private void log(String message) {
        System.out.println(message);
    }
}
