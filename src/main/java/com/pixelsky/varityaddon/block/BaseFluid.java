package com.pixelsky.varityaddon.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class BaseFluid extends BlockFluidClassic {

    public BaseFluid() {
        super(fluidForce, fluidForce.getMaterial());
        this.setRegistryName("fluidForce");
        this.setTranslationKey("fluid_force");
    }

    public void onEntityCollision(BlockPos pos, IBlockState state, Entity entityIn) {
        if (entityIn instanceof EntityLivingBase) {
            ((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 10, 10));
        }
    }
}