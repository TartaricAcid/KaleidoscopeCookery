package com.github.ysbbbbbb.kaleidoscopecookery.inventory.tooltip;

import net.minecraft.core.NonNullList;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public record ItemContainerTooltip(NonNullList<ItemStack> handler) implements TooltipComponent {
}
