package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.LampBase;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LangGen extends LanguageProvider {
    public LangGen(DataGenerator gen) {
        super(gen.getPackOutput(), SimplyLight.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.simplylight", "Simply Light");
        add("simplylight.shift", "Press <%s> for info.");
        add("simplylight.key.shift", "Shift");
        add("simplylight.redstone", "Redstone");
        add("simplylight.pack.fullblock_ctm", "Simply Light Full block CTM");

        add("simplylight.gui.facing", "Facing");
        add("simplylight.gui.exit", "Exit");
        add("simplylight.gui.north", "North");
        add("simplylight.gui.east", "East");
        add("simplylight.gui.south", "South");
        add("simplylight.gui.west", "West");

        SLBlocks.BLOCKS.getEntries().forEach(block -> ((LampBase) block.get()).addLang(this::add));
    }
}
