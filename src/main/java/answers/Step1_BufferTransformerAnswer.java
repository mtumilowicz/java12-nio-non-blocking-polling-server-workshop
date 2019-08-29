package answers;

import java.nio.ByteBuffer;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

/**
 * Created by mtumilowicz on 2019-07-31.
 */
class Step1_BufferTransformerAnswer {
    static void transformBytes(ByteBuffer buf, UnaryOperator<Byte> transformation) {
        IntStream.range(0, buf.limit()).forEach(i -> buf.put(i, transformation.apply(buf.get(i))));
    }
}
