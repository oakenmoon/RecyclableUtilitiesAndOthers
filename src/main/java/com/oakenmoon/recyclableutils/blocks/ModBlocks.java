package com.oakenmoon.recyclableutils.blocks;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("recyclableutils:granitegenerator")
    public static GraniteGenerator GRANITEGENERATOR;

    @ObjectHolder("recyclableutils:granitegenerator")
    public static TileEntityType<GraniteGeneratorTile> GRANITEGENERATOR_TILE;
}
