package eu.mixeration.messageoptions.events;

import eu.mixeration.messageoptions.mixeration;
import eu.mixeration.messageoptions.module._user;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class join implements Listener {

    @EventHandler
    public void userJoinEvent(PlayerJoinEvent joinEvent) {
        Player user = joinEvent.getPlayer();
        if(mixeration.getInstance().isJoinMessageEnable) {
            if(mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("oneLineText")) {
                String message = mixeration.getInstance().getConfig().getString("message-options.join.oneLineText.message");
                message = message.replace("%name", user.getName());
                message = message.replace("%displayname", user.getDisplayName());
                message = message.replace("%locale", _user.getCountry(user));
                joinEvent.setJoinMessage(ChatColor.translateAlternateColorCodes('&', message));
            } else if(mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("oversizedText")) {
                for (String message : mixeration.getInstance().getConfig().getStringList("message-options.join.oversizedText.message")) {
                    message = message.replace("%name", user.getName());
                    message = message.replace("%displayname", user.getDisplayName());
                    message = message.replace("%locale", _user.getCountry(user));
                    joinEvent.setJoinMessage(null);
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                }
            } else if(mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("usePermission")) {
                for (String groups : mixeration.getInstance().getConfig().getConfigurationSection("message-options.join.usePermission").getKeys(false)) {
                    String permission = mixeration.getInstance().getConfig().getString("message-options.join.usePermission." + groups + ".permission");
                    if(user.hasPermission(permission)) {
                        for (String message : mixeration.getInstance().getConfig().getStringList("message-options.join.usePermission." + groups + ".message")) {
                            message = message.replace("%name", user.getName());
                            message = message.replace("%displayname", user.getDisplayName());
                            message = message.replace("%locale", _user.getCountry(user));
                            joinEvent.setJoinMessage(null);
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                        }
                    }
                }
            } else if(mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("perPlayer")) {
                for (String groups : mixeration.getInstance().getConfig().getConfigurationSection("message-options.join.perPlayer").getKeys(false)) {
                    String name = mixeration.getInstance().getConfig().getString("message-options.join.perPlayer." + groups + ".name");
                    if(user.getName().equalsIgnoreCase(name)) {
                        for (String message : mixeration.getInstance().getConfig().getStringList("message-options.join.perPlayer." + groups + ".message")) {
                            message = message.replace("%name", user.getName());
                            message = message.replace("%displayname", user.getDisplayName());
                            message = message.replace("%locale", _user.getCountry(user));
                            joinEvent.setJoinMessage(null);
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                        }
                    }
                }
            }
        } else {
            joinEvent.setJoinMessage(null);
        }
        if(mixeration.getInstance().isMotdEnable) {
            for (String message : mixeration.getInstance().getConfig().getStringList("messages.join-motd")) {
                message = message.replace("%name", user.getName());
                message = message.replace("%displayname", user.getDisplayName());
                message = message.replace("%locale", _user.getCountry(user));
                user.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
        }
    }
}
