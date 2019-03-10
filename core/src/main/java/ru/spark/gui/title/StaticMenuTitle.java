package ru.spark.gui.title;

import ru.spark.gui.session.MenuSession;

public class StaticMenuTitle implements MenuTitle {

    private String title;

    public StaticMenuTitle(String title) {
        this.title = title;
    }

    @Override
    public String getTitle(MenuSession menuSession) {
        return this.title;
    }

}
