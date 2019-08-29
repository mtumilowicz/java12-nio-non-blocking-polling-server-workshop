package answers;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.function.UnaryOperator;

/**
 * Created by mtumilowicz on 2019-07-21.
 */
public class Step2_ClientConnectionAnswer implements Runnable {
    private final SocketChannel client;
    private final ByteBuffer buf;

    Step2_ClientConnectionAnswer(SocketChannel client) {
        this.client = client;
        this.buf = ByteBuffer.allocateDirect(80);
    }

    @Override
    public void run() {
        try {
            int read = client.read(buf);
            if (read > 0) {
                writeBufferToClient();
            }
            closeClientIfEnd(read);
        } catch (IOException exception) {
            // workshops
        }
    }

    private void closeClientIfEnd(int read) throws IOException {
        if (read == -1) {
            client.close();
        }
    }

    private void writeBufferToClient() throws IOException {
        buf.flip();
        Step1_BufferTransformerAnswer.transformBytes(buf, UnaryOperator.identity());
        client.write(buf);
    }
}