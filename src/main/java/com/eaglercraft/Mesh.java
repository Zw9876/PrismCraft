package com.eaglercraft;

import com.eaglercraft.js.WebGL2RenderingContext;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSBody;

public class Mesh {
    private final WebGL2RenderingContext gl;
    private JSObject vao;
    private JSObject vbo;
    private int vertexCount;

    public Mesh(WebGL2RenderingContext gl) {
        this.gl = gl;
    }

    @JSBody(params = {"data"}, script = "return new Float32Array(data);")
    private static native JSObject createFloat32Array(float[] data);

    public void upload(float[] vertices, int vertexCount) {
        this.vertexCount = vertexCount;

        vao = gl.createVertexArray();
        gl.bindVertexArray(vao);

        vbo = gl.createBuffer();
        gl.bindBuffer(gl.getArrayBuffer(), vbo);
        gl.bufferData(gl.getArrayBuffer(), createFloat32Array(vertices), gl.getStaticDraw());
    }

    public void setupAttribs(Shader shader) {
        int stride = 6 * 4; // 6 floats * 4 bytes each

        int posLoc = shader.getAttribLocation("aPosition");
        gl.enableVertexAttribArray(posLoc);
        gl.vertexAttribPointer(posLoc, 3, gl.getFloat(), false, stride, 0);

        int colLoc = shader.getAttribLocation("aColor");
        gl.enableVertexAttribArray(colLoc);
        gl.vertexAttribPointer(colLoc, 3, gl.getFloat(), false, stride, 3 * 4);
    }

    public void draw() {
        gl.bindVertexArray(vao);
        gl.drawArrays(gl.getTriangles(), 0, vertexCount);
    }
}