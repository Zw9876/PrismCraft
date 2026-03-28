package org.lwjgl.system;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class MemoryUtil {
    public static final long NULL = 0L;

    public static IntBuffer memAllocInt(int size) {
        return IntBuffer.allocate(size);
    }

    public static FloatBuffer memAllocFloat(int size) {
        return FloatBuffer.allocate(size);
    }

    public static ByteBuffer memAlloc(int size) {
        return ByteBuffer.allocate(size);
    }

    public static void memFree(Object buffer) {
        // No-op in browser - GC handles it
    }

    public static IntBuffer memIntBuffer(long address, int capacity) {
        return IntBuffer.allocate(capacity);
    }

    public static FloatBuffer memFloatBuffer(long address, int capacity) {
        return FloatBuffer.allocate(capacity);
    }

    public static ByteBuffer memByteBuffer(long address, int capacity) {
        return ByteBuffer.allocate(capacity);
    }
}