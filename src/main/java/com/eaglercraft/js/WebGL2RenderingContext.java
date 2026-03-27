package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;

public interface WebGL2RenderingContext extends JSObject {

    // Constants
    @JSProperty("COLOR_BUFFER_BIT")
    int getColorBufferBit();

    @JSProperty("VERTEX_SHADER")
    int getVertexShader();

    @JSProperty("FRAGMENT_SHADER")
    int getFragmentShader();

    @JSProperty("COMPILE_STATUS")
    int getCompileStatus();

    @JSProperty("LINK_STATUS")
    int getLinkStatus();

    @JSProperty("ARRAY_BUFFER")
    int getArrayBuffer();

    @JSProperty("STATIC_DRAW")
    int getStaticDraw();

    @JSProperty("FLOAT")
    int getFloat();

    @JSProperty("TRIANGLES")
    int getTriangles();

    // Viewport and clear
    @JSMethod
    void viewport(int x, int y, int width, int height);

    @JSMethod
    void clearColor(float r, float g, float b, float a);

    @JSMethod
    void clear(int mask);

    // Shader methods
    @JSMethod
    JSObject createShader(int type);

    @JSMethod
    void shaderSource(JSObject shader, String source);

    @JSMethod
    void compileShader(JSObject shader);

    @JSMethod
    boolean getShaderParameter(JSObject shader, int pname);

    @JSMethod
    String getShaderInfoLog(JSObject shader);

    @JSMethod
    void deleteShader(JSObject shader);

    // Program methods
    @JSMethod
    JSObject createProgram();

    @JSMethod
    void attachShader(JSObject program, JSObject shader);

    @JSMethod
    void linkProgram(JSObject program);

    @JSMethod
    boolean getProgramParameter(JSObject program, int pname);

    @JSMethod
    String getProgramInfoLog(JSObject program);

    @JSMethod
    void useProgram(JSObject program);

    // Buffer methods
    @JSMethod
    JSObject createBuffer();

    @JSMethod
    void bindBuffer(int target, JSObject buffer);

    @JSMethod
    void bufferData(int target, JSObject data, int usage);

    // Vertex attribute methods
    @JSMethod
    int getAttribLocation(JSObject program, String name);

    @JSMethod
    void enableVertexAttribArray(int index);

    @JSMethod
    void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset);

    @JSMethod
    JSObject createVertexArray();

    @JSMethod
    void bindVertexArray(JSObject vao);

    // Draw methods
    @JSMethod
    void drawArrays(int mode, int first, int count);

    // Uniform methods
    @JSMethod
    JSObject getUniformLocation(JSObject program, String name);

    @JSMethod
    void uniformMatrix4fv(JSObject location, boolean transpose, JSObject value);

    // Depth testing
    @JSProperty("DEPTH_BUFFER_BIT")
    int getDepthBufferBit();

    @JSProperty("DEPTH_TEST")
    int getDepthTest();

    @JSMethod
    void enable(int cap);

    // Texture methods
    @JSProperty("TEXTURE_2D")
    int getTexture2D();

    @JSProperty("TEXTURE0")
    int getTexture0();

    @JSProperty("RGBA")
    int getRGBA();

    @JSProperty("UNSIGNED_BYTE")
    int getUnsignedByte();

    @JSProperty("LINEAR")
    int getLinear();

    @JSProperty("TEXTURE_MIN_FILTER")
    int getTextureMinFilter();

    @JSProperty("TEXTURE_MAG_FILTER")
    int getTextureMagFilter();

    @JSProperty("NEAREST")
    int getNearest();

    @JSMethod
    JSObject createTexture();

    @JSMethod
    void bindTexture(int target, JSObject texture);

    @JSMethod
    void texImage2D(int target, int level, int internalFormat, int format, int type, JSObject image);

    @JSMethod
    void texParameteri(int target, int pname, int param);

    @JSMethod
    void activeTexture(int texture);

    @JSMethod
    void generateMipmap(int target);
}