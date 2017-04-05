package com.wiccanarts.common.net;

import com.wiccanarts.client.fx.*;
import com.wiccanarts.common.*;
import io.netty.buffer.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.minecraftforge.fml.relauncher.*;

/**
 * This class was created by Arekkuusu on 04/04/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
public class ParticleMessage implements IMessage {

	private ParticleF particleF;
	private double x;
	private double y;
	private double z;
	private int amount;
	private double xSpeed;
	private double ySpeed;
	private double zSpeed;
	private float[] args;

	public ParticleMessage() {
	}

	ParticleMessage(ParticleF particleF, double x, double y, double z, int amount, double xSpeed, double ySpeed, double zSpeed, float... args) {
		this.particleF = particleF;
		this.x = x;
		this.y = y;
		this.z = z;
		this.amount = amount;
		this.xSpeed = xSpeed;
		this.ySpeed = ySpeed;
		this.zSpeed = zSpeed;
		this.args = args;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		particleF = ParticleF.values()[buf.readInt()];

		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();

		amount = buf.readInt();

		xSpeed = buf.readDouble();
		ySpeed = buf.readDouble();
		zSpeed = buf.readDouble();

		int argCount = buf.readInt();

		args = new float[argCount];
		for (int i = 0; i < argCount; i++) {
			args[i] = buf.readFloat();
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(particleF.ordinal());

		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);

		buf.writeInt(amount);

		buf.writeDouble(xSpeed);
		buf.writeDouble(ySpeed);
		buf.writeDouble(zSpeed);

		buf.writeInt(args.length);

		for (float arg : args) {
			buf.writeFloat(arg);
		}
	}

	public static class ParticleMessageHandler implements IMessageHandler<ParticleMessage, IMessage> {

		@Override
		public IMessage onMessage(ParticleMessage message, MessageContext ctx) {
			if (ctx.side == Side.CLIENT) {
				for (int i = 0; i < message.amount; i++) {
					WiccanArts.proxy.spawnParticle(message.particleF, message.x, message.y, message.z, message.xSpeed, message.ySpeed, message.zSpeed, message.args);
				}
			}
			return null;
		}
	}
}
