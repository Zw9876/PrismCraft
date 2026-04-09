package net.minecraft;

import com.mojang.bridge.game.GameVersion;
import java.util.Date;

public class DetectedVersion implements GameVersion {
    public static final GameVersion BUILT_IN = new DetectedVersion();

    private DetectedVersion() {
    }

    @Override
    public String getId() {
        return "1.16.5";
    }

    @Override
    public String getName() {
        return "1.16.5";
    }

    @Override
    public String getReleaseTarget() {
        return "1.16.5";
    }

    @Override
    public int getWorldVersion() {
        return 2586;
    }

    @Override
    public int getProtocolVersion() {
        return SharedConstants.getProtocolVersion();
    }

    @Override
    public int getPackVersion() {
        return 6;
    }

    @Override
    public Date getBuildTime() {
        return new Date(0);
    }

    @Override
    public boolean isStable() {
        return true;
    }
}
