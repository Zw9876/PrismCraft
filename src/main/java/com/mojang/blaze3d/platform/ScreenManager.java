package com.mojang.blaze3d.platform;

public class ScreenManager {
    public Monitor getMonitor(long monitorHandle) {
        return new Monitor(monitorHandle);
    }
}