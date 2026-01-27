package dev.mcsync.events;

import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent;

public class Join {

    public static void onPlayerReady(PlayerReadyEvent event) {
        Player player = event.getPlayer();
        //player.sendMessage(Message.raw("Welcome to a mcsync powered server " + player.getDisplayName()));
    }

}