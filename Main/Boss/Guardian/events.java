package Main.Boss.Guardian;

import org.bukkit.Bukkit;
import org.bukkit.entity.Boss;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import Main.Boss.Guardian.boss_Guardian;
import org.bukkit.event.entity.EntityDeathEvent;

public class events extends boss_Guardian implements Listener
{
    @EventHandler
    public void onDamaged(EntityDamageEvent e)
    {
        Entity en = e.getEntity();

        if(isSpawned)
        {
            if(en.equals(boss))
            {
                bossBar.setProgress(boss.getHealth()/health);
            }
        }
    }

    @EventHandler
    public void onDead(EntityDeathEvent e)
    {
        Entity en = e.getEntity();

        if(isSpawned)
        {
            if (en.equals(boss))
            {
                bossBar.removeAll();
            }
        }
    }
}
