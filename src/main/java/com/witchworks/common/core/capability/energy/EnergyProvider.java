package com.witchworks.common.core.capability.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * This class was created by Arekkuusu on 20/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class EnergyProvider implements ICapabilitySerializable<NBTTagCompound> {

	@CapabilityInject (IEnergy.class)
	public static final Capability<IEnergy> ENERGY_CAPABILITY = null;
	private final IEnergy defaultEnergy = new CapabilityEnergy.DefaultEnergy();

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return ENERGY_CAPABILITY == capability;
	}

	@SuppressWarnings ("ConstantConditions")
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return ENERGY_CAPABILITY == capability ? ENERGY_CAPABILITY.cast(defaultEnergy) : null;
	}

	@SuppressWarnings ("ConstantConditions")
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) ENERGY_CAPABILITY.writeNBT(defaultEnergy, null);
	}

	@SuppressWarnings ("ConstantConditions")
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		ENERGY_CAPABILITY.readNBT(defaultEnergy, null, nbt);
	}
}
