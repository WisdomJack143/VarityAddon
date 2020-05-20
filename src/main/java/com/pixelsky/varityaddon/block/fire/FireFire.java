package com.pixelsky.varityaddon.block.fire;

import com.pixelsky.varityaddon.block.BaseFire;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class FireFire extends BaseFire {
    public FireFire(String name) {
        super(name);
    }

    @Override
    public void init() {

    }
    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        Block obj = worldIn.getBlockState(pos.down()).getBlock();

        if (!canCatchFire(worldIn,pos))
            return;
       ItemStack im= FurnaceRecipes.instance().getSmeltingResult(new ItemStack(worldIn.getBlockState(pos).getBlock()));
       if(im.getItem() instanceof ItemBlock){
           worldIn.setBlockState(pos, ((ItemBlock)im.getItem()).getBlock().getDefaultState());
       }else {
           EntityItem item=new EntityItem(worldIn);
           item.setItem(im);
           item.setPosition(pos.getX(),pos.getY(),pos.getZ());
           worldIn.spawnEntity(item);
       }
        return;
    }
    @Override
    public boolean canCatchFire(IBlockAccess world, BlockPos pos) {
      return FurnaceRecipes.instance().getSmeltingResult(new ItemStack(world.getBlockState(pos).getBlock()))!=null;
    }
}
