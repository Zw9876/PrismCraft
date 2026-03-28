package org.lwjgl.openal;

import com.eaglercraft.audio.SoundManager;

public class AL10 {
    public static final int AL_NONE = 0;
    public static final int AL_TRUE = 1;
    public static final int AL_FALSE = 0;
    public static final int AL_SOURCE_RELATIVE = 0x202;
    public static final int AL_CONE_INNER_ANGLE = 0x1001;
    public static final int AL_CONE_OUTER_ANGLE = 0x1002;
    public static final int AL_PITCH = 0x1003;
    public static final int AL_POSITION = 0x1004;
    public static final int AL_DIRECTION = 0x1005;
    public static final int AL_VELOCITY = 0x1006;
    public static final int AL_LOOPING = 0x1007;
    public static final int AL_BUFFER = 0x1009;
    public static final int AL_GAIN = 0x100A;
    public static final int AL_MIN_GAIN = 0x100D;
    public static final int AL_MAX_GAIN = 0x100E;
    public static final int AL_ORIENTATION = 0x100F;
    public static final int AL_SOURCE_STATE = 0x1010;
    public static final int AL_INITIAL = 0x1011;
    public static final int AL_PLAYING = 0x1012;
    public static final int AL_PAUSED = 0x1013;
    public static final int AL_STOPPED = 0x1014;
    public static final int AL_BUFFERS_QUEUED = 0x1015;
    public static final int AL_BUFFERS_PROCESSED = 0x1016;
    public static final int AL_FORMAT_MONO8 = 0x1100;
    public static final int AL_FORMAT_MONO16 = 0x1101;
    public static final int AL_FORMAT_STEREO8 = 0x1102;
    public static final int AL_FORMAT_STEREO16 = 0x1103;
    public static final int AL_REFERENCE_DISTANCE = 0x1020;
    public static final int AL_ROLLOFF_FACTOR = 0x1021;
    public static final int AL_MAX_DISTANCE = 0x1023;
    public static final int AL_NO_ERROR = 0;
    public static final int AL_INVALID_NAME = 0xA001;
    public static final int AL_INVALID_ENUM = 0xA002;
    public static final int AL_INVALID_VALUE = 0xA003;
    public static final int AL_INVALID_OPERATION = 0xA004;
    public static final int AL_OUT_OF_MEMORY = 0xA005;

    public static void alGenSources(int[] sources) {
        for (int i = 0; i < sources.length; i++) sources[i] = i + 1;
    }
    public static void alGenBuffers(int[] buffers) {
        for (int i = 0; i < buffers.length; i++) buffers[i] = i + 1;
    }
    public static void alDeleteSources(int[] sources) {}
    public static void alDeleteBuffers(int[] buffers) {}
    public static void alSourcef(int source, int param, float value) {}
    public static void alSource3f(int source, int param, float v1, float v2, float v3) {}
    public static void alSourcei(int source, int param, int value) {}
    public static void alSourcePlay(int source) {}
    public static void alSourceStop(int source) {}
    public static void alSourcePause(int source) {}
    public static void alBufferData(int buffer, int format, java.nio.ShortBuffer data, int freq) {}
    public static void alListenerf(int param, float value) {}
    public static void alListener3f(int param, float v1, float v2, float v3) {}
    public static void alListenerfv(int param, float[] values) {}
    public static int alGetSourcei(int source, int param) { return AL_STOPPED; }
    public static int alGetError() { return AL_NO_ERROR; }
}