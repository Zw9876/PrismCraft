package com.mojang.blaze3d.platform;

import com.eaglercraft.bridge.GLFWBridge;
import java.util.HashMap;
import java.util.Map;

public class InputConstants {

    public static final InputConstants.Key UNKNOWN = InputConstants.Type.KEYSYM.getOrCreate(-1);

    public static InputConstants.Key getKey(int keyCode, int scanCode) {
        return keyCode == -1 ? InputConstants.Type.SCANCODE.getOrCreate(scanCode)
                : InputConstants.Type.KEYSYM.getOrCreate(keyCode);
    }

    public static InputConstants.Key getKey(String name) {
        if (Key.NAME_MAP.containsKey(name)) {
            return Key.NAME_MAP.get(name);
        }
        for (InputConstants.Type type : InputConstants.Type.values()) {
            if (name.startsWith(type.defaultPrefix)) {
                String num = name.substring(type.defaultPrefix.length() + 1);
                return type.getOrCreate(Integer.parseInt(num));
            }
        }
        throw new IllegalArgumentException("Unknown key name: " + name);
    }

    public static boolean isKeyDown(long window, int keyCode) {
        return GLFWBridge.isKeyDown(keyCode);
    }

    public static void setupKeyboardCallbacks(long window, Object keyCallback, Object charModsCallback) {}
    public static void setupMouseCallbacks(long window, Object cursorPosCallback, Object mouseButtonCallback, Object scrollCallback, Object dropCallback) {}
    public static void setupRawMouseInput(long window) {}
    public static boolean isRawMouseInputSupported() { return false; }

    public static enum Type {
        KEYSYM("key.keyboard", "key"),
        SCANCODE("scancode", "scancode"),
        MOUSE("key.mouse", "mouse");

        private final Map<Integer, InputConstants.Key> map = new HashMap<>();
        final String defaultPrefix;
        private final String name;

        private Type(String defaultPrefix, String name) {
            this.defaultPrefix = defaultPrefix;
            this.name = name;
        }

        public InputConstants.Key getOrCreate(int code) {
            return map.computeIfAbsent(code, c -> new InputConstants.Key(this, c));
        }
    }

    public static final class Key {
        static final Map<String, InputConstants.Key> NAME_MAP = new HashMap<>();

        private final InputConstants.Type type;
        private final int value;
        private final String name;

        private Key(InputConstants.Type type, int value) {
            this.type = type;
            this.value = value;
            this.name = type.defaultPrefix + "." + value;
            NAME_MAP.put(this.name, this);
        }

        public InputConstants.Type getType() { return type; }
        public int getValue() { return value; }
        public String getName() { return name; }

        public boolean isUnknown() { return value == -1; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Key)) return false;
            Key other = (Key) obj;
            return this.type == other.type && this.value == other.value;
        }

        @Override
        public int hashCode() {
            return 31 * type.hashCode() + value;
        }

        @Override
        public String toString() { return name; }
    }

    // GLFW key constants
    public static final int GLFW_KEY_UNKNOWN = -1;
    public static final int GLFW_KEY_SPACE = 32;
    public static final int GLFW_KEY_APOSTROPHE = 39;
    public static final int GLFW_KEY_COMMA = 44;
    public static final int GLFW_KEY_MINUS = 45;
    public static final int GLFW_KEY_PERIOD = 46;
    public static final int GLFW_KEY_SLASH = 47;
    public static final int GLFW_KEY_0 = 48;
    public static final int GLFW_KEY_1 = 49;
    public static final int GLFW_KEY_2 = 50;
    public static final int GLFW_KEY_3 = 51;
    public static final int GLFW_KEY_4 = 52;
    public static final int GLFW_KEY_5 = 53;
    public static final int GLFW_KEY_6 = 54;
    public static final int GLFW_KEY_7 = 55;
    public static final int GLFW_KEY_8 = 56;
    public static final int GLFW_KEY_9 = 57;
    public static final int GLFW_KEY_SEMICOLON = 59;
    public static final int GLFW_KEY_EQUAL = 61;
    public static final int GLFW_KEY_A = 65;
    public static final int GLFW_KEY_B = 66;
    public static final int GLFW_KEY_C = 67;
    public static final int GLFW_KEY_D = 68;
    public static final int GLFW_KEY_E = 69;
    public static final int GLFW_KEY_F = 70;
    public static final int GLFW_KEY_G = 71;
    public static final int GLFW_KEY_H = 72;
    public static final int GLFW_KEY_I = 73;
    public static final int GLFW_KEY_J = 74;
    public static final int GLFW_KEY_K = 75;
    public static final int GLFW_KEY_L = 76;
    public static final int GLFW_KEY_M = 77;
    public static final int GLFW_KEY_N = 78;
    public static final int GLFW_KEY_O = 79;
    public static final int GLFW_KEY_P = 80;
    public static final int GLFW_KEY_Q = 81;
    public static final int GLFW_KEY_R = 82;
    public static final int GLFW_KEY_S = 83;
    public static final int GLFW_KEY_T = 84;
    public static final int GLFW_KEY_U = 85;
    public static final int GLFW_KEY_V = 86;
    public static final int GLFW_KEY_W = 87;
    public static final int GLFW_KEY_X = 88;
    public static final int GLFW_KEY_Y = 89;
    public static final int GLFW_KEY_Z = 90;
    public static final int GLFW_KEY_ESCAPE = 256;
    public static final int GLFW_KEY_ENTER = 257;
    public static final int GLFW_KEY_TAB = 258;
    public static final int GLFW_KEY_BACKSPACE = 259;
    public static final int GLFW_KEY_INSERT = 260;
    public static final int GLFW_KEY_DELETE = 261;
    public static final int GLFW_KEY_RIGHT = 262;
    public static final int GLFW_KEY_LEFT = 263;
    public static final int GLFW_KEY_DOWN = 264;
    public static final int GLFW_KEY_UP = 265;
    public static final int GLFW_KEY_F1 = 290;
    public static final int GLFW_KEY_F2 = 291;
    public static final int GLFW_KEY_F3 = 292;
    public static final int GLFW_KEY_F4 = 293;
    public static final int GLFW_KEY_F5 = 294;
    public static final int GLFW_KEY_F6 = 295;
    public static final int GLFW_KEY_F7 = 296;
    public static final int GLFW_KEY_F8 = 297;
    public static final int GLFW_KEY_F9 = 298;
    public static final int GLFW_KEY_F10 = 299;
    public static final int GLFW_KEY_F11 = 300;
    public static final int GLFW_KEY_F12 = 301;
    public static final int GLFW_KEY_LEFT_SHIFT = 340;
    public static final int GLFW_KEY_LEFT_CONTROL = 341;
    public static final int GLFW_KEY_LEFT_ALT = 342;
    public static final int GLFW_KEY_LEFT_SUPER = 343;
    public static final int GLFW_KEY_RIGHT_SHIFT = 344;
    public static final int GLFW_KEY_RIGHT_CONTROL = 345;
    public static final int GLFW_KEY_RIGHT_ALT = 346;
    public static final int GLFW_KEY_MOUSE_BUTTON_LEFT = 0;
    public static final int GLFW_KEY_MOUSE_BUTTON_RIGHT = 1;
    public static final int GLFW_KEY_MOUSE_BUTTON_MIDDLE = 2;
}