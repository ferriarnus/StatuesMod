package com.svennieke.statues.blocks.Statues;

import com.svennieke.statues.blocks.iStatue;
import com.svennieke.statues.blocks.StatueBase.BlockMagmaSlime;
import com.svennieke.statues.config.StatuesConfigGen;
import com.svennieke.statues.entity.fakeentity.FakeMagmaCube;
import com.svennieke.statues.tileentity.StatueTileEntity;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockMagmaSlime_Statue extends BlockMagmaSlime implements iStatue, ITileEntityProvider{
	
	private int TIER;
	
	public BlockMagmaSlime_Statue(String unlocalised, String registry, int tier) {
		super();
		this.TIER = tier;
		setUnlocalizedName(unlocalised);
		setRegistryName(registry);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		if (this.TIER == 2 || this.TIER == 3 || this.TIER == 4)
		{
			return new StatueTileEntity();
		}
		else
		return null;
	}
	
	private StatueTileEntity getTE(World world, BlockPos pos) {
        return (StatueTileEntity) world.getTileEntity(pos);
    }
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if(this.TIER == 2 || this.TIER == 3 || this.TIER == 4)
		{
	        if (!worldIn.isRemote) {
	        	int statuetier = getTE(worldIn, pos).getTier();
	        	if(statuetier != this.TIER)
	        	{
	        		getTE(worldIn, pos).setTier(this.TIER);
	        	}
	
	        	int meta1 = StatuesConfigGen.statues.magma.item1meta;
	        	int meta2 = StatuesConfigGen.statues.magma.item2meta;
	        	int meta3 = StatuesConfigGen.statues.magma.item3meta;
	        	
	        	ItemStack stack1 = new ItemStack(Item.getByNameOrId(StatuesConfigGen.statues.magma.item1), 1, meta1);
        		ItemStack stack2 = new ItemStack(Item.getByNameOrId(StatuesConfigGen.statues.magma.item2), 1, meta2);
        		ItemStack stack3 = new ItemStack(Item.getByNameOrId(StatuesConfigGen.statues.magma.item3), 1, meta3);
        		
	        	getTE(worldIn, pos).PlaySound(SoundEvents.ENTITY_MAGMACUBE_SQUISH, pos, worldIn);
	        	getTE(worldIn, pos).StatueBehavior(stack1, stack2, stack3, null, false, false, this, playerIn, worldIn, pos);
	        	
	        	getTE(worldIn, pos).holidayCheck(new FakeMagmaCube(worldIn), worldIn, pos, false);
	        }
	        return true;
		}
		else
		return false;
	}
}
