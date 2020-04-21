package com.oakenmoon.recyclableutils.setup;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

public class ServerProxy implements IProxy {

    @Override
    public World getClientWorld(){
         throw new IllegalStateException("Only run this on the client!");
    }
}
