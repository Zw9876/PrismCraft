package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;

public interface Window extends JSObject {
    @JSMethod
    void requestAnimationFrame(AnimationFrameCallback callback);

    @JSMethod
    void addEventListener(String type, KeyboardEventCallback callback);

    @JSMethod
    void addEventListener(String type, MouseEventCallback callback);

    @JSMethod
    void addEventListener(String type, VoidCallback callback);
}