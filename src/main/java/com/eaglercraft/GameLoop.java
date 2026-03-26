package com.eaglercraft;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.HTMLCanvas;
import com.eaglercraft.js.WebGL2RenderingContext;

public class GameLoop {
    private final WebGL2RenderingContext gl;
    private final HTMLCanvas canvas;
    private final InputHandler input;
    private boolean running = false;
    private int frameCount = 0;

    public GameLoop(HTMLCanvas canvas, WebGL2RenderingContext gl, InputHandler input) {
        this.canvas = canvas;
        this.gl = gl;
        this.input = input;
    }

    public void start() {
        running = true;
        System.out.println("Game loop starting...");
        scheduleFrame();
    }

    private void scheduleFrame() {
        Browser.getWindow().requestAnimationFrame(this::onFrame);
    }

    private void onFrame(double timestamp) {
        if (!running) return;

        update(timestamp);
        render();
        frameCount++;

        if (frameCount % 60 == 0) {
            System.out.println("Frame: " + frameCount);
        }

        scheduleFrame();
    }

    private void update(double timestamp) {
        // Game logic will go here later
    }

    private void render() {
        gl.viewport(0, 0, canvas.getWidth(), canvas.getHeight());
        gl.clearColor(0.2f, 0.3f, 0.8f, 1.0f);
        gl.clear(gl.getColorBufferBit());
    }
}