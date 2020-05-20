package com.pixelsky.varityaddon.block.fire;

import com.pixelsky.varityaddon.block.BaseFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireWind extends BaseFire {
    public FireWind(String name) {
        super(name);
    }

    @Override
    public void init() {
        SMELT_MAP.put(Blocks.GRASS,Blocks.DIRT);
        SMELT_MAP.put(Blocks.DIRT,Blocks.SAND);
        SMELT_MAP.put(Blocks.COAL_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.IRON_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.GOLD_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.DIAMOND_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.QUARTZ_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.EMERALD_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.LAPIS_ORE,Blocks.STONE);
        SMELT_MAP.put(Blocks.STONE,Blocks.COBBLESTONE);
        SMELT_MAP.put(Blocks.WATER,Blocks.AIR);
        SMELT_MAP.put(Blocks.GRASS,Blocks.AIR);
    }
    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entityIn) {
        if(world.isRemote)
            return;
        if(entityIn instanceof EntityLivingBase){
            ((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(20)));
            world.setBlockToAir(pos);
        }
    }
}
