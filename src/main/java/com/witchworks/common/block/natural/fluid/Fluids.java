package com.witchworks.common.block.natural.fluid;

import com.witchworks.common.lib.LibMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.IFluidBlock;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * This class was created by Arekkuusu on 03/05/2017.
 * It's distributed as part of Wiccan Arts under
 * the MIT license.
 */
@SuppressWarnings ("WeakerAccess")
public final class Fluids {

	public static final Set<IFluidBlock> MOD_FLUID_BLOCKS = new HashSet<>();

	public static final Fluid HONEY = createFluid("honey", false
			, fluid -> fluid.setLuminosity(10)
					.setEmptySound(SoundEvents.ITEM_BUCKET_EMPTY_LAVA)
					.setFillSound(SoundEvents.ITEM_BUCKET_FILL_LAVA)
					.setDensity(1500).setViscosity(8000)
			, fluid -> new BlockFluid(fluid, new MaterialLiquid(MapColor.YELLOW)) {
				@Override
				public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
					if (entityIn instanceof EntityLivingBase)
						((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 60));
				}
			}, true);

	public static final Fluid MUNDANE_OIL = createFluid("oil_mundane", true
			, fluid -> fluid.setDensity(800).setViscosity(4000)
			, fluid -> new BlockFluid(fluid, new MaterialLiquid(MapColor.GREEN)), true);

	public static final Fluid SOOTHING_OIL = createFluid("oil_soothing", true
			, fluid -> fluid.setDensity(800).setViscosity(4000)
			, fluid -> new BlockFluid(fluid, new MaterialLiquid(MapColor.BROWN)), true);

	public static final Fluid SPICY_OIL = createFluid("oil_spicy", true
			, fluid -> fluid.setDensity(800).setViscosity(4000)
			, fluid -> new BlockFluid(fluid, new MaterialLiquid(MapColor.ADOBE)), true);

	public static final Fluid TOXIC_OIL = createFluid("oil_toxic", true
			, fluid -> fluid.setDensity(800).setViscosity(4000)
			, fluid -> new BlockFluid(fluid, new MaterialLiquid(MapColor.GREEN)), true);

	public static final Fluid MAGIC_OIL = createFluid("oil_magical", true
			, fluid -> fluid.setDensity(800).setViscosity(4000)
			, fluid -> new BlockFluid(fluid, new MaterialLiquid(MapColor.EMERALD)), true);

	private Fluids() {
	}

	private static <T extends Block & IFluidBlock> Fluid createFluid(String name, boolean hasFlowIcon, Consumer<Fluid> fluidPropertyApplier, Function<Fluid, T> blockFactory, boolean hasBucket) {
		final ResourceLocation still = new ResourceLocation(LibMod.MOD_ID + ":blocks/fluid/" + name + "_still");
		final ResourceLocation flowing = hasFlowIcon ? new ResourceLocation(LibMod.MOD_ID + ":blocks/fluid/" + name + "_flow") : still;

		Fluid fluid = new Fluid(name, still, flowing);
		final boolean useOwnFluid = FluidRegistry.registerFluid(fluid);

		if (useOwnFluid) {
			fluidPropertyApplier.accept(fluid);
			MOD_FLUID_BLOCKS.add(blockFactory.apply(fluid));
			if (hasBucket)
				FluidRegistry.addBucketForFluid(fluid);
		} else {
			fluid = FluidRegistry.getFluid(name);
		}

		return fluid;
	}
}
