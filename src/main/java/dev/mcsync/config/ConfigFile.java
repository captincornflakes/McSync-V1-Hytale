package dev.mcsync.config;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class ConfigFile {

    public static final BuilderCodec<ConfigFile> CODEC = BuilderCodec.builder(ConfigFile.class, ConfigFile::new)
        .append(new KeyedCodec<String>("Token", Codec.STRING),
                (configFile, value, extraInfo) -> configFile.Token = value,
                (configFile, extraInfo) -> configFile.Token).add()
        .append(new KeyedCodec<String>("Parameters", Codec.STRING),
                (configFile, value, extraInfo) -> configFile.Parameters = value,
                (configFile, extraInfo) -> configFile.Parameters).add()
        .build();

    private String Token = "Token";
    private String Parameters = "false";

    // Correct constructor
    public ConfigFile() {
    }

    public String getToken() {
        return Token;
    }

    public String getParameters() {
        return Parameters;
    }
}