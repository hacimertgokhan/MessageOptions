package eu.mixeration.messageoptions;

import eu.mixeration.messageoptions.commands.admin;
import eu.mixeration.messageoptions.events.join;
import eu.mixeration.messageoptions.events.quit;
import eu.mixeration.messageoptions.module.file;
import eu.mixeration.messageoptions.module.message;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class mixeration extends JavaPlugin {
    private static mixeration instance;
    public static synchronized mixeration getInstance() {
        return instance;
    }
    public static synchronized void setInstance(mixeration mixeration) {
        instance = mixeration;
    }

    public String locale = getConfig().getString("message-options.locale");

    public boolean isJoinMessageEnable = getConfig().getBoolean("message-options.isEnable.join");
    public String getJoinMessageType = getConfig().getString("message-options.messageType.join");

    public boolean isQuitMessageEnable = getConfig().getBoolean("message-options.isEnable.quit");
    public String getQuitMessageType = getConfig().getString("message-options.messageType.quit");

    public boolean isMotdEnable = getConfig().getBoolean("message-options.isEnable.motd");

    @Override
    public void onEnable() {
        setInstance(this);
        eu.mixeration.messageoptions.module.file.loadConfig();
        if(locale.equalsIgnoreCase("en")) {
            message.console("&a[MessageOptions] &7Locale is &bEnglish ( En )");
        } else if(locale.equalsIgnoreCase("tr")) {
            message.console("&a[MessageOptions] &7Dil ayari &cTurkce ( TR )");
        }
        getCommand("MessageOptions").setExecutor(new admin(this));
        Bukkit.getServer().getPluginManager().registerEvents(new join(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new quit(), this);
        if(locale.equalsIgnoreCase("en")) {
            message.console("&a[MessageOptions] &7For support: &bmixeration#5118");
        } else if(locale.equalsIgnoreCase("tr")) {
            message.console("&a[MessageOptions] &7Destek ve sorunlariniz icin: &bmixeration#5118");
        }

    }
}
