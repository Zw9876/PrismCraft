package org.lwjgl.system;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MemoryStack implements AutoCloseable {
    private static final ThreadLocal<MemoryStack> stack = ThreadLocal.withInitial(MemoryStack::new);

    public static MemoryStack stackPush() {
        return stack.get();
    }

    public static MemoryStack stackGet() {
        return stack.get();
    }

    public IntBuffer mallocInt(int size) {
        return IntBuffer.allocate(size);
    }

    public FloatBuffer mallocFloat(int size) {
        return FloatBuffer.allocate(size);
    }

    public ByteBuffer malloc(int size) {
        return ByteBuffer.allocate(size);
    }

    public IntBuffer ints(int... values) {
        IntBuffer buf = IntBuffer.allocate(values.length);
        buf.put(values);
        buf.flip();
        return buf;
    }

    public FloatBuffer floats(float... values) {
        FloatBuffer buf = FloatBuffer.allocate(values.length);
        buf.put(values);
        buf.flip();
        return buf;
    }

    @Override
    public void close() {}
}