package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface WebSocketCallback extends JSObject {
    void onEvent(JSObject event);
}