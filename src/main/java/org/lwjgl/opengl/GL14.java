package org.lwjgl.opengl;

import com.eaglercraft.bridge.GLBridge;

public class GL14 {
    public static final int GL_MIRRORED_REPEAT = 0x8370;
    public static final int GL_CLAMP_TO_EDGE = 0x812F;
    public static final int GL_FUNC_ADD = 0x8006;
    public static final int GL_BLEND_SRC_ALPHA = 0x80CB;
    public static final int GL_BLEND_DST_ALPHA = 0x80CA;

    public static void glBlendFuncSeparate(int sfactorRGB, int dfactorRGB,
                                           int sfactorAlpha, int dfactorAlpha) {
        GLBridge.blendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
    }
}