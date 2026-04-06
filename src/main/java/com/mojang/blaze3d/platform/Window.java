package com.mojang.blaze3d.platform;

import com.eaglercraft.bridge.GLFWBridge;
import com.eaglercraft.js.HTMLCanvas;
import java.util.Optional;

public final class Window implements AutoCloseable {
    private final WindowEventHandler eventHandler;
    private int width;
    private int height;
    private int framebufferWidth;
    private int framebufferHeight;
    private int guiScaledWidth;
    private int guiScaledHeight;
    private double guiScale = 2.0;
    private boolean fullscreen = false;
    private int framerateLimit = 60;
    private boolean vsync = true;

    public Window(WindowEventHandler eventHandler, ScreenManager screenManager, DisplayData data, String videoMode, String title) {
        this.eventHandler = eventHandler;
        this.width = data.width > 0 ? data.width : 854;
        this.height = data.height > 0 ? data.height : 480;
        this.framebufferWidth = this.width;
        this.framebufferHeight = this.height;
        this.updateGuiScale();
        System.out.println("Window created: " + this.width + "x" + this.height + " - " + title);
    }

    private void updateGuiScale() {
        this.guiScaledWidth = (int)(this.width / this.guiScale);
        this.guiScaledHeight = (int)(this.height / this.guiScale);
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getFramebufferWidth() { return framebufferWidth; }
    public int getFramebufferHeight() { return framebufferHeight; }
    public int getGuiScaledWidth() { return guiScaledWidth; }
    public int getGuiScaledHeight() { return guiScaledHeight; }
    public double getGuiScale() { return guiScale; }
    public boolean isFullscreen() { return fullscreen; }
    public int getFramerateLimit() { return framerateLimit; }
    public boolean isVsync() { return vsync; }

    public void setFramerateLimit(int limit) { this.framerateLimit = limit; }
    public void updateVsync(boolean vsync) { this.vsync = vsync; }

    public boolean shouldClose() { return GLFWBridge.shouldClose(); }

    public void setTitle(String title) {
        System.out.println("Window title: " + title);
    }

    public void setIcon(java.io.InputStream icon16, java.io.InputStream icon32) {}

    public void updateDisplay() {}

    public void toggleFullScreen() {
        fullscreen = !fullscreen;
    }

    public void setGuiScale(double scale) {
        this.guiScale = scale;
        updateGuiScale();
    }

    public long getWindow() { return 1L; }

    public Monitor findBestMonitor() { return null; }

    public void setErrorSection(String section) {}

    public void defaultErrorCallback(int error, long description) {
        System.out.println("GLFW Error " + error);
    }

    public void setBootErrorCallback() {}

    public static void checkGlError(String message) {}

    @Override
    public void close() {}
}