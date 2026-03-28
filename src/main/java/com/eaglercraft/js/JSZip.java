package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;

public interface JSZip extends JSObject {
    @JSMethod
    JSObject file(String name);

    @JSMethod
    JSObject files();
}