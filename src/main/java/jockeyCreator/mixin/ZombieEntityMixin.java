package jockeyCreator.mixin;

import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

import static net.minecraft.entity.EntityType.CHICKEN;
import static net.minecraft.item.Items.EGG;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Inject(method= "canPickupItem(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
    private void createJokey(ItemStack stack, CallbackInfoReturnable<Boolean> cir){
        ZombieEntity zzz = (ZombieEntity)(Object)this;
        if(stack.isOf(EGG) && zzz.isBaby() && !zzz.hasVehicle())
        {
            stack.decrement(1);
            ChickenEntity ccc = CHICKEN.create(zzz.getWorld());
            if (ccc != null) {
                ccc.refreshPositionAndAngles(zzz.getX(), zzz.getY(), zzz.getZ(), zzz.getYaw(), 0.0f);
                ccc.initialize(Objects.requireNonNull(zzz.getServer()).getWorld(zzz.getEntityWorld().getRegistryKey()), zzz.getEntityWorld().getLocalDifficulty(zzz.getBlockPos()), SpawnReason.JOCKEY, null, null);
                ccc.setHasJockey(true);
                ccc.setSprinting(true);
                zzz.noClip=true; //stop zombies from clipping
                zzz.startRiding(ccc);
                zzz.getWorld().spawnEntity(ccc);
            }

            cir.setReturnValue(false);
        }
    }


}
