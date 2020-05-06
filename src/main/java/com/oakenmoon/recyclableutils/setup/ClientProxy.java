package com.oakenmoon.recyclableutils.setup;

import com.oakenmoon.recyclableutils.blocks.GraniteGeneratorScreen;
import com.oakenmoon.recyclableutils.blocks.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class ClientProxy implements IProxy {

    @Override
    public void init(){
        ScreenManager.registerFactory(ModBlocks.GRANITEGENERATOR_CONTAINER, GraniteGeneratorScreen::new);
    }

    @Override
    public World getClientWorld(){
        return Minecraft.getInstance().world;
    }

    @Override
    public PlayerEntity getClientPlayer(){
        return Minecraft.getInstance().player;
    }
}
