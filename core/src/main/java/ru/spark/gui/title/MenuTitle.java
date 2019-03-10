package ru.spark.gui.title;

import ru.spark.gui.session.MenuSession;

public interface MenuTitle {

    String DEFAULT_TITLE = "InterGUI";

    String getTitle(MenuSession menuSession);

}
