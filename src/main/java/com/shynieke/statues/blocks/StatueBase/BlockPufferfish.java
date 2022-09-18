package com.shynieke.statues.blocks.StatueBase;

import com.shynieke.statues.Statues;
import com.shynieke.statues.blocks.BaseBlock.BaseTranslucent;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class BlockPufferfish extends BaseTranslucent {

	private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.0625 * 6.5, 0, 0.0625 * 6, 0.0625 * 9.5, 0.0625 * 4.5, 0.0625 * 9);
	private final int size; //TODO change hitbox depending on size

	public BlockPufferfish(int size) {
		super(Material.TNT);
		this.setCreativeTab(Statues.instance.tabStatues);
		this.setSoundType(SoundType.CLOTH);
		this.size = size;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return BOUNDING_BOX;
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn, boolean p_185477_7_) {
		addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING_BOX);
	}
}
