package ru.spark.gui.template;

import ru.spark.gui.item.MenuItem;
import ru.spark.gui.session.MenuSession;
import ru.spark.gui.title.MenuTitle;
import ru.spark.gui.type.MenuType;
import ru.spark.gui.window.MenuWindow;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractMenuTemplate implements MenuTemplate {

    @Override
    public Optional<MenuWindow> findWindow(String id) {
        return Optional.ofNullable(this.getAllWindows().get(id));
    }

    @Override
    public MenuType getMenuType() {
        return this.getRootWindow().getMenuType();
    }

    @Override
    public MenuTitle getTitle() {
        return this.getRootWindow().getTitle();
    }

    @Override
    public List<MenuItem> getItems() {
        return this.getRootWindow().getItems();
    }

    @Override
    public boolean canView(MenuSession menuSession) {
        return this.getRootWindow().canView(menuSession);
    }

    @Override
    public boolean update() {
        return this.getRootWindow().update();
    }

    @Override
    public Consumer<MenuSession> getOpenHandler() {
        return this.getRootWindow().getOpenHandler();
    }

    public abstract MenuWindow getRootWindow();

}
