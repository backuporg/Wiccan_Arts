package com.witchworks.api.brew;

import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 16/06/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public interface IBrewRenderLiving {

	@SideOnly(Side.CLIENT)
	void onRenderLiving(RenderLivingEvent.Pre event, RenderLivingBase renderer, int amplifier);
}
