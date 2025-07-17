package com.github.ysbbbbbb.kaleidoscopecookery.inventory.tooltip;

import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.neoforged.neoforge.items.IItemHandler;

public record ItemContainerTooltip(IItemHandler handler) implements TooltipComponent {
}
