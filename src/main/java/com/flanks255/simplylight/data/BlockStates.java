package com.flanks255.simplylight.data;

import com.flanks255.simplylight.SLBlockReg;
import com.flanks255.simplylight.SLBlocks;
import com.flanks255.simplylight.SimplyLight;
import com.flanks255.simplylight.blocks.*;
import net.minecraft.core.Direction;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.client.model.generators.MultiPartBlockStateBuilder;
import net.neoforged.neoforge.client.model.generators.loaders.CompositeModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class BlockStates  extends BlockStateProvider {
    ExistingFileHelper existingFileHelper;
    public BlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen.getPackOutput(), SimplyLight.MODID, exFileHelper);
        existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        generateLampBlockModels();
        SLBlocks.LAMPBLOCKS_ON.forEach(this::generateLampBlock); // 2x16 = 32
        SLBlocks.LAMPBLOCKS_OFF.forEach(this::generateLampBlock); // 2x16 = 32
        SLBlocks.SLABS.forEach(this::generateSlab); // 2x16x6 = 192
        SLBlocks.PANELS.forEach(this::generatePanel); // 2x16x6 = 192
        SLBlocks.RODS.forEach(this::generateRodLamp); // 2x3x16 = 96
        SLBlocks.BULBS.forEach(this::generateLightBulb); // 2x16x6 = 192
        SLBlocks.FIXTURES.forEach(this::generateWallLamp); // 2x16x6 = 192
        SLBlocks.POSTS.forEach(this::generateLampPost); // 2x16x3 = 96
        SLBlocks.EDGE_LIGHTS.forEach(this::generateEdgeBlock); // 2x16x16 = 512
        SLBlocks.EDGE_LIGHTS_TOP.forEach(this::generateTopEdgeBlock); // 2x16x16 = 512
        // Total Blockstates: 2048
    }

    private void generateLampBlockModels() {
        for (DyeColor color: DyeColor.values()) { // Full blocks
            ResourceLocation offTex;
            ResourceLocation onTex;

            //Texture
            if (color == DyeColor.WHITE) { // White needs to generate first, if Mojang ever changes the ordering of DyeColor this will break.
                offTex = modLoc("block/full_block/illuminant_block");
                onTex = modLoc("block/full_block/illuminant_block_on");

                // Normal
                models().withExistingParent("block/illuminant_block", "cube")
                        .texture("all", offTex)
                        .texture("particle", offTex)
                        .ao(false)
                        .element()
                        .cube("#all")
                        .shade(false)
                        .end();

                // Inverted
                models().withExistingParent("block/illuminant_block_on", "cube")
                        .texture("all", onTex)
                        .texture("particle", onTex).ao(false)
                        .element()
                        .cube("#all")
                        .shade(false)
                        .end();
            }
            else {
                offTex = modLoc("block/full_block/illuminant_" + color.getName() + "_block");
                onTex = modLoc("block/full_block/illuminant_" + color.getName() + "_block_on");

                // "Illuminant_block doesn't exist"? Mojang probably changed the DyeColor order.
                models().withExistingParent("block/illuminant_" + color.getName() + "_block", modLoc("block/illuminant_block"))
                        .texture("all", offTex).
                        texture("particle", offTex);
                models().withExistingParent("block/illuminant_" + color.getName() + "_block_on", modLoc("block/illuminant_block"))
                        .texture("all", onTex)
                        .texture("particle", onTex);

            }
        }
    }

    private void generateLampBlock(SLBlockReg<LampBlock, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        ModelFile offModel;
        ModelFile onModel;

        if (color == DyeColor.WHITE) {
            offModel = models().getExistingFile(modLoc("block/illuminant_block"));
            onModel = models().getExistingFile(modLoc("block/illuminant_block_on"));
        }
        else {
            offModel = models().getExistingFile(modLoc("block/illuminant_" + color.getName() + "_block"));
            onModel = models().getExistingFile(modLoc("block/illuminant_" + color.getName() + "_block_on"));
        }

        //Blockstate
        var LampBlockBuilder = getVariantBuilder(block.get());

        LampBlockBuilder.partialState().with(LampBlock.ON, true).modelForState()
                .modelFile(onModel).addModel();
        LampBlockBuilder.partialState().with(LampBlock.ON, false).modelForState()
                .modelFile(offModel).addModel();
    }

    private void generateLightBulb(SLBlockReg<LightBulb, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        boolean isWhite = color == DyeColor.WHITE;
        ModelFile base;
        ModelFile glow;

        if (isWhite) {
            base = models().getExistingFile(modLoc("block/lightbulb_base"));
            glow = models().getExistingFile(modLoc("block/lightbulb_glow"));
        }
        else {
            ResourceLocation tex = modLoc("block/omni/omni_"+ color.getName());
            base = models().withExistingParent("block/lightbulb_base_" + color.getName(), modLoc("block/lightbulb_base")).texture("main", tex).texture("particle", tex);
            glow = models().withExistingParent("block/lightbulb_glow_" + color.getName(), modLoc("block/lightbulb_glow")).texture("main", tex).texture("particle", tex);
        }

        var model = models().getBuilder(isWhite? "block/lightbulb" : "block/lightbulb_" + block.getBlock().color.getName()).customLoader(CompositeModelBuilder::begin)
                .child("Solid", models().nested().renderType("minecraft:solid").parent(base))
                .child("Translucent", models().nested().renderType("minecraft:translucent").parent(glow))
                .end();

        model.texture("particle", isWhite? modLoc("block/omni"): modLoc("block/omni/omni_"+ color.getName()));

        myDirectionalBlock(block.get(), $ -> model, 180);
    }

