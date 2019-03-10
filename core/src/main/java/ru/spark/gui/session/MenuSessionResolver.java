package ru.spark.gui.session;

import org.bukkit.entity.Player;
import ru.spark.gui.window.MenuWindow;
import ru.spark.gui.template.MenuTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public interface MenuSessionResolver {

    MenuSession getSessionByPlayer(Player player);

    Collection<MenuSession> getSessions();

    Collection<MenuSession> findSessionsByTemplate(MenuTemplate menuTemplate, Collection<MenuSession> exclude);

    Collection<MenuSession> findSessionsByWindow(MenuWindow menuWindow, Collection<MenuSession> exclude);

    void inactiveSession(Player player);

    default Collection<MenuSession> findSessionsByTemplate(MenuTemplate menuTemplate, MenuSession... exclude) {
        return this.findSessionsByTemplate(menuTemplate,
                exclude.length == 0 ? Collections.emptyList() : Arrays.asList(exclude)
        );
    }

    default Collection<MenuSession> findSessionsByWindow(MenuWindow menuWindow, MenuSession... exclude) {
        return this.findSessionsByWindow(menuWindow, Arrays.asList(exclude));
    }

}
