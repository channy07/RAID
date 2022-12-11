package Main.Class;

import Main.class_manager;
import Main.game;
import Main.main;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
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
import org.bukkit.Material;

public class class_wizard extends class_manager implements Listener
{
    public static int attack_cooldown = 10;
    public static double attack_damage = 3;

    public static int right_click_cooldown = 20*20;
    public static double right_click_damage = 5;

    public static int shift_left_click_cooldown = 50*20;

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

            if(game.game_player.get(p.getName()).equalsIgnoreCase("wizard"))
            {
                if(item.getType() == Material.IRON_SHOVEL)
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

        if(cooldown_list.contains(p.getName() + ".wizard.attack"))
        {
            //p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".wizard.attack")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".wizard.attack");
        cooldown_manager.put(p.getName() + ".wizard.attack", attack_cooldown);

        Location loc = p.getEyeLocation().add(0, -0.5, 0);
        Vector v = loc.getDirection().multiply(0.8);

        p.getWorld().playSound(loc, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1.5f);

        new BukkitRunnable()
        {
            int i = 0;

            @Override
            public void run()
            {
                loc.add(v);

                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLUE, 2));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 2));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.AQUA, 2));

                if(i >= 8*20 || loc.getBlock().getType() != Material.AIR || rangeDamage(p, loc, attack_damage, 0.5))
                {
                    this.cancel();
                }

                i++;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);
    }

    private void right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".wizard.right_click"))
        {
            p.sendMessage(main.label + "-유도탄- 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".wizard.right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".wizard.right_click");
        cooldown_manager.put(p.getName() + ".wizard.right_click", right_click_cooldown);

        Location loc1 = p.getLocation().add(0, 1, 0);
        Location loc2 = loc1.clone();
        loc2.setYaw(loc2.getYaw() + 15);
        Location loc3 = loc1.clone();
        loc3.setYaw(loc3.getYaw() - 15);

        Vector v1 = loc1.getDirection().multiply(0.4);
        Vector v2 = loc2.getDirection().multiply(0.4);
        Vector v3 = loc3.getDirection().multiply(0.4);

        p.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, p.getLocation().add(v1).add(0, 1, 0), 1, 0.01, 0, 0, 0);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 0.5f, 1);

        new BukkitRunnable()  //0도
        {
            Location loc = p.getLocation().add(0, 1, 0);
            Vector v1 = p.getLocation().getDirection().multiply(0.4);
            Entity en1;

            int i = 0;

            @Override
            public void run()
            {
                if(en1 == null)
                {
                    for(Entity e : p.getWorld().getNearbyEntities(loc, 5, 5, 5))
                    {
                        if (Damageable(p, e))
                        {
                            en1 = e;
                        }
                    }
                }
                else
                {
                    v1 = en1.getLocation().toVector().subtract(loc.toVector()).multiply(0.08).normalize();
                }

                loc.add(v1);
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLUE, 3));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 3));

                if(i >= 5*20 || rangeDamage(p, loc, right_click_damage, 0.5))
                {
                    this.cancel();
                }

                i++;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);

        new BukkitRunnable() //+15도
        {
            Location loc = p.getLocation().add(0, 1, 0);
            Vector v1 = loc2.getDirection().multiply(0.4);
            Entity en1;

            int i = 0;

            @Override
            public void run()
            {
                if(en1 == null)
                {
                    for(Entity e : p.getWorld().getNearbyEntities(loc, 5, 5, 5))
                    {
                        if (Damageable(p, e))
                        {
                            en1 = e;
                        }
                    }
                }
                else
                {
                    v1 = en1.getLocation().toVector().subtract(loc.toVector()).multiply(0.08);
                }

                loc.add(v1);
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLUE, 3));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 3));

                if(i >= 5*20 || rangeDamage(p, loc, right_click_damage, 0.5))
                {
                    this.cancel();
                }

                i++;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);

        new BukkitRunnable() //-15도
        {
            Location loc = p.getLocation().add(0, 1, 0);
            Vector v1 = loc3.getDirection().multiply(0.4);
            Entity en1;

            int i = 0;

            @Override
            public void run()
            {
                if(en1 == null)
                {
                    for(Entity e : p.getWorld().getNearbyEntities(loc, 5, 5, 5))
                    {
                        if (Damageable(p, e))
                        {
                            en1 = e;
                        }
                    }
                }
                else
                {
                    v1 = en1.getLocation().toVector().subtract(loc.toVector()).multiply(0.08);
                }

                loc.add(v1);
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLUE, 3));
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 1, 0.01, 0, 0, 0, new Particle.DustOptions(Color.BLACK, 3));

                if(i >= 5*20 || rangeDamage(p, loc, right_click_damage, 0.5))
                {
                    this.cancel();
                }

                i++;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);
    }

    private void shift_left_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".wizard.shift_left_click"))
        {
            p.sendMessage(main.label + "-블랙홀- 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".wizard.shift_left_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".wizard.shift_left_click");
        cooldown_manager.put(p.getName() + ".wizard.shift_left_click", shift_left_click_cooldown);

        Block b = p.getTargetBlock(null, 5);

        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 0.1f, 1);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 0.1f, 1);

        new BukkitRunnable()
        {
            Location loc = b.getLocation().add(0, 1, 0);
            int i = 0;

            @Override
            public void run()
            {
                p.getWorld().spawnParticle(Particle.REDSTONE, loc, 10, 0, 0, 0, 3, new Particle.DustOptions(Color.BLACK, 3));
                p.getWorld().spawnParticle(Particle.PORTAL, loc, 20, 0, 0, 0);

                for(Entity e : p.getWorld().getNearbyEntities(loc, 5, 5, 5))
                {
                    if (Damageable(p, e))
                    {
                        Vector v = loc.toVector().subtract(e.getLocation().toVector()).multiply(1);
                        e.setVelocity(v);

                        ((LivingEntity) e).damage(5, p);
                    }
                }

                if(i >= 5)
                {
                    this.cancel();
                }

                i++;
            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 10L);
    }

    private void shift_right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".wizard.shift_right_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".wizard.shift_right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".wizard.shift_right_click");
        cooldown_manager.put(p.getName() + ".wizard.shift_right_click", shift_right_click_cooldown);

    }
}