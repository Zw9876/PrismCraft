package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface IDBCallback extends JSObject {
    void onResult(JSObject result);
}