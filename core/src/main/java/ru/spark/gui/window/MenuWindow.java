package ru.spark.gui.window;

import ru.spark.gui.Permissible;
import ru.spark.gui.Updatable;
import ru.spark.gui.item.MenuItem;
import ru.spark.gui.title.MenuTitle;
import ru.spark.gui.type.MenuType;
import ru.spark.gui.session.MenuSession;

import java.util.List;
import java.util.function.Consumer;

public interface MenuWindow extends Permissible, Updatable {

    MenuType getMenuType();

    MenuTitle getTitle();

    List<MenuItem> getItems();

    Consumer<MenuSession> getOpenHandler();

}
