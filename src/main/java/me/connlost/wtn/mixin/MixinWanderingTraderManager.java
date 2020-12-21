package me.connlost.wtn.mixin;

import me.connlost.wtn.WTN;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.poi.PointOfInterestStorage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;


@Mixin(WanderingTraderManager.class)
public abstract class MixinWanderingTraderManager {

    @Shadow
    private int spawnChance;
    @Shadow
    private int spawnTimer;
    @Shadow
    private int spawnDelay;


    @Inject(method = "method_18018", at=@At(value = "INVOKE",target = "net/minecraft/entity/passive/WanderingTraderEntity.setPositionTarget(Lnet/minecraft/util/math/BlockPos;I)V"),locals = LocalCapture.CAPTURE_FAILSOFT)
    private void onSpawn(ServerWorld serverWorld, CallbackInfoReturnable<Boolean> cir, PlayerEntity playerEntity, BlockPos blockPos, int i, PointOfInterestStorage pointOfInterestStorage, Optional optional, BlockPos blockPos2, BlockPos blockPos3, WanderingTraderEntity wanderingTraderEntity){
        WTN.notify(wanderingTraderEntity, playerEntity);
    }

//    @Inject(method = "spawn", at=@At("HEAD"))
//    private void setChance(ServerWorld world, boolean spawnMonsters, boolean spawnAnimals, CallbackInfoReturnable<Integer> cir){
////        this.spawnChance = 25;
//        this.spawnDelay = -1;
//        this.spawnTimer = -1;
//    }
}
