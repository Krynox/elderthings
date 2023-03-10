package me.krynox.elderthings.entity;

import me.krynox.elderthings.ElderThings;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.object.PlayState;

import java.util.ArrayList;

import static net.minecraft.world.entity.HumanoidArm.RIGHT;

public class EggEntity extends LivingEntity implements GeoAnimatable {
    private static final EntityDataAccessor<Byte> GROWTH = SynchedEntityData.defineId(EggEntity.class, EntityDataSerializers.BYTE);
    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private final Iterable<ItemStack> armorSlots = new ArrayList<>(); // lmao why is this required

    public EggEntity(EntityType<? extends LivingEntity> pEntityType, Level pLevel) {
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
    public InteractionResult interact(Player pPlayer, InteractionHand pHand) {

        if(!pPlayer.getLevel().isClientSide) {
            byte g = entityData.get(GROWTH);
            entityData.set(GROWTH, (byte) (g+1));
            ElderThings.LOGGER.info("TEST egg clicked, data=" + entityData.get(GROWTH));
            return InteractionResult.SUCCESS;
        }


        return InteractionResult.PASS;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        entityData.define(GROWTH, (byte) 0);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        //a boring controller that doesn't do anything. Makes sense, eggs aren't smart.
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
