package com.pixelsky.varityaddon.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BaseFluid extends BlockFluidClassic {
BaseFire fire;

    public BaseFluid(Fluid fluid,BaseFire fire) {
        super(fluid, Material.WATER);
        this.fire=fire;
        this.setRegistryName("fluidForce");
        this.setTranslationKey("fluid_force");
    }

    @Override
    public void updateTick(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state, @Nonnull Random rand) {
        super.updateTick(world, pos, state, rand);
        if(fire.canNeighborCatchFire(world,pos.up())){
            world.setBlockState(pos.up(),fire.getDefaultState());
        }
    }

    @Override
    public void onEntityCollision(World world,BlockPos pos, IBlockState state, Entity entityIn) {
    fire.onEntityCollision(world,pos,state,entityIn);
    }
}