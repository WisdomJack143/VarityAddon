package com.pixelsky.varityaddon.block;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BaseTorch extends BlockTorch implements ITileEntityProvider {

    private String name;

    public BaseTorch(String name) {
        this.setSoundType(SoundType.WOOD);
        this.setLightLevel(0.9375F);
        this.setTranslationKey(name);
        this.setRegistryName(name);
        this.name = name;
        this.setCreativeTab(dartcraftReloaded.creativeTab);
    }

    public void registerItemModel(Item itemBlock) {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTimeTorch();
    }
}
