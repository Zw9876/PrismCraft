package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;

public class EXTFramebufferBlit {
    public static final int GL_READ_FRAMEBUFFER_EXT = 0x8CA8;
    public static final int GL_DRAW_FRAMEBUFFER_EXT = 0x8CA9;

    public static void glBlitFramebufferEXT(int srcX0, int srcY0, int srcX1, int srcY1,
                                            int dstX0, int dstY0, int dstX1, int dstY1,
                                            int mask, int filter) {
        GLBridge.blitFramebuffer(srcX0, srcY0, srcX1, srcY1, dstX0, dstY0, dstX1, dstY1, mask, filter);
    }
}