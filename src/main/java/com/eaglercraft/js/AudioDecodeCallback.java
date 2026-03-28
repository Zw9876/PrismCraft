package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface AudioDecodeCallback extends JSObject {
    void onDecoded(JSObject audioBuffer);
}