package ru.spark.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import ru.spark.gui.listener.EventListener;
import ru.spark.gui.predicate.NotNeedCloseMenuPredicate;
import ru.spark.gui.predicate.SimpleNotNeedCloseMenuPredicate;
import ru.spark.gui.processor.DefaultMenuOpenProcessor;
import ru.spark.gui.processor.MenuOpenProcessor;
import ru.spark.gui.session.MenuSessionResolver;
import ru.spark.gui.session.SimpleMenuSessionResolver;
import ru.spark.gui.template.MenuTemplate;
import ru.spark.gui.window.MenuWindowBuilder;

public class MenuManager {

    private final Plugin plugin;

    private final MenuSessionResolver sessionResolver;

    public MenuManager(Plugin plugin) {
        this(plugin, DefaultMenuOpenProcessor.INSTANCE);
    }

    public MenuManager(Plugin plugin, MenuOpenProcessor menuOpenProcessor) {
        this(plugin, new SimpleNotNeedCloseMenuPredicate(plugin), menuOpenProcessor);
    }

    public MenuManager(Plugin plugin, NotNeedCloseMenuPredicate notNeedCloseMenuPredicate) {
        this(plugin, notNeedCloseMenuPredicate, DefaultMenuOpenProcessor.INSTANCE);
    }

    public MenuManager(Plugin plugin, NotNeedCloseMenuPredicate notNeedCloseMenuPredicate, MenuOpenProcessor menuOpenProcessor) {
        this(plugin, notNeedCloseMenuPredicate, new SimpleMenuSessionResolver(notNeedCloseMenuPredicate, menuOpenProcessor));
    }

    public MenuManager(Plugin plugin, NotNeedCloseMenuPredicate notNeedCloseMenuPredicate, MenuSessionResolver sessionResolver) {
        Bukkit.getPluginManager().registerEvents(
                new EventListener(
                        this.sessionResolver = sessionResolver,
                        notNeedCloseMenuPredicate
                ),
                this.plugin = plugin
        );
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public MenuSessionResolver getSessionResolver() {
        return this.sessionResolver;
    }

    public MenuWindowBuilder createWindowBuilder() {
        return new MenuWindowBuilder(this.sessionResolver);
    }

    public void open(Player player, MenuTemplate menuTemplate) {
        this.sessionResolver.getSessionByPlayer(player).open(menuTemplate);
    }

    public void open(Player player, MenuTemplate menuTemplate, String windowId) {
        this.sessionResolver.getSessionByPlayer(player).open(menuTemplate, windowId);
    }

}
