package me.connlost.wtn.mixin;

import me.connlost.wtn.WTN;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(method = "loadWorld", at=@At(value = "RETURN"))
    private void onLoad(CallbackInfo ci){
        WTN.setServer((MinecraftServer) (Object) this);
    }
}
