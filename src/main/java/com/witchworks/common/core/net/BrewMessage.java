package com.witchworks.common.core.net;

import com.witchworks.api.BrewRegistry;
import com.witchworks.api.brew.BrewEffect;
import com.witchworks.api.brew.IBrew;
import com.witchworks.api.capability.IBrewStorage;
import com.witchworks.api.helper.NBTHelper;
import com.witchworks.common.core.capability.brew.BrewStorageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * This class was created by Arekkuusu on 24/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class BrewMessage implements IMessage {

	private Map<IBrew, BrewEffect> effects;
	private UUID target;

	public BrewMessage() {
	}

	public BrewMessage(IBrewStorage effects, EntityLivingBase entity) {
		this.effects = effects.getBrewMap();
		this.target = entity.getUniqueID();
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		target = new UUID(buf.readLong(), buf.readLong());
		int size = buf.readInt();

		effects = new LinkedHashMap<>(size);

		for (int i = 0; i < size; i++) {
			IBrew brew = BrewRegistry.getBrew(buf.readInt());
			effects.put(brew, new BrewEffect(brew, buf.readInt(), buf.readInt()));
		}
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeLong(target.getMostSignificantBits());
		buf.writeLong(target.getLeastSignificantBits());

		buf.writeInt(effects.size());
		for (BrewEffect effect : effects.values()) {
			buf.writeInt(BrewRegistry.getBrewId(effect.getBrew()));
			buf.writeInt(effect.getDuration());
			buf.writeInt(effect.getAmplifier());
		}
	}

	public static class PotionMessageHandler implements IMessageHandler<BrewMessage, IMessage> {

		@Override
		public IMessage onMessage(BrewMessage message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				World world = Minecraft.getMinecraft().player.world;

				EntityLivingBase entity = world.getPlayerEntityByUUID(message.target);
				if (entity == null) {
					entity = NBTHelper.getEntityByUUID(EntityLivingBase.class, message.target, world).orElse(null);
				}

				if (entity != null) {
					Optional<IBrewStorage> optional = BrewStorageHandler.getBrewStorage(entity);
					if (optional.isPresent()) {
						IBrewStorage storage = optional.get();
						storage.setBrewMap(message.effects);
					}
				}
			});
			return null;
		}
	}
}
