package net.justacoder.communitymade.mixin;

import net.justacoder.communitymade.damage.CustomDamageTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.network.NetworkThreadUtils;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.listener.TickablePacketListener;
import net.minecraft.network.packet.s2c.play.EntityDamageS2CPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ClientPlayNetworkHandlerMixin implements TickablePacketListener, ClientPlayPacketListener {

    @Shadow @Final private MinecraftClient client;
    @Shadow private ClientWorld world;

    @Override
    public void onEntityDamage(EntityDamageS2CPacket packet) {
        NetworkThreadUtils.forceMainThread(packet, this, client);
        Entity entity = world.getEntityById(packet.entityId());
        if (entity == null) {
            return;
        }
        try {
            entity.onDamaged(packet.createDamageSource(world));
        } catch (Exception e) {
            entity.onDamaged(CustomDamageTypes.UNKNOWN_CAUSE); // prevent brick from going into pipi- ... error.
        }
    }

}
