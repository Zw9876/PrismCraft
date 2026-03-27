package com.eaglercraft.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

public class ImageLoader {
    @JSBody(params = {"src", "callback"}, script =
            "var img = new Image();" +
                    "img.onload = function() { callback(img); };" +
                    "img.src = src;")
    public static native void load(String src, ImageLoadCallback callback);
}