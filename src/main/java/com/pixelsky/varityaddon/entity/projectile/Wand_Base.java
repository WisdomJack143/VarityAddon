package com.pixelsky.varityaddon.entity.projectile;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public abstract class Wand_Base extends EntityFireball {
    private static final DataParameter<Boolean> INVULNERABLE = EntityDataManager
            .<Boolean>createKey(Wand_Base.class, DataSerializers.BOOLEAN);

    public Wand_Base(World worldIn) {
        super(worldIn);
        this.setSize(0.3125F, 0.3125F);
    }

    public Wand_Base(World worldIn, EntityLivingBase shooter, double accelX, double accelY, double accelZ) {
        super(worldIn, shooter, accelX, accelY, accelZ);
        this.setSize(0.3125F, 0.3125F);
    }

    public static void registerFixesCheeseBall(DataFixer fixer) {
        EntityFireball.registerFixesFireball(fixer, "Cheese");
    }

    /**
     * Return the motion factor for this projectile. The factor is multiplied by
     * the original motion.
     */
    protected float getMotionFactor() {
        return this.isInvulnerable() ? 0.73F : super.getMotionFactor();
    }

    @SideOnly(Side.CLIENT)
    public Wand_Base(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ,
                            EntityLivingBase shooter) {
        super(worldIn, x, y, z, accelX, accelY, accelZ);
        this.shootingEntity = shooter;
        this.setSize(0.3125F, 0.3125F);
    }

    /**
     * Returns true if the entity is on fire. Used by render to add the fire
     * effect on rendering.
     */
    public boolean isBurning() {
        return false;
    }

    /**
     * Explosion resistance of a block relative to this entity
     */
    public float getExplosionResistance(Explosion explosionIn, World worldIn, BlockPos pos, IBlockState blockStateIn) {
        float f = super.getExplosionResistance(explosionIn, worldIn, pos, blockStateIn);
        Block block = blockStateIn.getBlock();

        if (this.isInvulnerable() && EntityWither.canDestroyBlock(block)) {
            f = Math.min(0.8F, f);
        }

        return f;
    }


    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected abstract void onHit(RayTraceResult result);
    protected void onImpact(RayTraceResult result) {
        onHit(result);
        /*
        if (!this.world.isRemote && this.shootingEntity != result.entityHit) {
            if (result.entityHit != null) {
                if (this.shootingEntity != null) {
                    if (result.entityHit.attackEntityFrom(DamageSource.causeMobDamage(this.shootingEntity), 8.0F)) {
                        if (result.entityHit.isEntityAlive()) {
                            this.applyEnchantments(this.shootingEntity, result.entityHit);
                        } else {
                            this.shootingEntity.heal(5.0F);
                        }
                    }
                } else {
                    result.entityHit.attackEntityFrom(DamageSource.MAGIC, 5.0F);
                }
                if (result.entityHit instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) result.entityHit;
                    world.setBlockState(player.getPosition(), CheeseBlocks.QUICK_CHEESE.getDefaultState());
                }
            }

            EntityAreaEffectCloud cloud = new EntityAreaEffectCloud(world, this.posX, this.posY, this.posZ);
            cloud.setColor(0x9E881E);
            cloud.setPotion(PotionType.getPotionTypeForName("minecraft:instant_damage"));
            cloud.setDuration(100);
            world.spawnEntity(cloud);

        }
        */
        this.setDead();
    }

    /**
     * Returns true if other Entities should be prevented from moving through
     * this Entity.
     */
    public boolean canBeCollidedWith() {
        return false;
    }

    protected void entityInit() {
        this.dataManager.register(INVULNERABLE, Boolean.valueOf(false));
    }

    /**
     * Return whether this skull comes from an invulnerable (aura) wither boss.
     */
    public boolean isInvulnerable() {
        return ((Boolean) this.dataManager.get(INVULNERABLE)).booleanValue();
    }

    /**
     * Set whether this skull comes from an invulnerable (aura) wither boss.
     */
    public void setInvulnerable(boolean invulnerable) {
        this.dataManager.set(INVULNERABLE, Boolean.valueOf(invulnerable));
    }

    protected boolean isFireballFiery() {
        return false;
    }
}
