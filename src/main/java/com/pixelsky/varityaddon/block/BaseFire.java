package com.pixelsky.varityaddon.block;

import com.google.common.collect.Maps;
import com.pixelsky.varityaddon.VarityAddon;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;
import java.util.Random;

public abstract class BaseFire extends Block
{
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 15);


    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UPPER = PropertyBool.create("up");
    public static  Map<Block,Block> SMELT_MAP=Maps.newIdentityHashMap();

    protected String name;

    public BaseFire(String name)
    {
        super(Material.FIRE);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.name = name;


        this.setHardness(0.0F);
        this.setLightLevel(1.0F);
        this.setSoundType(SoundType.CLOTH);
        this.disableStats();

        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0))
                .withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false))
                .withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false))
                .withProperty(UPPER, Boolean.valueOf(false)));
        this.setTickRandomly(true);
        this.init();
    }

    public abstract void init();


    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public int quantityDropped(Random random) {
        return 0;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
    {
        Block obj = worldIn.getBlockState(pos.down()).getBlock();

        System.out.println("现在冶炼块我们有没有冶炼" + obj);

        if (this.SMELT_MAP.get(obj) == null)
            return;
        worldIn.setBlockState(pos, this.SMELT_MAP.get(obj).getDefaultState());
        return;
    }


    public boolean canNeighborCatchFire(World worldIn, BlockPos pos)
    {
        for (EnumFacing enumfacing : EnumFacing.values())
        {
            //if (this.canCatchFire(worldIn, pos.offset(enumfacing)))
            if (this.canCatchFire(worldIn, pos.down().offset(enumfacing)))
            {
                return true;
            }
        }

        return false;
    }


    @Override
    public boolean isCollidable()
    {
        return false;
    }


    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        boolean b = this.canNeighborCatchFire(worldIn, pos.down());
        return b;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.TNT;
    }


    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(24) == 0) {
            worldIn.playSound((float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F,
                    (float) pos.getZ() + 0.5F, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS,
                    1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }

        if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)
                && !canCatchFire(worldIn, pos.down())) {
            if (canCatchFire(worldIn, pos.up())) {
                for (int j1 = 0; j1 < 2; ++j1) {
                    double d7 = (double) pos.getX() + rand.nextDouble();
                    double d12 = (double) (pos.getY() + 1) - rand.nextDouble() * 0.10000000149011612D;
                    double d17 = (double) pos.getZ() + rand.nextDouble();
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d7, d12, d17, 0.0D, 0.0D, 0.0D);
                }
            }
        } else {
            for (int i = 0; i < 3; ++i) {
                double d0 = (double) pos.getX() + rand.nextDouble();
                double d1 = (double) pos.getY() + rand.nextDouble() * 0.5D + 0.5D;
                double d2 = (double) pos.getZ() + rand.nextDouble();
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
            }
        }
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE).intValue();
    }//ok

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE, NORTH, EAST, SOUTH, WEST, UPPER);
    }
    public boolean canCatchFire(IBlockAccess world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock() == Blocks.AIR)
            return false;

        return this.SMELT_MAP.get(world.getBlockState(pos).getBlock()) != null;
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }//ok
}