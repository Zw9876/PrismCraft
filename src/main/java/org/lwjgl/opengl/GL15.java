package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GL15 {
    public static final int GL_ARRAY_BUFFER = 0x8892;
    public static final int GL_ELEMENT_ARRAY_BUFFER = 0x8893;
    public static final int GL_STATIC_DRAW = 0x88B4;
    public static final int GL_DYNAMIC_DRAW = 0x88B8;
    public static final int GL_STREAM_DRAW = 0x88B0;

    public static int glGenBuffers() { return GLBridge.genBuffer(); }
    public static void glBindBuffer(int target, int buffer) { GLBridge.bindBuffer(target, buffer); }
    public static void glDeleteBuffers(int buffer) { GLBridge.deleteBuffer(buffer); }
    public static void glBufferData(int target, FloatBuffer data, int usage) { GLBridge.bufferDataFloat(target, data, usage); }
    public static void glBufferData(int target, IntBuffer data, int usage) { GLBridge.bufferDataInt(target, data, usage); }
    public static void glBufferData(int target, long size, int usage) { GLBridge.bufferDataEmpty(target, size, usage); }
    public static void glBufferSubData(int target, long offset, FloatBuffer data) { GLBridge.bufferSubDataFloat(target, offset, data); }
}