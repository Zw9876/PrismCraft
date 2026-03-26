package com.eaglercraft.js;

import com.eaglercraft.js.AnimationFrameCallback;
import org.teavm.jso.JSObject;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSMethod;

public interface Window extends JSObject {
    @JSMethod
    void requestAnimationFrame(AnimationFrameCallback callback);
}