package com.witchworks.api.ritual;

import com.witchworks.api.KettleRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This class was created by Arekkuusu on 06/05/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings ("WeakerAccess")
public class RitualHolder <T extends TileEntity> {

	public int energy_left;
	public int ticks;

	private IRitual<T> ritual;
	private boolean fail;

	private RitualHolder() {
		ritual = null;
	}

	public RitualHolder(IRitual<T> ritual) {
		this.ritual = ritual;
		this.energy_left = ritual.getCost();
	}

	public static <T extends TileEntity> RitualHolder<T> newInstance() {
		return new RitualHolder<>();
	}

	public boolean canPerform(T tile, World world, BlockPos pos) {
		return ritual != null && ritual.canPerform(tile, world, pos);
	}

	public void update(T tile) {
		if (ritual != null) {
			ritual.onUpdate(this, tile, tile.getWorld(), tile.getPos());
			++ticks;
		}
		if (hasEnded()) {
			ritual.onFinish(tile, tile.getWorld(), tile.getPos());
		}
	}

	public boolean isFail() {
		return fail;
	}

	public void fail() {
		this.fail = true;
	}

	public boolean hasEnded() {
		return ticks >= 100;
	}

	@SuppressWarnings ("unchecked")
	public void readNBT(NBTTagCompound cmp) {
		NBTTagCompound tag = cmp.getCompoundTag("ritual_data");
		ritual = KettleRegistry.getRituals().get(tag.getInteger("ritual"));
		energy_left = tag.getInteger("energy_left");
		ticks = tag.getInteger("ticks");
		fail = tag.getBoolean("fail");
	}

	public void writeNBT(NBTTagCompound cmp) {
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("ritual", KettleRegistry.getRituals().indexOf(ritual));
		tag.setInteger("energy_left", energy_left);
		tag.setInteger("ticks", ticks);
		tag.setBoolean("fail", fail);
		cmp.setTag("ritual_data", tag);
	}
}
