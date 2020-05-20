package com.pixelsky.varityaddon.block.fire;

import com.pixelsky.varityaddon.block.BaseFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireOrder extends BaseFire {
    public FireOrder(String name) {
        super(name);
    }

    @Override
    public void init() {
        SMELT_MAP.put(Blocks.COAL_ORE,Blocks.COAL_BLOCK);
        SMELT_MAP.put(Blocks.IRON_ORE,Blocks.IRON_BLOCK);
        SMELT_MAP.put(Blocks.GOLD_ORE,Blocks.GOLD_BLOCK);
        SMELT_MAP.put(Blocks.DIAMOND_ORE,Blocks.DIAMOND_BLOCK);
        SMELT_MAP.put(Blocks.QUARTZ_ORE,Blocks.QUARTZ_BLOCK);
        SMELT_MAP.put(Blocks.EMERALD_ORE,Blocks.EMERALD_BLOCK);
        SMELT_MAP.put(Blocks.LAPIS_ORE,Blocks.LAPIS_BLOCK);
        SMELT_MAP.put(Blocks.ICE,Blocks.FROSTED_ICE);
        SMELT_MAP.put(Blocks.SAND,Blocks.SANDSTONE);
    }
    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entityIn) {
        if(world.isRemote)
            return;
        if(entityIn instanceof EntityLivingBase){
            entityIn.getArmorInventoryList().forEach(a->{
                a.damageItem(-100, (EntityLivingBase) entityIn);
            });
                ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(14)));
            world.setBlockToAir(pos);
        }

    }
}
