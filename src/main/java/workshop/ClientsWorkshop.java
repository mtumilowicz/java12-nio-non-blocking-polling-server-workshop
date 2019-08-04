package workshop;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

class ClientsWorkshop {
    private final List<SocketChannel> clients = new ArrayList<>();

    // add client to clients
    void add(SocketChannel client) {

    }

    // handle client connections for all connected socket channels
    // hint: SocketChannel::isConnected, handle, new ClientConnectionAnswer(sc)
    void handleConnected() {

    }

    // remove all disconnected clients, hint: removeIf, not, SocketChannel::isConnected
    void removeNotConnected() {

    }

    // run client connection
    private void handle(Runnable clientConnection) {

    }
}
