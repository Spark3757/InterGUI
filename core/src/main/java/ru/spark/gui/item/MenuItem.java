package ru.spark.gui.item;

import ru.spark.gui.Permissible;
import ru.spark.gui.icon.MenuIcon;
import ru.spark.gui.session.MenuSession;

import java.util.function.Consumer;

public interface MenuItem extends Permissible {

    MenuIcon getIcon(MenuSession menuSession);

    Consumer<MenuItemClick> getClickHandler();

}
