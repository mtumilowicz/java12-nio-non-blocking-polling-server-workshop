package workshop;

import java.nio.ByteBuffer;
import java.util.function.UnaryOperator;

/**
 * Created by mtumilowicz on 2019-07-31.
 */
class Step1_BufferTransformerWorkshop {
    static void transformBytes(ByteBuffer buf, UnaryOperator<Byte> transformation) {
        // transform all bytes
        // hint: buf.limit(), buf.put
    }
}
