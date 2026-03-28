package com.eaglercraft.resource;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.JSZip;
import com.eaglercraft.js.RunnableCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private static RunnableCallback onLoadCallback = null;
    private static boolean loaded = false;
    private static JSObject jsResources = null;

    public static void loadEPK(String url, RunnableCallback onLoad) {
        onLoadCallback = onLoad;
        System.out.println("Loading EPK from: " + url);
        Browser.loadEPK(url, ResourceManager::onEPKLoaded);
    }

    private static void onEPKLoaded(JSZip zip) {
        System.out.println("EPK loaded, unpacking...");
        unpackZip(zip);
    }

    @JSBody(params = {"zip", "resources", "callback"}, script =
            "var files = zip.files;" +
                    "var names = Object.keys(files);" +
                    "var pending = names.length;" +
                    "if (pending === 0) { callback(); return; }" +
                    "names.forEach(function(name) {" +
                    "    if (files[name].dir) { pending--; if (pending === 0) callback(); return; }" +
                    "    files[name].async('arraybuffer').then(function(data) {" +
                    "        resources[name] = data;" +
                    "        pending--;" +
                    "        if (pending === 0) callback();" +
                    "    });" +
                    "});")
    private static native void unpackZipJS(JSZip zip, JSObject resources, RunnableCallback callback);

    @JSBody(script = "return {};")
    private static native JSObject createJSObject();

    @JSBody(params = {"obj", "key"}, script = "return obj[key];")
    private static native JSObject getFromJSObject(JSObject obj, String key);

    private static void unpackZip(JSZip zip) {
        jsResources = createJSObject();
        unpackZipJS(zip, jsResources, () -> {
            loaded = true;
            System.out.println("EPK unpacked successfully!");
            if (onLoadCallback != null) {
                onLoadCallback.run();
            }
        });
    }

    public static JSObject getResource(String path) {
        if (jsResources == null) return null;
        return getFromJSObject(jsResources, path);
    }

    public static boolean isLoaded() {
        return loaded;
    }
}