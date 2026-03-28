package com.eaglercraft.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

public class Browser {
    @JSBody(script = "return document;")
    public static native Document getDocument();

    @JSBody(script = "return window;")
    public static native Window getWindow();

    @JSBody(script = "return document.pointerLockElement !== null;")
    public static native boolean isPointerLocked();

    @JSBody(params = {"type", "callback"}, script = "document.addEventListener(type, callback);")
    public static native void addDocumentEventListener(String type, VoidCallback callback);

    @JSBody(params = {"url", "callback"}, script =
            "fetch(url)" +
                    ".then(function(r) { return r.arrayBuffer(); })" +
                    ".then(function(buf) { return JSZip.loadAsync(buf); })" +
                    ".then(function(zip) { callback(zip); });")
    public static native void loadEPK(String url, JSZipCallback callback);

    @JSBody(params = {"url"}, script = "return new WebSocket(url);")
    public static native WebSocket createWebSocket(String url);

    @JSBody(params = {"url", "protocol"}, script = "return new WebSocket(url, protocol);")
    public static native WebSocket createWebSocket(String url, String protocol);

    @JSBody(script = "return new (window.AudioContext || window.webkitAudioContext)();")
    public static native AudioContext createAudioContext();
}