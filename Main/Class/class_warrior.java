package Main.Class;

import java.util.ArrayList;
import java.util.HashMap;

import Main.class_manager;
import Main.game;
import Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class class_warrior extends class_manager implements Listener
{
    static HashMap<Player, Integer> attack_stack = new HashMap<Player, Integer>();
    private static double attack_reach = 2.5;
    public static int attack_cooldown = 15;
    public static double attack_damage = 4;

    public static double right_click_damage = 3; //per hit
    public static int right_click_cooldown = 20*20;

    public static int shift_left_click_cooldown = 60*60;

    public static int shift_right_click_cooldown = 60*60;

    @EventHandler
    public void onClick(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();

        if(e.getHand() != EquipmentSlot.HAND)
        {
            return;
        }

        if(p.getItemInHand() != null && game.game_player.containsKey(p.getName()))
        {
            ItemStack item = p.getItemInHand();

            if(game.game_player.get(p.getName()).equalsIgnoreCase("warrior"))
            {
                if(item.getType() == Material.IRON_SWORD)
                {
                    if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)
                    {
                        if(!p.isSneaking())
                        {
                            attack(p);
                        }
                        else
                        {
                            shift_left_click(p);
                        }
                    }
                    else if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
                    {
                        if(!p.isSneaking())
                        {
                            right_click(p);
                        }
                        else
                        {
                            shift_right_click(p);
                        }
                    }
                }
            }
        }
    }

    public static void attack(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".warrior.attack"))
        {
            //p.sendMessage(main.label + "-휘두르기- 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".warrior.attack")/20 + "초 남았습니다");
            return;
        }

        ArrayList<LivingEntity> damaged_entity = new ArrayList<>();

        if(!attack_stack.containsKey(p) || attack_stack.get(p) == 0)
        {
            cooldown_list.add(p.getName() + ".warrior.attack");
            cooldown_manager.put(p.getName() + ".warrior.attack", attack_cooldown);

            Location loc = p.getEyeLocation();
            loc.add(0, -0.1, 0);
            loc.setPitch(0);

            p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_HURT, 1, 1);

            Location loc1 = loc.clone();
            loc1.setYaw(loc1.getYaw() + 45);
            loc1.setPitch(loc1.getPitch() - 15);

            for(int i=0; i<=18; i++)
            {
                for(double d=-0.5; d<=0.5; d+=0.5)
                {
                    Location loc2 = loc1.clone();
                    loc2.setYaw(loc1.getYaw() - i*5);
                    loc2.setPitch((float) (loc1.getPitch() + i*2));

                    Vector v = loc2.getDirection().multiply(attack_reach);

                    p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v).add(0, d, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                    rangeDamage(p, loc.clone().add(v).add(0, d, 0), attack_damage, 0.5, damaged_entity);
                }

                Location loc3 = loc1.clone();
                loc3.setYaw(loc1.getYaw() - i*5);
                loc3.setPitch((float) (loc1.getPitch() + i*2));
                Location loc4 = loc1.clone();
                loc4.setYaw(loc1.getYaw() - i*5);
                loc4.setPitch((float) (loc1.getPitch() + i*2));

                Vector v1 = loc3.getDirection().multiply(attack_reach);
                Vector v2 = loc4.getDirection().multiply(attack_reach);

                p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v1).add(0, 0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v2).add(0, -0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
            }

            attack_stack.remove(p);
            attack_stack.put(p, 1);
        }
        else if(attack_stack.get(p) == 1)
        {
            cooldown_list.add(p.getName() + ".warrior.attack");
            cooldown_manager.put(p.getName() + ".warrior.attack", attack_cooldown);

            Location loc = p.getEyeLocation();
            loc.add(0, -0.1, 0);
            loc.setPitch(0);

            p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_HURT, 1, 1);

            Location loc1 = loc.clone();
            loc1.setYaw(loc1.getYaw() + 45);
            loc1.setPitch(loc1.getPitch() + 15);

            for(int i=0; i<=18; i++)
            {
                for(double d=-0.5; d<=0.5; d+=0.5)
                {
                    Location loc2 = loc1.clone();
                    loc2.setYaw(loc1.getYaw() - i*5);
                    loc2.setPitch((float) (loc1.getPitch() - i*2));

                    Vector v = loc2.getDirection().multiply(attack_reach);

                    p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v).add(0, d, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                    rangeDamage(p, loc.clone().add(v).add(0, d, 0), attack_damage, 0.5, damaged_entity);
                }

                Location loc3 = loc1.clone();
                loc3.setYaw(loc1.getYaw() - i*5);
                loc3.setPitch((float) (loc1.getPitch() - i*2));
                Location loc4 = loc1.clone();
                loc4.setYaw(loc1.getYaw() - i*5);
                loc4.setPitch((float) (loc1.getPitch() - i*2));

                Vector v1 = loc3.getDirection().multiply(attack_reach);
                Vector v2 = loc4.getDirection().multiply(attack_reach);

                p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v1).add(0, 0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v2).add(0, -0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
            }

            attack_stack.remove(p);
            attack_stack.put(p, 2);
        }
        else if(attack_stack.get(p) == 2)
        {
            cooldown_list.add(p.getName() + ".warrior.attack");
            cooldown_manager.put(p.getName() + ".warrior.attack", attack_cooldown);

            Location loc = p.getEyeLocation();
            loc.add(0, -0.1, 0);
            loc.setPitch(0);

            p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_HURT, 1, 1);

            Location loc1 = loc.clone();
            loc1.setYaw(loc1.getYaw() + 45);

            for(int i=0; i<=18; i++)
            {
                for(double d=-0.5; d<=0.5; d+=0.5)
                {
                    Location loc2 = loc1.clone();
                    loc2.setYaw(loc1.getYaw() - i*5);

                    Vector v = loc2.getDirection().multiply(attack_reach);

                    p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v).add(0, d, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                    rangeDamage(p, loc.clone().add(v).add(0, d, 0), attack_damage, 0.5, damaged_entity);
                }

                Location loc3 = loc1.clone();
                loc3.setYaw(loc1.getYaw() - i*5);
                Location loc4 = loc1.clone();
                loc4.setYaw(loc1.getYaw() - i*5);

                Vector v1 = loc3.getDirection().multiply(attack_reach);
                Vector v2 = loc4.getDirection().multiply(attack_reach);

                p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v1).add(0, 0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v2).add(0, -0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
            }

            attack_stack.remove(p);
            attack_stack.put(p, 0);
        }
    }

    public static void right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".warrior.right_click"))
        {
            p.sendMessage(main.label + "-돌진- 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".warrior.right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".warrior.right_click");
        cooldown_manager.put(p.getName() + ".warrior.right_click", right_click_cooldown);

        Location loc = p.getLocation();
        p.setGravity(false);

        unable_skill.add(p);

        new BukkitRunnable()
        {
            int i = 1;

            @Override
            public void run()
            {
                ArrayList<LivingEntity> damaged_entity = new ArrayList<>();

                p.setVelocity(loc.getDirection().setY(0).multiply(1));

                if(i == 1 || i == 10)
                {
                    Location loc = p.getEyeLocation();
                    loc.add(0, -0.1, 0);

                    p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_HURT, 1, 1);

                    Location loc1 = loc.clone();
                    loc1.setYaw(loc1.getYaw() + 45);
                    loc1.setPitch(loc1.getPitch() - 15);

                    for(int i=0; i<=18; i++)
                    {
                        for(double d=-0.5; d<=0.5; d+=0.5)
                        {
                            Location loc2 = loc1.clone();
                            loc2.setYaw(loc1.getYaw() - i*5);
                            loc2.setPitch((float) (loc1.getPitch() + i*2));

                            Vector v = loc2.getDirection().multiply(attack_reach);

                            p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v).add(0, d, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                            rangeDamage(p, loc.clone().add(v).add(0, d, 0), right_click_damage, 0.5, damaged_entity);
                        }

                        Location loc3 = loc1.clone();
                        loc3.setYaw(loc1.getYaw() - i*5);
                        loc3.setPitch((float) (loc1.getPitch() + i*2));
                        Location loc4 = loc1.clone();
                        loc4.setYaw(loc1.getYaw() - i*5);
                        loc4.setPitch((float) (loc1.getPitch() + i*2));

                        Vector v1 = loc3.getDirection().multiply(attack_reach);
                        Vector v2 = loc4.getDirection().multiply(attack_reach);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v1).add(0, 0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v2).add(0, -0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                    }
                }
                else if(i == 4 || i == 13)
                {
                    Location loc = p.getEyeLocation();
                    loc.add(0, -0.1, 0);

                    p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_HURT, 1, 1);

                    Location loc1 = loc.clone();
                    loc1.setYaw(loc1.getYaw() + 45);
                    loc1.setPitch(loc1.getPitch() + 15);

                    for(int i=0; i<=18; i++)
                    {
                        for(double d=-0.5; d<=0.5; d+=0.5)
                        {
                            Location loc2 = loc1.clone();
                            loc2.setYaw(loc1.getYaw() - i*5);
                            loc2.setPitch((float) (loc1.getPitch() - i*2));

                            Vector v = loc2.getDirection().multiply(attack_reach);

                            p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v).add(0, d, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                            rangeDamage(p, loc.clone().add(v).add(0, d, 0), right_click_damage, 0.5, damaged_entity);
                        }

                        Location loc3 = loc1.clone();
                        loc3.setYaw(loc1.getYaw() - i*5);
                        loc3.setPitch((float) (loc1.getPitch() - i*2));
                        Location loc4 = loc1.clone();
                        loc4.setYaw(loc1.getYaw() - i*5);
                        loc4.setPitch((float) (loc1.getPitch() - i*2));

                        Vector v1 = loc3.getDirection().multiply(attack_reach);
                        Vector v2 = loc4.getDirection().multiply(attack_reach);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v1).add(0, 0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v2).add(0, -0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                    }
                }
                else if(i == 7 || i == 16)
                {
                    Location loc = p.getEyeLocation();
                    loc.add(0, -0.1, 0);

                    p.getWorld().playSound(loc, Sound.ENTITY_BLAZE_HURT, 1, 1);

                    Location loc1 = loc.clone();
                    loc1.setYaw(loc1.getYaw() + 45);

                    for(int i=0; i<=18; i++)
                    {
                        for(double d=-0.5; d<=0.5; d+=0.5)
                        {
                            Location loc2 = loc1.clone();
                            loc2.setYaw(loc1.getYaw() - i*5);

                            Vector v = loc2.getDirection().multiply(attack_reach);

                            p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v).add(0, d, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.RED, 1));
                            rangeDamage(p, loc.clone().add(v).add(0, d, 0), right_click_damage, 0.5, damaged_entity);
                        }

                        Location loc3 = loc1.clone();
                        loc3.setYaw(loc1.getYaw() - i*5);
                        Location loc4 = loc1.clone();
                        loc4.setYaw(loc1.getYaw() - i*5);

                        Vector v1 = loc3.getDirection().multiply(attack_reach);
                        Vector v2 = loc4.getDirection().multiply(attack_reach);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v1).add(0, 0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(v2).add(0, -0.6, 0), 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 1));
                    }
                }

                if(i >= 20)
                {
                    p.setGravity(true);
                    unable_skill.remove(p);
                    this.cancel();
                }

                i++;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);;
    }

    public static void shift_left_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".warrior.shift_left_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".warrior.shift_left_click")/20 + "초 남았습니다");
            return;
        }



        cooldown_list.add(p.getName() + ".warrior.shift_left_click");
        cooldown_manager.put(p.getName() + ".warrior.shift_left_click", shift_left_click_cooldown);

        Location loc = p.getLocation();
    }

    public static void shift_right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".warrior.shift_right_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".warrior.shift_right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".warrior.shift_right_click");
        cooldown_manager.put(p.getName() + ".warrior.shift_right_click", shift_right_click_cooldown);

        Location loc = p.getLocation();
    }
}

