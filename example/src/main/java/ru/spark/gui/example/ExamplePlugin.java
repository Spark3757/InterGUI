package ru.spark.gui.example;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import ru.spark.gui.MenuManager;
import ru.spark.gui.icon.SimpleMenuIcon;
import ru.spark.gui.item.*;
import ru.spark.gui.processor.SeamlessMenuOpenProcessorFactory;
import ru.spark.gui.template.MenuTemplate;
import ru.spark.gui.template.PaginatedMenuTemplate;
import ru.spark.gui.template.SingleMenuTemplate;
import ru.spark.gui.utils.ItemStackBuilder;
import ru.spark.gui.window.MenuWindow;

import java.util.concurrent.atomic.AtomicBoolean;

public class ExamplePlugin extends JavaPlugin {

    private MenuManager menuManager;

    private MenuTemplate menuTemplate;

    private PaginatedMenuTemplate paginatedMenuTemplate;

    @Override
    public void onEnable() {
        this.menuManager = new MenuManager(this, SeamlessMenuOpenProcessorFactory.get());
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        MenuWindow mainWindow = this.menuManager.createWindowBuilder()
                .setTitle("Типа главное окно, пон да?")
                .addItems(StaticMenuItem.builder().setMenuIcon(new SimpleMenuIcon(0, new ItemStack(Material.STONE)))
                        .withClickHandler(ClickActions.navigateAction("xz"))
                        .build()
                ).build();
        MenuWindow playersInfoWindow = this.menuManager.createWindowBuilder()
                .setTitle(menuSession -> "Привет, " + menuSession.getOwner().getName())
                .addItems(PerPlayerMenuItem.builder().setIconRequest(menuSession -> {
                    return new SimpleMenuIcon(3, ItemStackBuilder.create()
                            .setMaterial(Material.STONE)
                            .withItemMeta().setName("Твой ник: " + menuSession.getOwner()
                                    .getName())
                            .and().build()
                    );
                }).build())
                .build();

        this.paginatedMenuTemplate = PaginatedMenuTemplate.builder(mainWindow)
                .registerWindow("xz", playersInfoWindow)
                .build();

        this.getCommand("menu").setExecutor((sender, command, label, args) -> {
            this.menuManager.open((Player) sender, this.paginatedMenuTemplate);

            return true;
        });
    }

}
