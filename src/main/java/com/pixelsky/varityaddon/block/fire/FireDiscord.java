package com.pixelsky.varityaddon.block.fire;

import com.pixelsky.varityaddon.block.BaseFire;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireDiscord extends BaseFire {
    public FireDiscord(String name) {
        super(name);
    }

    @Override
    public void init() {
    SMELT_MAP.put(Blocks.GLASS,Blocks.NETHERRACK);
    SMELT_MAP.put(Blocks.DIRT,Blocks.NETHERRACK);
        SMELT_MAP.put(Blocks.NETHERRACK, Blocks.STONE);
        SMELT_MAP.put(Blocks.STONE,Blocks.STONEBRICK);
        SMELT_MAP.put(Blocks.GRAVEL,Blocks.SOUL_SAND);
        //discord
        SMELT_MAP.put(Blocks.SAND,Blocks.AIR);
        SMELT_MAP.put(Blocks.WOOL,Blocks.ICE);
        SMELT_MAP.put(Blocks.REEDS,Blocks.COBBLESTONE_WALL);
        SMELT_MAP.put(Blocks.NETHER_WART,Blocks.GRAVEL);
        SMELT_MAP.put(Blocks.ORANGE_SHULKER_BOX,Blocks.LEVER);
    }
    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entityIn) {
        if(world.isRemote)
            return;
        if(entityIn instanceof EntityMob){
            ((EntityMob)entityIn).setAIMoveSpeed(2);
            entityIn.setGlowing(true);
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(1),1000));
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(14),1000));
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(5),1000));
            ((EntityMob) entityIn).addPotionEffect(new PotionEffect(Potion.getPotionById(8),1000,4));
            entityIn.setFire(100);
            entityIn.addVelocity(entityIn.getLookVec().x,entityIn.getLookVec().y,entityIn.getLookVec().z);
        world.setBlockToAir(pos);
        }

    }
}
