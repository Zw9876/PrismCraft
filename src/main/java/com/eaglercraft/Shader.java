package com.eaglercraft;

import com.eaglercraft.js.WebGL2RenderingContext;
import org.teavm.jso.JSObject;

public class Shader {
    private final WebGL2RenderingContext gl;
    private JSObject program;
    private final String customVertexSource;
    private final String customFragmentSource;

    private static final String VERTEX_SOURCE =
            "#version 300 es\n" +
                    "in vec3 aPosition;\n" +
                    "in vec2 aTexCoord;\n" +
                    "out vec2 vTexCoord;\n" +
                    "uniform mat4 uMVP;\n" +
                    "void main() {\n" +
                    "    gl_Position = uMVP * vec4(aPosition, 1.0);\n" +
                    "    vTexCoord = vec2(aTexCoord.x, 1.0 - aTexCoord.y);\n" +
                    "}\n";

    private static final String FRAGMENT_SOURCE =
            "#version 300 es\n" +
                    "precision mediump float;\n" +
                    "in vec2 vTexCoord;\n" +
                    "out vec4 fragColor;\n" +
                    "uniform sampler2D uTexture;\n" +
                    "void main() {\n" +
                    "    fragColor = texture(uTexture, vTexCoord);\n" +
                    "}\n";

    public Shader(WebGL2RenderingContext gl) {
        this.gl = gl;
        this.customVertexSource = null;
        this.customFragmentSource = null;
    }

    public Shader(WebGL2RenderingContext gl, String vertexSource, String fragmentSource) {
        this.gl = gl;
        this.customVertexSource = vertexSource;
        this.customFragmentSource = fragmentSource;
    }

    public boolean compile() {
        String vs = customVertexSource != null ? customVertexSource : VERTEX_SOURCE;
        String fs = customFragmentSource != null ? customFragmentSource : FRAGMENT_SOURCE;

        JSObject vertexShader = compileShader(gl.getVertexShader(), vs);
        if (vertexShader == null) return false;

        JSObject fragmentShader = compileShader(gl.getFragmentShader(), fs);
        if (fragmentShader == null) return false;

        program = gl.createProgram();
        gl.attachShader(program, vertexShader);
        gl.attachShader(program, fragmentShader);
        gl.linkProgram(program);

        if (!gl.getProgramParameter(program, gl.getLinkStatus())) {
            System.out.println("Shader link error: " + gl.getProgramInfoLog(program));
            return false;
        }

        gl.deleteShader(vertexShader);
        gl.deleteShader(fragmentShader);

        System.out.println("Shader compiled and linked successfully!");
        return true;
    }

    private JSObject compileShader(int type, String source) {
        JSObject shader = gl.createShader(type);
        gl.shaderSource(shader, source);
        gl.compileShader(shader);

        if (!gl.getShaderParameter(shader, gl.getCompileStatus())) {
            System.out.println("Shader compile error: " + gl.getShaderInfoLog(shader));
            gl.deleteShader(shader);
            return null;
        }

        return shader;
    }

    public void use() {
        gl.useProgram(program);
    }

    public void setMVP(float[] matrix) {
        JSObject location = gl.getUniformLocation(program, "uMVP");
        gl.uniformMatrix4fv(location, false, GLUtils.createFloat32Array(matrix));
    }

    public void setTexture(int unit) {
        JSObject location = gl.getUniformLocation(program, "uTexture");
        gl.uniform1i(location, unit);
    }

    public int getAttribLocation(String name) {
        return gl.getAttribLocation(program, name);
    }

    public JSObject getProgram() {
        return program;
    }
}