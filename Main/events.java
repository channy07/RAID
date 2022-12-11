package Main;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class events implements Listener
{
    @EventHandler
    public void InventoryClick(InventoryClickEvent e)
    {
        Player p = (Player) e.getWhoClicked();
        Inventory inv = e.getInventory();
        String title = e.getView().getTitle();
        ItemStack item = e.getCurrentItem();


        if(title.equalsIgnoreCase("class select"))
        {
            if(item == null)
            {
                return;
            }

            e.setCancelled(true);

            if(item.getType() == Material.IRON_SWORD) //전사
            {
                if(game.game_player.containsKey(p.getName()))
                {
                    if(game.game_player.get(p.getName()).equalsIgnoreCase("warrior"))
                    {
                        p.sendMessage(main.label + "클래스 선택이 취소되었습니다");
                        class_manager.player_class.remove("warrior");
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                        return;
                    }
                }

                if(class_manager.player_class.containsKey("warrior"))
                {
                    p.sendMessage(main.label + "이미 선택된 클래스 입니다");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                    return;
                }
                else
                {
                    if(game.game_player.containsKey(p.getName()))
                    {
                        class_manager.player_class.remove(game.game_player.get(p.getName()));
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                    }

                    class_manager.player_class.put("warrior", p.getName());
                    p.sendMessage(main.label + "클래스가 '전사' 로 선택되었습니다");
                    class_manager.player_class.put("warrior", p.getName());
                    game.game_player.put(p.getName(), "warrior");
                    game.player_list.add(p);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                }
            }
            else if(item.getType() == Material.IRON_SHOVEL) //마법사
            {
                if(game.game_player.containsKey(p.getName()))
                {
                    if(game.game_player.get(p.getName()).equalsIgnoreCase("wizard"))
                    {
                        p.sendMessage(main.label + "클래스 선택이 취소되었습니다");
                        class_manager.player_class.remove("wizard");
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                        return;
                    }
                }

                if(class_manager.player_class.containsKey("wizard"))
                {
                    p.sendMessage(main.label + "이미 선택된 클래스 입니다");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                    return;
                }
                else
                {
                    if(game.game_player.containsKey(p.getName()))
                    {
                        class_manager.player_class.remove(game.game_player.get(p.getName()));
                        game.game_player.remove(p.getName());
                    }

                    p.sendMessage(main.label + "클래스가 '마법사' 로 선택되었습니다");
                    class_manager.player_class.put("wizard", p.getName());
                    game.game_player.put(p.getName(), "wizard");
                    game.player_list.add(p);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                }
            }
            else if(item.getType() == Material.IRON_AXE) //선봉대
            {
                if(game.game_player.containsKey(p.getName()))
                {
                    if(game.game_player.get(p.getName()).equalsIgnoreCase("tanker"))
                    {
                        p.sendMessage(main.label + "클래스 선택이 취소되었습니다");
                        class_manager.player_class.remove("tanker");
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                        return;
                    }
                }

                if(class_manager.player_class.containsKey("tanker"))
                {
                    p.sendMessage(main.label + "이미 선택된 클래스 입니다");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                    return;
                }
                else
                {
                    if(game.game_player.containsKey(p.getName()))
                    {
                        class_manager.player_class.remove(game.game_player.get(p.getName()));
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                    }

                    class_manager.player_class.put("tanker", p.getName());
                    p.sendMessage(main.label + "클래스가 '선봉대' 로 선택되었습니다");
                    class_manager.player_class.put("tanker", p.getName());
                    game.game_player.put(p.getName(), "tanker");
                    game.player_list.add(p);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                }
            }
            else if(item.getType() == Material.IRON_HOE) //지원가
            {
                if(game.game_player.containsKey(p.getName()))
                {
                    if(game.game_player.get(p.getName()).equalsIgnoreCase("supporter"))
                    {
                        p.sendMessage(main.label + "클래스 선택이 취소되었습니다");
                        class_manager.player_class.remove("supporter");
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                        p.closeInventory();
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                        return;
                    }
                }

                if(class_manager.player_class.containsKey("supporter"))
                {
                    p.sendMessage(main.label + "이미 선택된 클래스 입니다");
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);

                    return;
                }
                else
                {
                    if(game.game_player.containsKey(p.getName()))
                    {
                        class_manager.player_class.remove(game.game_player.get(p.getName()));
                        game.game_player.remove(p.getName());
                        game.player_list.remove(p);
                    }

                    class_manager.player_class.put("supporter", p.getName());
                    p.sendMessage(main.label + "클래스가 '지원가' 로 선택되었습니다");
                    class_manager.player_class.put("supporter", p.getName());
                    game.game_player.put(p.getName(), "supporter");
                    game.player_list.add(p);
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                }
            }
            else if(item.getType() == Material.EMERALD_BLOCK) //게임 시작
            {
                game.gameStart(p);
                p.closeInventory();
            }
        }
        else if(title.equalsIgnoreCase("setting boss"))
        {
            if(e.getSlot() == 0)
            {
                p.closeInventory();
                p.openInventory(UI.getGuardianSettingInventory());
            }
        }
        else if(title.equalsIgnoreCase("setting Guardian"))
        {
            if(e.getSlot() == 0)
            {
                setting.showSettingBlocks(p);
                e.setCancelled(true);
                p.closeInventory();

                setting.setting.put(p, "raid.Guardian.start");
            }
            else if(e.getSlot() == 1)
            {
                setting.showSettingBlocks(p);
                e.setCancelled(true);
                p.closeInventory();

                setting.setting.put(p, "raid.Guardian.bossSpawn");
            }
        }
    }

    @EventHandler
    public void PlayerQuit(PlayerQuitEvent e)
    {
        Player p = e.getPlayer();

        if(game.isOnGame == false)
        {
            if(game.game_player.containsKey(p.getName()))
            {
                class_manager.player_class.remove(game.game_player.get(p.getName()));
                game.game_player.remove(p.getName());
                game.player_list.remove(p);
            }
        }
    }

    @EventHandler
    public void PlayerInterect(PlayerInteractEvent e)
    {
        Player p = e.getPlayer();

        if(e.getHand() != EquipmentSlot.HAND)
        {
            return;
        }

        if(p.getItemInHand() != null)
        {
            ItemStack item = p.getItemInHand();

            if (item.getType() == Material.STICK && item.getItemMeta().getDisplayName().equalsIgnoreCase("setting"))
            {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR)
                {
                    if(setting.setting.containsKey(p))
                    {
                        if(setting.setting.get(p).equalsIgnoreCase("raid.Guardian.start"))
                        {
                            if(setting.enableSetting.get(p))
                            {
                                Block b = p.getTargetBlockExact(15);

                                main.config.set("setting.raid.Guardian.start.x", b.getLocation().getX());
                                main.config.set("setting.raid.Guardian.start.y", b.getLocation().getY() + 1);
                                main.config.set("setting.raid.Guardian.start.z", b.getLocation().getZ());
                                main.config.set("setting.raid.Guardian.start.world", b.getLocation().getWorld().getName());
                                Bukkit.getPluginManager().getPlugin("RAID").saveDefaultConfig();

                                setting.setting.remove(p);

                                p.sendMessage(main.label + "레이드 시작점이 x:" + b.getLocation().getX() + " y:" + b.getLocation().clone().add(0, 1, 0).getY() + " z:" + b.getLocation().getZ() + " 로 설정되었습니다.");
                            }
                            else
                            {
                                setting.setting.remove(p);

                                p.sendMessage(main.label + "이곳에는 설정이 불가능합니다.");
                            }
                        }
                        else if(setting.setting.get(p).equalsIgnoreCase("raid.Guardian.bossSpawn"))
                        {
                            if(setting.enableSetting.get(p))
                            {
                                Block b = p.getTargetBlockExact(15);

                                main.config.set("setting.raid.Guardian.bossSpawn.x", b.getLocation().getX());
                                main.config.set("setting.raid.Guardian.bossSpawn.y", b.getLocation().getY() + 1);
                                main.config.set("setting.raid.Guardian.bossSpawn.z", b.getLocation().getZ());
                                main.config.set("setting.raid.Guardian.bossSpawn.world", b.getLocation().getWorld().getName());
                                Bukkit.getPluginManager().getPlugin("RAID").saveDefaultConfig();

                                setting.setting.remove(p);

                                p.sendMessage(main.label + "보스 스폰지점이 x:" + b.getLocation().getX() + " y:" + b.getLocation().clone().add(0, 1, 0).getY() + " z:" + b.getLocation().getZ() + " 로 설정되었습니다.");
                            }
                            else
                            {
                                setting.setting.remove(p);

                                p.sendMessage(main.label + "이곳에는 설정이 불가능합니다.");
                            }
                        }
                    }
                    else
                    {
                        p.openInventory(UI.getBossSettingInventory());
                    }
                }
            }
        }
    }
}