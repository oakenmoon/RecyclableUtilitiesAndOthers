package com.oakenmoon.recyclableutils.items;

import com.oakenmoon.recyclableutils.RecyclableUtils;
import net.minecraft.item.Item;

public class FieryCoil extends Item {

    public FieryCoil(){
        super(new Properties()
                .maxStackSize(64)
                .group(RecyclableUtils.setup.recyclableUtilsMain));
        setRegistryName("fierycoil");
    }
}
