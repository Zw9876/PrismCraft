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
    private Shader shader;
    private Mesh mesh;

    // Triangle vertices: x, y, z, r, g, b
    private static final float[] TRIANGLE_VERTICES = {
            0.0f,  0.5f, 0.0f,  1.0f, 0.0f, 0.0f,  // top - red
            -0.5f, -0.5f, 0.0f,  0.0f, 1.0f, 0.0f,  // bottom left - green
            0.5f, -0.5f, 0.0f,  0.0f, 0.0f, 1.0f   // bottom right - blue
    };

    public GameLoop(HTMLCanvas canvas, WebGL2RenderingContext gl, InputHandler input) {
        this.canvas = canvas;
        this.gl = gl;
        this.input = input;
    }

    public void start() {
        running = true;
        System.out.println("Game loop starting...");

        shader = new Shader(gl);
        if (!shader.compile()) {
            System.out.println("ERROR: Failed to compile shaders!");
            return;
        }

        mesh = new Mesh(gl);
        mesh.upload(TRIANGLE_VERTICES, 3);
        mesh.setupAttribs(shader);

        System.out.println("Rendering initialized!");
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
        gl.clearColor(0.1f, 0.1f, 0.1f, 1.0f);
        gl.clear(gl.getColorBufferBit());

        shader.use();
        mesh.draw();
    }
}