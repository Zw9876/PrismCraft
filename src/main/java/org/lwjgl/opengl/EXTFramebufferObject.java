package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;

public class EXTFramebufferObject {
    public static final int GL_FRAMEBUFFER_EXT = 0x8D40;
    public static final int GL_RENDERBUFFER_EXT = 0x8D41;
    public static final int GL_COLOR_ATTACHMENT0_EXT = 0x8CE0;
    public static final int GL_DEPTH_ATTACHMENT_EXT = 0x8D00;
    public static final int GL_FRAMEBUFFER_COMPLETE_EXT = 0x8CD5;
    public static final int GL_DEPTH_COMPONENT24 = 0x81A6;

    public static int glGenFramebuffersEXT() { return GLBridge.genFramebuffer(); }
    public static void glBindFramebufferEXT(int target, int framebuffer) { GLBridge.bindFramebuffer(target, framebuffer); }
    public static void glDeleteFramebuffersEXT(int framebuffer) { GLBridge.deleteFramebuffer(framebuffer); }
    public static int glCheckFramebufferStatusEXT(int target) { return GLBridge.checkFramebufferStatus(target); }
    public static int glGenRenderbuffersEXT() { return GLBridge.genRenderbuffer(); }
    public static void glBindRenderbufferEXT(int target, int renderbuffer) { GLBridge.bindRenderbuffer(target, renderbuffer); }
    public static void glRenderbufferStorageEXT(int target, int internalformat, int width, int height) { GLBridge.renderbufferStorage(target, internalformat, width, height); }
    public static void glFramebufferTexture2DEXT(int target, int attachment, int textarget, int texture, int level) { GLBridge.framebufferTexture2D(target, attachment, textarget, texture, level); }
    public static void glFramebufferRenderbufferEXT(int target, int attachment, int renderbuffertarget, int renderbuffer) { GLBridge.framebufferRenderbuffer(target, attachment, renderbuffertarget, renderbuffer); }
}