package Main;

import net.minecraft.server.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import Main.Boss.Guardian.boss_Guardian;

public class commands implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command c, String label, String[] args)
    {
        Player p = (Player) sender;

        if(p instanceof Player)
        {
            if(c.getName().equalsIgnoreCase("raid"))
            {
                if(args[0].equalsIgnoreCase("test"))
                {
                    boss_Guardian.spawnBoss();
                }
                else if(args[0].equalsIgnoreCase("menu"))
                {
                    p.openInventory(UI.getMenuInventory());
                }
                else if(args[0].equalsIgnoreCase("resetcd"))
                {
                    class_manager.cooldown_manager.clear();
                    class_manager.cooldown_list.clear();
                }
                else if(args[0].equalsIgnoreCase("setting_stick"))
                {
                    p.getInventory().addItem(UI.makeItem("setting", Material.STICK, 1));
                }
            }
        }
        return false;
    }
}
