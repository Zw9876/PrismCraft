package com.mojang.blaze3d.platform;

import com.eaglercraft.bridge.GLBridge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class GlStateManager {

    // Depth
    public static void _enableDepthTest() { GL11.glEnable(GL11.GL_DEPTH_TEST); }
    public static void _disableDepthTest() { GL11.glDisable(GL11.GL_DEPTH_TEST); }
    public static void _depthFunc(int func) { GL11.glDepthFunc(func); }
    public static void _depthMask(boolean flag) { GL11.glDepthMask(flag); }

    // Blend
    public static void _enableBlend() { GL11.glEnable(GL11.GL_BLEND); }
    public static void _disableBlend() { GL11.glDisable(GL11.GL_BLEND); }
    public static void _blendFunc(int src, int dst) { GL11.glBlendFunc(src, dst); }
    public static void _blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        GLBridge.blendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
    }
    public static void _blendEquation(int mode) {}

    // Cull
    public static void _enableCull() { GL11.glEnable(GL11.GL_CULL_FACE); }
    public static void _disableCull() { GL11.glDisable(GL11.GL_CULL_FACE); }
    public static void _cullFace(int mode) { GL11.glCullFace(mode); }

    // Texture
    public static void _enableTexture() {}
    public static void _disableTexture() {}
    public static void _activeTexture(int texture) { GL13.glActiveTexture(texture); }
    public static void _bindTexture(int texture) { GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture); }
    public static void _deleteTexture(int texture) { GL11.glDeleteTextures(texture); }
    public static int _genTexture() { return GL11.glGenTextures(); }
    public static void _texParameter(int target, int pname, int param) { GL11.glTexParameteri(target, pname, param); }
    public static void _texParameter(int target, int pname, float param) {}
    public static void _texImage2D(int target, int level, int internalFormat, int width, int height, int border, int format, int type, IntBuffer pixels) {}
    public static void _texSubImage2D(int target, int level, int x, int y, int width, int height, int format, int type, long pixels) {}
    public static void _pixelStore(int pname, int param) {}
    public static int _getTexLevelParameter(int target, int level, int pname) { return 0; }

    // Viewport and clear
    public static void _viewport(int x, int y, int width, int height) { GL11.glViewport(x, y, width, height); }
    public static void _clearColor(float r, float g, float b, float a) { GL11.glClearColor(r, g, b, a); }
    public static void _clear(int mask, boolean checkError) { GL11.glClear(mask); }
    public static void _clearDepth(double depth) {}
    public static void _clearStencil(int stencil) {}

    // Color mask
    public static void _colorMask(boolean r, boolean g, boolean b, boolean a) { GL11.glColorMask(r, g, b, a); }

    // Scissor
    public static void _enableScissorTest() { GL11.glEnable(0x0C11); }
    public static void _disableScissorTest() { GL11.glDisable(0x0C11); }
    public static void _scissorBox(int x, int y, int width, int height) { GL11.glScissor(x, y, width, height); }

    // Line width
    public static void _lineWidth(float width) { GL11.glLineWidth(width); }

    // Draw
    public static void _drawArrays(int mode, int first, int count) { GL11.glDrawArrays(mode, first, count); }
    public static void _drawElements(int mode, int count, int type, long indices) { GL11.glDrawElements(mode, count, type, indices); }

    // Polygon offset
    public static void _enablePolygonOffset() {}
    public static void _disablePolygonOffset() {}
    public static void _polygonOffset(float factor, float units) {}

    // Stencil
    public static void _enableStencilTest() {}
    public static void _disableStencilTest() {}
    public static void _stencilFunc(int func, int ref, int mask) {}
    public static void _stencilMask(int mask) {}
    public static void _stencilOp(int sfail, int dpfail, int dppass) {}

    // Legacy client state stubs (not supported in WebGL2 - use GENERIC vertex attribs instead)
    public static void _enableClientState(int cap) {}
    public static void _disableClientState(int cap) {}
    public static void _vertexPointer(int size, int type, int stride, long pointer) {}
    public static void _normalPointer(int type, int stride, long pointer) {}
    public static void _colorPointer(int size, int type, int stride, long pointer) {}
    public static void _texCoordPointer(int size, int type, int stride, long pointer) {}
    public static void _glClientActiveTexture(int texture) {}
    public static void _clearCurrentColor() {}

    // Vertex attribs (modern - actually used)
    public static void _enableVertexAttribArray(int index) { GL20.glEnableVertexAttribArray(index); }
    public static void _disableVertexAttribArray(int index) { GL20.glDisableVertexAttribArray(index); }
    public static void _vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        GL20.glVertexAttribPointer(index, size, type, normalized, stride, pointer);
    }

    // Shaders
    public static int glCreateShader(int type) { return GL20.glCreateShader(type); }
    public static void glShaderSource(int shader, CharSequence source) { GL20.glShaderSource(shader, source); }
    public static void glCompileShader(int shader) { GL20.glCompileShader(shader); }
    public static int glGetShaderi(int shader, int pname) { return GL20.glGetShaderi(shader, pname); }
    public static String glGetShaderInfoLog(int shader, int maxLength) { return GL20.glGetShaderInfoLog(shader, maxLength); }
    public static void glDeleteShader(int shader) { GL20.glDeleteShader(shader); }
    public static int glCreateProgram() { return GL20.glCreateProgram(); }
    public static void glAttachShader(int program, int shader) { GL20.glAttachShader(program, shader); }
    public static void glLinkProgram(int program) { GL20.glLinkProgram(program); }
    public static int glGetProgrami(int program, int pname) { return GL20.glGetProgrami(program, pname); }
    public static String glGetProgramInfoLog(int program, int maxLength) { return GL20.glGetProgramInfoLog(program, maxLength); }
    public static void glUseProgram(int program) { GL20.glUseProgram(program); }
    public static void glDeleteProgram(int program) { GL20.glDeleteProgram(program); }
    public static int glGetUniformLocation(int program, CharSequence name) { return GL20.glGetUniformLocation(program, name); }
    public static int glGetAttribLocation(int program, CharSequence name) { return GL20.glGetAttribLocation(program, name); }
    public static void glUniform1i(int location, int v0) { GL20.glUniform1i(location, v0); }
    public static void glUniform1f(int location, float v0) { GL20.glUniform1f(location, v0); }
    public static void glUniform2f(int location, float v0, float v1) { GL20.glUniform2f(location, v0, v1); }
    public static void glUniform3f(int location, float v0, float v1, float v2) { GL20.glUniform3f(location, v0, v1, v2); }
    public static void glUniform4f(int location, float v0, float v1, float v2, float v3) { GL20.glUniform4f(location, v0, v1, v2, v3); }
    public static void glUniformMatrix4fv(int location, boolean transpose, FloatBuffer value) { GL20.glUniformMatrix4fv(location, transpose, value); }

    // Buffers
    public static int _genBuffer() { return GLBridge.genBuffer(); }
    public static void _glBindBuffer(int target, int buffer) { GLBridge.bindBuffer(target, buffer); }
    public static void _glBufferData(int target, ByteBuffer data, int usage) {}
    public static void _glDeleteBuffers(int buffer) { GLBridge.deleteBuffer(buffer); }

    // VAO
    public static int _genVertexArray() { return GLBridge.genVertexArray(); }
    public static void _glBindVertexArray(int vao) { GLBridge.bindVertexArray(vao); }
    public static void _glDeleteVertexArrays(int vao) { GLBridge.deleteVertexArray(vao); }

    // Framebuffer
    public static int glGenFramebuffers() { return GLBridge.genFramebuffer(); }
    public static void glBindFramebuffer(int target, int framebuffer) { GLBridge.bindFramebuffer(target, framebuffer); }
    public static void glDeleteFramebuffers(int framebuffer) { GLBridge.deleteFramebuffer(framebuffer); }
    public static int glCheckFramebufferStatus(int target) { return GLBridge.checkFramebufferStatus(target); }
    public static int glGenRenderbuffers() { return GLBridge.genRenderbuffer(); }
    public static void glBindRenderbuffer(int target, int renderbuffer) { GLBridge.bindRenderbuffer(target, renderbuffer); }
    public static void glRenderbufferStorage(int target, int internalformat, int width, int height) { GLBridge.renderbufferStorage(target, internalformat, width, height); }
    public static void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) { GLBridge.framebufferTexture2D(target, attachment, textarget, texture, level); }
    public static void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) { GLBridge.framebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer); }
    public static void glBlitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) { GLBridge.blitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter); }

    // Misc
    public static int glGetError() { return GL11.glGetError(); }
    public static void _getIntegerv(int pname, IntBuffer params) { GLBridge.getIntegerv(pname, params.array()); }
    public static String _getString(int name) { return "WebGL2"; }
    public static int _getInteger(int pname) {
        int[] params = new int[1];
        GL11.glGetIntegerv(pname, params);
        return params[0];
    }

    // FBO mode detection
    public static boolean isFramebufferSupported() { return true; }
    public static void initFboMode() {}
}