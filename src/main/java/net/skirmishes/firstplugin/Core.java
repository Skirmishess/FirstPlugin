package net.skirmishes.firstplugin;

import net.skirmishes.firstplugin.commands.FirstPluginCommand;
import net.skirmishes.firstplugin.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public final class Core extends JavaPlugin {

    public static Core plugin;
    public File configYml = new File(getDataFolder() +"config.yml"); public FileConfiguration configFile = YamlConfiguration.loadConfiguration(this.configYml);

    public File itemJoinYml = new File(getDataFolder() +"itemjoin.yml"); public FileConfiguration itemJoinFile = YamlConfiguration.loadConfiguration(this.itemJoinYml);
    public File selectorYml = new File(getDataFolder() +"selector.yml"); public FileConfiguration selectorFile = YamlConfiguration.loadConfiguration(this.selectorYml);
    public File langYml = new File(getDataFolder() +"lang.yml"); public FileConfiguration langFile = YamlConfiguration.loadConfiguration(this.langYml);
    public Inventory inventory;
    @Override
    public void onEnable()
    {
        plugin = this;
        loadConfigs();

        this.getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
        this.getServer().getPluginManager().registerEvents(new GUIInventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new SelectorInventoryListener(), this);
        this.getServer().getPluginManager().registerEvents(new JoinListener(), this);

        this.getCommand("firstplugin").setExecutor(new FirstPluginCommand());

        Bukkit.getConsoleSender().sendMessage(cc(langFile.getString("Prefix")+" &aEnabled FirstPlugin by skirmishes."));

    }

    @Override
    public void onDisable()
    {
        Bukkit.getConsoleSender().sendMessage(cc(langFile.getString("Prefix")+" &cDisabled FirstPlugin by skirmishes."));
    }

    public void loadConfigs()
    {
        try {
            configYml = new File(getDataFolder() +"/config.yml");
            if (!configYml.exists()) {
                langFile.save(getDataFolder() +"/config.yml");

                InputStream inputStream = this.getResource("config.yml");
                OutputStream outputStream = Files.newOutputStream(configYml.toPath());

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                Bukkit.getConsoleSender().sendMessage(cc(" &8- &bCreated new config.yml in plugins folder."));
            }

            itemJoinYml = new File(getDataFolder() +"/itemjoin.yml");
            if (!itemJoinYml.exists()) {
                itemJoinFile.save(getDataFolder() +"/itemjoin.yml");

                InputStream inputStream = this.getResource("itemjoin.yml");
                OutputStream outputStream = Files.newOutputStream(itemJoinYml.toPath());

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                Bukkit.getConsoleSender().sendMessage(cc(" &8- &bCreated new itemjoin.yml in plugins folder."));
            }

            selectorYml = new File(getDataFolder() +"/selector.yml");
            if (!selectorYml.exists()) {
                selectorFile.save(getDataFolder() +"/selector.yml");

                InputStream inputStream = this.getResource("selector.yml");
                OutputStream outputStream = Files.newOutputStream(selectorYml.toPath());

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }
                Bukkit.getConsoleSender().sendMessage(cc(" &8- &bCreated new selector.yml in plugins folder."));
            }

            langYml = new File(getDataFolder() +"/lang.yml");
            if (!langYml.exists()) {
                langFile.save(getDataFolder() +"/lang.yml");

                InputStream inputStream;
                OutputStream outputStream;

                inputStream = this.getResource("lang.yml");
                outputStream = Files.newOutputStream(langYml.toPath());

                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = inputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, read);
                }

                Bukkit.getConsoleSender().sendMessage(cc(" &8- &bCreated new lang.yml in plugins folder."));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.configFile = YamlConfiguration.loadConfiguration(this.configYml);
        this.itemJoinFile = YamlConfiguration.loadConfiguration(this.itemJoinYml);
        this.selectorFile = YamlConfiguration.loadConfiguration(this.selectorYml);
        this.langFile = YamlConfiguration.loadConfiguration(this.langYml);
    }
    public void reloadConfigs()
    {
        this.configFile = YamlConfiguration.loadConfiguration(this.configYml);
        this.itemJoinFile = YamlConfiguration.loadConfiguration(this.itemJoinYml);
        this.selectorFile = YamlConfiguration.loadConfiguration(this.selectorYml);
        this.langFile = YamlConfiguration.loadConfiguration(this.langYml);
    }
    public static Material getMaterialByName(String name) {
        if (name.equalsIgnoreCase("null"))
            return Material.AIR;
        for (Material material : Material.values()) {
            if (!material.name().equalsIgnoreCase(name)) continue;
            return material;
        }
        return null;
    }
    public static String cc(String message)
    {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
