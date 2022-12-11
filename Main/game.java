package Main;

import java.util.ArrayList;
import java.util.HashMap;

import Main.Boss.Guardian.boss_Guardian;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class game
{
    public static HashMap<String, String> game_player = new HashMap<String, String>(); // (플레이어, 클래스)
    public static ArrayList<Player> player_list = new ArrayList<Player>();
    public static boolean isOnGame = false;

    public static void gameStart(Player p)
    {
        if(game_player.size() == 4)
        {
            p.sendMessage(main.label + "인원이 충분하지 않습니다");
        }
        else if(game_player.size() == 1 && !boss_manager.isBossSpawned)
        {
            isOnGame = true;

            new BukkitRunnable()
            {
                int i = 5;

                @Override
                public void run()
                {
                    for(Player p : player_list)
                    {
                        p.sendTitle(ChatColor.RED + "레이드가 곧 시작됩니다", i + "", 0, 20, 2*20);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 1, 3);
                    }

                    if(i == 0)
                    {
                        boss_Guardian.spawnBoss();

                        for(Player p : player_list)
                        {
                            p.teleport(setting.getStartLoc());
                        }
                        this.cancel();
                    }

                    i--;
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 20L);
        }
    }
}
