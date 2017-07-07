package com.witchworks.client.fx;

import com.witchworks.client.ResourceLocations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;

@SideOnly(Side.CLIENT)
class ParticleBubble extends Particle {

	private static final VertexFormat VERTEX_FORMAT = (new VertexFormat()).addElement(DefaultVertexFormats.POSITION_3F).addElement(DefaultVertexFormats.TEX_2F).addElement(DefaultVertexFormats.COLOR_4UB).addElement(DefaultVertexFormats.TEX_2S).addElement(DefaultVertexFormats.NORMAL_3B).addElement(DefaultVertexFormats.PADDING_1B);
	private final TextureManager textureManager;
	private int life;

	private ParticleBubble(TextureManager textureManagerIn, World world, double x, double y, double z, int rgb) {
		super(world, x, y, z, 0, 0, 0);
		this.textureManager = textureManagerIn;
		this.motionX *= 0.009999999776482582D;
		this.motionY = 0.1D * 0.20000000298023224D + (Math.random() * 2.0D - 1.0D) * 0.019999999552965164D;
		this.motionZ *= 0.009999999776482582D;

		this.particleScale *= 0.025F;
		this.particleMaxAge = 2;
		rgb = new Color(rgb, false).brighter().brighter().brighter().brighter().hashCode();
		float r = (rgb >>> 16 & 0xFF) / 256.0F;
		float g = (rgb >>> 8 & 0xFF) / 256.0F;
		float b = (rgb & 0xFF) / 256.0F;
		setRBGColorF(r, g, b);
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.move(this.motionX, this.motionY, this.motionZ);

		this.motionY += 0.001D;
		if (this.posY == this.prevPosY) {
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}

		this.motionX *= 0.6600000262260437D;
		this.motionY *= 0.4500000238418579D;
		this.motionZ *= 0.6600000262260437D;

		if (life++ >= 16) {
			this.setExpired();
		}
	}

	@Override
	public void renderParticle(VertexBuffer buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		int i = (int) (((float) this.life + partialTicks) / (float) this.particleMaxAge);

		if (i <= 7) {
			this.textureManager.bindTexture(ResourceLocations.BUBBLE);
			float minX = 0;
			float maxX = minX + 1;

			float minY = (float) i / 8F;
			float maxY = minY + 0.125F;

			float scale = 1.0F * this.particleScale;
			float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
			float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
			float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
			Vec3d vec0 = new Vec3d(-rotationX * scale - rotationXY * scale, -rotationZ * scale, -rotationYZ * scale - rotationXZ * scale);
			Vec3d vec1 = new Vec3d(-rotationX * scale + rotationXY * scale, rotationZ * scale, -rotationYZ * scale + rotationXZ * scale);
			Vec3d vec2 = new Vec3d(rotationX * scale + rotationXY * scale, rotationZ * scale, rotationYZ * scale + rotationXZ * scale);
			Vec3d vec3 = new Vec3d(rotationX * scale - rotationXY * scale, -rotationZ * scale, rotationYZ * scale - rotationXZ * scale);

			GlStateManager.color(getRedColorF(), getGreenColorF(), getBlueColorF(), 1.0F);
			buffer.begin(7, VERTEX_FORMAT);
			buffer.pos((double) x + vec0.x, (double) y + vec0.y, (double) z + vec0.z).tex((double) maxX, (double) maxY).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			buffer.pos((double) x + vec1.x, (double) y + vec1.y, (double) z + vec1.z).tex((double) maxX, (double) minY).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			buffer.pos((double) x + vec2.x, (double) y + vec2.y, (double) z + vec2.z).tex((double) minX, (double) minY).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			buffer.pos((double) x + vec3.x, (double) y + vec3.y, (double) z + vec3.z).tex((double) minX, (double) maxY).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
			Tessellator.getInstance().draw();
		}
	}

	@Override
	public int getFXLayer() {
		return 3;
	}

	@SideOnly(Side.CLIENT)
	static class Factory implements IParticleF {
		public Particle createParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... args) {
			Color color = new Color(args[0]).darker();
			return new ParticleBubble(Minecraft.getMinecraft().getTextureManager(), worldIn, xCoordIn, yCoordIn, zCoordIn, color.getRGB());
		}
	}
}