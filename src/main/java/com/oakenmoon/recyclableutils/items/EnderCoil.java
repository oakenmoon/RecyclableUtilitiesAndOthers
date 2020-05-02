package com.oakenmoon.recyclableutils.items;

import com.oakenmoon.recyclableutils.RecyclableUtils;
import net.minecraft.item.Item;

public class EnderCoil extends Item {

    public EnderCoil(){
        super(new Properties()
                .maxStackSize(64)
                .group(RecyclableUtils.setup.recyclableUtilsMain));
        setRegistryName("endercoil");
    }
}
