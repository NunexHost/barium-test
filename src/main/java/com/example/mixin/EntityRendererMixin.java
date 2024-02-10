package com.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {

    @Shadow @Final private EntityRenderDispatcher entityRenderDispatcher;

    @Inject
    public EntityRendererMixin(EntityRenderer entityRenderer) {
        // ...
    }

    @Overwrite
    public void render(Entity entity, double x, double y, double z, float partialTicks, float tickDelta) {
        // Chamada da função original
        super.render(entity, x, y, z, partialTicks, tickDelta);

        // Opção: Desabilitar renderização de entidades distantes
        if (entity.getDistanceSq(this.entityRenderDispatcher.getCamera().getPos()) > 256.0D) {
            return;
        }

        // ...
    }
}
