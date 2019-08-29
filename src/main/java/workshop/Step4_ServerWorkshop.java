package workshop;

import java.io.IOException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Optional;

/**
 * Created by mtumilowicz on 2019-07-21.
 */
public class Step4_ServerWorkshop extends Step3_NonBlockingServerWorkshop {

    public Step4_ServerWorkshop(int port) {
        super(port);
    }

    public static void main(String[] args) throws IOException {
        new Step4_ServerWorkshop(81).start();
    }

    @Override
    protected void processSockets(ServerSocketChannel ssc) throws IOException {
        var clients = new Step2_ClientsWorkshop();
        // spinning loop, hint: while(true)
        // accept connection and add to clients, hint: acceptConnection, ifPresent
        // handle connected, hint: client.handleConnected()
        // remove not connected, hint: clients.removeNotConnected()
    }

    private Optional<SocketChannel> acceptConnection(ServerSocketChannel ssc) throws IOException {
        // accept connection, hint: ssc.accept()
        // observation: never blocking, nearly always null
        // if not null - configure to be non blocking, hint: configureBlocking(false)
        // if not null - log("Connected to " + newSocket);
        // return socket channel wrapped in optional

        return Optional.empty();
    }

    private void log(String message) {
        System.out.println(message);
    }
}