package com.github.ysbbbbbb.kaleidoscopecookery.util;

import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.Nullable;

public final class CarpetColor {
    public static Item getCarpetByColor(DyeColor color) {
        return switch (color) {
            case WHITE -> Items.WHITE_CARPET;
            case ORANGE -> Items.ORANGE_CARPET;
            case MAGENTA -> Items.MAGENTA_CARPET;
            case LIGHT_BLUE -> Items.LIGHT_BLUE_CARPET;
            case YELLOW -> Items.YELLOW_CARPET;
            case LIME -> Items.LIME_CARPET;
            case PINK -> Items.PINK_CARPET;
            case GRAY -> Items.GRAY_CARPET;
            case LIGHT_GRAY -> Items.LIGHT_GRAY_CARPET;
            case CYAN -> Items.CYAN_CARPET;
            case PURPLE -> Items.PURPLE_CARPET;
            case BLUE -> Items.BLUE_CARPET;
            case BROWN -> Items.BROWN_CARPET;
            case GREEN -> Items.GREEN_CARPET;
            case RED -> Items.RED_CARPET;
            case BLACK -> Items.BLACK_CARPET;
        };
    }

    @Nullable
    public static DyeColor getColorByCarpet(Item item) {
        if (item == Items.WHITE_CARPET) {
            return DyeColor.WHITE;
        }
        if (item == Items.ORANGE_CARPET) {
            return DyeColor.ORANGE;
        }
        if (item == Items.MAGENTA_CARPET) {
            return DyeColor.MAGENTA;
        }
        if (item == Items.LIGHT_BLUE_CARPET) {
            return DyeColor.LIGHT_BLUE;
        }
        if (item == Items.YELLOW_CARPET) {
            return DyeColor.YELLOW;
        }
        if (item == Items.LIME_CARPET) {
            return DyeColor.LIME;
        }
        if (item == Items.PINK_CARPET) {
            return DyeColor.PINK;
        }
        if (item == Items.GRAY_CARPET) {
            return DyeColor.GRAY;
        }
        if (item == Items.LIGHT_GRAY_CARPET) {
            return DyeColor.LIGHT_GRAY;
        }
        if (item == Items.CYAN_CARPET) {
            return DyeColor.CYAN;
        }
        if (item == Items.PURPLE_CARPET) {
            return DyeColor.PURPLE;
        }
        if (item == Items.BLUE_CARPET) {
            return DyeColor.BLUE;
        }
        if (item == Items.BROWN_CARPET) {
            return DyeColor.BROWN;
        }
        if (item == Items.GREEN_CARPET) {
            return DyeColor.GREEN;
        }
        if (item == Items.RED_CARPET) {
            return DyeColor.RED;
        }
        if (item == Items.BLACK_CARPET) {
            return DyeColor.BLACK;
        }
        return null;
    }
}
