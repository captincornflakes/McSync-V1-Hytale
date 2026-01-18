package dev.mcsync;

import javax.annotation.Nonnull;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

import dev.mcsync.commands.Commands;
import dev.mcsync.config.ConfigFile;
import dev.mcsync.events.GateKeeper;
import dev.mcsync.events.Join;


public class main extends JavaPlugin {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    public static Config<ConfigFile> CONFIG;
    
    public main(@Nonnull JavaPluginInit init) {
        super(init);
        CONFIG = this.withConfig("McSync", ConfigFile.CODEC);
    }

    @Override
    protected void setup() {
        /*
        * First Initialize
        */
        super.setup();
        CONFIG.save();
        LOGGER.atInfo().log("MCSync Plugin Enabled!" + " version " + this.getManifest().getVersion().toString());

        /*
        * register commands
        */
        this.getCommandRegistry().registerCommand(new Commands("mcsync", "An mcsync command"));
/*
        * register Events
        */
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, Join::onPlayerReady);
        this.getEventRegistry().registerGlobal(PlayerReadyEvent.class, GateKeeper::onPlayerReady);
    }
}