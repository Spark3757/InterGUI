package ru.spark.gui;

import ru.spark.gui.session.MenuSession;

public interface Permissible {

    boolean canView(MenuSession menuSession);

}
