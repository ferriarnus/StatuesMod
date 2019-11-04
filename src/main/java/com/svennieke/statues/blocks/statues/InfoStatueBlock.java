package com.svennieke.statues.blocks.statues;

import com.svennieke.statues.blocks.AbstractBaseBlock;
import com.svennieke.statues.config.StatuesConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;

import java.util.List;
import java.util.Random;

public class InfoStatueBlock extends AbstractBaseBlock {

	private static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(3.0D, 0.0D, 3.0D, 13.0D, 4.5D, 13.0D);
	private static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(5.5D, 4.5D, 5.5D, 10.5D, 7.0D, 10.5D);
	private static final VoxelShape SHAPE = VoxelShapes.or(BOTTOM_SHAPE, TOP_SHAPE);

	public InfoStatueBlock(Properties builder) {
		super(builder.sound(SoundType.STONE));
	}

	@Override
	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn, BlockRayTraceResult result) {
		sendInfoMessage(playerIn, worldIn, pos);
		return true;
	}

	public void sendInfoMessage(PlayerEntity player, World worldIn, BlockPos pos) {
		if (!worldIn.isRemote) {
			int random = worldIn.rand.nextInt(100);

			List<String> messages = StatuesConfig.COMMON.info_messages.get();
			List<String> LuckyPlayers = StatuesConfig.COMMON.lucky_players.get();

			int idx = new Random().nextInt(messages.size());
			String randommessage = messages.get(idx);

			if(ModList.get().isLoaded("veinminer") && random < 20)
			{
				randommessage = ("Did you know we have veinminer");
			}
			else if(LuckyPlayers.size() != 0 && random < 20)
			{
				for (int i = 0; (i < LuckyPlayers.size()) && (LuckyPlayers.get(i) != null); i++) {
					randommessage = ("Luck is not on your side today");
				}
			}
			else
			{
				randommessage = (messages.get(idx));
			}

			player.sendMessage(new TranslationTextComponent(randommessage));
			worldIn.playSound(null, pos, SoundEvents.BLOCK_DISPENSER_FAIL, SoundCategory.NEUTRAL, 0.5F, 1.0F);
		}
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return this.SHAPE;
	}
}
