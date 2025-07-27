package com.github.ysbbbbbb.kaleidoscopecookery.event;

import com.github.ysbbbbbb.kaleidoscopecookery.advancements.critereon.ModEventTriggerType;
import com.github.ysbbbbbb.kaleidoscopecookery.init.ModTrigger;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;

public class HoeUseEvent {
    public static void register() {
        UseBlockCallback.EVENT.register(HoeUseEvent::onUseBlock);
    }

    private static InteractionResult onUseBlock(Player player, Level level, InteractionHand hand, BlockHitResult hitResult) {
        BlockPos pos = hitResult.getBlockPos();
        ItemStack stack = player.getItemInHand(hand);

        // 判断是否为锄头
        if (!(stack.getItem() instanceof HoeItem)) {
            return InteractionResult.PASS;
        }

        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        // 判断目标方块是否为泥土/草方块等
        if (!(block == Blocks.DIRT || block == Blocks.GRASS_BLOCK || block == Blocks.DIRT_PATH)) {
            return InteractionResult.PASS;
        }

        // 判断方块上方是否为水或含水
        BlockPos above = pos.above();
        FluidState fluidState = level.getFluidState(above);
        boolean isWater = fluidState.is(FluidTags.WATER);

        if (!isWater) {
            return InteractionResult.PASS;
        }

        // 替换为耕地
        if (!level.isClientSide) {
            level.setBlockAndUpdate(pos, Blocks.FARMLAND.defaultBlockState());
            level.playSound(null, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            stack.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(hand));
            ModTrigger.EVENT.trigger(player, ModEventTriggerType.USE_HOE_ON_WATER_FIELD);
        }
        return InteractionResult.SUCCESS;
    }
}
