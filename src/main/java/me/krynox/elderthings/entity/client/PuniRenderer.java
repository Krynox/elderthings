package me.krynox.elderthings.entity.client;

import me.krynox.elderthings.entity.PuniEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class PuniRenderer extends GeoEntityRenderer<PuniEntity> {
    public PuniRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PuniModel());
        this.shadowRadius = 0.5f;
        this.scaleHeight = 2f;
        this.scaleWidth = 2f;
    }


}
