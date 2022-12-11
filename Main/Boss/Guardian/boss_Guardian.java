package Main.Boss.Guardian;


import Main.game;
import Main.setting;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.ElderGuardian;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import Main.boss_manager;

public class boss_Guardian extends boss_manager
{
    public static boolean isSpawned = false;
    public static ElderGuardian boss;
    public static BossBar bossBar;
    public static float health = 10;

    public static void spawnBoss()
    {
        Location loc = setting.getBossGuardianSpawnLoc();
        boss = (ElderGuardian) loc.getWorld().spawnEntity(loc, EntityType.ELDER_GUARDIAN);
        boss.setMaxHealth(health);
        boss.setHealth(health);
        isSpawned = true;
        bossBar = Bukkit.createBossBar("GUARDIAN", BarColor.BLUE, BarStyle.SOLID);
        bossBar.setProgress(boss.getHealth()/health);

        for(Player p : game.player_list)
        {
            bossBar.addPlayer(p);
        }
    }
}
