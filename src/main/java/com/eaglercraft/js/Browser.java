package com.eaglercraft.js;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

public class Browser {
    @JSBody(script = "return document;")
    public static native Document getDocument();

    @JSBody(script = "return window;")
    public static native Window getWindow();
}