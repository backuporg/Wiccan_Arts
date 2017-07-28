package com.witchworks.common.core.command;

import com.witchworks.common.core.capability.energy.EnergyHandler;
import com.witchworks.common.core.net.PacketHandler;
import com.witchworks.common.tile.TileCandle;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * This class was created by Arekkuusu on 4/20/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
class IncantationSnuff implements IIncantation {

	@Override
	public void cast(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		World world = sender.getEntityWorld();
		BlockPos source = sender.getPosition();
		for (BlockPos pos : BlockPos.getAllInBox(source.add(5, 5, 5), source.add(-5, -5, -5))) {
			TileEntity tile = world.getTileEntity(pos);
			if (tile instanceof TileCandle && ((TileCandle) tile).isLit()) {
				for (int i = 0; i < 5; i++) {
					double x = pos.getX() + world.rand.nextFloat();
					double y = pos.getY() + world.rand.nextFloat();
					double z = pos.getZ() + world.rand.nextFloat();
					world.spawnParticle(EnumParticleTypes.FLAME, x, y, z, 0, 0, 0);
				}
				((TileCandle) tile).unLitCandle();
				PacketHandler.updateToNearbyPlayers(world, pos);
			}
		}
		EnergyHandler.addEnergy((EntityPlayer) sender, 8);
	}
}
