package com.eaglercraft;

import com.eaglercraft.js.WebGL2RenderingContext;
import org.teavm.jso.JSObject;

public class Mesh {
    private final WebGL2RenderingContext gl;
    private JSObject vao;
    private JSObject vbo;
    private int vertexCount;

    public Mesh(WebGL2RenderingContext gl) {
        this.gl = gl;
    }

    public void upload(float[] vertices, int vertexCount) {
        this.vertexCount = vertexCount;

        vao = gl.createVertexArray();
        gl.bindVertexArray(vao);

        vbo = gl.createBuffer();
        gl.bindBuffer(gl.getArrayBuffer(), vbo);
        gl.bufferData(gl.getArrayBuffer(), GLUtils.createFloat32Array(vertices), gl.getStaticDraw());
    }

    public void setupAttribs(Shader shader) {
        gl.bindVertexArray(vao);
        int stride = 5 * 4; // 5 floats * 4 bytes (x, y, z, u, v)

        int posLoc = shader.getAttribLocation("aPosition");
        gl.enableVertexAttribArray(posLoc);
        gl.vertexAttribPointer(posLoc, 3, gl.getFloat(), false, stride, 0);

        int texLoc = shader.getAttribLocation("aTexCoord");
        gl.enableVertexAttribArray(texLoc);
        gl.vertexAttribPointer(texLoc, 2, gl.getFloat(), false, stride, 3 * 4);
    }

    public void setupAttribs2D(Shader shader) {
        gl.bindVertexArray(vao);
        int stride = 4 * 4; // 4 floats * 4 bytes (x, y, u, v)

        int posLoc = shader.getAttribLocation("aPos");
        gl.enableVertexAttribArray(posLoc);
        gl.vertexAttribPointer(posLoc, 2, gl.getFloat(), false, stride, 0);

        int texLoc = shader.getAttribLocation("aTexCoord");
        gl.enableVertexAttribArray(texLoc);
        gl.vertexAttribPointer(texLoc, 2, gl.getFloat(), false, stride, 2 * 4);
    }

    public void draw() {
        gl.bindVertexArray(vao);
        gl.drawArrays(gl.getTriangles(), 0, vertexCount);
    }

    public void updateVertices(float[] vertices) {
        gl.bindVertexArray(vao);
        gl.bindBuffer(gl.getArrayBuffer(), vbo);
        gl.bufferData(gl.getArrayBuffer(), GLUtils.createFloat32Array(vertices), gl.getStaticDraw());
    }
}