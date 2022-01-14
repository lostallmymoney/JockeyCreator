package jockeyCreator.mixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "removePassenger(Lnet/minecraft/entity/Entity;)V", at = @At("HEAD"))
    private void stopJockeySuffocating(Entity ridingEntity, CallbackInfo ci){
        Entity rideEntity = (Entity)(Object)this;
        if(ridingEntity != null && rideEntity.getType() == EntityType.CHICKEN && ( ridingEntity.getType() == EntityType.ZOMBIE || ridingEntity.getType() == EntityType.ZOMBIE_VILLAGER || ridingEntity.getType() == EntityType.ZOMBIFIED_PIGLIN) ){
            ridingEntity.noClip = false;
        }
    }
}
