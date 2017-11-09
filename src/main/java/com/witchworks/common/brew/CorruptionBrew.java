package com.witchworks.common.brew;

import com.witchworks.api.brew.BrewEffect;
import com.witchworks.api.brew.IBrew;
import com.witchworks.common.core.capability.brew.BrewStorageHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 24/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class CorruptionBrew implements IBrew {

	@Override
	public void apply(World world, BlockPos pos, EntityLivingBase entity, int amplifier, int tick) {
		BrewStorageHandler.getBrewEffects(entity).stream().map(BrewEffect::getBrew).filter(brew -> !brew.isBad())
				.forEach((brew -> BrewStorageHandler.removeActiveBrew(entity, brew)));
	}

	@Override
	public boolean isBad() {
		return true;
	}

	@Override
	public boolean isInstant() {
		return true;
	}

	@Override
	public int getColor() {
		return 0x66FF00;
	}

	@Override
	public String getName() {
		return "corruption";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUD(int x, int y, Minecraft mc, int amplifier) {
		render(x, y, mc, 4);
	}
}
