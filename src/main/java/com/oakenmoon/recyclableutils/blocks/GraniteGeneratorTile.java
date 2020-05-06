package com.oakenmoon.recyclableutils.blocks;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.oakenmoon.recyclableutils.blocks.ModBlocks.GRANITEGENERATOR_TILE;

public class GraniteGeneratorTile extends TileEntity implements ITickableTileEntity {

    private ItemStackHandler itemHandler;

    //burnableItems set holds all the possibilities for items burnable for power.
    //madeItemFilter determines whether the item filter was already made for that tile entity, so it runs once instead of every tick.
    private boolean madeItemFilter = false;
    private Set<Item> burnableItems;



    public GraniteGeneratorTile() {
        super(GRANITEGENERATOR_TILE);
    }

    @Override
    public void tick() {
        if(!madeItemFilter){
            //Executes once or twice on placement/world restart
            Item[] itemTypeArray = {Items.GRANITE, Items.GRANITE_SLAB, Items.GRANITE_STAIRS, Items.GRANITE_WALL,
                                    Items.POLISHED_GRANITE, Items.POLISHED_GRANITE_SLAB, Items.POLISHED_GRANITE_STAIRS};
            burnableItems = new HashSet<>(Arrays.asList(itemTypeArray));
            System.out.println("GraniteGenerator: Made item filter set.");
            madeItemFilter = true;
        }
    }

    //Item handler stuff
    public void read(CompoundNBT tag) {
        CompoundNBT nbt = tag.getCompound("inventory");
        getHandler().deserializeNBT(nbt);
        super.read(tag);
    }

    public CompoundNBT write(CompoundNBT tag){
        CompoundNBT nbt = getHandler().serializeNBT();
        tag.put("inventory", nbt);
        return super.write(tag);
    }

    private ItemStackHandler getHandler(){
        if(itemHandler == null){
            itemHandler = new ItemStackHandler(1){
                //isItemValid may be unnecessary? Kept for probable good practice purposes.
                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack){
                    return !burnableItems.contains(stack.getItem());
                }

                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate){
                    if(!burnableItems.contains(stack.getItem())){
                        return stack;
                    }
                    return super.insertItem(slot, stack, simulate);
                }
            };
        }
        return itemHandler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(() -> (T) getHandler());
        }
        return super.getCapability(cap, null);
    }
}
