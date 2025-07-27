package com.github.ysbbbbbb.kaleidoscopecookery.advancements.critereon;

import com.github.ysbbbbbb.kaleidoscopecookery.KaleidoscopeCookery;
import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.LivingEntity;

public class ModEventTrigger extends SimpleCriterionTrigger<ModEventTrigger.Instance> {
    public static final ResourceLocation ID = new ResourceLocation(KaleidoscopeCookery.MOD_ID, "mod_event");

    public static ModEventTrigger.Instance create(String eventName) {
        return new ModEventTrigger.Instance(eventName);
    }

    @Override
    protected ModEventTrigger.Instance createInstance(JsonObject json, ContextAwarePredicate entityPredicate, DeserializationContext conditionsParser) {
        String eventName = GsonHelper.getAsString(json, "event");
        return new ModEventTrigger.Instance(eventName);
    }

    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(LivingEntity user, String eventName) {
        if (user instanceof ServerPlayer serverPlayer) {
            super.trigger(serverPlayer, instance -> instance.matches(eventName));
        }
    }

    public static class Instance extends AbstractCriterionTriggerInstance {
        private final String eventName;

        public Instance(String eventName) {
            super(ID, ContextAwarePredicate.ANY);
            this.eventName = eventName;
        }

        public boolean matches(String eventNameIn) {
            return this.eventName.equals(eventNameIn);
        }

        @Override
        public JsonObject serializeToJson(SerializationContext context) {
            JsonObject jsonObject = super.serializeToJson(context);
            jsonObject.addProperty("event", this.eventName);
            return jsonObject;
        }
    }
}
