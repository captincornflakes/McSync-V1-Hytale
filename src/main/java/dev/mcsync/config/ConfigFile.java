package dev.mcsync.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class ConfigFile {

    public static final BuilderCodec<ConfigFile> CODEC = BuilderCodec.builder(ConfigFile.class, ConfigFile::new)
        .append(new KeyedCodec<String>("Token", Codec.STRING),
                (configFile, value, extraInfo) -> configFile.Token = value,
                (configFile, extraInfo) -> configFile.Token).add()
        .append(new KeyedCodec<Boolean>("Debug", Codec.BOOLEAN),
                (configFile, value, extraInfo) -> configFile.EnableDebug = value,
                (configFile, extraInfo) -> configFile.EnableDebug).add()
        .build();

    private String Token = "Token";
    private boolean EnableDebug = false;


    public void ConfigFile() {

    }
    public String getToken() {
        return Token;
    }
    
    public boolean isEnableDebug() {
        return EnableDebug;
    }
}