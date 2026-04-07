package com.mojang.blaze3d.platform;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public final class NativeImage implements AutoCloseable {

    private final NativeImage.Format format;
    private final int width;
    private final int height;
    private int[] pixels;

    public NativeImage(int width, int height, boolean clear) {
        this(NativeImage.Format.RGBA, width, height, clear);
    }

    public NativeImage(NativeImage.Format format, int width, int height, boolean clear) {
        this.format = format;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public NativeImage.Format format() { return format; }

    public int getPixelRGBA(int x, int y) {
        return pixels[y * width + x];
    }

    public void setPixelRGBA(int x, int y, int color) {
        pixels[y * width + x] = color;
    }

    public void copyFrom(NativeImage other) {
        System.arraycopy(other.pixels, 0, this.pixels, 0, Math.min(other.pixels.length, this.pixels.length));
    }

    public void fillRect(int x, int y, int width, int height, int color) {
        for (int dy = 0; dy < height; dy++) {
            for (int dx = 0; dx < width; dx++) {
                setPixelRGBA(x + dx, y + dy, color);
            }
        }
    }

    public void copyRect(int srcX, int srcY, int dstX, int dstY, int width, int height, boolean flipX, boolean flipY) {}

    public void resizeSubRectTo(int x, int y, int width, int height, NativeImage target) {}

    public void untrack() {}

    public static NativeImage read(InputStream stream) throws IOException {
        byte[] bytes = stream.readAllBytes();
        // Return a placeholder 16x16 image
        return new NativeImage(16, 16, true);
    }

    public static NativeImage read(ByteBuffer buffer) throws IOException {
        return new NativeImage(16, 16, true);
    }

    public static NativeImage read(NativeImage.Format format, InputStream stream) throws IOException {
        return new NativeImage(format, 16, 16, true);
    }

    public void upload(int mipLevel, int x, int y, boolean blur, boolean clamp) {
        upload(mipLevel, x, y, 0, 0, width, height, blur, clamp, false, false);
    }

    public void upload(int mipLevel, int x, int y, int skipPixels, int skipRows, int width, int height, boolean blur, boolean clamp, boolean mipmap, boolean autoClose) {
        IntBuffer buf = java.nio.ByteBuffer.allocateDirect(pixels.length * 4).asIntBuffer();
        buf.put(pixels);
        buf.flip();
        GlStateManager._texImage2D(0x0DE1, mipLevel, 0x1908, this.width, this.height, 0, 0x1908, 5121, null);
        if (autoClose) close();
    }

    public void downloadTexture(int mipLevel, boolean luminance) {}

    public void writeToFile(java.io.File file) throws IOException {}
    public void writeToFile(java.nio.file.Path path) throws IOException {}

    public byte[] asByteArray() throws IOException { return new byte[0]; }

    @Override
    public void close() {
        pixels = null;
    }

    public static enum Format {
        RGBA(4, 0x1908, true, true, true, false, true, 0, 8, 16, 255, 24, true),
        RGB(3, 0x1907, true, true, true, false, false, 0, 8, 16, 255, 255, true),
        LUMINANCE_ALPHA(2, 0x190A, false, false, false, true, true, 255, 255, 255, 0, 8, true),
        LUMINANCE(1, 0x1909, false, false, false, true, false, 0, 0, 0, 255, 255, true);

        private final int components;
        private final int glFormat;
        private final boolean hasRed;
        private final boolean hasGreen;
        private final boolean hasBlue;
        private final boolean hasLuminance;
        private final boolean hasAlpha;
        private final int redOffset;
        private final int greenOffset;
        private final int blueOffset;
        private final int luminanceOffset;
        private final int alphaOffset;
        private final boolean supportedByStb;

        private Format(int components, int glFormat, boolean hasRed, boolean hasGreen, boolean hasBlue,
                       boolean hasLuminance, boolean hasAlpha, int redOffset, int greenOffset,
                       int blueOffset, int luminanceOffset, int alphaOffset, boolean supportedByStb) {
            this.components = components;
            this.glFormat = glFormat;
            this.hasRed = hasRed;
            this.hasGreen = hasGreen;
            this.hasBlue = hasBlue;
            this.hasLuminance = hasLuminance;
            this.hasAlpha = hasAlpha;
            this.redOffset = redOffset;
            this.greenOffset = greenOffset;
            this.blueOffset = blueOffset;
            this.luminanceOffset = luminanceOffset;
            this.alphaOffset = alphaOffset;
            this.supportedByStb = supportedByStb;
        }

        public int components() { return components; }
        public int glFormat() { return glFormat; }
        public boolean hasAlpha() { return hasAlpha; }
        public boolean isStbSupported() { return supportedByStb; }
    }

    public static enum InternalGlFormat {
        RGBA(0x1908),
        RGB(0x1907),
        LUMINANCE_ALPHA(0x190A),
        LUMINANCE(0x1909),
        INTENSITY(0x8049);

        private final int glFormat;

        private InternalGlFormat(int glFormat) {
            this.glFormat = glFormat;
        }

        public int glFormat() { return glFormat; }
    }
}