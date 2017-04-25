package com.witchworks.common.item.baubles;

import baubles.api.BaublesApi;
import baubles.api.IBauble;
import com.witchworks.common.item.ItemMod;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

/**
 * This class was created by BerciTheBeast on 21.4.2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
public abstract class ItemBauble extends ItemMod implements IBauble {

	public ItemBauble(String id) {
		super(id);
		setMaxStackSize(1);
	}

	@SuppressWarnings ("deprecation")
	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
		ItemStack toEquip = stack.copy();
		toEquip.stackSize = 1;
		if (canEquip(toEquip, player)) {
			IInventory baubles = BaublesApi.getBaubles(player);
			for (int i = 0; i < baubles.getSizeInventory(); i++) {
				if (baubles.isItemValidForSlot(i, toEquip)) {
					ItemStack stackInSlot = baubles.getStackInSlot(i);
					if (stackInSlot == null || ((IBauble) stackInSlot.getItem()).canUnequip(stackInSlot, player)) {
						if (!world.isRemote) {
							baubles.setInventorySlotContents(i, toEquip);
							stack.stackSize--;
						}

						if (stackInSlot != null) {
							((IBauble) stackInSlot.getItem()).onUnequipped(stackInSlot, player);
							return ActionResult.newResult(EnumActionResult.SUCCESS, stackInSlot.copy());
						}
						break;
					}
				}
			}
		}
		return ActionResult.newResult(EnumActionResult.PASS, stack);
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {

	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {

	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {

	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
		return false;
	}
}
