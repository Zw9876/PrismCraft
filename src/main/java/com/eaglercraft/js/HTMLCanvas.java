package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;
import org.teavm.jso.JSMethod;

public interface HTMLCanvas extends JSObject {
    @JSProperty
    int getWidth();

    @JSProperty
    int getHeight();

    @JSMethod
    JSObject getContext(String contextType);

    @JSMethod
    void requestPointerLock();

    @JSMethod
    void addEventListener(String type, VoidCallback callback);
}