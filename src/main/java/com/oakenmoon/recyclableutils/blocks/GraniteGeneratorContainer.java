package com.oakenmoon.recyclableutils.blocks;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

import javax.annotation.Nullable;

import static com.oakenmoon.recyclableutils.blocks.ModBlocks.GRANITEGENERATOR_CONTAINER;

public class GraniteGeneratorContainer extends Container {

    private TileEntity tileEntity;
    private PlayerEntity playerEntity;
    private IItemHandler playerInventoryHandler;

    public GraniteGeneratorContainer(int id, BlockPos pos, World world, PlayerInventory playerInventory, PlayerEntity player) {
        super(GRANITEGENERATOR_CONTAINER, id);
        tileEntity = world.getTileEntity(pos);
        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY);
        this.playerEntity = player;
        this.playerInventoryHandler = new InvWrapper(playerInventory);

        tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
            addSlot(new SlotItemHandler(h, 0, 80, 36));
        });
        layoutPlayerInventorySlots(8, 84);
    }


    //-Adding slots to the GUI-
    //Convenience methods for adding lots of slots at once.
    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx){
        for(int i = 0; i < amount; i++){
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int xAmount, int yAmount, int dx, int dy){
        for(int i = 0; i < yAmount; i++){
            addSlotRange(handler, index, x, y, xAmount, dx);
            y += dy;
            index += xAmount;
        }
        return index;
    }

    //Lays out the player's inventory in the GUI.
    private void layoutPlayerInventorySlots(int leftCol, int topRow){
        //Player inventory
        addSlotBox(playerInventoryHandler, 9, leftCol, topRow, 9, 3, 18, 18);

        //Hotbar
        topRow += 58;
        addSlotRange(playerInventoryHandler, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos()), playerEntity, ModBlocks.GRANITEGENERATOR);
    }
}
