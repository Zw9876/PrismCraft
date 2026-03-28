package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;

public class GL11 {
    public static final int GL_TRUE = 1;
    public static final int GL_FALSE = 0;
    public static final int GL_TRIANGLES = 0x0004;
    public static final int GL_QUADS = 0x0007;
    public static final int GL_LINES = 0x0001;
    public static final int GL_LINE_STRIP = 0x0003;
    public static final int GL_TRIANGLE_STRIP = 0x0005;
    public static final int GL_TRIANGLE_FAN = 0x0006;
    public static final int GL_DEPTH_TEST = 0x0B71;
    public static final int GL_BLEND = 0x0BE2;
    public static final int GL_CULL_FACE = 0x0B44;
    public static final int GL_TEXTURE_2D = 0x0DE1;
    public static final int GL_COLOR_BUFFER_BIT = 0x4000;
    public static final int GL_DEPTH_BUFFER_BIT = 0x0100;
    public static final int GL_STENCIL_BUFFER_BIT = 0x0400;
    public static final int GL_MODELVIEW = 0x1700;
    public static final int GL_PROJECTION = 0x1701;
    public static final int GL_TEXTURE = 0x1702;
    public static final int GL_FLOAT = 0x1406;
    public static final int GL_UNSIGNED_BYTE = 0x1401;
    public static final int GL_UNSIGNED_INT = 0x1405;
    public static final int GL_UNSIGNED_SHORT = 0x1403;
    public static final int GL_RGBA = 0x1908;
    public static final int GL_RGB = 0x1907;
    public static final int GL_NEAREST = 0x2600;
    public static final int GL_LINEAR = 0x2601;
    public static final int GL_NEAREST_MIPMAP_LINEAR = 0x2702;
    public static final int GL_LINEAR_MIPMAP_LINEAR = 0x2703;
    public static final int GL_TEXTURE_MIN_FILTER = 0x2801;
    public static final int GL_TEXTURE_MAG_FILTER = 0x2800;
    public static final int GL_TEXTURE_WRAP_S = 0x2802;
    public static final int GL_TEXTURE_WRAP_T = 0x2803;
    public static final int GL_REPEAT = 0x2901;
    public static final int GL_CLAMP = 0x2900;
    public static final int GL_SRC_ALPHA = 0x0302;
    public static final int GL_ONE_MINUS_SRC_ALPHA = 0x0303;
    public static final int GL_ONE = 1;
    public static final int GL_ZERO = 0;
    public static final int GL_BACK = 0x0405;
    public static final int GL_FRONT = 0x0404;
    public static final int GL_FRONT_AND_BACK = 0x0408;
    public static final int GL_LEQUAL = 0x0203;
    public static final int GL_LESS = 0x0201;
    public static final int GL_GREATER = 0x0204;
    public static final int GL_EQUAL = 0x0202;
    public static final int GL_ALWAYS = 0x0207;
    public static final int GL_NEVER = 0x0200;
    public static final int GL_NOTEQUAL = 0x0205;
    public static final int GL_VIEWPORT = 0x0BA2;
    public static final int GL_MODELVIEW_MATRIX = 0x0BA6;
    public static final int GL_PROJECTION_MATRIX = 0x0BA7;

    public static void glEnable(int cap) { GLBridge.enable(cap); }
    public static void glDisable(int cap) { GLBridge.disable(cap); }
    public static void glClear(int mask) { GLBridge.clear(mask); }
    public static void glClearColor(float r, float g, float b, float a) { GLBridge.clearColor(r, g, b, a); }
    public static void glViewport(int x, int y, int width, int height) { GLBridge.viewport(x, y, width, height); }
    public static void glBindTexture(int target, int texture) { GLBridge.bindTexture(target, texture); }
    public static void glDeleteTextures(int texture) { GLBridge.deleteTexture(texture); }
    public static int glGenTextures() { return GLBridge.genTexture(); }
    public static void glTexParameteri(int target, int pname, int param) { GLBridge.texParameteri(target, pname, param); }
    public static void glBlendFunc(int sfactor, int dfactor) { GLBridge.blendFunc(sfactor, dfactor); }
    public static void glDepthMask(boolean flag) { GLBridge.depthMask(flag); }
    public static void glCullFace(int mode) { GLBridge.cullFace(mode); }
    public static void glDepthFunc(int func) { GLBridge.depthFunc(func); }
    public static void glScissor(int x, int y, int width, int height) { GLBridge.scissor(x, y, width, height); }
    public static void glColorMask(boolean r, boolean g, boolean b, boolean a) { GLBridge.colorMask(r, g, b, a); }
    public static void glLineWidth(float width) { GLBridge.lineWidth(width); }
    public static void glGetIntegerv(int pname, int[] params) { GLBridge.getIntegerv(pname, params); }
    public static int glGetError() { return GLBridge.getError(); }
    public static void glDrawArrays(int mode, int first, int count) { GLBridge.drawArrays(mode, first, count); }
    public static void glDrawElements(int mode, int count, int type, long indices) { GLBridge.drawElements(mode, count, type, (int) indices); }
}