package me.krynox.elderthings.entity.client;

import me.krynox.elderthings.ElderThings;
import me.krynox.elderthings.entity.EggEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class EggModel extends GeoModel<EggEntity> {
    @Override
    public ResourceLocation getModelResource(EggEntity animatable) {
        return ElderThings.resLoc("geo/egg.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(EggEntity animatable) {
        return ElderThings.resLoc("textures/entity/egg.png");
    }

    @Override
    public ResourceLocation getAnimationResource(EggEntity animatable) {
        return null;
    }
}
