package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

public interface KeyboardEvent extends JSObject {
    @JSProperty
    String getCode();

    @JSProperty
    String getKey();

    @JSProperty
    boolean isShiftKey();

    @JSProperty
    boolean isCtrlKey();

    @JSProperty
    boolean isAltKey();

    @JSProperty
    int getKeyCode();
}