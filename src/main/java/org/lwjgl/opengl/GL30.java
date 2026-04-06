package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;

public class GL30 {
    public static final int GL_RGBA8 = 0x8058;
    public static final int GL_RGB8 = 0x8051;
    public static final int GL_FRAMEBUFFER = 0x8D40;
    public static final int GL_RENDERBUFFER = 0x8D41;
    public static final int GL_COLOR_ATTACHMENT0 = 0x8CE0;
    public static final int GL_DEPTH_ATTACHMENT = 0x8D00;
    public static final int GL_FRAMEBUFFER_COMPLETE = 0x8CD5;
    public static final int GL_DEPTH_COMPONENT16 = 0x81A5;
    public static final int GL_DEPTH_COMPONENT24 = 0x81A6;

    public static int glGenVertexArrays() { return GLBridge.genVertexArray(); }
    public static void glBindVertexArray(int vao) { GLBridge.bindVertexArray(vao); }
    public static void glDeleteVertexArrays(int vao) { GLBridge.deleteVertexArray(vao); }
    public static int glGenFramebuffers() { return GLBridge.genFramebuffer(); }
    public static void glBindFramebuffer(int target, int framebuffer) { GLBridge.bindFramebuffer(target, framebuffer); }
    public static void glDeleteFramebuffers(int framebuffer) { GLBridge.deleteFramebuffer(framebuffer); }
    public static int glGenRenderbuffers() { return GLBridge.genRenderbuffer(); }
    public static void glBindRenderbuffer(int target, int renderbuffer) { GLBridge.bindRenderbuffer(target, renderbuffer); }
    public static void glRenderbufferStorage(int target, int internalformat, int width, int height) { GLBridge.renderbufferStorage(target, internalformat, width, height); }
    public static void glFramebufferTexture2D(int target, int attachment, int textarget, int texture, int level) { GLBridge.framebufferTexture2D(target, attachment, textarget, texture, level); }
    public static void glFramebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) { GLBridge.framebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer); }
    public static void glDeleteRenderbuffers(int renderbuffer) { GLBridge.deleteRenderbuffer(renderbuffer); }
    public static int glCheckFramebufferStatus(int target) { return GLBridge.checkFramebufferStatus(target); }
    public static void glGenerateMipmap(int target) { GLBridge.generateMipmap(target); }
}