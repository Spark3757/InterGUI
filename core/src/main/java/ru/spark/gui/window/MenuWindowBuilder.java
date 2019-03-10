package ru.spark.gui.window;

import org.bukkit.event.inventory.InventoryType;
import ru.spark.gui.item.MenuItem;
import ru.spark.gui.session.MenuSession;
import ru.spark.gui.session.MenuSessionResolver;
import ru.spark.gui.title.MenuTitle;
import ru.spark.gui.title.PerPlayerMenuTitle;
import ru.spark.gui.title.StaticMenuTitle;
import ru.spark.gui.type.MenuType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MenuWindowBuilder {

    private final MenuSessionResolver menuSessionResolver;

    private MenuTitle title;

    private MenuType type;

    private Predicate<MenuSession> canViewChecker = (menuSession -> true);

    private List<MenuItem> items = new ArrayList<>();

    private Consumer<MenuSession> openHandler = (menuSession -> {});

    public MenuWindowBuilder(MenuSessionResolver menuSessionResolver) {
        this.menuSessionResolver = menuSessionResolver;
    }

    public MenuWindowBuilder setMenuTitle(MenuTitle title) {
        this.title = title;

        return this;
    }

    public MenuWindowBuilder setTitle(String title) {
        return this.setMenuTitle(new StaticMenuTitle(title));
    }

    public MenuWindowBuilder setTitle(Function<MenuSession, String> titleFunction) {
        return this.setMenuTitle(new PerPlayerMenuTitle(titleFunction));
    }

    public MenuWindowBuilder setType(MenuType type) {
        this.type =  type;

        return this;
    }

    public MenuWindowBuilder setRows(int rows) {
        return this.setSize(rows * 9);
    }

    public MenuWindowBuilder setSize(int size) {
        return this.setType(MenuType.ofSize(size));
    }

    public MenuWindowBuilder setInventoryType(InventoryType inventoryType) {
        return this.setType(MenuType.ofInventoryType(inventoryType));
    }

    public MenuWindowBuilder withViewChecker(Predicate<MenuSession> canViewChecker) {
        this.canViewChecker = this.canViewChecker.and(canViewChecker);

        return this;
    }

    public MenuWindowBuilder withOpenHandler(Consumer<MenuSession> openHandler) {
        this.openHandler = openHandler;

        return this;
    }

    public MenuWindowBuilder setItems(List<MenuItem> items) {
        this.items = items;

        return this;
    }

    public MenuWindowBuilder setItems(MenuItem... items) {
        return this.setItems(new ArrayList<>(Arrays.asList(items)));
    }

    public MenuWindowBuilder addItems(List<MenuItem> items) {
        this.items.addAll(items);

        return this;
    }

    public MenuWindowBuilder addItems(MenuItem... items) {
        return this.addItems(Arrays.asList(items));
    }

    public MenuWindow build() {
        if (this.title == null) {
            this.title = new StaticMenuTitle(MenuTitle.DEFAULT_TITLE);
        }

        if (this.type == null) {
            this.type = MenuType.ofSize(9);
        }

        return new SimpleMenuWindow(
                this.menuSessionResolver,
                this.title,
                this.type,
                this.canViewChecker,
                this.openHandler,
                this.items
        );
    }

}
