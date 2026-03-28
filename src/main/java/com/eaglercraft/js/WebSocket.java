package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;

public interface WebSocket extends JSObject {
    @JSProperty
    int getReadyState();

    @JSProperty
    String getUrl();

    @JSMethod
    void send(String data);

    @JSMethod
    void send(JSObject data);

    @JSMethod
    void close();

    @JSMethod
    void close(int code);

    @JSMethod
    void addEventListener(String type, WebSocketCallback callback);
}