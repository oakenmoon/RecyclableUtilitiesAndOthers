package com.oakenmoon.recyclableutils.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class GraniteGenerator extends Block {

    public GraniteGenerator() {
        super(Properties.create(Material.IRON)
                .sound(SoundType.METAL)
                .hardnessAndResistance(2.0f));
        setRegistryName("granitegenerator");
    }
}
