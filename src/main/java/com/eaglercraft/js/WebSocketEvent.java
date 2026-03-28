package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

public interface WebSocketEvent extends JSObject {
    @JSProperty
    JSObject getData();

    @JSProperty
    String getType();
}