package com.witchworks.api.recipe;

import com.witchworks.common.crafting.kettle.ItemRitual;

/**
 * This class was created by Arekkuusu on 04/04/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
public class KettleItemRecipe extends FlawlessRecipe {

	private final ItemRitual ritual;

	public KettleItemRecipe(ItemRitual ritual, Object... inputs) {
		super(ritual.getStack(), inputs);
		this.ritual = ritual;
	}

	public ItemRitual getRitual() {
		return ritual;
	}
}