/*
    private void generateEdgeBlocks() {
        ModelFile model = models().getExistingFile(modLoc("block/edge_light"));
        MultiPartBlockStateBuilder builder = getMultipartBuilder(SimplyLight.EDGELAMP.get());

        builder.part().modelFile(model).addModel().useOr()
                .condition(EdgeLight.NORTH, true);


    }
*/
    private void generateEdgeBlock(SLBlockReg<EdgeLight, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        ModelFile model;
        if (color == DyeColor.WHITE)
            model = models().getExistingFile(modLoc("block/edge_light"));
        else {
            ResourceLocation tex = modLoc("block/omni/omni_"+ color.getName());
            model = models().withExistingParent("block/edge_light_" + color.getName(), modLoc("block/edge_light")).texture("0", tex).texture("particle", tex);
        }

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());
        builder.part().modelFile(model).addModel().useOr().nestedGroup()
                .condition(EdgeLight.NORTH, true).end()
                .nestedGroup()
                    .condition(EdgeLight.NORTH, false)
                    .condition(EdgeLight.SOUTH, false)
                    .condition(EdgeLight.EAST, false)
                    .condition(EdgeLight.WEST, false).end();
        builder.part().modelFile(model).rotationY(180).addModel().useOr()
                .nestedGroup().condition(EdgeLight.SOUTH, true).end()
                .nestedGroup()
                    .condition(EdgeLight.NORTH, false)
                    .condition(EdgeLight.SOUTH, false)
                    .condition(EdgeLight.EAST, false)
                    .condition(EdgeLight.WEST, false).end();
        builder.part().modelFile(model).rotationY(90).addModel().useOr()
                .nestedGroup().condition(EdgeLight.EAST, true).end()
                .nestedGroup()
                    .condition(EdgeLight.NORTH, false)
                    .condition(EdgeLight.SOUTH, false)
                    .condition(EdgeLight.EAST, false)
                    .condition(EdgeLight.WEST, false).end();
        builder.part().modelFile(model).rotationY(270).addModel().useOr()
                .nestedGroup().condition(EdgeLight.WEST, true).end()
                .nestedGroup()
                    .condition(EdgeLight.NORTH, false)
                    .condition(EdgeLight.SOUTH, false)
                    .condition(EdgeLight.EAST, false)
                    .condition(EdgeLight.WEST, false).end();
    }

    private void generateTopEdgeBlock(SLBlockReg<EdgeLight, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        ModelFile model;
        if (color == DyeColor.WHITE)
            model = models().getExistingFile(modLoc("block/edge_light"));
        else {
            model = models().getExistingFile(modLoc("block/edge_light_" + color.getName()));
        }

        MultiPartBlockStateBuilder builder = getMultipartBuilder(block.get());
        builder.part().modelFile(model).rotationY(180).rotationX(180).addModel().useOr().nestedGroup()
                .condition(EdgeLight.NORTH, true).end()
                .nestedGroup()
                .condition(EdgeLight.NORTH, false)
                .condition(EdgeLight.SOUTH, false)
                .condition(EdgeLight.EAST, false)
                .condition(EdgeLight.WEST, false).end();
        builder.part().modelFile(model).rotationX(180).addModel().useOr()
                .nestedGroup().condition(EdgeLight.SOUTH, true).end()
                .nestedGroup()
                .condition(EdgeLight.NORTH, false)
                .condition(EdgeLight.SOUTH, false)
                .condition(EdgeLight.EAST, false)
                .condition(EdgeLight.WEST, false).end();
        builder.part().modelFile(model).rotationY(270).rotationX(180).addModel().useOr()
                .nestedGroup().condition(EdgeLight.EAST, true).end()
                .nestedGroup()
                .condition(EdgeLight.NORTH, false)
                .condition(EdgeLight.SOUTH, false)
                .condition(EdgeLight.EAST, false)
                .condition(EdgeLight.WEST, false).end();
        builder.part().modelFile(model).rotationY(90).rotationX(180).addModel().useOr()
                .nestedGroup().condition(EdgeLight.WEST, true).end()
                .nestedGroup()
                .condition(EdgeLight.NORTH, false)
                .condition(EdgeLight.SOUTH, false)
                .condition(EdgeLight.EAST, false)
                .condition(EdgeLight.WEST, false).end();
    }

    private void generateLampPost(SLBlockReg<LampPost, LampPostItem> block) {
        ModelFile base = models().getExistingFile(modLoc("block/post_base"));
        ModelFile mid = models().getExistingFile(modLoc("block/post_mid"));
        ModelFile top;
        DyeColor color = block.getBlock().color;

        if (color == DyeColor.WHITE) {
            top = models().getExistingFile(modLoc("block/post_top")); //undyed top already exists
        }
        else {
            ResourceLocation tex = modLoc("block/post/post_lightface_"+ color.getName());
            top = models().withExistingParent("block/post_top_" + color.getName(), modLoc("block/post_top"))
                    .texture("1", tex); //generate a new top.
            models().withExistingParent("block/lamp_post_item_" + color.getName(), modLoc("block/lamp_post_item"))
                    .texture("3_1", tex); //generate full model for the item.
        }

        getVariantBuilder(block.get())
            .partialState().with(LampPost.POSITION, LampPost.Position.BOTTOM).modelForState()
            .modelFile(base).addModel()
            .partialState().with(LampPost.POSITION, LampPost.Position.MIDDLE).modelForState()
            .modelFile(mid).addModel()
            .partialState().with(LampPost.POSITION, LampPost.Position.TOP).modelForState()
            .modelFile(top).addModel();
    }
    private void generateWallLamp(SLBlockReg<Fixture, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        ModelFile wallModel;
        ModelFile floorModel;

        if (color == DyeColor.WHITE) {
            wallModel = models().getExistingFile(modLoc("block/wall_lamp"));
            floorModel = models().getExistingFile(modLoc("block/floor_lamp"));
        }
        else {
            ResourceLocation tex = modLoc("block/omni/omni_"+ color.getName());
            wallModel = models().withExistingParent("block/wall_lamp_" + color.getName(), modLoc("block/wall_lamp")).texture("0", tex).texture("particle", tex);
            floorModel = models().withExistingParent("block/floor_lamp_" + color.getName(), modLoc("block/floor_lamp")).texture("0", tex).texture("particle", tex);
        }
        getVariantBuilder(block.get())
            .partialState().with(BlockStateProperties.FACING, Direction.UP)
            .modelForState().modelFile(floorModel).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.DOWN)
            .modelForState().modelFile(floorModel).rotationX(180).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.EAST)
            .modelForState().modelFile(wallModel).rotationY(270).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.WEST)
            .modelForState().modelFile(wallModel).rotationY(90).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.SOUTH)
            .modelForState().modelFile(wallModel).addModel()
            .partialState().with(BlockStateProperties.FACING, Direction.NORTH)
            .modelForState().modelFile(wallModel).rotationY(180).addModel();
    }

    private void generateSlab(SLBlockReg<ThinLamp, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;

        if (color == DyeColor.WHITE)
            myDirectionalBlock(block.get(), $ -> models().getExistingFile(modLoc("block/illuminant_slab")), 180);
        else {
            ResourceLocation tex = modLoc("block/slab/illuminant_slab_"+ color.getName());
            ModelFile model = models().withExistingParent("block/illuminant_slab_" + color.getName(), modLoc("block/illuminant_slab")).texture("up", tex);
            myDirectionalBlock(block.get(), $ -> model, 180);
        }
    }

    private void generatePanel(SLBlockReg<ThinLamp, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        if (color == DyeColor.WHITE)
            myDirectionalBlock(block.get(), $ -> models().getExistingFile(modLoc("block/illuminant_panel")), 180);
        else {
            ResourceLocation tex = modLoc("block/panel/illuminant_panel_"+ color.getName());
            ModelFile model = models().withExistingParent("block/illuminant_panel_" + color.getName(), modLoc("block/illuminant_panel")).texture("up", tex);
            myDirectionalBlock(block.get(), $ -> model, 180);
        }
    }
    void generateRodLamp(SLBlockReg<RodLamp, BaseBlockItem> block) {
        DyeColor color = block.getBlock().color;
        ModelFile model;
        if (color == DyeColor.WHITE)
            model = models().getExistingFile(modLoc("block/rodlamp"));
        else {
            ResourceLocation tex = modLoc("block/omni/omni2_"+ color.getName());
            model = models().withExistingParent("block/rodlamp_" + color.getName(), modLoc("block/rodlamp")).texture("all", tex).texture("particle", tex);
            }

        getVariantBuilder(block.getBlock())
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(model).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(model).rotationX(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(model).rotationX(90).rotationY(90).addModel();
    }

    public void myDirectionalBlock(Block block, Function<BlockState, ModelFile> modelFunc, int angleOffset) {
        getVariantBuilder(block)
            .forAllStatesExcept(state -> {
                Direction dir = state.getValue(BlockStateProperties.FACING);
                return ConfiguredModel.builder()
                    .modelFile(modelFunc.apply(state))
                    .rotationX(dir == Direction.DOWN ? 180 : dir.getAxis().isHorizontal() ? 90 : 0)
                    .rotationY(dir.getAxis().isVertical() ? 0 : (((int) dir.toYRot()) + angleOffset) % 360)
                    .build();
            }, BlockStateProperties.WATERLOGGED);
    }
}
