package com.eaglercraft;

import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

public class GLUtils {
    @JSBody(params = {"data"}, script = "return new Float32Array(data);")
    public static native JSObject createFloat32Array(float[] data);

    @JSBody(params = {"data"}, script = "return new Int32Array(data);")
    public static native JSObject createInt32Array(int[] data);
}