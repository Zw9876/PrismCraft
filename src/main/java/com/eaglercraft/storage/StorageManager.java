package com.eaglercraft.storage;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.IDBCallback;
import com.eaglercraft.js.IDBDatabase;
import com.eaglercraft.js.RunnableCallback;
import org.teavm.jso.JSBody;
import org.teavm.jso.JSObject;

public class StorageManager {
    private static IDBDatabase database;
    private static boolean initialized = false;

    public static void init(RunnableCallback onReady) {
        Browser.openIndexedDB("eaglercraft", 1, db -> {
            database = (IDBDatabase) db;
            initialized = true;
            System.out.println("StorageManager initialized!");
            onReady.run();
        });
    }

    // Simple key-value storage using localStorage for settings
    public static void saveSetting(String key, String value) {
        Browser.localStorageSet("eaglercraft." + key, value);
    }

    public static String loadSetting(String key, String defaultValue) {
        String value = Browser.localStorageGet("eaglercraft." + key);
        return value != null ? value : defaultValue;
    }

    public static void removeSetting(String key) {
        Browser.localStorageRemove("eaglercraft." + key);
    }

    // IndexedDB for larger data like worlds
    public static void saveData(String key, JSObject data, RunnableCallback onComplete) {
        if (!initialized) return;
        putData(database, key, data, onComplete);
    }

    public static void loadData(String key, IDBCallback onComplete) {
        if (!initialized) return;
        getData(database, key, onComplete);
    }

    public static void deleteData(String key, RunnableCallback onComplete) {
        if (!initialized) return;
        deleteDataJS(database, key, onComplete);
    }

    @JSBody(params = {"db", "key", "data", "callback"}, script =
            "var tx = db.transaction('data', 'readwrite');" +
                    "var store = tx.objectStore('data');" +
                    "var req = store.put(data, key);" +
                    "req.onsuccess = function() { callback(); };")
    private static native void putData(JSObject db, String key, JSObject data, RunnableCallback callback);

    @JSBody(params = {"db", "key", "callback"}, script =
            "var tx = db.transaction('data', 'readonly');" +
                    "var store = tx.objectStore('data');" +
                    "var req = store.get(key);" +
                    "req.onsuccess = function(e) { callback(e.target.result); };")
    private static native void getData(JSObject db, String key, IDBCallback callback);

    @JSBody(params = {"db", "key", "callback"}, script =
            "var tx = db.transaction('data', 'readwrite');" +
                    "var store = tx.objectStore('data');" +
                    "var req = store.delete(key);" +
                    "req.onsuccess = function() { callback(); };")
    private static native void deleteDataJS(JSObject db, String key, RunnableCallback callback);

    public static boolean isInitialized() {
        return initialized;
    }
}