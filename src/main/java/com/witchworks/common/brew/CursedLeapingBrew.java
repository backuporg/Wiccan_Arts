package com.witchworks.common.brew;

import com.witchworks.api.brew.IBrew;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 24/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class CursedLeapingBrew implements IBrew {

	@Override
	//Note: Ascended glitch. This came as the result of trying to toy with operators for the brew of sinking.
	public void apply(World world, BlockPos pos, EntityLivingBase entity, int amplifier, int tick) {
		if (entity.motionY >= 0.0D)
			entity.motionY -= -1.0D;
		entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 1500, 2));
	}


	@Override
	public void onFinish(World world, BlockPos pos, EntityLivingBase entity, int amplifier) {
		//NO-OP
	}

	@Override
	public boolean isInstant() {
		return false;
	}

	@Override
	public int getColor() {
		return 0x4F7942;
	}

	@Override
	public String getName() {
		return "brew.cursed_leaping_brew";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUD(int x, int y, Minecraft mc) {
		render(x, y, mc, 10);
	}

}
