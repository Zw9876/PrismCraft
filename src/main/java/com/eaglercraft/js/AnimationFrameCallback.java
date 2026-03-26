package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface AnimationFrameCallback extends JSObject {
    void onAnimationFrame(double timestamp);
}