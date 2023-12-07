package net.justacoder.communitymade.mixin;

import net.minecraft.predicate.TagPredicate;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TagPredicate.class)
public abstract class TagPredicateMixin<T> {

    @Inject(method = "test", at = @At("HEAD"), cancellable = true)
    private void onTest(RegistryEntry<T> registryEntry, CallbackInfoReturnable<Boolean> cir) {
        if (registryEntry == null) {
            cir.setReturnValue(false); // prevent brick going into pipi-, I mean prevent error
        }
    }

}
