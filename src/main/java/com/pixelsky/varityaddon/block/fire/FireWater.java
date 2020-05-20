package com.pixelsky.varityaddon.block.fire;

import com.pixelsky.varityaddon.block.BaseFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FireWater extends BaseFire {
    public FireWater(String name) {
        super(name);
    }

    @Override
    public void init() {
        SMELT_MAP.put(Blocks.WATER,Blocks.ICE);
        SMELT_MAP.put(Blocks.DEADBUSH,Blocks.WATER);
        SMELT_MAP.put(Blocks.SAND,Blocks.DIRT);
        SMELT_MAP.put(Blocks.LAPIS_BLOCK,Blocks.SEA_LANTERN);
        SMELT_MAP.put(Blocks.SANDSTONE,Blocks.SNOW);
    }
    @Override
    public void onEntityCollision(World world, BlockPos pos, IBlockState state, Entity entityIn) {
        if(world.isRemote)
            return;
        if(entityIn instanceof EntityMob){
            entityIn.setDead();
            EntitySnowman snowman=new EntitySnowman(world);
            snowman.setPosition(pos.getX(),pos.getY(),pos.getZ());
            world.spawnEntity(snowman);
            world.setBlockToAir(pos);
        }else if (entityIn instanceof EntitySnowman){
           EntitySnowman snown=(EntitySnowman)entityIn;
            snown.addPotionEffect(new PotionEffect(Potion.getPotionById(6),1000,1));
            snown.addPotionEffect(new PotionEffect(Potion.getPotionById(5),1000,5));
            snown.addPotionEffect(new PotionEffect(Potion.getPotionById(1),1000,3));
            snown.addPotionEffect(new PotionEffect(Potion.getPotionById(8),1000,3));
            world.setBlockToAir(pos);
        }

    }
}
