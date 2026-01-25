package dev.mcsync.events;

import java.util.UUID;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.event.events.player.PlayerSetupConnectEvent;

import dev.mcsync.auth.auth;
import dev.mcsync.main;

public class GateKeeper {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    public static void onPlayerReady(PlayerSetupConnectEvent  event) {
        String username = event.getUsername();
        UUID uuid = event.getUuid();
        String token = main.CONFIG.get().getToken();

        LOGGER.atInfo().log("Welcome " + username + " Login gateway check initiated!");
        System.out.println("Incoming connection: " + username + " (" + uuid + ")");

        
        if (!auth.mcsyncAuth(token, uuid.toString()) && !token.equals("Token")) {
            LOGGER.atInfo().log("Blocked connection from " + username + " (" + uuid + ") - Failed to authenticate with McSync. Token: " + token);
            event.setCancelled(true);
            return;
            }

        LOGGER.atInfo().log("Authenticated " + username + " (" + uuid + ") Token: " + token);
        }

}
