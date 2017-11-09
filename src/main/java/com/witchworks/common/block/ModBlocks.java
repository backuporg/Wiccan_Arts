package com.witchworks.common.block;

import com.witchworks.common.block.magic.*;
import com.witchworks.common.block.magic.plants.BlockEmberGrass;
import com.witchworks.common.block.magic.plants.BlockTorchwood;
import com.witchworks.common.block.natural.BlockBeehive;
import com.witchworks.common.block.natural.BlockGemOre;
import com.witchworks.common.block.natural.BlockSaltOre;
import com.witchworks.common.block.natural.BlockSilverOre;
import com.witchworks.common.block.natural.crop.*;
import com.witchworks.common.block.natural.fluid.Fluids;
import com.witchworks.common.block.natural.tree.BlockCypressSapling;
import com.witchworks.common.block.tools.*;
import com.witchworks.common.crafting.VanillaCrafting;
import com.witchworks.common.lib.LibBlockName;
import com.witchworks.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
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
@ObjectHolder(LibMod.MOD_ID)
public final class ModBlocks {

	//Todo: Add new gemstone blocks, and meta the existing ones.

	public static final BlockCrop crop_aconitum = null;
	public static final BlockCrop crop_asphodel = null;
	public static final BlockCrop crop_belladonna = null;
	public static final BlockCrop crop_ginger = null;
	public static final BlockCrop crop_kelp = null;
	public static final BlockCrop crop_mint = null;
	public static final BlockCrop crop_white_sage = null;
	public static final BlockCrop crop_mandrake_root = null;
	public static final BlockCrop crop_lavender = null;
	public static final BlockCrop crop_thistle = null;
	public static final BlockCrop crop_tulsi = null;
	public static final BlockCrop crop_kenaf = null;
	public static final BlockCrop crop_silphium = null;
	public static final BlockCrop crop_garlic = null;
	public static final BlockCrop crop_wormwood = null;
	private static Block PLACE_HOLDER = new Block(Material.AIR);
	//--------------------------------Blocks--------------------------------//
	public static final Block silver_block = PLACE_HOLDER;
	public static final Block silver_ore = PLACE_HOLDER;
	public static final Block moldavite_block = PLACE_HOLDER;
	public static final Block coquina = PLACE_HOLDER;
	public static final Block bloodstone_block = PLACE_HOLDER;
	public static final Block tourmaline_block = PLACE_HOLDER;
	public static final Block malachite_block = PLACE_HOLDER;
	public static final Block tigers_eye_block = PLACE_HOLDER;
	public static final Block nuummite_block = PLACE_HOLDER;
	public static final Block alexandrite_block = PLACE_HOLDER;
	public static final Block jasper_block = PLACE_HOLDER;
	public static final Block amethyst_block = PLACE_HOLDER;
	public static final Block garnet_block = PLACE_HOLDER;
	public static final Block cauldron = PLACE_HOLDER;
	public static final Block candle_large = PLACE_HOLDER;
	public static final Block candle_medium = PLACE_HOLDER;
	public static final Block candle_small = PLACE_HOLDER;
	public static final Block salt_barrier = PLACE_HOLDER;
	public static final Block beehive = PLACE_HOLDER;
	public static final Block altar = PLACE_HOLDER;
	public static final Block oven = PLACE_HOLDER;
	public static final Block apiary = PLACE_HOLDER;
	public static final Block salt_ore = PLACE_HOLDER;
	public static final Block gem_ore = PLACE_HOLDER;
	public static final Block cypress_sapling = PLACE_HOLDER;
	public static final Block chalk = PLACE_HOLDER;
	public static final Block nethersteel = PLACE_HOLDER;
	public static final Block fake_ice = PLACE_HOLDER;
	public static final Block fake_ice_fence = PLACE_HOLDER;
	public static final Block fake_ice_stairs = PLACE_HOLDER;
	public static final Block fake_ice_slab_half = PLACE_HOLDER;
	public static final Block fake_ice_slab_double = PLACE_HOLDER;
	public static final Block torchwood = PLACE_HOLDER;
	public static final Block ember_grass = PLACE_HOLDER;
	private static Block STAIRS_ICE = new Block(Material.ICE);

	private ModBlocks() {

	}

