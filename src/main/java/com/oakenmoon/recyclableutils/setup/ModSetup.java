package com.oakenmoon.recyclableutils.setup;

import com.oakenmoon.recyclableutils.blocks.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModSetup {

    public ItemGroup recyclableUtilsMain = new ItemGroup("RecyclableUtils"){
        @Override
        public ItemStack createIcon(){
            return new ItemStack(ModBlocks.GRANITEGENERATOR);
        }
    };

    public void init(){

    }
}
