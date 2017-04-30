package com.witchworks.common.core.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;

/**
 * This class was created by Arekkuusu on 19/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
class IncantationHeal implements IIncantation {

	@SuppressWarnings ("ConstantConditions")
	@Override
	public void cast(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		final EntityLivingBase entity = (EntityLivingBase) sender.getCommandSenderEntity();
		if (entity.isEntityAlive() && entity.getHealth() < entity.getMaxHealth()) {
			entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 80, 0));
		}
	}
}
