package com.pixelsky.varityaddon.block.fire;

import com.pixelsky.varityaddon.block.BaseFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireEarth  extends BaseFire {
    public FireEarth(String name) {
        super(name);
    }

    @Override
    public void init() {
        SMELT_MAP.put( Blocks.RED_FLOWER, Blocks.CARROTS);
        SMELT_MAP.put( Blocks.YELLOW_FLOWER, Blocks.POTATOES);
        SMELT_MAP.put(Blocks.REEDS,Blocks.WHEAT);
        SMELT_MAP.put(Blocks.DIRT,Blocks.FARMLAND);
        SMELT_MAP.put(Blocks.GRASS,Blocks.FARMLAND);
        SMELT_MAP.put(Blocks.OBSIDIAN,Blocks.WATER);
        SMELT_MAP.put(Blocks.LAVA,Blocks.OBSIDIAN);
    }
    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entityIn) {
        if(world.isRemote)
            return;
        if(entityIn instanceof EntityMob){
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(9),1000));
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(18),1000));
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(2),1000));
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(12),1000));
            entityIn.setGlowing(true);
            entityIn.setNoGravity(true);
            world.setBlockToAir(pos);

        }else if(entityIn instanceof EntityAgeable) {
          EntityAgeable child=  ((EntityAgeable) entityIn).createChild((EntityAgeable) entityIn);
            ((EntityAgeable)entityIn).setGrowingAge(0);
            child.setPosition(entityIn.getPosition().getX(),entityIn.getPosition().getY(),entityIn.getPosition().getZ());
            world.spawnEntity(child);
            world.setBlockToAir(pos);
        }

    }
}
