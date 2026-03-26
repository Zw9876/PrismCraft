package com.eaglercraft.js;

import org.teavm.jso.JSFunctor;
import org.teavm.jso.JSObject;

@JSFunctor
public interface MouseEventCallback extends JSObject {
    void onEvent(MouseEvent event);
}