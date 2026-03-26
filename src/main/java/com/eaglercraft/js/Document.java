package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;

public interface Document extends JSObject {
    @JSMethod
    HTMLCanvas getElementById(String id);
}