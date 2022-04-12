package eu.mixeration.messageoptions.events;

import eu.mixeration.messageoptions.mixeration;
import eu.mixeration.messageoptions.module._user;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class quit implements Listener {

    @EventHandler
    public void userQuitEvent(PlayerQuitEvent quitEvent) {
        Player user = quitEvent.getPlayer();
        if (mixeration.getInstance().isJoinMessageEnable) {
            if (mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("oneLineText")) {
                String message = mixeration.getInstance().getConfig().getString("message-options.quit.oneLineText.message");
                message = message.replace("%name", user.getName());
                message = message.replace("%displayname", user.getDisplayName());
                message = message.replace("%locale", _user.getCountry(user));
                quitEvent.setQuitMessage(ChatColor.translateAlternateColorCodes('&', message));
            } else if (mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("oversizedText")) {
                for (String message : mixeration.getInstance().getConfig().getStringList("message-options.quit.oversizedText.message")) {
                    message = message.replace("%name", user.getName());
                    message = message.replace("%displayname", user.getDisplayName());
                    message = message.replace("%locale", _user.getCountry(user));
                    quitEvent.setQuitMessage(null);
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                }
            } else if (mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("usePermission")) {
                for (String groups : mixeration.getInstance().getConfig().getConfigurationSection("message-options.quit.usePermission").getKeys(false)) {
                    String permission = mixeration.getInstance().getConfig().getString("message-options.quit.usePermission." + groups + ".permission");
                    if (user.hasPermission(permission)) {
                        for (String message : mixeration.getInstance().getConfig().getStringList("message-options.quit.usePermission." + groups + ".message")) {
                            message = message.replace("%name", user.getName());
                            message = message.replace("%displayname", user.getDisplayName());
                            message = message.replace("%locale", _user.getCountry(user));
                            quitEvent.setQuitMessage(null);
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                        }
                    }
                }
            } else if (mixeration.getInstance().getJoinMessageType.equalsIgnoreCase("perPlayer")) {
                for (String groups : mixeration.getInstance().getConfig().getConfigurationSection("message-options.quit.perPlayer").getKeys(false)) {
                    String name = mixeration.getInstance().getConfig().getString("message-options.quit.perPlayer." + groups + ".name");
                    if (user.getName().equalsIgnoreCase(name)) {
                        for (String message : mixeration.getInstance().getConfig().getStringList("message-options.quit.perPlayer." + groups + ".message")) {
                            message = message.replace("%name", user.getName());
                            message = message.replace("%displayname", user.getDisplayName());
                            message = message.replace("%locale", _user.getCountry(user));
                            quitEvent.setQuitMessage(null);
                            Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', message));
                        }
                    }
                }
            }
        } else {
            quitEvent.setQuitMessage(null);
        }
    }
}
