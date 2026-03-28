package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;

public interface IDBDatabase extends JSObject {
    @JSMethod
    JSObject transaction(String storeName, String mode);

    @JSMethod
    JSObject transaction(JSObject storeNames, String mode);
}