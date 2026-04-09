package net.minecraft;

import com.mojang.bridge.game.GameVersion;

public class SharedConstants {
    public static final long MAXIMUM_TICK_TIME_NANOS = 300_000_000L;
    public static boolean CHECK_DATA_FIXER_SCHEMA = false;
    public static boolean IS_RUNNING_IN_IDE = false;
    public static final char[] ILLEGAL_FILE_CHARACTERS = new char[]{
            '/', '\n', '\r', '\t', '\u0000', '\f', '`', '?', '*', '\\', '<', '>', '|', '"', ':'
    };

    public static boolean isAllowedChatCharacter(char c) {
        return c != 167 && c >= ' ' && c != 127;
    }

    public static String filterText(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (isAllowedChatCharacter(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static GameVersion getCurrentVersion() {
        return DetectedVersion.BUILT_IN;
    }

    public static int getProtocolVersion() {
        return 754;
    }
}
