/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2016
 http://railcraft.info

 This code is the property of CovertJaguar
 and may only be used with explicit written
 permission unless otherwise specified on the
 license page at http://railcraft.info/wiki/info:license.
 -----------------------------------------------------------------------------*/
package mods.railcraft.common.items;

import mods.railcraft.api.core.IVariantEnum;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import mods.railcraft.common.plugins.forge.LootPlugin;
import mods.railcraft.common.util.collections.CollectionTools;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;

import javax.annotation.Nullable;

import static mods.railcraft.common.items.Metal.*;

public class ItemPlate extends ItemMetal {

    public ItemPlate() {
        super(Form.PLATE, false, false, CollectionTools.createIndexedLookupTable(STEEL, IRON, GOLD, COPPER, TIN, LEAD, SILVER, BRONZE));
    }

    @Override
    public void initializeDefinintion() {
        for (Metal m : getMetalBiMap().values()) {
            LootPlugin.addLoot(RailcraftItems.PLATE, m, 6, 18, LootPlugin.Type.WORKSHOP);
        }
    }

    @Override
    public String getOreTag(@Nullable IVariantEnum variant) {
        return null;
    }

    @Override
    public void defineRecipes() {
        RailcraftItems plate = RailcraftItems.PLATE;

        for (Metal m : getMetalBiMap().values()) {
            IRecipe recipe = new ShapedOreRecipe(plate.getStack(4, m),
                    "II",
                    "II",
                    'I', m.getOreTag(Form.INGOT));
            RailcraftCraftingManager.rollingMachine.getRecipeList().add(recipe);
        }

        RailcraftCraftingManager.blastFurnace.addRecipe(plate.getStack(Metal.IRON), true, false, 1280, Metal.STEEL.getStack(Form.INGOT));
    }
}
