package com.eaglercraft;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.HTMLCanvas;
import com.eaglercraft.js.WebGL2RenderingContext;
import com.eaglercraft.resource.EaglerResourceManager;

public class GameLoop {
    private final WebGL2RenderingContext gl;
    private final HTMLCanvas canvas;
    private final InputHandler input;
    private boolean running = false;
    private Shader shader;
    private Mesh mesh;
    private Texture bgTexture;
    private Texture logoTexture;

    // Simple fullscreen quad
    private static final float[] QUAD_VERTICES = {
            // x      y     u     v
            -1.0f, -1.0f,  0.0f, 1.0f,
            1.0f, -1.0f,  1.0f, 1.0f,
            1.0f,  1.0f,  1.0f, 0.0f,
            1.0f,  1.0f,  1.0f, 0.0f,
            -1.0f,  1.0f,  0.0f, 0.0f,
            -1.0f, -1.0f,  0.0f, 1.0f,
    };

    private static final String VERT_SRC =
            "#version 300 es\n" +
                    "in vec2 aPos;\n" +
                    "in vec2 aTexCoord;\n" +
                    "out vec2 vTexCoord;\n" +
                    "void main() {\n" +
                    "    gl_Position = vec4(aPos, 0.0, 1.0);\n" +
                    "    vTexCoord = aTexCoord;\n" +
                    "}\n";

    private static final String FRAG_SRC =
            "#version 300 es\n" +
                    "precision mediump float;\n" +
                    "in vec2 vTexCoord;\n" +
                    "uniform sampler2D uTexture;\n" +
                    "out vec4 fragColor;\n" +
                    "void main() {\n" +
                    "    fragColor = texture(uTexture, vTexCoord);\n" +
                    "}\n";

    public GameLoop(HTMLCanvas canvas, WebGL2RenderingContext gl, InputHandler input) {
        this.canvas = canvas;
        this.gl = gl;
        this.input = input;
    }

    public void start() {
        running = true;
        System.out.println("Game loop starting...");

        shader = new Shader(gl, VERT_SRC, FRAG_SRC);
        if (!shader.compile()) {
            System.out.println("ERROR: Failed to compile shaders!");
            return;
        }

        mesh = new Mesh(gl);
        mesh.upload(QUAD_VERTICES, 6);
        mesh.setupAttribs2D(shader);

        // Load panorama background from EPK
        bgTexture = new Texture(gl);
        byte[] bgData = EaglerResourceManager.getInstance()
                .getRawResource("minecraft/textures/gui/title/background/panorama_0.png");
        if (bgData != null) {
            bgTexture.loadFromBytes(bgData, "panorama_0.png");
        } else {
            System.out.println("WARNING: panorama_0.png not found in EPK, using fallback");
            bgTexture.load("grass.png");
        }

        // Load Minecraft logo from EPK
        logoTexture = new Texture(gl);
        byte[] logoData = EaglerResourceManager.getInstance()
                .getRawResource("minecraft/textures/gui/title/minecraft.png");
        if (logoData != null) {
            logoTexture.loadFromBytes(logoData, "minecraft.png");
        }

        System.out.println("Rendering initialized!");
        scheduleFrame();
    }

    private void scheduleFrame() {
        Browser.getWindow().requestAnimationFrame(this::onFrame);
    }

    private void onFrame(double timestamp) {
        if (!running) return;
        render();
        scheduleFrame();
    }

    private void render() {
        gl.viewport(0, 0, canvas.getWidth(), canvas.getHeight());
        gl.clearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.clear(gl.getColorBufferBit());
        gl.disable(gl.getDepthTest());
        gl.enable(gl.getBlend());
        gl.blendFunc(gl.getSrcAlpha(), gl.getOneMinusSrcAlpha());

        shader.use();

        // Draw background panorama
        if (bgTexture.isLoaded()) {
            bgTexture.bind(0);
            mesh.draw();
        }

        // Draw logo on top if loaded
        if (logoTexture != null && logoTexture.isLoaded()) {
            logoTexture.bind(0);
            mesh.draw();
        }
    }
}