package com.mojang.blaze3d.systems;

import com.eaglercraft.bridge.GLBridge;
import com.mojang.blaze3d.pipeline.RenderCall;
import com.mojang.blaze3d.platform.Window;
import com.mojang.math.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class RenderSystem {
    private static boolean isInInit = false;
    private static Thread gameThread;
    private static Thread renderThread;
    private static int MAX_SUPPORTED_TEXTURE_SIZE = 16384;
    private static double lastDrawTime = 0;
    private static Window window;

    public static void initRenderThread() {
        renderThread = Thread.currentThread();
    }

    public static void initGameThread(boolean sameThread) {
        gameThread = Thread.currentThread();
    }

    public static boolean isOnRenderThread() { return true; }
    public static boolean isOnRenderThreadOrInit() { return true; }
    public static boolean isOnGameThread() { return true; }
    public static boolean isOnGameThreadOrInit() { return true; }
    public static boolean isInInitPhase() { return true; }

    public static void assertThread(Supplier<Boolean> check) {}

    public static void beginInitialization() { isInInit = true; }
    public static void finishInitialization() { isInInit = false; }

    public static void setWindow(Window w) { window = w; }
    public static Window getWindow() { return window; }

    public static void flipFrame(long windowHandle) {}

    public static void enableDepthTest() { GL11.glEnable(GL11.GL_DEPTH_TEST); }
    public static void disableDepthTest() { GL11.glDisable(GL11.GL_DEPTH_TEST); }
    public static void depthFunc(int func) { GL11.glDepthFunc(func); }
    public static void depthMask(boolean flag) { GL11.glDepthMask(flag); }

    public static void enableBlend() { GL11.glEnable(GL11.GL_BLEND); }
    public static void disableBlend() { GL11.glDisable(GL11.GL_BLEND); }
    public static void blendFunc(int srcFactor, int dstFactor) { GL11.glBlendFunc(srcFactor, dstFactor); }
    public static void blendFuncSeparate(int srcRGB, int dstRGB, int srcAlpha, int dstAlpha) {
        GLBridge.blendFuncSeparate(srcRGB, dstRGB, srcAlpha, dstAlpha);
    }
    public static void blendEquation(int mode) {}
    public static void defaultBlendFunc() {
        blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
    }

    public static void enableCull() { GL11.glEnable(GL11.GL_CULL_FACE); }
    public static void disableCull() { GL11.glDisable(GL11.GL_CULL_FACE); }

    public static void enableTexture() { GL11.glEnable(GL11.GL_TEXTURE_2D); }
    public static void disableTexture() { GL11.glDisable(GL11.GL_TEXTURE_2D); }

    public static void activeTexture(int texture) { GL13.glActiveTexture(texture); }
    public static void bindTexture(int texture) { GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture); }

    public static void enableScissor(int x, int y, int width, int height) {
        GL11.glEnable(0x0C11); // GL_SCISSOR_TEST
        GL11.glScissor(x, y, width, height);
    }
    public static void disableScissor() { GL11.glDisable(0x0C11); }

    public static void viewport(int x, int y, int width, int height) {
        GL11.glViewport(x, y, width, height);
    }

    public static void clearDepth(double depth) {}
    public static void clearColor(float r, float g, float b, float a) { GL11.glClearColor(r, g, b, a); }
    public static void clear(int mask, boolean checkError) { GL11.glClear(mask); }

    public static void colorMask(boolean r, boolean g, boolean b, boolean a) { GL11.glColorMask(r, g, b, a); }
    public static void lineWidth(float width) { GL11.glLineWidth(width); }

    public static void useProgram(int program) { GL20.glUseProgram(program); }

    public static void setupGui3DDiffuseLighting(com.mojang.math.Vector3f v1, com.mojang.math.Vector3f v2) {}
    public static void setupGuiFlatDiffuseLighting(com.mojang.math.Vector3f v1, com.mojang.math.Vector3f v2) {}
    public static void setupLevelDiffuseLighting(com.mojang.math.Vector3f v1, com.mojang.math.Vector3f v2) {}

    public static void setShaderFogStart(float start) {}
    public static void setShaderFogEnd(float end) {}
    public static void setShaderFogColor(float r, float g, float b, float a) {}
    public static void setShaderColor(float r, float g, float b, float a) {}

    public static int getMaxSupportedTextureSize() { return MAX_SUPPORTED_TEXTURE_SIZE; }

    public static void recordRenderCall(RenderCall call) { call.execute(); }

    public static void replayQueue() {}

    public static double getLastDrawTime() { return lastDrawTime; }
    public static void setLastDrawTime(double time) { lastDrawTime = time; }

    public static void setShader(Supplier<?> shader) {}
    public static Object getShader() { return null; }

    public static void setShaderTexture(int unit, int textureId) {
        GL13.glActiveTexture(GL13.GL_TEXTURE0 + unit);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId);
    }

    public static void setShaderTexture(int unit, Object location) {}

    public static void applyModelViewMatrix() {}
    public static void resetTextureMatrix() {}

    public static Matrix4f getProjectionMatrix() { return new Matrix4f(); }
    public static void setProjectionMatrix(Matrix4f matrix) {}

    public static void pushMatrix() {}
    public static void popMatrix() {}

    public static boolean isOnRenderThread(Thread thread) { return true; }

    public static void glGenBuffers(Consumer<IntBuffer> consumer) {}
    public static void glGenVertexArrays(Consumer<IntBuffer> consumer) {}

    public static void glBindBuffer(int target, IntSupplier buffer) {
        GLBridge.bindBuffer(target, buffer.getAsInt());
    }
    public static void glBindVertexArray(IntSupplier vao) {
        GLBridge.bindVertexArray(vao.getAsInt());
    }
    public static void glBufferData(int target, FloatBuffer data, int usage) {
        GLBridge.bufferDataFloat(target, data, usage);
    }
    public static void glDeleteBuffers(int buffer) { GLBridge.deleteBuffer(buffer); }
    public static void glDeleteVertexArrays(int vao) { GLBridge.deleteVertexArray(vao); }

    public static void glUniform1i(int location, int value) { GL20.glUniform1i(location, value); }
    public static void glUniform1(int location, IntBuffer values) {}
    public static void glUniform2(int location, IntBuffer values) {}
    public static void glUniform3(int location, IntBuffer values) {}
    public static void glUniform4(int location, IntBuffer values) {}
    public static void glUniform1(int location, FloatBuffer values) {}
    public static void glUniform2(int location, FloatBuffer values) {}
    public static void glUniform3(int location, FloatBuffer values) {}
    public static void glUniform4(int location, FloatBuffer values) {}
    public static void glUniformMatrix4(int location, boolean transpose, FloatBuffer values) {
        GL20.glUniformMatrix4fv(location, transpose, values);
    }

    public static void setupOverlayColor(IntSupplier supplier, int i) {}
    public static void teardownOverlayColor() {}
    public static void setupNovideo() {}
}