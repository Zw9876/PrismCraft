package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface ImageLoadCallback extends JSObject {
    void onLoad(JSObject image);
}