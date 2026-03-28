package com.eaglercraft.audio;

import com.eaglercraft.js.AudioContext;
import com.eaglercraft.js.AudioDecodeCallback;
import com.eaglercraft.js.Browser;
import com.eaglercraft.resource.ResourceManager;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import java.util.HashMap;
import java.util.Map;

public class SoundManager {
    private static AudioContext audioContext;
    private static final Map<String, JSObject> soundBuffers = new HashMap<>();
    private static float masterVolume = 1.0f;
    private static boolean initialized = false;

    public static void init() {
        audioContext = Browser.createAudioContext();
        initialized = true;
        System.out.println("SoundManager initialized!");
    }

    public static void loadSound(String name, String path) {
        JSObject resource = ResourceManager.getResource(path);
        if (resource == null) {
            System.out.println("Sound resource not found: " + path);
            return;
        }
        decodeAudio(audioContext, resource, buffer -> {
            soundBuffers.put(name, buffer);
            System.out.println("Sound loaded: " + name);
        });
    }

    @JSBody(params = {"ctx", "data", "callback"}, script =
            "ctx.decodeAudioData(data).then(function(buffer) { callback(buffer); });")
    private static native void decodeAudio(JSObject ctx, JSObject data, AudioDecodeCallback callback);

    @JSBody(params = {"ctx", "buffer", "gainNode", "loop"}, script =
            "var source = ctx.createBufferSource();" +
                    "source.buffer = buffer;" +
                    "source.loop = loop;" +
                    "source.connect(gainNode);" +
                    "gainNode.connect(ctx.destination);" +
                    "source.start(0);" +
                    "return source;")
    private static native JSObject playSound(JSObject ctx, JSObject buffer, JSObject gainNode, boolean loop);

    @JSBody(params = {"ctx", "volume"}, script =
            "var gain = ctx.createGain();" +
                    "gain.gain.value = volume;" +
                    "return gain;")
    private static native JSObject createGain(JSObject ctx, float volume);

    public static void playSound(String name, float volume, boolean loop) {
        if (!initialized || !soundBuffers.containsKey(name)) return;
        JSObject buffer = soundBuffers.get(name);
        JSObject gainNode = createGain(audioContext, volume * masterVolume);
        playSound(audioContext, buffer, gainNode, loop);
    }

    public static void playSound(String name) {
        playSound(name, 1.0f, false);
    }

    public static void setMasterVolume(float volume) {
        masterVolume = volume;
    }

    public static float getMasterVolume() {
        return masterVolume;
    }

    public static boolean isInitialized() {
        return initialized;
    }
}