package eu.mixeration.messageoptions.commands;

import eu.mixeration.messageoptions.mixeration;
import eu.mixeration.messageoptions.module.file;
import eu.mixeration.messageoptions.module.message;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class admin implements CommandExecutor {

    public admin(mixeration mixeration) {}

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player user = (Player) sender;
            if(command.getName().equalsIgnoreCase("MessageOptions")) {
                if(user.hasPermission("messageoptions.admin")) {
                    if(args.length == 0) {
                        if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lHelp message:"));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "    &7- &f/Mo reload &8: &7Reload plugin file."));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "    &7- &f/Mo settings &8: &7Get settings from config."));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            return true;
                        } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a&lYardım komutları:"));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "    &7- &f/Mo reload &8: &7Eklentiyi yeniler."));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "    &7- &f/Mo settings &8: &7Ayarları görürsün."));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            return true;
                        }
                    } else if(args[0].equalsIgnoreCase("reload")) {
                        mixeration.getInstance().saveConfig();
                        mixeration.getInstance().reloadConfig();
                        mixeration.getInstance().saveConfig();
                        file.saveMessageOptions();
                        if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                            message.console("&a[MessageOptions] &7Plugin reloaded by " + user.getName() + ".");
                            message.player(user, "&a[MessageOptions] &7Plugin reloaded.");
                        } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                            message.console("&a[MessageOptions] &7Eklenti " + user.getName() + " tarafından yenilendi.");
                            message.player(user, "&a[MessageOptions] &7Eklenti yenilendi.");
                        }
                        return true;
                    } else if(args[0].equalsIgnoreCase("settings")) {
                        if (mixeration.getInstance().locale.equalsIgnoreCase("en")) {
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            if (mixeration.getInstance().isMotdEnable) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Is join motd enable: &aYes"));
                            } else {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Is join motd enable: &cNo"));
                            }
                            if (mixeration.getInstance().isJoinMessageEnable) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Is join message enable: &aYes"));
                            } else {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Is join message enable: &cNo"));
                            }
                            if (mixeration.getInstance().isQuitMessageEnable) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Is quit message enable: &aYes"));
                            } else {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Is quit message enable: &cNo"));
                            }
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Join message type: &a" + mixeration.getInstance().getJoinMessageType));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Quit message type: &a" + mixeration.getInstance().getQuitMessageType));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        } else if (mixeration.getInstance().locale.equalsIgnoreCase("tr")) {
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                            if (mixeration.getInstance().isMotdEnable) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Giriş bilgilendirme mesajı aktif mi: &aEvet"));
                            } else {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Giriş bilgilendirme mesajı aktif mi: &cHayır"));
                            }
                            if (mixeration.getInstance().isJoinMessageEnable) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Giriş mesajı aktif mi: &aEvet"));
                            } else {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Giriş mesajı aktif mi: &cHayır"));
                            }
                            if (mixeration.getInstance().isQuitMessageEnable) {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Çıkış mesajı aktif mi: &aEvet"));
                            } else {
                                user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Çıkış mesajı aktif mi: &cHayır"));
                            }
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Giriş mesahı türü: &a" + mixeration.getInstance().getJoinMessageType));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Çıkış mesajı türü: &a" + mixeration.getInstance().getQuitMessageType));
                            user.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                        }
                    }
                } else {
                    message.player(user, mixeration.getInstance().getConfig().getString("messages.no-permission"));
                    return true;
                }
            }
        } else {
            message.console(mixeration.getInstance().getConfig().getString("messages.no-console"));
            return true;
        }
        return true;
    }
}
