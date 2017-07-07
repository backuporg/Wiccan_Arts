package com.witchworks.common;

import com.witchworks.api.CropRegistry;
import com.witchworks.api.crop.Crop;
import com.witchworks.common.block.ModBlocks;
import com.witchworks.common.block.natural.crop.BlockCrop;
import com.witchworks.common.item.ModItems;
import com.witchworks.common.item.food.*;
import com.witchworks.common.item.food.seed.ItemKelpSeed;
import com.witchworks.common.item.food.seed.ItemSeed;
import com.witchworks.common.lib.LibItemName;
import com.witchworks.common.lib.LibMod;
import com.witchworks.common.tile.ModTiles;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static com.witchworks.api.crop.Crop.*;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
@Mod.EventBusSubscriber(modid = LibMod.MOD_ID)
public final class RegistryEvents {

	private RegistryEvents() {
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		registerCrop(WHITE_SAGE, ModBlocks.crop_white_sage
				, new ItemCrop(LibItemName.WHITE_SAGE, 1, 0.4F, false), LibItemName.SEED_WHITE_SAGE);
		registerCrop(WORMWOOD, ModBlocks.crop_wormwood
				, new ItemCrop(LibItemName.WORMWOOD, 4, 0.8F, false), LibItemName.SEED_WORMWOOD);
		registerCrop(SILPHIUM, ModBlocks.crop_silphium
				, new ItemCrop(LibItemName.SILPHIUM, 4, 6F, false), LibItemName.SEED_SILPHIUM);
		registerCrop(MANDRAKE, ModBlocks.crop_mandrake_root
				, new ItemCrop(LibItemName.MANDRAKE, 4, 6F, false), LibItemName.SEED_MANDRAKE);
		registerCrop(GARLIC, ModBlocks.crop_garlic
				, new ItemCrop(LibItemName.GARLIC, 4, 6F, false), LibItemName.SEED_GARLIC);
		registerCrop(TULSI, ModBlocks.crop_tulsi
				, new ItemCrop(LibItemName.TULSI, 1, 0.4F, false), LibItemName.SEED_TULSI);
		registerCrop(KENAF, ModBlocks.crop_kenaf
				, new ItemCrop(LibItemName.KENAF, 4, 6F, false), LibItemName.SEED_KENAF);
		registerCrop(MINT, ModBlocks.crop_mint
				, new ItemCrop(LibItemName.MINT, 1, 2F, false), LibItemName.SEED_MINT);
		registerCrop(BELLADONNA, ModBlocks.crop_belladonna
				, new ItemBelladonna(), LibItemName.SEED_BELLADONNA);
		registerCrop(ACONITUM, ModBlocks.crop_aconitum
				, new ItemAconitum(), LibItemName.SEED_ACONITUM);
		registerCrop(ASPHODEL, ModBlocks.crop_asphodel
				, new ItemAsphodel(), LibItemName.SEED_ASPHODEL);
		registerCrop(LAVENDER, ModBlocks.crop_lavender
				, new ItemLavender(), LibItemName.SEED_LAVENDER);
		registerCrop(THISTLE, ModBlocks.crop_thistle
				, new ItemThistle(), LibItemName.SEED_THISTLE);
		registerCrop(GINGER, ModBlocks.crop_ginger
				, new ItemGinger(), LibItemName.SEED_GINGER);
		CropRegistry.registerCrop(KELP, ModBlocks.crop_kelp
				, new ItemKelp(), new ItemKelpSeed());

		ModItems.register(event.getRegistry());
	}

	private static void registerCrop(Crop crop, BlockCrop placed, Item cropItem, String seedName) {
		CropRegistry.registerCrop(crop, placed, cropItem, new ItemSeed(seedName, placed, crop.getSoil()));
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		ModBlocks.register(event.getRegistry());
		ModTiles.registerAll();
	}

	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().registerAll();
	}
}
