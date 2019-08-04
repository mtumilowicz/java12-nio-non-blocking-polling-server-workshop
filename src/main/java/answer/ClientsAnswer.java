package answer;

import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static java.util.function.Predicate.not;

class ClientsAnswer {
    private final List<SocketChannel> clients = new ArrayList<>();

    void add(SocketChannel client) {
        clients.add(client);
    }

    void handleConnected() {
        clients.stream()
                .filter(SocketChannel::isConnected)
                .forEach(sc -> handle(new ClientConnectionAnswer(sc)));
    }

    void removeNotConnected() {
        clients.removeIf(not(SocketChannel::isConnected));
    }

    private void handle(Runnable clientConnection) {
        clientConnection.run();
    }
}
