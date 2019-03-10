package ru.spark.gui.session;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import ru.spark.gui.Updatable;
import ru.spark.gui.item.MenuItem;
import ru.spark.gui.window.MenuWindow;
import ru.spark.gui.template.MenuTemplate;

import java.util.Map;
import java.util.Optional;

public interface MenuSession extends InventoryHolder, Updatable {

    String getTitle();

    Map<Integer, MenuItem> getItems();

    Optional<MenuItem> getItemByIndex(int index);

    MenuTemplate getActiveMenuTemplate();

    MenuWindow getActiveWindow();

    Player getOwner();

    boolean open(MenuTemplate menuTemplate, String windowId);

    default boolean open(MenuTemplate menuTemplate) {
        return this.open(menuTemplate, MenuTemplate.MAIN_WINDOW_ID);
    }

    void close();

    boolean update(int index);

    void moveItem(int from, int to);

}
