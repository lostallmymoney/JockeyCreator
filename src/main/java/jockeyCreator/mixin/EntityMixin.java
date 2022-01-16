package jockeyCreator.mixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.entity.EntityType.*;


@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "removePassenger(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void makeZombiesClipAgain(Entity ridingEntity, CallbackInfo ci){
        Entity rideEntity = (Entity)(Object)this;
        EntityType ridingEntityType = ridingEntity.getType();
        if ((ridingEntityType == ZOMBIE) || (ridingEntityType == ZOMBIE_VILLAGER) || (ridingEntityType == ZOMBIFIED_PIGLIN))
            if ((ridingEntity != null) && (rideEntity.getType() == CHICKEN)) ridingEntity.noClip = false;
    }

    @Inject(method= "tickRiding()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;updatePassengerPosition(Lnet/minecraft/entity/Entity;)V", shift = At.Shift.AFTER))
    private void makeJockeysNoClipOnRiding(CallbackInfo ci){
        Entity zzz = (Entity)(Object)this;
        if((zzz.getVehicle().getType() == CHICKEN) && ! zzz.noClip && ((zzz.getType() == ZOMBIE) || (zzz.getType() == ZOMBIE_VILLAGER) || (zzz.getType() == ZOMBIFIED_PIGLIN))){
            zzz.noClip = true;
            zzz.getVehicle().setSprinting(true);
        }
    }
}
