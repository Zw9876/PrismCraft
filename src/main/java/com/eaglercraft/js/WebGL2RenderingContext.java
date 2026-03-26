package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;

public interface WebGL2RenderingContext extends JSObject {

    @JSMethod
    void clearColor(float r, float g, float b, float a);

    @JSMethod
    void clear(int mask);

    @JSMethod
    void viewport(int x, int y, int width, int height);

    @JSProperty("COLOR_BUFFER_BIT")
    int getColorBufferBit();
}