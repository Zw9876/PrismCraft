package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;
import org.teavm.jso.JSBody;

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

    @JSProperty("DEPTH_BUFFER_BIT")
    int getDepthBufferBit();

    @JSProperty("DEPTH_TEST")
    int getDepthTest();

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

    // Viewport and clear
    @JSMethod
    void viewport(int x, int y, int width, int height);

    @JSMethod
    void clearColor(float r, float g, float b, float a);

    @JSMethod
    void clear(int mask);

    @JSMethod
    void enable(int cap);

    @JSMethod
    void disable(int cap);

    @JSMethod
    void blendFunc(int sfactor, int dfactor);

    @JSMethod
    void blendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha);

    @JSMethod
    void depthMask(boolean flag);

    @JSMethod
    void cullFace(int mode);

    @JSMethod
    void depthFunc(int func);

    @JSMethod
    void scissor(int x, int y, int width, int height);

    @JSMethod
    void colorMask(boolean r, boolean g, boolean b, boolean a);

    @JSMethod
    void lineWidth(float width);

    @JSMethod
    int getError();

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

    @JSBody(params = {"target", "size", "usage"}, script = "this.bufferData(target, size, usage);")
    void bufferDataSize(int target, int size, int usage);

    @JSMethod
    void bufferSubData(int target, int offset, JSObject data);

    @JSMethod
    void deleteBuffer(JSObject buffer);

    // Vertex attribute methods
    @JSMethod
    int getAttribLocation(JSObject program, String name);

    @JSMethod
    void enableVertexAttribArray(int index);

    @JSMethod
    void disableVertexAttribArray(int index);

    @JSMethod
    void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, int offset);

    @JSMethod
    JSObject createVertexArray();

    @JSMethod
    void bindVertexArray(JSObject vao);

    // Draw methods
    @JSMethod
    void drawArrays(int mode, int first, int count);

    @JSMethod
    void drawElements(int mode, int count, int type, int offset);

    // Uniform methods
    @JSMethod
    JSObject getUniformLocation(JSObject program, String name);

    @JSMethod
    void uniformMatrix4fv(JSObject location, boolean transpose, JSObject value);

    @JSMethod("uniform1i")
    void uniform1iObj(JSObject location, int v0);

    @JSMethod("uniform1f")
    void uniform1fObj(JSObject location, float v0);

    @JSMethod("uniform2f")
    void uniform2fObj(JSObject location, float v0, float v1);

    @JSMethod("uniform3f")
    void uniform3fObj(JSObject location, float v0, float v1, float v2);

    @JSMethod("uniform4f")
    void uniform4fObj(JSObject location, float v0, float v1, float v2, float v3);

    // Texture methods
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

    @JSMethod
    void deleteTexture(JSObject texture);

    // Framebuffer methods
    @JSMethod
    JSObject createFramebuffer();

    @JSMethod
    void bindFramebuffer(int target, JSObject framebuffer);

    @JSMethod
    void deleteFramebuffer(JSObject framebuffer);

    @JSMethod
    int checkFramebufferStatus(int target);

    @JSMethod
    void framebufferTexture2D(int target, int attachment, int textarget, JSObject texture, int level);

    @JSMethod
    void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, JSObject renderbuffer);

    // Renderbuffer methods
    @JSMethod
    JSObject createRenderbuffer();

    @JSMethod
    void bindRenderbuffer(int target, JSObject renderbuffer);

    @JSMethod
    void deleteRenderbuffer(JSObject renderbuffer);

    @JSMethod
    void renderbufferStorage(int target, int internalformat, int width, int height);

    // Get methods
    @JSBody(params = {"pname"}, script = "return this.getParameter(pname);")
    int getParameterInt(int pname);

    @JSMethod
    void blitFramebuffer(int srcX0, int srcY0, int srcX1, int srcY1,
                         int dstX0, int dstY0, int dstX1, int dstY1,
                         int mask, int filter);
}