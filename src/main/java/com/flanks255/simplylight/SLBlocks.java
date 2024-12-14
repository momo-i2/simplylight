package com.flanks255.simplylight;

import com.flanks255.simplylight.blocks.*;
import com.google.common.collect.ImmutableSet;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Set;

public class SLBlocks {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimplyLight.MODID);
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(SimplyLight.MODID);

    private static final Item.Properties ITEMPROPERTIES = new Item.Properties();

    public static SLBlockReg<LampBlock, BaseBlockItem> addLamp(DyeColor color, boolean state) {
        return new SLBlockReg<>("illuminant_" + color.getName() + "_block" + (state?"_on":""), () -> new LampBlock(state, color), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    }

    public static SLBlockReg<ThinLamp, BaseBlockItem> addThin(int thick, DyeColor color) {
        return new SLBlockReg<>("illuminant_" + (thick == 8?"slab":"panel") + "_" + color.getName(), () -> new ThinLamp(thick, color), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    }

    public static SLBlockReg<RodLamp, BaseBlockItem> addRod(DyeColor color) {
        return new SLBlockReg<>("rodlamp_" + color.getName(), () -> new RodLamp(color), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    }

    public static SLBlockReg<LightBulb, BaseBlockItem> addBulb(DyeColor color) {
        return new SLBlockReg<>("lightbulb_" + color.getName(), () -> new LightBulb(color), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    }

    public static SLBlockReg<Fixture, BaseBlockItem> addFixture(DyeColor color) {
        return new SLBlockReg<>("wall_lamp_" + color.getName(), () -> new Fixture(color), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    }

    public static void init(IEventBus bus) {
        BLOCKS.register(bus);
        ITEMS.register(bus);
    }

    public static final SLBlockReg<EdgeLight, BaseBlockItem> EDGELAMP_TOP = new SLBlockReg<>("edge_light_top", () -> new EdgeLight(true), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<EdgeLight, BaseBlockItem> EDGELAMP = new SLBlockReg<>("edge_light", () -> new EdgeLight(false), b -> new BaseBlockItem(b, ITEMPROPERTIES));

    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP = new SLBlockReg<>("rodlamp", () -> new RodLamp(DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_ORANGE = addRod(DyeColor.ORANGE);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_MAGENTA = addRod(DyeColor.MAGENTA);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_LIGHT_BLUE = addRod(DyeColor.LIGHT_BLUE);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_YELLOW = addRod(DyeColor.YELLOW);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_LIME = addRod(DyeColor.LIME);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_PINK = addRod(DyeColor.PINK);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_GRAY = addRod(DyeColor.GRAY);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_LIGHT_GRAY = addRod(DyeColor.LIGHT_GRAY);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_CYAN = addRod(DyeColor.CYAN);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_PURPLE = addRod(DyeColor.PURPLE);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_BLUE = addRod(DyeColor.BLUE);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_BROWN = addRod(DyeColor.BROWN);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_GREEN = addRod(DyeColor.GREEN);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_RED = addRod(DyeColor.RED);
    public static final SLBlockReg<RodLamp, BaseBlockItem> RODLAMP_BLACK = addRod(DyeColor.BLACK);


    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB = new SLBlockReg<>("lightbulb", () -> new LightBulb(DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_ORANGE = addBulb(DyeColor.ORANGE);
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_MAGENTA = addBulb(DyeColor.MAGENTA);
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_LIGHT_BLUE = addBulb(DyeColor.LIGHT_BLUE);
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_YELLOW = addBulb(DyeColor.YELLOW);
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_LIME = addBulb(DyeColor.LIME);
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_LIGHT_GRAY = addBulb(DyeColor.LIGHT_GRAY);
    public static final SLBlockReg<LightBulb, BaseBlockItem> LIGHTBULB_RED = addBulb(DyeColor.RED);


    // TODO 1.22, change registry name to fixture
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE = new SLBlockReg<>("wall_lamp", () -> new Fixture(DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_ORANGE = addFixture(DyeColor.ORANGE);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_MAGENTA = addFixture(DyeColor.MAGENTA);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_LIGHT_BLUE = addFixture(DyeColor.LIGHT_BLUE);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_YELLOW = addFixture(DyeColor.YELLOW);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_LIME = addFixture(DyeColor.LIME);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_PINK = addFixture(DyeColor.PINK);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_GRAY = addFixture(DyeColor.GRAY);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_LIGHT_GRAY = addFixture(DyeColor.LIGHT_GRAY);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_CYAN = addFixture(DyeColor.CYAN);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_PURPLE = addFixture(DyeColor.PURPLE);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_BLUE = addFixture(DyeColor.BLUE);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_BROWN = addFixture(DyeColor.BROWN);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_GREEN = addFixture(DyeColor.GREEN);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_RED = addFixture(DyeColor.RED);
    public static final SLBlockReg<Fixture, BaseBlockItem> FIXTURE_BLACK = addFixture(DyeColor.BLACK);

    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL = new SLBlockReg<>("illuminant_panel",() -> new ThinLamp(4, DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_ORANGE = addThin(4, DyeColor.ORANGE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_MAGENTA = addThin(4, DyeColor.MAGENTA);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_LIGHT_BLUE = addThin(4, DyeColor.LIGHT_BLUE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_YELLOW = addThin(4, DyeColor.YELLOW);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_LIME = addThin(4, DyeColor.LIME);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_PINK = addThin(4, DyeColor.PINK);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_GRAY = addThin(4, DyeColor.GRAY);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_LIGHT_GRAY = addThin(4, DyeColor.LIGHT_GRAY);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_CYAN = addThin(4, DyeColor.CYAN);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_PURPLE = addThin(4, DyeColor.PURPLE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_BLUE = addThin(4, DyeColor.BLUE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_BROWN = addThin(4, DyeColor.BROWN);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_GREEN = addThin(4, DyeColor.GREEN);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_RED = addThin(4, DyeColor.RED);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_PANEL_BLACK = addThin(4, DyeColor.BLACK);

    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB = new SLBlockReg<>("illuminant_slab",() -> new ThinLamp(8, DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_ORANGE = addThin(8, DyeColor.ORANGE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_MAGENTA = addThin(8, DyeColor.MAGENTA);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_LIGHT_BLUE = addThin(8, DyeColor.LIGHT_BLUE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_YELLOW = addThin(8, DyeColor.YELLOW);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_LIME = addThin(8, DyeColor.LIME);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_PINK = addThin(8, DyeColor.PINK);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_GRAY = addThin(8, DyeColor.GRAY);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_LIGHT_GRAY = addThin(8, DyeColor.LIGHT_GRAY);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_CYAN = addThin(8, DyeColor.CYAN);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_PURPLE = addThin(8, DyeColor.PURPLE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_BLUE = addThin(8, DyeColor.BLUE);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_BROWN = addThin(8, DyeColor.BROWN);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_GREEN = addThin(8, DyeColor.GREEN);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_RED = addThin(8, DyeColor.RED);
    public static final SLBlockReg<ThinLamp, BaseBlockItem> ILLUMINANT_SLAB_BLACK = addThin(8, DyeColor.BLACK);


    public static final SLBlockReg<LampPost, LampPostItem> LAMP_POST = new SLBlockReg<>("lamp_post", LampPost::new, b -> new LampPostItem(b));

    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANTBLOCK_ON = new SLBlockReg<>("illuminant_block_on", () -> new LampBlock(true, DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANTBLOCK = new SLBlockReg<>("illuminant_block", () -> new LampBlock(false, DyeColor.WHITE), b -> new BaseBlockItem(b, ITEMPROPERTIES));

    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_ORANGE = addLamp(DyeColor.ORANGE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_ORANGE_ON = addLamp(DyeColor.ORANGE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_MAGENTA = addLamp(DyeColor.MAGENTA, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_MAGENTA_ON = addLamp(DyeColor.MAGENTA, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_BLUE = addLamp(DyeColor.LIGHT_BLUE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_BLUE_ON = addLamp(DyeColor.LIGHT_BLUE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_YELLOW = addLamp(DyeColor.YELLOW, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_YELLOW_ON = addLamp(DyeColor.YELLOW, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIME = addLamp(DyeColor.LIME, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIME_ON = addLamp(DyeColor.LIME, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PINK = addLamp(DyeColor.PINK, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PINK_ON = addLamp(DyeColor.PINK, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GRAY = addLamp(DyeColor.GRAY, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GRAY_ON = addLamp(DyeColor.GRAY, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_GRAY = addLamp(DyeColor.LIGHT_GRAY, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_LIGHT_GRAY_ON = addLamp(DyeColor.LIGHT_GRAY, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_CYAN = addLamp(DyeColor.CYAN, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_CYAN_ON = addLamp(DyeColor.CYAN, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PURPLE = addLamp(DyeColor.PURPLE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_PURPLE_ON = addLamp(DyeColor.PURPLE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLUE = addLamp(DyeColor.BLUE, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLUE_ON = addLamp(DyeColor.BLUE, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BROWN = addLamp(DyeColor.BROWN, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BROWN_ON = addLamp(DyeColor.BROWN, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GREEN = addLamp(DyeColor.GREEN, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_GREEN_ON = addLamp(DyeColor.GREEN, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_RED = addLamp(DyeColor.RED, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_RED_ON = addLamp(DyeColor.RED, true);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLACK = addLamp(DyeColor.BLACK, false);
    public static final SLBlockReg<LampBlock, BaseBlockItem> ILLUMINANT_BLOCK_BLACK_ON = addLamp(DyeColor.BLACK, true);


    public static final Set<SLBlockReg<LampBlock, BaseBlockItem>> LAMPBLOCKS_OFF = ImmutableSet.of(
            ILLUMINANTBLOCK, ILLUMINANT_BLOCK_LIGHT_GRAY, ILLUMINANT_BLOCK_GRAY, ILLUMINANT_BLOCK_BLACK,
            ILLUMINANT_BLOCK_BROWN, ILLUMINANT_BLOCK_RED, ILLUMINANT_BLOCK_ORANGE, ILLUMINANT_BLOCK_YELLOW,
            ILLUMINANT_BLOCK_LIME, ILLUMINANT_BLOCK_GREEN, ILLUMINANT_BLOCK_CYAN, ILLUMINANT_BLOCK_LIGHT_BLUE,
            ILLUMINANT_BLOCK_BLUE, ILLUMINANT_BLOCK_PURPLE, ILLUMINANT_BLOCK_MAGENTA, ILLUMINANT_BLOCK_PINK
    );
    public static final Set<SLBlockReg<LampBlock, BaseBlockItem>> LAMPBLOCKS_ON = ImmutableSet.of(
            ILLUMINANTBLOCK_ON, ILLUMINANT_BLOCK_LIGHT_GRAY_ON, ILLUMINANT_BLOCK_GRAY_ON, ILLUMINANT_BLOCK_BLACK_ON,
            ILLUMINANT_BLOCK_BROWN_ON, ILLUMINANT_BLOCK_RED_ON, ILLUMINANT_BLOCK_ORANGE_ON, ILLUMINANT_BLOCK_YELLOW_ON,
            ILLUMINANT_BLOCK_LIME_ON, ILLUMINANT_BLOCK_GREEN_ON, ILLUMINANT_BLOCK_CYAN_ON, ILLUMINANT_BLOCK_LIGHT_BLUE_ON,
            ILLUMINANT_BLOCK_BLUE_ON, ILLUMINANT_BLOCK_PURPLE_ON, ILLUMINANT_BLOCK_MAGENTA_ON, ILLUMINANT_BLOCK_PINK_ON
    );

    public static final Set<SLBlockReg<ThinLamp, BaseBlockItem>> SLABS = ImmutableSet.of(
            ILLUMINANT_SLAB, ILLUMINANT_SLAB_LIGHT_GRAY, ILLUMINANT_SLAB_GRAY, ILLUMINANT_SLAB_BLACK,
            ILLUMINANT_SLAB_BROWN, ILLUMINANT_SLAB_RED, ILLUMINANT_SLAB_ORANGE, ILLUMINANT_SLAB_YELLOW,
            ILLUMINANT_SLAB_LIME, ILLUMINANT_SLAB_GREEN, ILLUMINANT_SLAB_CYAN, ILLUMINANT_SLAB_LIGHT_BLUE,
            ILLUMINANT_SLAB_BLUE, ILLUMINANT_SLAB_PURPLE, ILLUMINANT_SLAB_MAGENTA, ILLUMINANT_SLAB_PINK
    );

    public static final Set<SLBlockReg<ThinLamp, BaseBlockItem>> PANELS = ImmutableSet.of(
            ILLUMINANT_PANEL, ILLUMINANT_PANEL_LIGHT_GRAY, ILLUMINANT_PANEL_GRAY, ILLUMINANT_PANEL_BLACK,
            ILLUMINANT_PANEL_BROWN, ILLUMINANT_PANEL_RED, ILLUMINANT_PANEL_ORANGE, ILLUMINANT_PANEL_YELLOW,
            ILLUMINANT_PANEL_LIME, ILLUMINANT_PANEL_GREEN, ILLUMINANT_PANEL_CYAN, ILLUMINANT_PANEL_LIGHT_BLUE,
            ILLUMINANT_PANEL_BLUE, ILLUMINANT_PANEL_PURPLE, ILLUMINANT_PANEL_MAGENTA, ILLUMINANT_PANEL_PINK
    );

    public static final Set<SLBlockReg<RodLamp, BaseBlockItem>> RODS = ImmutableSet.of(
            RODLAMP, RODLAMP_LIGHT_GRAY, RODLAMP_GRAY, RODLAMP_BLACK,
            RODLAMP_BROWN, RODLAMP_RED, RODLAMP_ORANGE, RODLAMP_YELLOW,
            RODLAMP_LIME, RODLAMP_GREEN, RODLAMP_CYAN, RODLAMP_LIGHT_BLUE,
            RODLAMP_BLUE, RODLAMP_PURPLE, RODLAMP_MAGENTA, RODLAMP_PINK
    );

    public static final Set<SLBlockReg<LightBulb, BaseBlockItem>> BULBS = ImmutableSet.of(
            LIGHTBULB, LIGHTBULB_LIGHT_GRAY, LIGHTBULB_RED, LIGHTBULB_ORANGE, LIGHTBULB_YELLOW,
            LIGHTBULB_LIME, LIGHTBULB_LIGHT_BLUE,
            LIGHTBULB_MAGENTA
    );

    public static final Set<SLBlockReg<Fixture, BaseBlockItem>> FIXTURES = ImmutableSet.of(
            FIXTURE, FIXTURE_LIGHT_GRAY, FIXTURE_GRAY, FIXTURE_BLACK,
            FIXTURE_BROWN, FIXTURE_RED, FIXTURE_ORANGE, FIXTURE_YELLOW,
            FIXTURE_LIME, FIXTURE_GREEN, FIXTURE_CYAN, FIXTURE_LIGHT_BLUE,
            FIXTURE_BLUE, FIXTURE_PURPLE, FIXTURE_MAGENTA, FIXTURE_PINK
    );

    public static final Set<SLBlockReg<?,?>> TAB_ORDER = ImmutableSet.of(
            EDGELAMP, EDGELAMP_TOP, LAMP_POST
    );
}
