package com.github.ysbbbbbb.kaleidoscopecookery.block.crop;

import com.github.ysbbbbbb.kaleidoscopecookery.init.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class LettuceCropBlock extends BaseCropBlock {
    public LettuceCropBlock() {
        super(ModItems.LETTUCE, ModItems.LETTUCE_SEED);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(AGE) >= this.getMaxAge()) {
            // 10% 几率掉落猪儿虫
            if (level.getRandom().nextInt(10) == 0) {
                Block.popResource(level, pos, ModItems.CATERPILLAR.get().getDefaultInstance());
            }
            Block.popResource(level, pos, this.result.get().getDefaultInstance());
            super.onUseBreakCrop(level, pos, 0);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
