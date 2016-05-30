/* 
 * Copyright (c) CovertJaguar, 2014 http://railcraft.info
 * 
 * This code is the property of CovertJaguar
 * and may only be used with explicit written
 * permission unless otherwise specified on the
 * license page at http://railcraft.info/wiki/info:license.
 */
package mods.railcraft.common.util.network;

import mods.railcraft.client.gui.GuiTicket;
import mods.railcraft.common.items.ItemTicketGold;
import mods.railcraft.common.util.misc.Game;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

public class PacketTicketGui extends RailcraftPacket {

    @Override
    public void writeData(RailcraftDataOutputStream data) throws IOException {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void readData(RailcraftDataInputStream data) throws IOException {
        try {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            ItemStack current = player.getCurrentEquippedItem();
            if (current != null && current.getItem() == ItemTicketGold.item)
                Minecraft.getMinecraft().displayGuiScreen(new GuiTicket(player, current));
        } catch (Exception exception) {
            Game.logThrowable("Error reading Golden Ticket Gui Packet", exception);
        }
    }

    @Override
    public int getID() {
        return PacketType.GOLDEN_TICKET_GUI.ordinal();
    }

}
