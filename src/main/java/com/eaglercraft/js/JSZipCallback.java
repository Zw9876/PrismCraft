package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface JSZipCallback extends JSObject {
    void onLoaded(JSZip zip);
}