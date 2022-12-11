package Main.Class;

import Main.class_manager;
import Main.game;
import Main.main;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;

public class class_tanker extends class_manager implements Listener
{
    public static int attack_cooldown = 15;
    static HashMap<Player, Integer> attack_stack = new HashMap<Player, Integer>();
    public static double attack_damage = 2;

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

            if(game.game_player.get(p.getName()).equalsIgnoreCase("tanker"))
            {
                if(item.getType() == Material.IRON_AXE)
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

    private void attack(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".tanker.attack"))
        {
            //p.sendMessage(main.label + "-검기- 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".tanker.attack")/20 + "초 남았습니다");
            return;
        }

        ArrayList<LivingEntity> damaged_entity = new ArrayList<>();

        cooldown_list.add(p.getName() + ".tanker.attack");
        cooldown_manager.put(p.getName() + ".tanker.attack", attack_cooldown);

        Location loc = p.getEyeLocation();
        loc.add(0, -0.5, 0);

        if(!attack_stack.containsKey(p) || attack_stack.get(p) == 0)
        {
            p.playSound(loc, Sound.ENTITY_WITHER_SHOOT, 0.3F, 1);

            Location loc1 = loc.clone();
            loc1.setYaw(loc1.getYaw() + 45);

            new BukkitRunnable()
            {
                double i = 0;
                @Override
                public void run()
                {
                    loc1.setYaw(loc1.getYaw() - 6);
                    for(double d=1; d<=6; d+=0.5)
                    {
                        Location loc2 = loc1.clone();
                        Vector v = loc2.getDirection().multiply(d + 1);

                        loc2.add(v);

                        loc2.add(0, d*0.5, 0);
                        loc2.add(0, -i*0.2, 0);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.GRAY, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.SILVER, 1));

                        rangeDamage(p, loc2, attack_damage, 0.2, damaged_entity);
                    }

                    loc1.setYaw(loc1.getYaw() - 6);
                    for(double d=1; d<=6; d+=0.5)
                    {
                        Location loc2 = loc1.clone();
                        Vector v = loc2.getDirection().multiply(d + 1);

                        loc2.add(v);

                        loc2.add(0, d*0.5, 0);
                        loc2.add(0, -i*0.2, 0);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.GRAY, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.SILVER, 1));

                        rangeDamage(p, loc2, attack_damage, 0.2, damaged_entity);
                    }

                    if(i == 7)
                    {
                        this.cancel();
                    }

                    i++;
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);

            if(attack_stack.containsKey(p))
            {
                attack_stack.remove(p);
            }
            attack_stack.put(p, 1);
        }
        else if(attack_stack.get(p) == 1)
        {
            p.playSound(loc, Sound.ENTITY_WITHER_SHOOT, 0.3F, 1);

            Location loc1 = loc.clone();
            loc1.setYaw(loc1.getYaw() - 45);

            new BukkitRunnable()
            {
                double i = 0;
                @Override
                public void run()
                {
                    loc1.setYaw(loc1.getYaw() + 6);
                    for(double d=1; d<=6; d+=0.5)
                    {
                        Location loc2 = loc1.clone();
                        Vector v = loc2.getDirection().multiply(d + 1);

                        loc2.add(v);

                        loc2.add(0, d*0.5, 0);
                        loc2.add(0, -i*0.2, 0);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.GRAY, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.SILVER, 1));

                        rangeDamage(p, loc2, attack_damage, 0.2, damaged_entity);
                    }

                    loc1.setYaw(loc1.getYaw() + 6);
                    for(double d=1; d<=6; d+=0.5)
                    {
                        Location loc2 = loc1.clone();
                        Vector v = loc2.getDirection().multiply(d + 1);

                        loc2.add(v);

                        loc2.add(0, d*0.5, 0);
                        loc2.add(0, -i*0.2, 0);

                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.GRAY, 1));
                        p.getWorld().spawnParticle(Particle.REDSTONE, loc2, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.SILVER, 1));

                        rangeDamage(p, loc2, attack_damage, 0.2, damaged_entity);
                    }

                    if(i == 7)
                    {
                        this.cancel();
                    }

                    i++;
                }
            }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);

            if(attack_stack.containsKey(p))
            {
                attack_stack.remove(p);
            }
            attack_stack.put(p, 0);
        }
    }

    private void right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".tanker.right_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".tanker.right_click")/20 + "초 남았습니다");
            return;
        }

        ArrayList<LivingEntity> damaged_entity = new ArrayList<>();

        new BukkitRunnable()
        {
            int i = 0;
            @Override
            public void run()
            {
                Location loc = p.getLocation();

                for(double t=0; t<=Math.PI*2; t+=Math.PI/8)
                {
                    double r = 4;

                    double x = r*Math.cos(t);
                    double z = r*Math.sin(t);

                    loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE,  loc.clone().add(x, 0, z), 1, 0, 0, 0);

                }

                loc.getWorld().playSound(loc, Sound.BLOCK_ANVIL_PLACE, 1, 1);

                for(Entity en : loc.getWorld().getNearbyEntities(loc, 4.5, 1, 4.5))
                {
                    if(Damageable(p, en))
                    {
                        ((LivingEntity) en).damage(5, p);
                        en.setVelocity(new Vector(0, 0.3, 0));
                    }
                }

                i++;

                if(i >= 3)
                {
                    this.cancel();
                }
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 20L);


        cooldown_list.add(p.getName() + ".wizard.right_click");
        cooldown_manager.put(p.getName() + ".wizard.right_click", right_click_cooldown);

    }

    private void shift_left_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".tanker.shift_left_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".tanker.shift_left_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".tanker.shift_left_click");
        cooldown_manager.put(p.getName() + ".tanker.shift_left_click", shift_left_click_cooldown);

    }

    private void shift_right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".tanker.shift_right_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".tanker.shift_right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".tanker.shift_right_click");
        cooldown_manager.put(p.getName() + ".tanker.shift_right_click", shift_right_click_cooldown);

    }
}