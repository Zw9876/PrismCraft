package com.mojang.blaze3d.platform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TextureUtil {

    public static int generateTextureId() {
        return GlStateManager._genTexture();
    }

    public static void releaseTextureId(int id) {
        GlStateManager._deleteTexture(id);
    }

    public static void prepareImage(int id, int width, int height) {
        GlStateManager._bindTexture(id);
        GlStateManager._texImage2D(3553, 0, 0x1908, width, height, 0, 0x1908, 5121, null);
    }

    public static void prepareImage(int id, int mipLevel, int width, int height) {
        GlStateManager._bindTexture(id);
        for (int i = 0; i <= mipLevel; i++) {
            GlStateManager._texImage2D(3553, i, 0x1908, width >> i, height >> i, 0, 0x1908, 5121, null);
        }
    }

    public static ByteBuffer readResource(InputStream stream) throws IOException {
        return ByteBuffer.wrap(stream.readAllBytes());
    }

    public static String readResourceAsString(InputStream stream) {
        try {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            return null;
        }
    }
}