	public static void register(final IForgeRegistry<Block> registry) {
		for (final IFluidBlock fluidBlock : Fluids.MOD_FLUID_BLOCKS) {
			registry.register((Block) fluidBlock);
		}
		//Crops
		registry.registerAll(
				new BlockCrop(LibBlockName.CROP_ACONITUM),
				new BlockCrop(LibBlockName.CROP_ASPHODEL),
				new BlockCrop(LibBlockName.CROP_GINGER),
				new BlockCrop(LibBlockName.CROP_WHITE_SAGE),
				new BlockCrop(LibBlockName.CROP_MANDRAKE),
				new BlockCrop(LibBlockName.CROP_LAVENDER),
				new BlockCrop(LibBlockName.CROP_TULSI),
				new BlockCrop(LibBlockName.CROP_GARLIC),
				new CropWormwood(),
				new CropSilphium(),
				new CropKenaf(),
				new CropThistle(),
				new CropKelp(),
				new CropBelladonna(),
				new CropMint()
		);
		//Ore
		registry.register(new BlockSilverOre());
		registry.register(new BlockSaltOre());
		registry.register(new BlockGemOre());
		//Tool Blocks
		registry.registerAll(
				new BlockCauldron(),
				new BlockOven(),
				new BlockCandleLarge(),
				new BlockCandleMedium(),
				new BlockCandleSmall(),
				new BlockSaltBarrier(),
				new BlockApiary(),
				new BlockAltar(),
				new BlockTorchwood(),
				new BlockEmberGrass(),
				new BlockCypressSapling(),
				new BlockFakeIce(),
				new BlockFakeIceFence(),
				new BlockFakeIceStairs("fake_ice_stairs", STAIRS_ICE.getDefaultState(), Material.ICE),
				new BlockFakeIceSlabDouble("fake_ice_slab_double"),
				new BlockFakeIceSlabHalf("fake_ice_slab_half"),
				new BlockBeehive()
		);
		//Normal Blocks
		registry.registerAll(
				new BlockMod(LibBlockName.MOLDAVITE_BLOCK, Material.ROCK).setSound(SoundType.GLASS).setHardness(5.0F),
				new BlockMod(LibBlockName.SILVER_BLOCK, Material.IRON).setSound(SoundType.METAL).setHardness(5.0F),
				new BlockMod(LibBlockName.NETHERSTEEL, Material.IRON).setSound(SoundType.METAL).setHardness(5.0F),
				new BlockMod(LibBlockName.TOURMALINE_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.BLOODSTONE_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.MALACHITE_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.TIGERS_EYE_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.NUUMMITE_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.ALEXANDRITE_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.JASPER_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.AMETHYST_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.GARNET_BLOCK, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.COQUINA, Material.ROCK).setHardness(5.0F),
				new BlockMod(LibBlockName.CHALK, Material.ROCK).setHardness(5.0F)
		);
	}

	public static void init() {
		VanillaCrafting.blocks();
		initOreDictionary();
	}

	private static void initOreDictionary() {
		OreDictionary.registerOre("coquina", new ItemStack(ModBlocks.coquina));
		OreDictionary.registerOre("limestone", new ItemStack(ModBlocks.coquina));
		OreDictionary.registerOre("blockSilver", new ItemStack(ModBlocks.silver_block));
		OreDictionary.registerOre("blockMoldavite", new ItemStack(ModBlocks.moldavite_block));
		OreDictionary.registerOre("blockBloodstone", new ItemStack(ModBlocks.bloodstone_block));
		OreDictionary.registerOre("oreGarnet", new ItemStack(ModBlocks.gem_ore, 1, 0));
		OreDictionary.registerOre("oreNuummite", new ItemStack(ModBlocks.gem_ore, 1, 2));
		OreDictionary.registerOre("oreAmethyst", new ItemStack(ModBlocks.gem_ore, 1, 8));
		OreDictionary.registerOre("oreAlexandrite", new ItemStack(ModBlocks.gem_ore, 1, 9));
		OreDictionary.registerOre("oreSilver", new ItemStack(ModBlocks.silver_ore));
		OreDictionary.registerOre("oreMoldavite", new ItemStack(ModBlocks.gem_ore, 1, 1));
		OreDictionary.registerOre("oreBloodstone", new ItemStack(ModBlocks.gem_ore, 1, 5));
		OreDictionary.registerOre("oreTourmaline", new ItemStack(ModBlocks.gem_ore, 1, 4));
		OreDictionary.registerOre("oreMalachite", new ItemStack(ModBlocks.gem_ore, 1, 7));
		OreDictionary.registerOre("oreTigersEye", new ItemStack(ModBlocks.gem_ore, 1, 3));
		OreDictionary.registerOre("oreJasper", new ItemStack(ModBlocks.gem_ore, 1, 6));
		OreDictionary.registerOre("blockNethersteel", new ItemStack(ModBlocks.nethersteel));
		OreDictionary.registerOre("oreSalt", new ItemStack(ModBlocks.salt_ore));
		OreDictionary.registerOre("blockTourmaline", new ItemStack(ModBlocks.tourmaline_block));
		OreDictionary.registerOre("chalk", new ItemStack(ModBlocks.chalk));
	}
}
