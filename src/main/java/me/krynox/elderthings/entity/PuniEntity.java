package me.krynox.elderthings.entity;

import me.krynox.elderthings.ElderThings;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.ArrayList;

import static net.minecraft.world.entity.HumanoidArm.RIGHT;

public class PuniEntity extends Mob implements GeoEntity {
    private static final EntityDataAccessor<Byte> GROWTH = SynchedEntityData.defineId(EggEntity.class, EntityDataSerializers.BYTE);
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Iterable<ItemStack> armorSlots = new ArrayList<>(); // lmao why is this required

    public PuniEntity(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public Iterable<ItemStack> getArmorSlots() {
        return armorSlots;
    }

    @Override
    public ItemStack getItemBySlot(EquipmentSlot pSlot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {

    }

    @Override
    public HumanoidArm getMainArm() {
        return RIGHT;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(GROWTH, (byte) 0);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //a boring controller that doesn't do anything. Makes sense, eggs aren't smart.
        controllers.add(DefaultAnimations.genericIdleController(this));
        /*
        controllers.add(new AnimationController<PuniEntity>(this, "controller", (x) -> {
            x.getController().setAnimation(RawAnimation.begin().thenLoop("animation.Puni.Idle"));
            return PlayState.CONTINUE;
        }));

         */
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}
