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
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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

    private final Map<Block, Block> SMELT_MAP = Maps.newIdentityHashMap();

    protected int speed = 10;
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


        this.setCreativeTab(VarityAddon.tab);
        this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0))
                .withProperty(NORTH, Boolean.valueOf(false)).withProperty(EAST, Boolean.valueOf(false))
                .withProperty(SOUTH, Boolean.valueOf(false)).withProperty(WEST, Boolean.valueOf(false))
                .withProperty(UPPER, Boolean.valueOf(false)));
        this.setTickRandomly(true);
        this.init();
    }

    public void registerItemModel(Item itemBlock)
    {
        dartcraftReloaded.proxy.registerItemRenderer(itemBlock, 0, name);
    }

    public Item createItemBlock()
    {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

	/*
	@Override
	public void onNeighborChange(IBlockAccess world, BlockPos pos, BlockPos neighbor)
	{
		if (!this.canPlaceBlockAt((World) world, pos))
		{
			((World) world).setBlockToAir(pos);
			System.out.println("Oppos There arn't any block able to burn");
			return;
		} else
		{
			System.out.println("Yeah keepBurning");
		}
	}
	*/

    public void init()
    {
        setFireInfo(Blocks.GRASS, Blocks.DIRT);
        setFireInfo(Blocks.DIRT, Blocks.NETHERRACK);
        setFireInfo(Blocks.STONE, Blocks.NETHERRACK);
        setFireInfo(Blocks.REDSTONE_BLOCK, Blocks.GLOWSTONE);
    }

    public void setFireInfo(Block blockIn, Block blockOut)
    {
        if (blockIn == Blocks.AIR)
            throw new IllegalArgumentException("Tried to set air on fire... This is bad.");
        this.SMELT_MAP.put(blockIn, blockOut);
    }


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


		/*
		if (!worldIn.getGameRules().getBoolean("doFireTick"))
			return;

		if (!worldIn.isAreaLoaded(pos, 2))
			return; // Forge: prevent loading unloaded chunks when spreading fire

		if (!this.canPlaceBlockAt(worldIn, pos))
		{
			worldIn.setBlockToAir(pos);
			System.out.println("对面没有可以燃烧的障碍物");
			return;
		}
		else
		{
			System.out.println("是的，一直烧");
		}
		int i = ((Integer) state.getValue(AGE)).intValue();
		System.out.println("更新啦偶尔：:" + i);




		if (i < 1)
		{
			Block obj = worldIn.getBlockState(pos.down()).getBlock();

			System.out.println("现在冶炼块我们有没有冶炼" + obj);

			if (this.SMELT_MAP.get(obj) == null)
				return;
			worldIn.setBlockState(pos, this.SMELT_MAP.get(obj).getDefaultState());
			return;
		}
		*/

		/*
		//if (i < speed)
		if (i < 1)
		{
			state = state.withProperty(AGE, Integer.valueOf(i + rand.nextInt(3) / 2));
			worldIn.setBlockState(pos, state, 4);

			System.out.println("更新年龄:" + state.getValue(AGE));

		}
		else
		{
			Block obj = worldIn.getBlockState(pos.down()).getBlock();

			System.out.println("现在冶炼块我们有没有冶炼" + obj);

			if (this.SMELT_MAP.get(obj) == null)
				return;
			worldIn.setBlockState(pos, this.SMELT_MAP.get(obj).getDefaultState());
			return;
		}
		*/

        //System.out.println("着火");

        // this.tryCatchFire(worldIn, pos.east(), 300 , rand, i );
        // this.tryCatchFire(worldIn, pos.west(), 300 , rand, i );
        // this.tryCatchFire(worldIn, pos.down(), 250 , rand, i );
        // this.tryCatchFire(worldIn, pos.up(), 250 , rand, i );
        // this.tryCatchFire(worldIn, pos.north(), 300 , rand, i );
        // this.tryCatchFire(worldIn, pos.south(), 300 , rand, i );



        /*
         * for (int k = -1; k <= 1; ++k) { for (int l = -1; l <= 1; ++l) { for (int i1 =
         * -1; i1 <= 4; ++i1) { if (k != 0 || i1 != 0 || l != 0) { int j1 = 100;
         *
         * if (i1 > 1) { j1 += (i1 - 1) * 100; }
         *
         * BlockPos blockpos = pos.add(k, i1, l);
         *
         * if (k1 > 0) { int l1 = (k1 + 40 + worldIn.getDifficulty().getId() * 7) / (i +
         * 30); if (l1 > 0 && rand.nextInt(j1) <= l1 && (!worldIn.isRaining() )) { int
         * i2 = i + rand.nextInt(5) / 4;
         *
         * if (i2 > 15) { i2 = 15; }
         *
         * worldIn.setBlockState(blockpos, state.withProperty(AGE, Integer.valueOf(i2)),
         * 3); } } } } } }
         */

    }

    // 随机生成火
    private void tryCatchFire(World worldIn, BlockPos pos, int chance, Random random, int age)
    {
        int i = 20;
        if (random.nextInt(chance) < i)
        {
            if (random.nextInt(age + 10) < 5)
            {
                int j = age + random.nextInt(5) / 4;

                if (j > 15)
                {
                    j = 15;
                }

                worldIn.setBlockState(pos, this.getDefaultState().withProperty(AGE, Integer.valueOf(j)), 3);
            }

        }
    }

    private boolean canNeighborCatchFire(World worldIn, BlockPos pos)
    {
        //: EnumFacing.Plane.HORIZONTAL
        //for (EnumFacing enumfacing : EnumFacing.values())

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
        //boolean b = this.canNeighborCatchFire(worldIn, pos);
        boolean b = this.canNeighborCatchFire(worldIn, pos.down());
        //pos.down()

        System.out.println("can Place Block at? " + b);

        return b;
    }

    @Override
    public MapColor getMapColor(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        return MapColor.TNT;
    }

    // todo 发行时候记得删除掉注释

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(24) == 0) {
            worldIn.playSound((float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F,
                    (float) pos.getZ() + 0.5F, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS,
                    1.0F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.3F, false);
        }

        if (!worldIn.getBlockState(pos.down()).isSideSolid(worldIn, pos.down(), EnumFacing.UP)
                && !canCatchFire(worldIn, pos.down())) {
			/*
			if (canCatchFire(worldIn, pos.west())) {
				for (int j = 0; j < 2; ++j) {
					double d3 = (double) pos.getX() + rand.nextDouble() * 0.10000000149011612D;
					double d8 = (double) pos.getY() + rand.nextDouble();
					double d13 = (double) pos.getZ() + rand.nextDouble();
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d3, d8, d13, 0.0D, 0.0D, 0.0D);
				}
			}
			if (canCatchFire(worldIn, pos.east())) {
				for (int k = 0; k < 2; ++k) {
					double d4 = (double) (pos.getX() + 1) - rand.nextDouble() * 0.10000000149011612D;
					double d9 = (double) pos.getY() + rand.nextDouble();
					double d14 = (double) pos.getZ() + rand.nextDouble();
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d4, d9, d14, 0.0D, 0.0D, 0.0D);
				}
			}
			if (canCatchFire(worldIn, pos.north())) {
				for (int l = 0; l < 2; ++l) {
					double d5 = (double) pos.getX() + rand.nextDouble();
					double d10 = (double) pos.getY() + rand.nextDouble();
					double d15 = (double) pos.getZ() + rand.nextDouble() * 0.10000000149011612D;
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d5, d10, d15, 0.0D, 0.0D, 0.0D);
				}
			}
			if (canCatchFire(worldIn, pos.south())) {
				for (int i1 = 0; i1 < 2; ++i1) {
					double d6 = (double) pos.getX() + rand.nextDouble();
					double d11 = (double) pos.getY() + rand.nextDouble();
					double d16 = (double) (pos.getZ() + 1) - rand.nextDouble() * 0.10000000149011612D;
					worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d6, d11, d16, 0.0D, 0.0D, 0.0D);
				}
			}
			*/

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

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(AGE, Integer.valueOf(meta));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(AGE).intValue();
    }//ok

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AGE, NORTH, EAST, SOUTH, WEST, UPPER);
    }//ok

    /*
     * ================================= Forge Start
     * ======================================
     */

    public boolean canCatchFire(IBlockAccess world, BlockPos pos)
    {
		/*
		System.out.println("Current Block" + world.getBlockState(pos).getBlock().getRegistryName() + "Do we Have it?"
				+ (this.SMELT_MAP.get(world.getBlockState(pos).getBlock()) != null));
				*/


        if (world.getBlockState(pos).getBlock() == Blocks.AIR)
            return false;

        return this.SMELT_MAP.get(world.getBlockState(pos).getBlock()) != null;

		/*
		System.out.println("Current Block" + world.getBlockState(pos).getBlock().getRegistryName() + "Do we Have it?"
				+ (this.SMELT_MAP.get(world.getBlockState(pos).getBlock()) != null));


		if (world.getBlockState(pos).getBlock() == Blocks.AIR)
			return false;

		return this.SMELT_MAP.get(world.getBlockState(pos).getBlock()) != null;
		*/
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face)
    {
        return BlockFaceShape.UNDEFINED;
    }//ok
}