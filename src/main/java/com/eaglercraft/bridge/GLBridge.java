package com.eaglercraft.bridge;

import com.eaglercraft.GLUtils;
import com.eaglercraft.js.WebGL2RenderingContext;
import org.teavm.jso.JSObject;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class GLBridge {
    private static WebGL2RenderingContext gl;
    private static int nextTextureId = 1;
    private static final Map<Integer, JSObject> textures = new HashMap<>();
    private static final Map<Integer, JSObject> buffers = new HashMap<>();
    private static final Map<Integer, JSObject> vaos = new HashMap<>();
    private static final Map<Integer, JSObject> shaders = new HashMap<>();
    private static final Map<Integer, JSObject> programs = new HashMap<>();
    private static final Map<Integer, JSObject> uniformLocations = new HashMap<>();
    private static final Map<Integer, JSObject> framebuffers = new HashMap<>();
    private static final Map<Integer, JSObject> renderbuffers = new HashMap<>();
    private static int nextBufferId = 1;
    private static int nextVaoId = 1;
    private static int nextShaderId = 1;
    private static int nextProgramId = 1;
    private static int nextUniformId = 1;

    public static void init(WebGL2RenderingContext glContext) {
        gl = glContext;
        System.out.println("GLBridge initialized!");
    }

    public static WebGL2RenderingContext getGL() { return gl; }

    // Core
    public static void enable(int cap) { gl.enable(cap); }
    public static void disable(int cap) { gl.disable(cap); }
    public static void clear(int mask) { gl.clear(mask); }
    public static void clearColor(float r, float g, float b, float a) { gl.clearColor(r, g, b, a); }
    public static void viewport(int x, int y, int width, int height) { gl.viewport(x, y, width, height); }
    public static void blendFunc(int sfactor, int dfactor) { gl.blendFunc(sfactor, dfactor); }
    public static void blendFuncSeparate(int sfactorRGB, int dfactorRGB, int sfactorAlpha, int dfactorAlpha) {
        gl.blendFuncSeparate(sfactorRGB, dfactorRGB, sfactorAlpha, dfactorAlpha);
    }
    public static void depthMask(boolean flag) { gl.depthMask(flag); }
    public static void cullFace(int mode) { gl.cullFace(mode); }
    public static void depthFunc(int func) { gl.depthFunc(func); }
    public static void scissor(int x, int y, int width, int height) { gl.scissor(x, y, width, height); }
    public static void colorMask(boolean r, boolean g, boolean b, boolean a) { gl.colorMask(r, g, b, a); }
    public static void lineWidth(float width) { gl.lineWidth(width); }
    public static int getError() { return gl.getError(); }
    public static void drawArrays(int mode, int first, int count) { gl.drawArrays(mode, first, count); }
    public static void drawElements(int mode, int count, int type, int offset) { gl.drawElements(mode, count, type, offset); }
    public static void activeTexture(int texture) { gl.activeTexture(texture); }

    // Textures
    public static int genTexture() {
        int id = nextTextureId++;
        textures.put(id, gl.createTexture());
        return id;
    }
    public static void bindTexture(int target, int texture) {
        if (texture == 0) { gl.bindTexture(target, null); return; }
        JSObject tex = textures.get(texture);
        if (tex != null) gl.bindTexture(target, tex);
    }
    public static void deleteTexture(int texture) {
        JSObject tex = textures.remove(texture);
        if (tex != null) gl.deleteTexture(tex);
    }
    public static void texParameteri(int target, int pname, int param) { gl.texParameteri(target, pname, param); }
    public static void generateMipmap(int target) { gl.generateMipmap(target); }

    // Buffers
    public static int genBuffer() {
        int id = nextBufferId++;
        buffers.put(id, gl.createBuffer());
        return id;
    }
    public static void bindBuffer(int target, int buffer) {
        if (buffer == 0) { gl.bindBuffer(target, null); return; }
        JSObject buf = buffers.get(buffer);
        if (buf != null) gl.bindBuffer(target, buf);
    }
    public static void deleteBuffer(int buffer) {
        JSObject buf = buffers.remove(buffer);
        if (buf != null) gl.deleteBuffer(buf);
    }
    public static void bufferDataFloat(int target, FloatBuffer data, int usage) {
        float[] arr = new float[data.remaining()];
        data.get(arr);
        gl.bufferData(target, GLUtils.createFloat32Array(arr), usage);
    }
    public static void bufferDataInt(int target, IntBuffer data, int usage) {
        int[] arr = new int[data.remaining()];
        data.get(arr);
        gl.bufferData(target, GLUtils.createInt32Array(arr), usage);
    }
    public static void bufferDataEmpty(int target, long size, int usage) {
        gl.bufferDataSize(target, (int) size, usage);
    }
    public static void bufferSubDataFloat(int target, long offset, FloatBuffer data) {
        float[] arr = new float[data.remaining()];
        data.get(arr);
        gl.bufferSubData(target, (int) offset, GLUtils.createFloat32Array(arr));
    }

    // VAOs
    public static int genVertexArray() {
        int id = nextVaoId++;
        vaos.put(id, gl.createVertexArray());
        return id;
    }
    public static void bindVertexArray(int vao) {
        if (vao == 0) { gl.bindVertexArray(null); return; }
        JSObject v = vaos.get(vao);
        if (v != null) gl.bindVertexArray(v);
    }
    public static void deleteVertexArray(int vao) { vaos.remove(vao); }

    // Shaders
    public static int createShader(int type) {
        int id = nextShaderId++;
        shaders.put(id, gl.createShader(type));
        return id;
    }
    public static void shaderSource(int shader, CharSequence source) {
        JSObject s = shaders.get(shader);
        if (s != null) gl.shaderSource(s, source.toString());
    }
    public static void compileShader(int shader) {
        JSObject s = shaders.get(shader);
        if (s != null) gl.compileShader(s);
    }
    public static boolean getShaderCompileStatus(int shader) {
        JSObject s = shaders.get(shader);
        if (s == null) return false;
        return gl.getShaderParameter(s, gl.getCompileStatus());
    }
    public static String getShaderInfoLog(int shader) {
        JSObject s = shaders.get(shader);
        if (s == null) return "";
        return gl.getShaderInfoLog(s);
    }
    public static void deleteShader(int shader) {
        JSObject s = shaders.remove(shader);
        if (s != null) gl.deleteShader(s);
    }

    // Programs
    public static int createProgram() {
        int id = nextProgramId++;
        programs.put(id, gl.createProgram());
        return id;
    }
    public static void attachShader(int program, int shader) {
        JSObject p = programs.get(program);
        JSObject s = shaders.get(shader);
        if (p != null && s != null) gl.attachShader(p, s);
    }
    public static void linkProgram(int program) {
        JSObject p = programs.get(program);
        if (p != null) gl.linkProgram(p);
    }
    public static boolean getProgramLinkStatus(int program) {
        JSObject p = programs.get(program);
        if (p == null) return false;
        return gl.getProgramParameter(p, gl.getLinkStatus());
    }
    public static String getProgramInfoLog(int program) {
        JSObject p = programs.get(program);
        if (p == null) return "";
        return gl.getProgramInfoLog(p);
    }
    public static void useProgram(int program) {
        if (program == 0) { gl.useProgram(null); return; }
        JSObject p = programs.get(program);
        if (p != null) gl.useProgram(p);
    }
    public static void deleteProgram(int program) { programs.remove(program); }

    // Attribs
    public static int getAttribLocation(int program, CharSequence name) {
        JSObject p = programs.get(program);
        if (p == null) return -1;
        return gl.getAttribLocation(p, name.toString());
    }
    public static void enableVertexAttribArray(int index) { gl.enableVertexAttribArray(index); }
    public static void disableVertexAttribArray(int index) { gl.disableVertexAttribArray(index); }
    public static void vertexAttribPointer(int index, int size, int type, boolean normalized, int stride, long pointer) {
        gl.vertexAttribPointer(index, size, type, normalized, stride, (int) pointer);
    }

    // Uniforms
    public static int getUniformLocation(int program, CharSequence name) {
        JSObject p = programs.get(program);
        if (p == null) return -1;
        JSObject loc = gl.getUniformLocation(p, name.toString());
        if (loc == null) return -1;
        int id = nextUniformId++;
        uniformLocations.put(id, loc);
        return id;
    }
    public static void uniform1i(int location, int v0) {
        JSObject loc = uniformLocations.get(location);
        if (loc != null) gl.uniform1iObj(loc, v0);
    }
    public static void uniform1f(int location, float v0) {
        JSObject loc = uniformLocations.get(location);
        if (loc != null) gl.uniform1fObj(loc, v0);
    }
    public static void uniform2f(int location, float v0, float v1) {
        JSObject loc = uniformLocations.get(location);
        if (loc != null) gl.uniform2fObj(loc, v0, v1);
    }
    public static void uniform3f(int location, float v0, float v1, float v2) {
        JSObject loc = uniformLocations.get(location);
        if (loc != null) gl.uniform3fObj(loc, v0, v1, v2);
    }
    public static void uniform4f(int location, float v0, float v1, float v2, float v3) {
        JSObject loc = uniformLocations.get(location);
        if (loc != null) gl.uniform4fObj(loc, v0, v1, v2, v3);
    }
    public static void uniformMatrix4fv(int location, boolean transpose, float[] value) {
        JSObject loc = uniformLocations.get(location);
        if (loc != null) gl.uniformMatrix4fv(loc, transpose, GLUtils.createFloat32Array(value));
    }
    public static void uniformMatrix4fvBuffer(int location, boolean transpose, FloatBuffer value) {
        float[] arr = new float[value.remaining()];
        value.get(arr);
        uniformMatrix4fv(location, transpose, arr);
    }

    // Framebuffers
    public static int genFramebuffer() {
        int id = nextProgramId++;
        framebuffers.put(id, gl.createFramebuffer());
        return id;
    }
    public static void bindFramebuffer(int target, int framebuffer) {
        if (framebuffer == 0) { gl.bindFramebuffer(target, null); return; }
        JSObject fb = framebuffers.get(framebuffer);
        if (fb != null) gl.bindFramebuffer(target, fb);
    }
    public static void deleteFramebuffer(int framebuffer) {
        JSObject fb = framebuffers.remove(framebuffer);
        if (fb != null) gl.deleteFramebuffer(fb);
    }
    public static int checkFramebufferStatus(int target) {
        return gl.checkFramebufferStatus(target);
    }

    // Renderbuffers
    public static int genRenderbuffer() {
        int id = nextProgramId++;
        renderbuffers.put(id, gl.createRenderbuffer());
        return id;
    }
    public static void bindRenderbuffer(int target, int renderbuffer) {
        if (renderbuffer == 0) { gl.bindRenderbuffer(target, null); return; }
        JSObject rb = renderbuffers.get(renderbuffer);
        if (rb != null) gl.bindRenderbuffer(target, rb);
    }
    public static void renderbufferStorage(int target, int internalformat, int width, int height) {
        gl.renderbufferStorage(target, internalformat, width, height);
    }
    public static void framebufferTexture2D(int target, int attachment, int textarget, int texture, int level) {
        JSObject tex = textures.get(texture);
        if (tex != null) gl.framebufferTexture2D(target, attachment, textarget, tex, level);
    }
    public static void framebufferRenderbuffer(int target, int attachment, int renderbuffertarget, int renderbuffer) {
        JSObject rb = renderbuffers.get(renderbuffer);
        if (rb != null) gl.framebufferRenderbuffer(target, attachment, renderbuffertarget, rb);
    }

    // Get
    public static void getIntegerv(int pname, int[] params) {
        if (params.length > 0) params[0] = gl.getParameterInt(pname);
    }
}