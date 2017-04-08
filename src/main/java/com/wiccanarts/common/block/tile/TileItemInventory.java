package com.wiccanarts.common.block.tile;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

/**
 * This class was created by Arekkuusu on 21/03/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */

@SuppressWarnings ("WeakerAccess")
public abstract class TileItemInventory extends TileEntity {

	public ItemStackHandlerTile itemHandler = createItemHandler ();

	@Override
	public boolean shouldRefresh (World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock () != newState.getBlock ();
	}

	@Override
	public NBTTagCompound writeToNBT (NBTTagCompound nbtTagCompound) {
		final NBTTagCompound ret = super.writeToNBT (nbtTagCompound);
		writeDataNBT (ret);
		return ret;
	}

	@Override
	public final SPacketUpdateTileEntity getUpdatePacket () {
		final NBTTagCompound tag = getUpdateTag ();
		writeDataNBT (tag);
		return new SPacketUpdateTileEntity (pos, 0, tag);
	}

	@Override
	public void onDataPacket (NetworkManager net, SPacketUpdateTileEntity packet) {
		super.onDataPacket (net, packet);
		readDataNBT (packet.getNbtCompound ());
	}

	@Override
	public void readFromNBT (NBTTagCompound nbtTagCompound) {
		super.readFromNBT (nbtTagCompound);
		readDataNBT (nbtTagCompound);
	}

	@Override
	public final NBTTagCompound getUpdateTag () {
		return writeToNBT (new NBTTagCompound ());
	}

	public void writeDataNBT (NBTTagCompound nbtTagCompound) {
		nbtTagCompound.merge (itemHandler.serializeNBT ());
	}

	public void readDataNBT (NBTTagCompound tagCompound) {
		itemHandler = createItemHandler ();
		itemHandler.deserializeNBT (tagCompound);
	}

	protected ItemStackHandlerTile createItemHandler () {
		return new ItemStackHandlerTile (this, true);
	}

	@Override
	public boolean hasCapability (Capability<?> cap, EnumFacing side) {
		return cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability (cap, side);
	}

	@Override
	public <T> T getCapability (Capability<T> capability, EnumFacing side) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast (itemHandler);
		return super.getCapability (capability, side);
	}

	public abstract int getSizeInventory ();

	public static class ItemStackHandlerTile extends ItemStackHandler {

		private final TileItemInventory tile;
		private final boolean allow;

		ItemStackHandlerTile (TileItemInventory tile, boolean allow) {
			super (tile.getSizeInventory ());
			this.tile = tile;
			this.allow = allow;
		}

		@Override
		public ItemStack insertItem (int slot, ItemStack stack, boolean simulate) {
			if (allow) {
				return super.insertItem (slot, stack, simulate);
			} else return stack;
		}

		@Override
		@Nullable
		public ItemStack extractItem (int slot, int amount, boolean simulate) {
			if (allow) {
				return super.extractItem (slot, 1, simulate);
			} else return null;
		}

		@Nullable
		public ItemStack getItemSimulate (int slot) {
			if (allow) {
				return super.extractItem (slot, 1, true);
			} else return null;
		}

		@Override
		public void onContentsChanged (int slot) {
			tile.markDirty ();
		}
	}
}
