package me.connlost.wtn;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.MessageType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import net.minecraft.util.math.Vec3d;
import net.minecraft.village.TradeOffer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WTN implements ModInitializer{
    public static MinecraftServer server;
    public static final Logger LOG = LogManager.getLogger();


    @Override
    public void onInitialize() {

    }

    public static void notify(WanderingTraderEntity e, PlayerEntity p){
        if (server == null){
            LOG.error("NO SERVER!");
            return;
        }
        Vec3d pos = e.getPos();
        int x = (int) pos.x;
        int y = (int) pos.y;
        int z = (int) pos.z;

        TranslatableText text = new TranslatableText(
                "chat.type.announcement",
                "流浪商人",
                "GKD出来交易 ["+x+", "+y+", "+z+"]:"
        );
        for (TradeOffer offer : e.getOffers()){
            text.append(offer.getSellItem().toHoverableText());
        }
        server.getPlayerManager().broadcastChatMessage(text, MessageType.SYSTEM, Util.NIL_UUID);

        e.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING,1000,1));
    }

    public static void setServer(MinecraftServer s){
        server = s;
    }
}
