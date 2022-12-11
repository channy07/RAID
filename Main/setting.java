package Main;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;

public class setting implements Listener
{
    public static HashMap<Player, String> setting = new HashMap<>();
    public static HashMap<Player, Boolean> enableSetting = new HashMap<>();

    public static void showSettingBlocks(Player p)
    {
        new BukkitRunnable()
        {
            ArrayList<FallingBlock> fallingblock = new ArrayList<>();
            Location settingLoc;

            @Override
            public void run()
            {
                if(!setting.containsKey(p))
                {
                    if(fallingblock.size() != 0)
                    {
                        for (FallingBlock fb : fallingblock)
                        {
                            fb.remove();
                        }

                        fallingblock.clear();
                    }

                    this.cancel();
                    return;
                }

                if(p.getItemInHand() != null)
                {
                    if(p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("setting"))
                    {
                        Block b = p.getTargetBlockExact(15);

                        if(b == null)
                        {
                            if(fallingblock.size() != 0)
                            {
                                for (FallingBlock fb : fallingblock)
                                {
                                    fb.remove();
                                }

                                fallingblock.clear();
                            }
                        }
                        else if(b.getLocation().clone().add(0, 1, 0).getBlock().getType() == Material.AIR)
                        {
                            if(settingLoc == null)
                            {
                                settingLoc = b.getLocation();

                                if(settingLoc.clone().add(0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                        settingLoc.clone().add(-0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                        settingLoc.clone().add(0.5, 1, -0.5).getBlock().getType() == Material.AIR &&
                                        settingLoc.clone().add(-0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                {
                                    FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b1.setGravity(false);
                                    fallingblock.add(b1);

                                    FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b2.setGravity(false);
                                    fallingblock.add(b2);

                                    FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b3.setGravity(false);
                                    fallingblock.add(b3);

                                    FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                    b4.setGravity(false);
                                    fallingblock.add(b4);

                                    enableSetting.put(p, true);
                                }
                                else
                                {
                                    FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                    b1.setGravity(false);
                                    fallingblock.add(b1);

                                    FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                    b2.setGravity(false);
                                    fallingblock.add(b2);

                                    FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                    b3.setGravity(false);
                                    fallingblock.add(b3);

                                    FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                    b4.setGravity(false);
                                    fallingblock.add(b4);

                                    enableSetting.put(p, false);
                                }
                            }
                            else
                            {
                                if(settingLoc != b.getLocation())
                                {
                                    settingLoc = b.getLocation();

                                    for (FallingBlock fb : fallingblock)
                                    {
                                        fb.remove();
                                    }

                                    fallingblock.clear();

                                    if(settingLoc.clone().add(0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                            settingLoc.clone().add(-0.5, 1, 0.5).getBlock().getType() == Material.AIR &&
                                            settingLoc.clone().add(0.5, 1, -0.5).getBlock().getType() == Material.AIR &&
                                            settingLoc.clone().add(-0.5, 1, -0.5).getBlock().getType() == Material.AIR)
                                    {
                                        FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b1.setGravity(false);
                                        fallingblock.add(b1);

                                        FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b2.setGravity(false);
                                        fallingblock.add(b2);

                                        FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b3.setGravity(false);
                                        fallingblock.add(b3);

                                        FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.GREEN_STAINED_GLASS, (byte) 0);
                                        b4.setGravity(false);
                                        fallingblock.add(b4);

                                        enableSetting.replace(p, true);
                                    }
                                    else
                                    {
                                        FallingBlock b1 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b1.setGravity(false);
                                        fallingblock.add(b1);

                                        FallingBlock b2 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, 0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b2.setGravity(false);
                                        fallingblock.add(b2);

                                        FallingBlock b3 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b3.setGravity(false);
                                        fallingblock.add(b3);

                                        FallingBlock b4 = p.getWorld().spawnFallingBlock(settingLoc.clone().add(-0.5, 1, -0.5), Material.RED_STAINED_GLASS, (byte) 0);
                                        b4.setGravity(false);
                                        fallingblock.add(b4);

                                        enableSetting.replace(p, false);
                                    }
                                }
                            }
                        }
                        else
                        {
                            if(fallingblock.size() != 0)
                            {
                                for (FallingBlock fb : fallingblock)
                                {
                                    fb.remove();
                                }

                                fallingblock.clear();
                            }
                        }
                    }
                    else
                    {
                        for (FallingBlock fb : fallingblock)
                        {
                            fb.remove();
                        }

                        p.sendMessage(main.label + "설정이 취소되었습니다");
                        setting.remove(p);
                        this.cancel();
                    }
                }

            }
        }.runTaskTimer(Bukkit.getServer().getPluginManager().getPlugin("RAID"), 0L, 1L);
    }

    public static Location getStartLoc()
    {
        Location loc = new Location(Bukkit.getWorld(main.config.getString("setting.raid.Guardian.start.world")), main.config.getDouble("setting.raid.Guardian.start.x"), main.config.getDouble("setting.raid.Guardian.start.y"), main.config.getDouble("setting.raid.Guardian.start.z"));

        return loc;
    }

    public static Location getBossGuardianSpawnLoc()
    {
        Location loc = new Location(Bukkit.getWorld(main.config.getString("setting.raid.Guardian.bossSpawn.world")), main.config.getDouble("setting.raid.Guardian.bossSpawn.x"), main.config.getDouble("setting.raid.Guardian.bossSpawn.y"), main.config.getDouble("setting.raid.Guardian.bossSpawn.z"));

        return loc;
    }
}
