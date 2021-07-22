package com.flanks255.simplylight.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class RotatableLamp extends LampBase implements SimpleWaterloggedBlock {
    public RotatableLamp(Properties props) {
        super(props.lightLevel((bState) -> 15));
        registerDefaultState(getStateDefinition().any().setValue(BlockStateProperties.WATERLOGGED, false));
    }
    public VoxelShape DOWN;
    public VoxelShape UP;
    public VoxelShape NORTH;
    public VoxelShape SOUTH;
    public VoxelShape WEST;
    public VoxelShape EAST;

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState blockState, @Nonnull BlockGetter world, @Nonnull BlockPos blockPos, @Nonnull CollisionContext context) {
        VoxelShape ret;
        Direction facing = blockState.getValue(BlockStateProperties.FACING);
        //D-U-N-S-W-E
        switch (facing.get3DDataValue()) {
            case 0:
                ret = DOWN;
                break;
            default:
            case 1:
                ret = UP;
                break;
            case 2:
                ret = NORTH;
                break;
            case 3:
                ret = SOUTH;
                break;
            case 4:
                ret = WEST;
                break;
            case 5:
                ret = EAST;
                break;
        }
        return ret;
    }

    @Override
    public boolean canPlaceLiquid(@Nonnull BlockGetter world, @Nonnull BlockPos blockpos, @Nonnull BlockState blockState, @Nonnull Fluid fluid) {
        return true;
    }

    @Nonnull
    @Override
    public FluidState getFluidState(BlockState blockState) {
        return blockState.getValue(BlockStateProperties.WATERLOGGED)? Fluids.WATER.getSource(false) : super.getFluidState(blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.WATERLOGGED);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return defaultBlockState().setValue(BlockStateProperties.FACING, context.getClickedFace()).setValue(BlockStateProperties.WATERLOGGED, waterlogged);
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter world, BlockPos pos) {
        return 15;
    }

}
