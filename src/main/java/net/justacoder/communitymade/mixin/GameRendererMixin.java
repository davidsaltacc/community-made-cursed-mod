package net.justacoder.communitymade.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(method = "renderWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;multiply(Lorg/joml/Quaternionf;)V", ordinal = 3, shift = At.Shift.AFTER))
    private void onRenderWorld(float tickDelta, long limitTime, MatrixStack matrices, CallbackInfo ci) {
        float time = System.currentTimeMillis() % 2000 / 1000f;
        matrices.multiply(new Quaternionf().rotationX((float) Math.sin(time * Math.PI * 2) * .04f));
        matrices.multiply(new Quaternionf().rotationZ((float) Math.cos(time * Math.PI * 2) * .04f));
    }
}
