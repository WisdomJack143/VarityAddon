package com.pixelsky.varityaddon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = References.modId, name = References.name, version = References.version)
public class VarityAddon{
    public static CreativeTabs tab=new CreativeTabs("多彩物品") {
        @Override
        public ItemStack getTabIconItem() {
            return null;
        }
    };



}
