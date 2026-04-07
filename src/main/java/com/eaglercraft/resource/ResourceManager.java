package com.eaglercraft.resource;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.JSZip;
import com.eaglercraft.js.RunnableCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

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
                    "if (pending === 0) { callback([]); return; }" +
                    "var results = [];" +
                    "names.forEach(function(name) {" +
                    "    if (files[name].dir) { pending--; if (pending === 0) callback(results); return; }" +
                    "    files[name].async('uint8array').then(function(data) {" +
                    "        results.push({name: name, data: data});" +
                    "        resources[name] = data.buffer;" +
                    "        pending--;" +
                    "        if (pending === 0) callback(results);" +
                    "    });" +
                    "});")
    private static native void unpackZipJS(JSZip zip, JSObject resources, UnpackCallback callback);

    @JSBody(script = "return {};")
    private static native JSObject createJSObject();

    @JSBody(params = {"obj", "key"}, script = "return obj[key];")
    private static native JSObject getFromJSObject(JSObject obj, String key);

    @JSBody(params = {"results"}, script = "return results.length;")
    private static native int getResultCount(JSObject results);

    @JSBody(params = {"results", "index"}, script = "return results[index].name;")
    private static native String getResultName(JSObject results, int index);

    @JSBody(params = {"results", "index"}, script = "return results[index].data;")
    private static native JSObject getResultData(JSObject results, int index);

    @JSBody(params = {"data"}, script = "return Array.from(data);")
    private static native int[] uint8ArrayToBytes(JSObject data);

    private static void unpackZip(JSZip zip) {
        jsResources = createJSObject();
        unpackZipJS(zip, jsResources, results -> {
            // Populate EaglerResourceManager with all resources
            int count = getResultCount(results);
            for (int i = 0; i < count; i++) {
                String name = getResultName(results, i);
                JSObject data = getResultData(results, i);
                int[] bytes = uint8ArrayToBytes(data);
                byte[] byteArray = new byte[bytes.length];
                for (int j = 0; j < bytes.length; j++) {
                    byteArray[j] = (byte) bytes[j];
                }
                EaglerResourceManager.getInstance().putResource(name, byteArray);
            }
            loaded = true;
            System.out.println("EPK unpacked successfully! Loaded " + count + " resources.");
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

    @org.teavm.jso.JSFunctor
    public interface UnpackCallback extends JSObject {
        void onUnpacked(JSObject results);
    }
}