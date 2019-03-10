package ru.spark.gui.processor.v1_12;

import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_12_R1.event.CraftEventFactory;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftContainer;
import org.bukkit.craftbukkit.v1_12_R1.inventory.CraftInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import ru.spark.gui.processor.AbstractSeamlessMenuOpenProcessor;

@SuppressWarnings("Duplicates")
public class SeamlessMenuOpenProcessor extends AbstractSeamlessMenuOpenProcessor {

    @Override
    public void openSeamless(Player bukkitPlayer, Inventory bukkitInventory) {
        IInventory iinventory = ((CraftInventory) bukkitInventory).getInventory();
        EntityPlayer player = ((CraftPlayer) bukkitPlayer).getHandle();
        Container container = CraftEventFactory.callInventoryOpenEvent(player, new ContainerChest(player.inventory, iinventory, player));

        if (container != null) {
            if (player.activeContainer != null) {
                CraftEventFactory.handleInventoryCloseEvent(player);
                player.activeContainer.b(player);
            }

            int containerCounter = player.nextContainerCounter();

            player.playerConnection.sendPacket(new PacketPlayOutOpenWindow(
                    containerCounter,
                    CraftContainer.getNotchInventoryType(bukkitInventory.getType()),
                    new ChatComponentText(bukkitInventory.getTitle()),
                    bukkitInventory.getSize()
            ));
            player.activeContainer = container;
            player.activeContainer.windowId = containerCounter;
            player.activeContainer.addSlotListener(player);
        }
    }

}
