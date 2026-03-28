package org.lwjgl.openal;

public class ALC10 {
    public static final int ALC_NO_ERROR = 0;
    public static final int ALC_FREQUENCY = 0x1007;
    public static final int ALC_REFRESH = 0x1008;
    public static final int ALC_SYNC = 0x1009;
    public static final int ALC_MONO_SOURCES = 0x1010;
    public static final int ALC_STEREO_SOURCES = 0x1011;

    public static long alcOpenDevice(String devicename) { return 1L; }
    public static long alcCreateContext(long device, int[] attrlist) { return 1L; }
    public static boolean alcMakeContextCurrent(long context) { return true; }
    public static void alcDestroyContext(long context) {}
    public static void alcCloseDevice(long device) {}
    public static int alcGetError(long device) { return ALC_NO_ERROR; }
}