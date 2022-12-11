package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class UI
{
    public static ItemStack makeItem(String name, Material material, int amount)
    {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);
        item.setItemMeta(itemMeta);

        return item;
    }

    public static Inventory getMenuInventory()
    {
        Inventory inv = Bukkit.createInventory(null, 5*9, "class select");

        for(int i=0; i<=8; i++)
        {
            inv.setItem(i, makeItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1));
            inv.setItem(i+36, makeItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1));
        }

        for(int i=9; i<=27; i+=9)
        {
            inv.setItem(i, makeItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1));
            inv.setItem(i+8, makeItem(" ", Material.BLACK_STAINED_GLASS_PANE, 1));
        }

        inv.setItem(20, makeItem("전사", Material.IRON_SWORD, 1));
        inv.setItem(21, makeItem("마법사", Material.IRON_SHOVEL, 1));
        inv.setItem(23, makeItem("선봉대", Material.IRON_AXE, 1));
        inv.setItem(24, makeItem("지원가", Material.IRON_HOE, 1));

        if(class_manager.player_class.containsKey("warrior"))
        {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setDisplayName(class_manager.player_class.get("warrior"));
            skull.setOwner(class_manager.player_class.get("warrior"));
            item.setItemMeta(skull);

            inv.setItem(29, item);
        }

        if(class_manager.player_class.containsKey("wizard"))
        {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setDisplayName(class_manager.player_class.get("wizard"));
            skull.setOwner(class_manager.player_class.get("wizard"));
            item.setItemMeta(skull);

            inv.setItem(30, item);
        }

        if(class_manager.player_class.containsKey("tanker"))
        {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setDisplayName(class_manager.player_class.get("tanker"));
            skull.setOwner(class_manager.player_class.get("tanker"));
            item.setItemMeta(skull);

            inv.setItem(32, item);
        }

        if(class_manager.player_class.containsKey("supporter"))
        {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
            SkullMeta skull = (SkullMeta) item.getItemMeta();
            skull.setDisplayName(class_manager.player_class.get("supporter"));
            skull.setOwner(class_manager.player_class.get("supporter"));
            item.setItemMeta(skull);

            inv.setItem(33, item);
        }

        inv.setItem(22, makeItem(ChatColor.GREEN + "게임 시작", Material.EMERALD_BLOCK, 1));

        return inv;
    }

    public static Inventory getBossSettingInventory()
    {
        Inventory inv = Bukkit.createInventory(null, 1*9, "setting boss");

        inv.setItem(0, makeItem("GUARDIAN", Material.PRISMARINE_BRICKS, 1));

        return inv;
    }

    public static Inventory getGuardianSettingInventory()
    {
        Inventory inv = Bukkit.createInventory(null, 1*9, "setting Guardian");

        inv.setItem(0, makeItem("레이드 시작점", Material.GRASS_BLOCK, 1));
        inv.setItem(1, makeItem("보스 스폰 지점", Material.REDSTONE_BLOCK, 1));

        return inv;
    }
}