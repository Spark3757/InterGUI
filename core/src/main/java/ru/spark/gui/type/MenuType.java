package ru.spark.gui.type;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import ru.spark.gui.session.MenuSession;

public interface MenuType {

    Inventory createInventory(MenuSession menuSession, String title);

    static MenuType ofSize(int size) {
        return new CommonMenuType(size);
    }

    static MenuType ofInventoryType(InventoryType inventoryType) {
        return new MenuTypeAsInventoryType(inventoryType);
    }

}
