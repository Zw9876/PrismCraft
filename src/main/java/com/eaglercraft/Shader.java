package com.eaglercraft;

import com.eaglercraft.js.WebGL2RenderingContext;
import org.teavm.jso.JSObject;

public class Shader {
    private final WebGL2RenderingContext gl;
    private JSObject program;

    private static final String VERTEX_SOURCE =
            "#version 300 es\n" +
                    "in vec3 aPosition;\n" +
                    "in vec3 aColor;\n" +
                    "out vec3 vColor;\n" +
                    "uniform mat4 uMVP;\n" +
                    "void main() {\n" +
                    "    gl_Position = uMVP * vec4(aPosition, 1.0);\n" +
                    "    vColor = aColor;\n" +
                    "}\n";

    private static final String FRAGMENT_SOURCE =
            "#version 300 es\n" +
                    "precision mediump float;\n" +
                    "in vec3 vColor;\n" +
                    "out vec4 fragColor;\n" +
                    "void main() {\n" +
                    "    fragColor = vec4(vColor, 1.0);\n" +
                    "}\n";

    public Shader(WebGL2RenderingContext gl) {
        this.gl = gl;
    }

    public boolean compile() {
        JSObject vertexShader = compileShader(gl.getVertexShader(), VERTEX_SOURCE);
        if (vertexShader == null) return false;

        JSObject fragmentShader = compileShader(gl.getFragmentShader(), FRAGMENT_SOURCE);
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

    public int getAttribLocation(String name) {
        return gl.getAttribLocation(program, name);
    }

    public JSObject getProgram() {
        return program;
    }
}