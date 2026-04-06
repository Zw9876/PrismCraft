package com.mojang.blaze3d.pipeline;

import com.eaglercraft.bridge.GLBridge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;

public class RenderTarget {
    public int width;
    public int height;
    public int viewWidth;
    public int viewHeight;
    public int frameBufferId = -1;
    public int colorTextureId = -1;
    public int depthBufferId = -1;
    public boolean useDepth;

    public RenderTarget(boolean useDepth) {
        this.useDepth = useDepth;
    }

    public void createBuffers(int width, int height, boolean onMac) {
        this.viewWidth = width;
        this.viewHeight = height;
        this.width = width;
        this.height = height;

        this.frameBufferId = GL30.glGenFramebuffers();
        this.colorTextureId = GL11.glGenTextures();

        if (useDepth) {
            this.depthBufferId = GL30.glGenRenderbuffers();
        }

        this.bindWrite(true);
    }

    public void bindWrite(boolean setViewport) {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, this.frameBufferId);
        if (setViewport) {
            GL11.glViewport(0, 0, this.viewWidth, this.viewHeight);
        }
    }

    public void unbindWrite() {
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
    }

    public void bindRead() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.colorTextureId);
    }

    public void unbindRead() {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
    }

    public void blitToScreen(int width, int height) {
        blitToScreen(width, height, true);
    }

    public void blitToScreen(int width, int height, boolean disableBlend) {}

    public void destroyBuffers() {
        this.unbindRead();
        this.unbindWrite();
        if (this.depthBufferId > -1) {
            GL30.glDeleteRenderbuffers(this.depthBufferId);
            this.depthBufferId = -1;
        }
        if (this.colorTextureId > -1) {
            GL11.glDeleteTextures(this.colorTextureId);
            this.colorTextureId = -1;
        }
        if (this.frameBufferId > -1) {
            GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
            GL30.glDeleteFramebuffers(this.frameBufferId);
            this.frameBufferId = -1;
        }
    }

    public void resize(int width, int height, boolean onMac) {
        this.destroyBuffers();
        this.createBuffers(width, height, onMac);
    }

    public void copyDepthFrom(RenderTarget other) {}

    public void setFilterMode(int filter) {
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.colorTextureId);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, filter);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, filter);
    }

    public boolean isStencilEnabled() { return false; }
    public void enableStencil() {}
}