package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;
import java.nio.FloatBuffer;

public class GL20 {
    public static final int GL_FRAGMENT_SHADER = 0x8B30;
    public static final int GL_VERTEX_SHADER = 0x8B31;
    public static final int GL_COMPILE_STATUS = 0x8B81;
    public static final int GL_LINK_STATUS = 0x8B82;
    public static final int GL_INFO_LOG_LENGTH = 0x8B84;

    public static int glCreateShader(int type) { return GLBridge.createShader(type); }
    public static void glShaderSource(int shader, CharSequence source) { GLBridge.shaderSource(shader, source); }
    public static void glCompileShader(int shader) { GLBridge.compileShader(shader); }
    public static int glGetShaderi(int shader, int pname) { return GLBridge.getShaderCompileStatus(shader) ? 1 : 0; }
    public static String glGetShaderInfoLog(int shader, int maxLength) { return GLBridge.getShaderInfoLog(shader); }
    public static void glDeleteShader(int shader) { GLBridge.deleteShader(shader); }
    public static int glCreateProgram() { return GLBridge.createProgram(); }
    public static void glAttachShader(int program, int shader) { GLBridge.attachShader(program, shader); }
    public static void glLinkProgram(int program) { GLBridge.linkProgram(program); }
    public static int glGetProgrami(int program, int pname) { return GLBridge.getProgramLinkStatus(program) ? 1 : 0; }
    public static String glGetProgramInfoLog(int program, int maxLength) { return GLBridge.getProgramInfoLog(program); }
    public static void glUseProgram(int program) { GLBridge.useProgram(program); }
    public static void glDeleteProgram(int program) { GLBridge.deleteProgram(program); }
    public static int glGetAttribLocation(int program, CharSequence name) { return GLBridge.getAttribLocation(program, name); }
    public static int glGetUniformLocation(int program, CharSequence name) { return GLBridge.getUniformLocation(program, name); }
    public static void glUniform1i(int location, int v0) { GLBridge.uniform1i(location, v0); }
    public static void glUniform1f(int location, float v0) { GLBridge.uniform1f(location, v0); }
    public static void glUniform2f(int location, float v0, float v1) { GLBridge.uniform2f(location, v0, v1); }
    public static void glUniform3f(int location, float v0, float v1, float v2) { GLBridge.uniform3f(location, v0, v1, v2); }
    public static void glUniform4f(int location, float v0, float v1, float v2, float v3) { GLBridge.uniform4f(location, v0, v1, v2, v3); }
    public static void glUniformMatrix4fv(int location, boolean transpose, FloatBuffer value) { GLBridge.uniformMatrix4fvBuffer(location, transpose, value); }
    public static void glEnableVertexAttribArray(int index) { GLBridge.enableVertexAttribArray(index); }
    public static void glDisableVertexAttribArray(int index) { GLBridge.disableVertexAttribArray(index); }
    public static void glVertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) { GLBridge.vertexAttribPointer(index, size, type, normalized, stride, pointer); }
}