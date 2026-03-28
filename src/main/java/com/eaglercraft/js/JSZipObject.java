package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;

public interface JSZipObject extends JSObject {
    @JSMethod
    JSObject async(String type);
}