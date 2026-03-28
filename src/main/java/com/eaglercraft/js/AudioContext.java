package com.eaglercraft.js;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSMethod;
import org.teavm.jso.JSProperty;

public interface AudioContext extends JSObject {
    @JSProperty
    double getCurrentTime();

    @JSProperty
    JSObject getDestination();

    @JSMethod
    JSObject createBufferSource();

    @JSMethod
    JSObject createGain();

    @JSMethod
    JSObject createPanner();

    @JSMethod
    JSObject decodeAudioData(JSObject arrayBuffer);

    @JSMethod
    JSObject createBuffer(int numberOfChannels, int length, float sampleRate);
}