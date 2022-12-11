package Main.Class;

import Main.class_manager;
import Main.game;
import Main.main;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class class_supporter extends class_manager implements Listener
{
    public static int attack_cooldown = 15;

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

            if(game.game_player.get(p.getName()).equalsIgnoreCase("supporter"))
            {
                if(item.getType() == Material.IRON_HOE)
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

        if(cooldown_list.contains(p.getName() + ".supporter.attack"))
        {
            //p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".supporter.attack")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".supporter.attack");
        cooldown_manager.put(p.getName() + ".supporter.attack", attack_cooldown);

    }

    private void right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".supporter.right_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".supporter.right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".supporter.right_click");
        cooldown_manager.put(p.getName() + ".supporter.right_click", right_click_cooldown);

    }

    private void shift_left_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".supporter.shift_left_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".supporter.shift_left_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".supporter.shift_left_click");
        cooldown_manager.put(p.getName() + ".supporter.shift_left_click", shift_left_click_cooldown);

    }

    private void shift_right_click(Player p)
    {
        if(!isEnableSkill(p))
        {
            return;
        }

        if(cooldown_list.contains(p.getName() + ".supporter.shift_right_click"))
        {
            p.sendMessage(main.label + "-  - 의 쿨타임이 " + cooldown_manager.get(p.getName() + ".supporter.shift_right_click")/20 + "초 남았습니다");
            return;
        }

        cooldown_list.add(p.getName() + ".supporter.shift_right_click");
        cooldown_manager.put(p.getName() + ".supporter.shift_right_click", shift_right_click_cooldown);

    }
}