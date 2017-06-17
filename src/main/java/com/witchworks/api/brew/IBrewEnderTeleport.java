package com.witchworks.api.brew;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

/**
 * This class was created by Arekkuusu on 11/06/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public interface IBrewEnderTeleport {

	void onTeleport(EnderTeleportEvent event, EntityLivingBase entity, double targetX, double targetY, double targetZ, int amplifier);
}
