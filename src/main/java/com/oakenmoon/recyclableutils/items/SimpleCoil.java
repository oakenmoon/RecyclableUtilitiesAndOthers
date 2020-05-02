package com.oakenmoon.recyclableutils.items;

import com.oakenmoon.recyclableutils.RecyclableUtils;
import net.minecraft.item.Item;

public class SimpleCoil extends Item {

    public SimpleCoil(){
        super(new Item.Properties()
                .maxStackSize(64)
                .group(RecyclableUtils.setup.recyclableUtilsMain));
        setRegistryName("simplecoil");
    }
}
