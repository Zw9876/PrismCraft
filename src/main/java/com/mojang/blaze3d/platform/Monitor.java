package com.mojang.blaze3d.platform;

public class Monitor {
    private final long monitor;

    public Monitor(long monitor) {
        this.monitor = monitor;
    }

    public long getMonitor() { return monitor; }

    public VideoMode getPreferredVidMode(java.util.Optional<VideoMode> preferred) {
        return preferred.orElse(new VideoMode(854, 480, 8, 8, 8, 60));
    }
}