package me.krynox.elderthings.entity.client;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.entity.PuniEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.model.GeoModel;

public class PuniModel extends GeoModel<PuniEntity> {
    @Override
    public ResourceLocation getModelResource(PuniEntity animatable) {
        return ElderThings.resLoc("geo/puni.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(PuniEntity animatable) {
        return ElderThings.resLoc("textures/entity/puni.png");
    }

    @Override
    public ResourceLocation getAnimationResource(PuniEntity animatable) {
        return ElderThings.resLoc("animations/puni_idle.animation.json");
    }
}
