package com.witchworks.client.fx;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * This class was created by Arekkuusu on 14/04/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SideOnly (Side.CLIENT)
class ParticleSpark extends Particle {

	private float oSize;

	private ParticleSpark(World world, double x, double y, double z, float[] color) {
		super(world, x, y, z, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.motionX += rand.nextFloat() * (rand.nextBoolean() ? 0.1D : -0.1D);
		this.motionY += rand.nextFloat() * 0.25D;
		this.motionZ += rand.nextFloat() * (rand.nextBoolean() ? 0.1D : -0.1D);
		setRBGColorF(color[0], color[1], color[2]);
		this.particleScale *= 0.25F;
		this.oSize = this.particleScale;
		this.particleMaxAge = (int) (6.0D / (Math.random() * 0.8D + 0.6D));
		this.setParticleTextureIndex(65);
	}

	public void renderParticle(VertexBuffer buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		float scale = ((float) this.particleAge + partialTicks) / (float) this.particleMaxAge * 32.0F;
		scale = MathHelper.clamp(scale, 0.0F, 1.0F);
		this.particleScale = this.oSize * scale;
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
		GlStateManager.color(getRedColorF(), getGreenColorF(), getBlueColorF(), 1.0F);
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}

		this.move(this.motionX, this.motionY, this.motionZ);
		this.particleGreen = (float) ((double) this.particleGreen * 0.96D);
		this.particleBlue = (float) ((double) this.particleBlue * 0.9D);
		this.motionX *= 0.699999988079071D;
		this.motionY *= 0.699999988079071D;
		this.motionZ *= 0.699999988079071D;
		this.motionY -= 0.019999999552965164D;

		if (this.isCollided) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
		}
	}

	@SideOnly (Side.CLIENT)
	static class Factory implements IParticleF {
		public Particle createParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, float... args) {
			return new ParticleSpark(worldIn, xCoordIn, yCoordIn, zCoordIn, args);
		}
	}
}
