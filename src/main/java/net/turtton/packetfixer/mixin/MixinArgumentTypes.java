package net.turtton.packetfixer.mixin;

import com.mojang.brigadier.arguments.ArgumentType;
import net.minecraft.command.argument.ArgumentTypes;
import net.minecraft.command.argument.serialize.ArgumentSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArgumentTypes.class)
public class MixinArgumentTypes {
    @Inject(
            method = "register(Ljava/lang/String;Ljava/lang/Class;Lnet/minecraft/command/argument/serialize/ArgumentSerializer;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private static <T extends ArgumentType<?>> void disableTestArgumentTypes(String id, Class<T> argClass, ArgumentSerializer<T> serializer, CallbackInfo ci) {
        if (id.startsWith("test")) {
            ci.cancel();
        }
    }
}
