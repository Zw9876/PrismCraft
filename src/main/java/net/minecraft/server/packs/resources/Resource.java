package net.minecraft.server.packs.resources;

import java.io.Closeable;
import java.io.InputStream;
import net.minecraft.resources.ResourceLocation;

public interface Resource extends Closeable {
    ResourceLocation getLocation();
    InputStream getInputStream();
    <T> T getMetadata(Object serializer);
    String getSourceName();
}