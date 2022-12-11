package Main;

import java.util.ArrayList;
import java.util.Iterator;

import Main.Class.class_supporter;
import Main.Class.class_tanker;
import Main.Class.class_warrior;
import Main.Class.class_wizard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class main extends JavaPlugin
{
    public static String label = ChatColor.RED + "[" + ChatColor.WHITE + "RAID" + ChatColor.RED + "] " + ChatColor.RESET;

    public static FileConfiguration config = Bukkit.getServer().spigot().getConfig();

    @Override
    public void onEnable()
    {
        System.out.println("----------------------------------------------");
        System.out.println("  P r o j e c t   R A I D ");
        System.out.println(" bug report : channy070123@gmail.com");
        System.out.println("----------------------------------------------");

        this.getCommand("raid").setExecutor(new commands());
        getServer().getPluginManager().registerEvents(new events(), this);
        getServer().getPluginManager().registerEvents(new class_warrior(), this);
        getServer().getPluginManager().registerEvents(new class_wizard(), this);
        getServer().getPluginManager().registerEvents(new class_tanker(), this);
        getServer().getPluginManager().registerEvents(new class_supporter(), this);
        getServer().getPluginManager().registerEvents(new Main.Boss.Guardian.events(), this);
        this.getCommand("raid").setTabCompleter(new commands_tap());

        config = this.getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                try
                {
                    ArrayList<String> cooldown_list = class_manager.cooldown_list;

                    for(String name : cooldown_list)
                    {
                        class_manager.cooldown_manager.replace(name, class_manager.cooldown_manager.get(name) - 1);

                        if(class_manager.cooldown_manager.get(name) <= 0)
                        {
                            class_manager.cooldown_list.remove(name);
                            class_manager.cooldown_manager.remove(name);
                        }
                    }
                } catch (Exception e)
                {
                    //nothing
                }

                try
                {
                    ArrayList<Player> silence_list = class_manager.silence_list;

                    for(Player p : silence_list)
                    {
                        class_manager.silence_manager.replace(p, class_manager.silence_manager.get(p) - 1);

                        if(class_manager.silence_manager.get(p) <= 0)
                        {
                            class_manager.silence_list.remove(p);
                            class_manager.silence_manager.remove(p);
                        }
                    }
                } catch (Exception e)
                {
                    //nothing
                }

            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);
    }

    @Override
    public void onDisable()
    {
        saveConfig();

        System.out.println("----------------------------------------------");
        System.out.println("  P r o j e c t   R A I D ");
        System.out.println(" bug report : channy070123@gmail.com");
        System.out.println("----------------------------------------------");
    }
}
