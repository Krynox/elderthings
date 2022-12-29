package me.krynox.elderthings.entity;

import me.krynox.elderthings.ElderThings;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;

public class EggEntity extends Entity implements GeoAnimatable {
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public EggEntity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {
        if(!pPlayer.getLevel().isClientSide) {
            ElderThings.LOGGER.info("TEST egg clicked");
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag pCompound) {

    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //a boring controller that doesn't do anything
        controllers.add(new AnimationController<EggEntity>(this, "controller", (x) -> PlayState.CONTINUE));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public double getTick(Object object) {
        return 0;
    }
}
