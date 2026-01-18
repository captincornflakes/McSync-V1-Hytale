package dev.mcsync.events;

import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

import dev.mcsync.auth.auth;

public class GateKeeper {
    public static final HytaleLogger LOGGER = HytaleLogger.forEnclosingClass();
    
    @SuppressWarnings("removal")
    public static void onPlayerReady(PlayerReadyEvent event) {
        String token = "example_token"; // Replace with actual token retrieval logic
        String uuid = event.getPlayer().getUuid().toString();  // Replace with actual UUID retrieval logic
        Player player = event.getPlayer();
        LOGGER.atInfo().log("Welcome " + player.getDisplayName() + " Login gateway check initiated!");
        player.sendMessage(Message.raw("Checking Login security for " + player.getDisplayName()));
        auth.mcsyncAuth(token, uuid);
    }

    
}
