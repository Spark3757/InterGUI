package ru.spark.gui.animation;

import ru.spark.gui.session.MenuSession;

import java.util.Map;

public interface AnimationScript {

    String getName();

    void load();

    void invoke(MenuSession menuSession, Map<String, Object> args);

    boolean isWatching(MenuSession menuSession, Integer slot);

}
