package com.eaglercraft;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.HTMLCanvas;
import com.eaglercraft.js.WebGL2RenderingContext;
import com.eaglercraft.math.Matrix4f;

public class GameLoop {
    private final WebGL2RenderingContext gl;
    private final HTMLCanvas canvas;
    private final InputHandler input;
    private boolean running = false;
    private int frameCount = 0;
    private Shader shader;
    private Mesh mesh;
    private Camera camera;
    private Texture texture;

    private static final float[] CUBE_VERTICES = {
            // x      y      z     u     v
            // Front face
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 1.0f,
            -0.5f,  0.5f,  0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            // Back face
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  0.0f, 0.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  1.0f, 0.0f,
            // Left face
            -0.5f,  0.5f,  0.5f,  1.0f, 1.0f,
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f,
            -0.5f, -0.5f,  0.5f,  1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  1.0f, 1.0f,
            // Right face
            0.5f,  0.5f,  0.5f,  0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            0.5f, -0.5f, -0.5f,  1.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  0.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  0.0f, 1.0f,
            // Top face
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            0.5f,  0.5f, -0.5f,  1.0f, 1.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f,
            0.5f,  0.5f,  0.5f,  1.0f, 0.0f,
            -0.5f,  0.5f,  0.5f,  0.0f, 0.0f,
            -0.5f,  0.5f, -0.5f,  0.0f, 1.0f,
            // Bottom face
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f,
            0.5f, -0.5f, -0.5f,  1.0f, 0.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 1.0f,
            0.5f, -0.5f,  0.5f,  1.0f, 1.0f,
            -0.5f, -0.5f,  0.5f,  0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f,  0.0f, 0.0f,
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
        mesh.upload(CUBE_VERTICES, 36);
        mesh.setupAttribs(shader);

        camera = new Camera(0.0f, 0.0f, 3.0f);
        gl.enable(gl.getDepthTest());

        texture = new Texture(gl);
        texture.load("grass.png");

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
        float speed = 0.05f;

        if (input.isKeyDown("KeyW")) camera.moveForward(speed);
        if (input.isKeyDown("KeyS")) camera.moveBackward(speed);
        if (input.isKeyDown("KeyA")) camera.moveLeft(speed);
        if (input.isKeyDown("KeyD")) camera.moveRight(speed);

        if (input.isPointerLocked()) {
            float sensitivity = 0.1f;
            camera.rotate(
                    (float) input.getMouseDeltaX() * sensitivity,
                    (float) -input.getMouseDeltaY() * sensitivity
            );
            input.consumeMouseDelta();
        }
    }

    private void render() {
        gl.viewport(0, 0, canvas.getWidth(), canvas.getHeight());
        gl.clearColor(0.1f, 0.1f, 0.1f, 1.0f);
        gl.clear(gl.getColorBufferBit() | gl.getDepthBufferBit());

        if (!texture.isLoaded()) return;

        shader.use();
        texture.bind(0);

        float aspectRatio = (float) canvas.getWidth() / (float) canvas.getHeight();
        Matrix4f projection = camera.getProjectionMatrix(aspectRatio);
        Matrix4f view = camera.getViewMatrix();
        Matrix4f model = new Matrix4f().identity();
        Matrix4f mvp = projection.multiply(view).multiply(model);

        shader.setMVP(mvp.toArray());
        mesh.draw();
    }
}