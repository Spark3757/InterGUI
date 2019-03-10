package ru.spark.gui.utils;

import ru.spark.gui.item.MenuItemClick;
import ru.spark.gui.template.MenuTemplate;

import java.util.function.Consumer;

public class MenuItemUtils {

    public static Consumer<MenuItemClick> moveToPage(int page) {
        return moveToPage(page == 0 ? MenuTemplate.MAIN_WINDOW_ID : String.valueOf(page));
    }

    public static Consumer<MenuItemClick> moveToPage(String windowId) {
        return (click) -> {
            MenuTemplate menuTemplate = click.getMenuSession().getActiveMenuTemplate();

            click.getMenuSession().open(menuTemplate, windowId);
        };
    }

}
