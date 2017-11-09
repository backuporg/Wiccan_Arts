package com.witchworks.common.item;

import com.witchworks.api.CropRegistry;
import com.witchworks.common.block.ModBlocks;
import com.witchworks.common.block.natural.fluid.Fluids;
import com.witchworks.common.core.WitchWorksCreativeTabs;
import com.witchworks.common.item.block.ItemBlockColor;
import com.witchworks.common.item.block.ItemGemOre;
import com.witchworks.common.item.equipment.ItemSilverArmor;
import com.witchworks.common.item.food.ItemHeart;
import com.witchworks.common.item.food.ItemHoney;
import com.witchworks.common.item.magic.ItemGem;
import com.witchworks.common.item.magic.ItemTaglock;
import com.witchworks.common.item.magic.books.ItemDustyGrimoire;
import com.witchworks.common.item.magic.books.ItemShadowBook;
import com.witchworks.common.item.magic.brew.ItemBrewDrink;
import com.witchworks.common.item.magic.brew.ItemBrewLinger;
import com.witchworks.common.item.magic.brew.ItemBrewSplash;
import com.witchworks.common.item.tool.*;
import com.witchworks.common.lib.LibItemName;
import com.witchworks.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidBlock;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

import static net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

/**
 * This class was created by <Arekkuusu> on 26/02/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
@SuppressWarnings("ConstantConditions")
@ObjectHolder(LibMod.MOD_ID)
public final class ModItems {

	private static final Item PLACE_HOLDER = new Item();
	//--------------------------------Items--------------------------------//
	public static final Item gem = PLACE_HOLDER;
	public static final Item gemstone_amalgam = PLACE_HOLDER;
	public static final Item gem_powder = PLACE_HOLDER;

	public static final Item mandrake_root = PLACE_HOLDER;
	public static final Item seed_mandrake = PLACE_HOLDER;
	public static final Item lavender = PLACE_HOLDER;
	public static final Item seed_lavender = PLACE_HOLDER;
	public static final Item belladonna = PLACE_HOLDER;
	public static final Item seed_belladonna = PLACE_HOLDER;
	public static final Item asphodel = PLACE_HOLDER;
	public static final Item seed_asphodel = PLACE_HOLDER;
	public static final Item kelp = PLACE_HOLDER;
	public static final Item seed_kelp = PLACE_HOLDER;
	public static final Item ginger = PLACE_HOLDER;
	public static final Item seed_ginger = PLACE_HOLDER;
	public static final Item mint = PLACE_HOLDER;
	public static final Item seed_mint = PLACE_HOLDER;
	public static final Item thistle = PLACE_HOLDER;
	public static final Item seed_thistle = PLACE_HOLDER;
	public static final Item garlic = PLACE_HOLDER;
	public static final Item seed_garlic = PLACE_HOLDER;
	public static final Item aconitum = PLACE_HOLDER;
	public static final Item seed_aconitum = PLACE_HOLDER;
	public static final Item white_sage = PLACE_HOLDER;
	public static final Item seed_white_sage = PLACE_HOLDER;
	public static final Item tulsi = PLACE_HOLDER;
	public static final Item seed_tulsi = PLACE_HOLDER;
	public static final Item kenaf = PLACE_HOLDER;
	public static final Item seed_kenaf = PLACE_HOLDER;
	public static final Item silphium = PLACE_HOLDER;
	public static final Item seed_silphium = PLACE_HOLDER;
	public static final Item wormwood = PLACE_HOLDER;
	public static final Item seed_wormwood = PLACE_HOLDER;
	public static final Item bee = PLACE_HOLDER;
	public static final Item glass_jar = PLACE_HOLDER;

	public static final Item brew_phial_drink = PLACE_HOLDER;
	public static final Item brew_phial_splash = PLACE_HOLDER;
	public static final Item brew_phial_linger = PLACE_HOLDER;

	public static final Item wax = PLACE_HOLDER;
	public static final Item honey = PLACE_HOLDER;
	public static final Item salt = PLACE_HOLDER;
	public static final Item wool_of_bat = PLACE_HOLDER;
	public static final Item tongue_of_dog = PLACE_HOLDER;
	public static final Item wood_ash = PLACE_HOLDER;
	public static final Item honeycomb = PLACE_HOLDER;
	public static final Item empty_honeycomb = PLACE_HOLDER;
	public static final Item unrefined_chalk = PLACE_HOLDER;
	public static final Item needle_bone = PLACE_HOLDER;
	public static final Item cold_iron_ingot = PLACE_HOLDER;
	public static final Item silver_ingot = PLACE_HOLDER;
	public static final Item silver_powder = PLACE_HOLDER;
	public static final Item silver_nugget = PLACE_HOLDER;
	public static final Item book_of_shadows = PLACE_HOLDER;
	public static final Item dusty_grimoire = PLACE_HOLDER;
	public static final Item mortar_and_pestle = PLACE_HOLDER;
	public static final Item chalk_item = PLACE_HOLDER;
	public static final Item wand = PLACE_HOLDER;
	public static final Item athame = PLACE_HOLDER;
	public static final Item boline = PLACE_HOLDER;
	public static final Item taglock = PLACE_HOLDER;
	public static final Item ectoplasm = PLACE_HOLDER;
	public static final Item spectral_dust = PLACE_HOLDER;
	public static final Item silver_scales = PLACE_HOLDER;
	public static final Item eye_of_old = PLACE_HOLDER;
	public static final Item heart = PLACE_HOLDER;
	public static final Item envenomed_fang = PLACE_HOLDER;
	public static final Item dimensional_sand = PLACE_HOLDER;
	public static final Item chromatic_quill = PLACE_HOLDER;
	public static final Item carnivorous_tooth = PLACE_HOLDER;
	public static final Item catechu = PLACE_HOLDER;
	public static final Item eye_of_ancient = PLACE_HOLDER;
	public static final Item hoof = PLACE_HOLDER;
	public static final Item equine_tail = PLACE_HOLDER;
	public static final Item albedo = PLACE_HOLDER;
	public static final Item absinthe_green = PLACE_HOLDER;
	public static final Item oak_apple_gall = PLACE_HOLDER;
	public static final Item iron_gall_ink = PLACE_HOLDER;

	public static final Item silver_pickaxe = PLACE_HOLDER;
	public static final Item silver_axe = PLACE_HOLDER;
	public static final Item silver_spade = PLACE_HOLDER;
	public static final Item silver_hoe = PLACE_HOLDER;
	public static final Item silver_sword = PLACE_HOLDER;
	public static final Item silver_helmet = PLACE_HOLDER;
	public static final Item silver_chestplate = PLACE_HOLDER;
	public static final Item silver_leggings = PLACE_HOLDER;
	public static final Item silver_boots = PLACE_HOLDER;

	private ModItems() {
	}

	public static void register(final IForgeRegistry<Item> registry) {
		CropRegistry.getFoods().forEach((crop, item) -> registry.register(item));
		CropRegistry.getSeeds().forEach((crop, item) -> registry.register(item));
		for (final IFluidBlock fluidBlock : Fluids.MOD_FLUID_BLOCKS) {
			registry.register(itemBlock((Block) fluidBlock));
		}
		registry.register(new ItemGem());
		registry.register(new ItemMod(LibItemName.GEMSTONE_AMALGAM));
		registry.register(new ItemMod(LibItemName.COLD_IRON_INGOT));
		registry.register(new ItemMod(LibItemName.SILVER_POWDER));
		registry.register(new ItemMod(LibItemName.SILVER_INGOT));
		registry.register(new ItemMod(LibItemName.SILVER_NUGGET));
		//Misc
		registry.registerAll(
				new ItemHoney(),
				new ItemSalt(),
				new ItemMod(LibItemName.WAX),
				new ItemMod(LibItemName.BEE).setMaxDamage(35),
				new ItemMod(LibItemName.HONEYCOMB),
				new ItemMod(LibItemName.EMPTY_HONEYCOMB),
				new ItemMod(LibItemName.GEM_POWDER),
				new ItemMod(LibItemName.MORTAR_AND_PESTLE),
				new ItemMod(LibItemName.UNREFINED_CHALK),
				new ItemBrewDrink(),
				new ItemBrewSplash(),
				new ItemBrewLinger(),
				new ItemMod(LibItemName.GLASS_JAR),
				new ItemAthame(),
				new ItemBoline(),
				new ItemTaglock(),
				new ItemMod(LibItemName.CHALK_ITEM),
				new ItemMod(LibItemName.NEEDLE_BONE),
				new ItemMod(LibItemName.WOOL_OF_BAT),
				new ItemMod(LibItemName.TONGUE_OF_DOG),
				new ItemMod(LibItemName.WOOD_ASH),
				new ItemMod(LibItemName.ECTOPLASM),
				new ItemMod(LibItemName.SPECTRAL_DUST),
				new ItemMod(LibItemName.SILVER_SCALES),
				new ItemMod(LibItemName.EYE_OF_OLD),
				new ItemMod(LibItemName.ENVENOMED_FANG),
				new ItemMod(LibItemName.DIMENSIONAL_SAND),
				new ItemMod(LibItemName.CHROMATIC_QUILL),
				new ItemMod(LibItemName.CARNIVOROUS_TOOTH),
				new ItemMod(LibItemName.EYE_OF_ANCIENT),
				new ItemMod(LibItemName.HOOF),
				new ItemMod(LibItemName.EQUINE_TAIL),
				new ItemMod(LibItemName.CATECHU),
				new ItemMod(LibItemName.OAK_APPLE_GALL),
				new ItemMod(LibItemName.IRON_GALL_INK),
				new ItemMod(LibItemName.ABSINTHE_GREEN),
				new ItemMod(LibItemName.ALBEDO),
				new ItemHeart(),
				new ItemShadowBook(),
				new ItemDustyGrimoire()
		);
		//Equipment
		registry.registerAll(
				new ItemSilverPickaxe(),
				new ItemSilverAxe(),
				new ItemSilverSpade(),
				new ItemSilverHoe(),
				new ItemSilverSword(), new ItemSilverArmor(LibItemName.SILVER_HELMET, ModMaterials.ARMOR_SILVER, 1, EntityEquipmentSlot.HEAD),
				new ItemSilverArmor(LibItemName.SILVER_CHESTPLATE, ModMaterials.ARMOR_SILVER, 1, EntityEquipmentSlot.CHEST),
				new ItemSilverArmor(LibItemName.SILVER_LEGGINGS, ModMaterials.ARMOR_SILVER, 2, EntityEquipmentSlot.LEGS),
				new ItemSilverArmor(LibItemName.SILVER_BOOTS, ModMaterials.ARMOR_SILVER, 1, EntityEquipmentSlot.FEET)
		);
		//Item Blocks
		registry.registerAll(
				new ItemBlockColor(ModBlocks.candle_large),
				new ItemBlockColor(ModBlocks.candle_medium),
				new ItemBlockColor(ModBlocks.candle_small),
				itemBlock(ModBlocks.crop_aconitum),
				itemBlock(ModBlocks.crop_asphodel),
				itemBlock(ModBlocks.crop_belladonna),
				itemBlock(ModBlocks.crop_ginger),
				itemBlock(ModBlocks.crop_kelp),
				itemBlock(ModBlocks.crop_mint),
				itemBlock(ModBlocks.crop_silphium),
				itemBlock(ModBlocks.crop_white_sage),
				itemBlock(ModBlocks.crop_mandrake_root),
				itemBlock(ModBlocks.crop_lavender),
				itemBlock(ModBlocks.crop_thistle),
				itemBlock(ModBlocks.crop_tulsi),
				itemBlock(ModBlocks.crop_kenaf),
				itemBlock(ModBlocks.crop_garlic),
				itemBlock(ModBlocks.crop_wormwood),
				itemBlock(ModBlocks.cypress_sapling),
				itemBlock(ModBlocks.fake_ice),
				itemBlock(ModBlocks.fake_ice_fence),
				itemBlock(ModBlocks.fake_ice_stairs),

				new ItemGemOre(ModBlocks.gem_ore).setCreativeTab(WitchWorksCreativeTabs.BLOCKS_CREATIVE_TAB),
				new ItemSlab(ModBlocks.fake_ice_slab_half, (BlockSlab) ModBlocks.fake_ice_slab_half, (BlockSlab) ModBlocks.fake_ice_slab_double).setRegistryName(ModBlocks.fake_ice_slab_half.getRegistryName()),
				itemBlock(ModBlocks.silver_block),
				itemBlock(ModBlocks.silver_ore),
				itemBlock(ModBlocks.moldavite_block),
				itemBlock(ModBlocks.coquina),
				itemBlock(ModBlocks.bloodstone_block),
				itemBlock(ModBlocks.cauldron),
				itemBlock(ModBlocks.altar),
				itemBlock(ModBlocks.oven),
				itemBlock(ModBlocks.apiary),
				itemBlock(ModBlocks.torchwood),
				itemBlock(ModBlocks.ember_grass),
				itemBlock(ModBlocks.beehive),
				itemBlock(ModBlocks.tourmaline_block),
				itemBlock(ModBlocks.malachite_block),
				itemBlock(ModBlocks.tigers_eye_block),
				itemBlock(ModBlocks.nuummite_block),
				itemBlock(ModBlocks.alexandrite_block),
				itemBlock(ModBlocks.garnet_block),
				itemBlock(ModBlocks.jasper_block),
				itemBlock(ModBlocks.amethyst_block),
				itemBlock(ModBlocks.salt_ore),
				itemBlock(ModBlocks.nethersteel),
				itemBlock(ModBlocks.salt_barrier),
				itemBlock(ModBlocks.chalk)
		);
	}

	private static Item itemBlock(Block block) {
		return new ItemBlock(block).setRegistryName(block.getRegistryName());
	}

	public static void init() {
		initOreDictionary();
	}

	private static void initOreDictionary() {
		OreDictionary.registerOre("gemBloodstone", new ItemStack(ModItems.gem, 1, 5));
		OreDictionary.registerOre("gemMoldavite", new ItemStack(ModItems.gem, 1, 1));
		OreDictionary.registerOre("gemNuummite", new ItemStack(ModItems.gem, 1, 2));
		OreDictionary.registerOre("gemGarnet", new ItemStack(ModItems.gem, 1, 0));
		OreDictionary.registerOre("gemTourmaline", new ItemStack(ModItems.gem, 1, 4));
		OreDictionary.registerOre("gemTigersEye", new ItemStack(ModItems.gem, 1, 3));
		OreDictionary.registerOre("gemJasper", new ItemStack(ModItems.gem, 1, 6));
		OreDictionary.registerOre("gemMalachite", new ItemStack(ModItems.gem, 1, 7));
		OreDictionary.registerOre("gemAmethyst", new ItemStack(ModItems.gem, 1, 8));
		OreDictionary.registerOre("gemAlexandrite", new ItemStack(ModItems.gem, 1, 9));
		OreDictionary.registerOre("nuggetSilver", new ItemStack(ModItems.silver_nugget));
		OreDictionary.registerOre("ingotSilver", new ItemStack(ModItems.silver_ingot));
		OreDictionary.registerOre("honeyDrop", new ItemStack(ModItems.honey));
		OreDictionary.registerOre("dropHoney", new ItemStack(ModItems.honey));
		OreDictionary.registerOre("listAllsugar", new ItemStack(ModItems.honey));
		OreDictionary.registerOre("materialWax", new ItemStack(ModItems.wax));
		OreDictionary.registerOre("materialBeeswax", new ItemStack(ModItems.wax));
		OreDictionary.registerOre("materialPressedWax", new ItemStack(ModItems.wax));
		OreDictionary.registerOre("itemBeeswax", new ItemStack(ModItems.wax));
		OreDictionary.registerOre("foodSalt", new ItemStack(ModItems.salt));
		OreDictionary.registerOre("dustSalt", new ItemStack(ModItems.salt));
		OreDictionary.registerOre("materialSalt", new ItemStack(ModItems.salt));
		OreDictionary.registerOre("lumpSalt", new ItemStack(ModItems.salt));
		OreDictionary.registerOre("salt", new ItemStack(ModItems.salt));
		OreDictionary.registerOre("cropLavender", new ItemStack(ModItems.lavender));
		OreDictionary.registerOre("listAllherb", new ItemStack(ModItems.lavender));
		OreDictionary.registerOre("cropBelladonna", new ItemStack(ModItems.belladonna));
		OreDictionary.registerOre("cropMandrake", new ItemStack(ModItems.mandrake_root));
		OreDictionary.registerOre("cropKelp", new ItemStack(ModItems.kelp));
		OreDictionary.registerOre("cropSeaweed", new ItemStack(ModItems.kelp));
		OreDictionary.registerOre("listAllveggie", new ItemStack(ModItems.kelp));
		OreDictionary.registerOre("listAllgreenveggie", new ItemStack(ModItems.kelp));
		OreDictionary.registerOre("cropAsphodel", new ItemStack(ModItems.asphodel));
		OreDictionary.registerOre("listAllspice", new ItemStack(ModItems.ginger));
		OreDictionary.registerOre("cropGinger", new ItemStack(ModItems.ginger));
		OreDictionary.registerOre("cropMint", new ItemStack(ModItems.mint));
		OreDictionary.registerOre("listAllspice", new ItemStack(ModItems.mint));
		OreDictionary.registerOre("cropSpiceleaf", new ItemStack(ModItems.mint));
		OreDictionary.registerOre("listAllgreenveggie", new ItemStack(ModItems.mint));
		OreDictionary.registerOre("cropThistle", new ItemStack(ModItems.thistle));
		OreDictionary.registerOre("cropGarlic", new ItemStack(ModItems.garlic));
		OreDictionary.registerOre("listAllherb", new ItemStack(ModItems.garlic));
		OreDictionary.registerOre("cropAconitum", new ItemStack(ModItems.aconitum));
		OreDictionary.registerOre("cropWhiteSage", new ItemStack(ModItems.white_sage));
		OreDictionary.registerOre("cropTulsi", new ItemStack(ModItems.tulsi));
		OreDictionary.registerOre("listAllherb", new ItemStack(ModItems.tulsi));
		OreDictionary.registerOre("cropKenaf", new ItemStack(ModItems.kenaf));
		OreDictionary.registerOre("cropSilphium", new ItemStack(ModItems.silphium));
		OreDictionary.registerOre("listAllgreenveggie", new ItemStack(ModItems.silphium));
		OreDictionary.registerOre("listAllherb", new ItemStack(ModItems.silphium));
		OreDictionary.registerOre("listAllspice", new ItemStack(ModItems.silphium));
		OreDictionary.registerOre("listAllspice", new ItemStack(ModItems.wormwood));
		OreDictionary.registerOre("cropWormwood", new ItemStack(ModItems.wormwood));
		OreDictionary.registerOre("feather", new ItemStack(ModItems.chromatic_quill));
		OreDictionary.registerOre("dyeBrown", new ItemStack(ModItems.catechu));
		OreDictionary.registerOre("dyeWhite", new ItemStack(ModItems.albedo));
		OreDictionary.registerOre("dyeBlack", new ItemStack(ModItems.iron_gall_ink));
		OreDictionary.registerOre("dyeGreen", new ItemStack(ModItems.absinthe_green));
		OreDictionary.registerOre("blockWoolWHITE", new ItemStack(Blocks.WOOL, 1, 0));
		OreDictionary.registerOre("blockWoolORANGE", new ItemStack(Blocks.WOOL, 1, 1));
		OreDictionary.registerOre("blockWoolMAGENTA", new ItemStack(Blocks.WOOL, 1, 2));
		OreDictionary.registerOre("blockWoolLIGHT_BLUE", new ItemStack(Blocks.WOOL, 1, 3));
		OreDictionary.registerOre("blockWoolYELLOW", new ItemStack(Blocks.WOOL, 1, 4));
		OreDictionary.registerOre("blockWoolLIME", new ItemStack(Blocks.WOOL, 1, 5));
		OreDictionary.registerOre("blockWoolPINK", new ItemStack(Blocks.WOOL, 1, 6));
		OreDictionary.registerOre("blockWoolGRAY", new ItemStack(Blocks.WOOL, 1, 7));
		OreDictionary.registerOre("blockWoolSILVER", new ItemStack(Blocks.WOOL, 1, 8));
		OreDictionary.registerOre("blockWoolCYAN", new ItemStack(Blocks.WOOL, 1, 9));
		OreDictionary.registerOre("blockWoolPURPLE", new ItemStack(Blocks.WOOL, 1, 10));
		OreDictionary.registerOre("blockWoolBLUE", new ItemStack(Blocks.WOOL, 1, 11));
		OreDictionary.registerOre("blockWoolBROWN", new ItemStack(Blocks.WOOL, 1, 12));
		OreDictionary.registerOre("blockWoolGREEN", new ItemStack(Blocks.WOOL, 1, 13));
		OreDictionary.registerOre("blockWoolRED", new ItemStack(Blocks.WOOL, 1, 14));
		OreDictionary.registerOre("blockWoolBLACK", new ItemStack(Blocks.WOOL, 1, 15));
	}
}
