package Main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class commands_tap implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command c, String label, String[] args)
    {
        if(c.getName().equalsIgnoreCase("raid"))
        {
            if(args.length == 1)
            {
                List<String> commands = new ArrayList<String>();
                commands.add("menu");
                commands.add("resetcd");
                commands.add("setting_stick");

                return commands;
            }
        }

        return null;
    }

}
