package com.svennieke.statues.blocks.tiers.muted;

import javax.annotation.Nullable;

import com.svennieke.statues.Reference;
import com.svennieke.statues.blocks.tiers.base.BlockCow_Statue;
import com.svennieke.statues.blocks.tiers.functional.BlockCow_Statue_T3;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCow_Statue_T4 extends BlockCow_Statue{
	public boolean milk = true;
	
	private final String TAG_COOLDOWN = "cooldown";
	public static double cooldown;
	
	public BlockCow_Statue_T4() {
		super();
		setUnlocalizedName(Reference.StatuesBlocks.COWSTATUET4.getUnlocalisedName());
		setRegistryName(Reference.StatuesBlocks.COWSTATUET4.getRegistryName());
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		cooldown = Math.random();
		if (cooldown < 0.15) cooldown = StatueBehavior(this, playerIn, worldIn, hand);
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
	
	public int StatueBehavior(BlockCow_Statue_T4 blockCow_Statue_T4, EntityPlayer playerIn, World worldIn, EnumHand hand) {
		ItemStack stack = playerIn.getHeldItem(hand);
				
		if(!worldIn.isRemote)
			//System.out.println(i);
		if(milk){
			if (stack.getItem() == Items.BUCKET && !playerIn.capabilities.isCreativeMode)
	        {
	            stack.shrink(1);

	            if (stack.isEmpty())
	            {
	            	playerIn.setHeldItem(hand, new ItemStack(Items.MILK_BUCKET));
	            }
	            else if (!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.MILK_BUCKET)))
	            {
	            	playerIn.dropItem(new ItemStack(Items.MILK_BUCKET), false);
	            }
	        }
		}
		return 0;
	}
}