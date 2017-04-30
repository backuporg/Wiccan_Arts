package com.witchworks.common.core.capability.potion;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * This class was created by Arekkuusu on 23/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class BrewStorageProvider implements ICapabilitySerializable<NBTTagCompound> {

	@CapabilityInject (IBrewStorage.class)
	public static final Capability<IBrewStorage> BREW_STORAGE_CAPABILITY = null;
	private final IBrewStorage defaultPotionStorage = new CapabilityBrewStorage.DefaultBrewStorage();

	@Override
	public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
		return BREW_STORAGE_CAPABILITY == capability;
	}

	@SuppressWarnings ("ConstantConditions")
	@Override
	public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
		return BREW_STORAGE_CAPABILITY == capability ? BREW_STORAGE_CAPABILITY.cast(defaultPotionStorage) : null;
	}

	@SuppressWarnings ("ConstantConditions")
	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) BREW_STORAGE_CAPABILITY.writeNBT(defaultPotionStorage, null);
	}

	@SuppressWarnings ("ConstantConditions")
	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		BREW_STORAGE_CAPABILITY.readNBT(defaultPotionStorage, null, nbt);
	}
}
