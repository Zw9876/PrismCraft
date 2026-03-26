package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

public interface MouseEvent extends JSObject {
    @JSProperty
    int getButton();

    @JSProperty
    double getClientX();

    @JSProperty
    double getClientY();

    @JSProperty
    double getMovementX();

    @JSProperty
    double getMovementY();
}