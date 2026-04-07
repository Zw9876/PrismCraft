package com.eaglercraft.bridge;

import com.eaglercraft.js.Browser;
import com.eaglercraft.js.KeyboardEvent;
import com.eaglercraft.js.MouseEvent;
import org.lwjgl.glfw.*;

public class GLFWBridge {
    private static boolean shouldClose = false;
    private static int windowWidth = 854;
    private static int windowHeight = 480;
    private static double mouseX = 0;
    private static double mouseY = 0;
    private static double startTime = 0;

    private static GLFWKeyCallback keyCallback;
    private static GLFWMouseButtonCallback mouseButtonCallback;
    private static GLFWCursorPosCallback cursorPosCallback;
    private static GLFWScrollCallback scrollCallback;
    private static GLFWFramebufferSizeCallback framebufferSizeCallback;
    private static GLFWCharCallback charCallback;

    private static final java.util.Set<Integer> keysDown = new java.util.HashSet<>();

    public static long createWindow(int width, int height, String title) {
        windowWidth = width;
        windowHeight = height;
        startTime = System.currentTimeMillis();
        setupInputListeners();
        System.out.println("GLFW window created: " + width + "x" + height + " - " + title);
        return 1L;
    }

    private static void setupInputListeners() {
        Browser.getWindow().addEventListener("keydown", GLFWBridge::onKeyDown);
        Browser.getWindow().addEventListener("keyup", GLFWBridge::onKeyUp);
        Browser.getWindow().addEventListener("mousemove", GLFWBridge::onMouseMove);
        Browser.addDocumentEventListener("pointerlockchange", GLFWBridge::onPointerLockChange);
    }

    private static void onKeyDown(KeyboardEvent event) {
        int key = keyCodeToGLFW(event.getCode());
        keysDown.add(key);
        if (keyCallback != null) {
            keyCallback.invoke(1L, key, 0, 1, 0);
        }
        if (charCallback != null) {
            String k = event.getKey();
            if (k.length() == 1) {
                charCallback.invoke(1L, k.charAt(0));
            }
        }
    }

    private static void onKeyUp(KeyboardEvent event) {
        int key = keyCodeToGLFW(event.getCode());
        keysDown.remove(key);
        if (keyCallback != null) {
            keyCallback.invoke(1L, key, 0, 0, 0);
        }
    }

    private static void onMouseMove(MouseEvent event) {
        mouseX = event.getClientX();
        mouseY = event.getClientY();
        if (cursorPosCallback != null) {
            cursorPosCallback.invoke(1L, mouseX, mouseY);
        }
    }

    private static void onPointerLockChange() {
        // handled by InputHandler
    }

    public static boolean shouldClose() { return shouldClose; }
    public static void setShouldClose(boolean value) { shouldClose = value; }
    public static void pollEvents() {}

    public static void setKeyCallback(GLFWKeyCallback callback) { keyCallback = callback; }
    public static void setMouseButtonCallback(GLFWMouseButtonCallback callback) { mouseButtonCallback = callback; }
    public static void setCursorPosCallback(GLFWCursorPosCallback callback) { cursorPosCallback = callback; }
    public static void setScrollCallback(GLFWScrollCallback callback) { scrollCallback = callback; }
    public static void setFramebufferSizeCallback(GLFWFramebufferSizeCallback callback) { framebufferSizeCallback = callback; }
    public static void setCharCallback(GLFWCharCallback callback) { charCallback = callback; }

    public static void getFramebufferSize(int[] width, int[] height) {
        if (width.length > 0) width[0] = windowWidth;
        if (height.length > 0) height[0] = windowHeight;
    }

    public static void setInputMode(int mode, int value) {}

    public static double getTime() {
        return (System.currentTimeMillis() - startTime) / 1000.0;
    }

    private static int keyCodeToGLFW(String code) {
        switch (code) {
            case "KeyA": return 65;
            case "KeyB": return 66;
            case "KeyC": return 67;
            case "KeyD": return 68;
            case "KeyE": return 69;
            case "KeyF": return 70;
            case "KeyG": return 71;
            case "KeyH": return 72;
            case "KeyI": return 73;
            case "KeyJ": return 74;
            case "KeyK": return 75;
            case "KeyL": return 76;
            case "KeyM": return 77;
            case "KeyN": return 78;
            case "KeyO": return 79;
            case "KeyP": return 80;
            case "KeyQ": return 81;
            case "KeyR": return 82;
            case "KeyS": return 83;
            case "KeyT": return 84;
            case "KeyU": return 85;
            case "KeyV": return 86;
            case "KeyW": return 87;
            case "KeyX": return 88;
            case "KeyY": return 89;
            case "KeyZ": return 90;
            case "Space": return 32;
            case "Enter": return 257;
            case "Escape": return 256;
            case "Backspace": return 259;
            case "Tab": return 258;
            case "ShiftLeft": case "ShiftRight": return 340;
            case "ControlLeft": case "ControlRight": return 341;
            case "AltLeft": case "AltRight": return 342;
            case "ArrowLeft": return 263;
            case "ArrowRight": return 262;
            case "ArrowUp": return 265;
            case "ArrowDown": return 264;
            case "F1": return 290;
            case "F2": return 291;
            case "F3": return 292;
            case "F4": return 293;
            case "F5": return 294;
            case "F6": return 295;
            case "F7": return 296;
            case "F8": return 297;
            case "F9": return 298;
            case "F10": return 299;
            case "F11": return 300;
            case "F12": return 301;
            case "Digit0": return 48;
            case "Digit1": return 49;
            case "Digit2": return 50;
            case "Digit3": return 51;
            case "Digit4": return 52;
            case "Digit5": return 53;
            case "Digit6": return 54;
            case "Digit7": return 55;
            case "Digit8": return 56;
            case "Digit9": return 57;
            default: return 0;
        }
    }

    public static boolean isKeyDown(int keyCode) {
        return keysDown.contains(keyCode);
    }
}