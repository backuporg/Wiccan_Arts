package com.witchworks.api.capability;

import com.witchworks.api.brew.BrewEffect;
import com.witchworks.api.brew.IBrew;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * This class was created by Arekkuusu on 23/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public interface IBrewStorage {

	default void addBrew(EntityLivingBase entity, BrewEffect effect) {
		if (effect.isInstant()) {
			effect.update(entity.world, entity.getPosition(), entity);
		} else {
			effect.start(entity.world, entity.getPosition(), entity);
			getBrewMap().put(effect.getBrew(), effect);
			syncToNear(entity);
		}
	}

	default void removeBrew(EntityLivingBase entity, IBrew brew) {
		BrewEffect effect = getBrew(brew);
		if (effect != null) {
			effect.end(entity.world, entity.getPosition(), entity);
			getBrewMap().remove(brew);
			syncToNear(entity);
		}
	}

	default BrewEffect getBrew(IBrew brew) {
		return getBrewMap().get(brew);
	}

	Map<IBrew, BrewEffect> getBrewMap();

	void setBrewMap(Map<IBrew, BrewEffect> effect);

	Collection<BrewEffect> getBrewEffects();

	Set<IBrew> getBrews();

	void syncToNear(EntityLivingBase target);

	void syncTo(EntityPlayerMP target);
}
