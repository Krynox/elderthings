package me.krynox.elderthings.entity.client;

import me.krynox.elderthings.entity.EggEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EggRenderer extends GeoEntityRenderer<EggEntity> {
    public EggRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new EggModel());
        this.shadowRadius = 0.5f;
        this.scaleHeight = 9f;
        this.scaleWidth = 9f;
    }


}
