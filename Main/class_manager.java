package Main;

import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class class_manager
{
    public static HashMap<String, String> player_class = new HashMap<String, String>(); // (클래스, 플레이어)
    public static HashMap<String, Integer> cooldown_manager = new HashMap<String, Integer>();
    public static ArrayList<String> cooldown_list = new ArrayList<String>();
    public static ArrayList<Player> unable_skill = new ArrayList<>();
    public static ArrayList<Player> silence_list = new ArrayList<>();
    public static HashMap<Player, Integer> silence_manager = new HashMap<>();


    public static boolean Damageable(Player p, Entity en)
    {
        if(en instanceof LivingEntity && en != p)
        {
            if(en instanceof Player)
            {

            }

            return true;
        }

        return false;
    }

    public static boolean rangeDamage(Player p, Location loc, double damage, double r, ArrayList<LivingEntity> list)
    {
        for(Entity e : p.getWorld().getNearbyEntities(loc, r, r, r))
        {
            if(Damageable(p, e) && !list.contains(e))
            {
                ((org.bukkit.entity.Damageable) e).damage(damage, p);
                list.add((LivingEntity) e);
                Bukkit.getServer().broadcastMessage("hit");
                return true;
            }
        }

        return false;
    }

    public static boolean rangeDamage(Player p, Location loc, double damage, double r)
    {
        for(Entity e : p.getWorld().getNearbyEntities(loc, r, r, r))
        {
            if(Damageable(p, e))
            {
                ((org.bukkit.entity.Damageable) e).damage(damage, p);
                Bukkit.getServer().broadcastMessage("hit");
                return true;
            }
        }

        return false;
    }

    public static void silencePlayer(Player p, int i)
    {
        silence_list.add(p);
        silence_manager.put(p, i);
    }

    public static boolean isEnableSkill(Player p)
    {
        return !(silence_list.contains(p) || unable_skill.contains(p));
    }

    public static void healPlayer(Player target, double heal)
    {
        target.setHealth(target.getHealth() + heal);
    }

    public static boolean isTeam(Player p1, Player p2)
    {
        if(game.player_list.contains(p1) && game.player_list.contains(p2))
        {
            return true;
        }

        return false;
    }

    public static boolean getLookingAt(Player player, LivingEntity livingEntity)
    {
        Location eye = player.getEyeLocation();
        Vector toEntity = livingEntity.getEyeLocation().toVector().subtract(eye.toVector());
        double dot = toEntity.normalize().dot(eye.getDirection());

        return dot > 0.99D;
    }
}
