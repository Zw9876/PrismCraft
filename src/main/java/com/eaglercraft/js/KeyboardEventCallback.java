package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface KeyboardEventCallback extends JSObject {
    void onEvent(KeyboardEvent event);
}