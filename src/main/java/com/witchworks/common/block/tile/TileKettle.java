package com.witchworks.common.block.tile;

import com.witchworks.api.KettleRegistry;
import com.witchworks.api.recipe.ItemValidator;
import com.witchworks.api.recipe.KettleItemRecipe;
import com.witchworks.api.ritual.RitualHolder;
import com.witchworks.client.fx.ParticleF;
import com.witchworks.common.WitchWorks;
import com.witchworks.common.core.net.PacketHandler;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static net.minecraftforge.fluids.Fluid.BUCKET_VOLUME;

/**
 * This class was created by Arekkuusu on 08/03/2017.
 * It's distributed as part of Witchworks under
 * the MIT license.
 */
@SuppressWarnings("WeakerAccess")
public class TileKettle extends TileFluidInventory implements ITickable {

	private final String TAG_HEAT = "heat";
	private final String TAG_RGB = "rgb";
	private final String TAG_MODE = "mode";
	private final String TAG_INGREDIENTS = "ingredients";
	private final String TAG_CONTAINER = "container";
	private final KettleFluid inv = tank();

	private int rgb = -14532558;
	private RitualHolder ritual;
	private Mode mode = Mode.NORMAL;
	private ItemStack[] ingredients = new ItemStack[64];
	private ItemStack container;
	private int heat;
	private int ticks;

	@SuppressWarnings("ConstantConditions")
	public void collideItem(EntityItem entityItem) {
		final ItemStack dropped = entityItem.getEntityItem();
		if (dropped == null || entityItem.isDead)
			return;

		if (inv.hasFluid()) {
			if (!inv.hasFluid(FluidRegistry.LAVA)) {
				boolean splash = false;
				if (isBoiling()) {
					splash = recipeDropLogic(dropped);
				}
				if (splash)
					play(SoundEvents.ENTITY_GENERIC_SPLASH, 0.5F, 0.5F);
			} else {
				play(SoundEvents.BLOCK_LAVA_EXTINGUISH, 1F, 1F);
				entityItem.setDead();
				return;
			}
			dropped.setCount(0);
			entityItem.setDead();
		}
	}

	public boolean recipeDropLogic(ItemStack dropped) {
		switch (mode) {
			case NORMAL:
				return processingLogic(dropped)
						|| (inv.hasFluid(FluidRegistry.WATER) && acceptIngredient(dropped));
			case POTION:
				return false;
			case CUSTOM:
				return false;
			default:
		}
		return false;
	}

	public boolean useKettle(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem) {
		if (!world.isRemote) {
			if (heldItem == null) {
				if (getContainer() != null && mode != Mode.RITUAL) {
					giveItem(player, hand, null, getContainer());
					setContainer(null);
				} else if (inv.isFull() && hasIngredients() && mode != Mode.RITUAL) {
					itemRitualLogic();
				}
				return true;
			}
			//Held Item is not null
			if (heldItem.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null)) {
				handleLiquid(heldItem.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null));
			} else if (heldItem.getItem() == Items.POTIONITEM && PotionUtils.getEffectsFromStack(heldItem).isEmpty()) {
				int level = inv.getFluidAmount();
				if (level < BUCKET_VOLUME && (inv.getFluid() == null || inv.hasFluid(FluidRegistry.WATER))) {
					play(SoundEvents.ITEM_BUCKET_FILL, 1F, 1F);
					giveItem(player, hand, heldItem, new ItemStack(Items.GLASS_BOTTLE));
					FluidStack fluidStack = new FluidStack(FluidRegistry.WATER, 250);
					inv.fill(fluidStack, true);
				}
			} else if (heldItem.getItem() == Items.GLASS_BOTTLE) {
				//TODO: Add Potion Logic here
			} else if (getContainer() == null) {
				ItemStack copy = heldItem.copy();
				copy.setCount(1);
				setContainer(copy);
				giveItem(player, hand, heldItem, null);
			}
		}
		return true;
	}

	private void handleLiquid(IFluidHandler handler) {
		if (inv.isEmpty()) {
			FluidStack drain = handler.drain(BUCKET_VOLUME, false);

			if (drain != null && drain.amount <= BUCKET_VOLUME) {
				handler.drain(drain.amount, true);
				inv.setFluid(drain);
				onLiquidChange();

				play(drain.getFluid().getEmptySound(), 1F, 1F);
			}
		} else {
			if (inv.isFull()) {
				FluidStack fill = inv.drain(BUCKET_VOLUME, false);
				FluidStack compare = handler.drain(BUCKET_VOLUME, false);
				int filled = handler.fill(fill, false);

				if (fill != null && (compare == null || fill.isFluidEqual(compare)) && filled <= BUCKET_VOLUME) {
					handler.fill(fill, true);
					inv.drain(filled, true);

					play(fill.getFluid().getEmptySound(), 1F, 1F);
				}
			} else {
				FluidStack drain = handler.drain(BUCKET_VOLUME, false);

				if (drain != null && drain.isFluidEqual(inv.getFluid()) && drain.amount <= BUCKET_VOLUME) {
					handler.drain(drain.amount, true);
					inv.fill(drain, true);

					play(drain.getFluid().getFillSound(), 1F, 1F);
				}
			}
		}
	}

	private void giveItem(EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, @Nullable ItemStack toGive) {
		if (heldItem == null) heldItem.shrink(0); {
			player.setHeldItem(hand, toGive);
		} else if (!player.inventory.addItemStackToInventory(toGive)) {
			player.dropItem(toGive, false);
		} else if (player instanceof EntityPlayerMP) {
			((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
		}
	}

	@Override
	public void update() {
		if (!world.isRemote && ticks % 2 == 0) {
			double x = getPos().getX();
			double y = getPos().getY();
			double z = getPos().getZ();
			AxisAlignedBB box = new AxisAlignedBB(x, y, z, x + 1, y + 0.7D, z + 1);
			final List<EntityItem> entityItemList = world.getEntitiesWithinAABB(EntityItem.class, box);
			entityItemList.forEach(this::collideItem);
		}

		if (inv.hasFluid()) {
			if (!inv.hasFluid(FluidRegistry.LAVA)) {
				if (isBoiling()) {
					handleParticles();
					if (ticks % 5 == 0 && world.rand.nextInt(15) == 0) {
						play(SoundEvents.BLOCK_LAVA_AMBIENT, 0.1F, 1F);
					}
				}
			} else if (ticks % 5 == 0 && world.rand.nextInt(20) == 0) {
				play(SoundEvents.BLOCK_LAVA_AMBIENT, 1F, 1F);
			}
		}

		if (ticks % 20 == 0) {
			handleHeat();
		}

		if (!world.isRemote && mode == Mode.RITUAL && ritual != null) {
			handleRitual();
		}

		++ticks;
	}

	private void handleParticles() {
		if (world.rand.nextInt(10) == 0) {
			float x = getPos().getX();

			float z = getPos().getZ();
			for (int i = 0; i < 4; i++) {
				final float posX = x + MathHelper.clamp(world.rand.nextFloat(), 0.2F, 0.9F);
				final float posY = getParticleLevel();
				final float posZ = z + MathHelper.clamp(world.rand.nextFloat(), 0.2F, 0.9F);
				WitchWorks.proxy.spawnParticle(ParticleF.CAULDRON_BUBBLE, posX, posY, posZ, 0.0D, 0.01D, 0.0D, rgb);
			}
		}
		if (hasIngredients() && ticks % 2 == 0) {
			final float x = getPos().getX() + MathHelper.clamp(world.rand.nextFloat(), 0.2F, 0.9F);
			float y = getParticleLevel();
			final float z = getPos().getZ() + MathHelper.clamp(world.rand.nextFloat(), 0.2F, 0.9F);
			WitchWorks.proxy.spawnParticle(ParticleF.SPARK, x, y, z, 0.0D, 0.1D, 0.0D);
		}
	}

	private void handleHeat() {
		boolean aboveFire = world.getBlockState(getPos().down()).getMaterial() == Material.FIRE;
		if (aboveFire && inv.getFluidAmount() > 0 && heat < 10) {
			++heat;
		} else if ((!aboveFire || !(inv.getFluidAmount() > 0)) && heat > 0) {
			--heat;
		}
	}

	@SuppressWarnings("unchecked")
	private void handleRitual() {
		if (!ritual.isFail()) {
			ritual.update(this);
		} else {
			failHorribly();
		}
	}

	@Override
	void writeDataNBT(NBTTagCompound cmp) {
		saveItems(cmp);
		cmp.setInteger(TAG_HEAT, heat);
		cmp.setInteger(TAG_RGB, rgb);
		cmp.setString(TAG_MODE, mode.name());
		if (ritual != null) {
			ritual.writeNBT(cmp);
		}
	}

	private void saveItems(NBTTagCompound cmp) {
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < ingredients.length; ++i) {
			if (ingredients[i] != null) {
				NBTTagCompound tag = new NBTTagCompound();
				tag.setByte("slot", (byte) i);

				ingredients[i].writeToNBT(tag);
				nbttaglist.appendTag(tag);
			}
		}
		cmp.setTag(TAG_INGREDIENTS, nbttaglist);

		if (container != null) {
			NBTTagCompound tag = new NBTTagCompound();
			container.writeToNBT(tag);
			cmp.setTag(TAG_CONTAINER, tag);
		}
	}

	@Override
	void readDataNBT(NBTTagCompound cmp) {
		loadItems(cmp);
		heat = cmp.getInteger(TAG_HEAT);
		setColorRGB(cmp.getInteger(TAG_RGB));
		setMode(Mode.valueOf(cmp.getString(TAG_MODE)));
		if (cmp.hasKey("ritual_data")) {
			ritual = RitualHolder.newInstance();
			ritual.readNBT(cmp);
		}
	}

	private void loadItems(NBTTagCompound cmp) {
		NBTTagList nbttaglist = cmp.getTagList(TAG_INGREDIENTS, 10);
		ingredients = new ItemStack[64];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("slot");

			if (j >= 0 && j < ingredients.length) {
				this.ingredients[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}

		if (cmp.hasKey(TAG_CONTAINER)) {
			NBTTagCompound tag = cmp.getCompoundTag(TAG_CONTAINER);
			container = ItemStack.loadItemStackFromNBT(tag);
		} else {
			container = null;
		}
	}

	@SuppressWarnings("ConstantConditions")
	@Override
	public void onLiquidChange() {
		ingredients = new ItemStack[64];
		mode = Mode.NORMAL;
		ritual = null;
		Fluid fluid = inv.getInnerFluid();
		if (fluid == FluidRegistry.WATER) {
			setColorRGB(-14532558);
		} else if (fluid != null) {
			setColorRGB(fluid.getBlock().getMapColor(null).colorValue);
		}
		if (!world.isRemote)
			PacketHandler.updateToNearbyPlayers(world, pos);
	}

	public boolean acceptIngredient(ItemStack stack) {
		if (ingredients[ingredients.length - 1] == null) {
			addIngredient(stack);
			final float hue = world.rand.nextFloat();
			final float saturation = (world.rand.nextInt(2000) + 1000) / 7000f;
			final float luminance = 0.25f;
			setColorRGB(Color.getHSBColor(hue, saturation, luminance).getRGB());
			PacketHandler.updateToNearbyPlayers(world, pos);
			return true;
		}
		return false;
	}

	public void addIngredient(ItemStack stack) {
		for (int i = 0; i < ingredients.length; i++) {
			if (ingredients[i] == null) {
				ingredients[i] = stack.copy();
				stack.shrink(0);
				break;
			}
		}
	}

	public boolean isBoiling() {
		return heat == 10;
	}

	public boolean hasIngredients() {
		return ingredients[0] != null;
	}

	public int getColorRGB() {
		return rgb;
	}

	public void setColorRGB(int rgbIn) {
		this.rgb = rgbIn;
	}

	public float getParticleLevel() {
		float level = (float) inv.getFluidAmount() / (Fluid.BUCKET_VOLUME * 2F);
		return getPos().getY() + 0.1F + level;
	}

	@Nullable
	public ItemStack getContainer() {
		return container;
	}

	public void setContainer(@Nullable ItemStack container) {
		this.container = container;
		world.updateComparatorOutputLevel(pos, world.getBlockState(pos).getBlock());
		PacketHandler.updateToNearbyPlayers(world, pos);
	}

	public void setMode(Mode mode) {
		this.mode = mode;
		markDirty();
	}

	public void setRitual(RitualHolder ritual) {
		this.ritual = ritual;
		markDirty();
	}

	//------------------------------------Crafting Logic------------------------------------//

	@SuppressWarnings("ConstantConditions")
	public boolean processingLogic(ItemStack stack) {
		if (!isBoiling() || hasIngredients() || stack.getCount() > 64) return false;
		Map<Item, ItemValidator<ItemStack>> processing = KettleRegistry.getKettleProcessing(inv.getInnerFluid());
		if (processing != null && processing.containsKey(stack.getItem())) {
			ItemValidator<ItemStack> validator = processing.get(stack.getItem());
			Optional<ItemStack> optional = validator.getMatchFor(stack);
			if (optional.isPresent()) {
				ItemStack out = optional.get().copy();
				if (stack.isItemDamaged() && out.isItemStackDamageable())
					out.setItemDamage(stack.getItemDamage());
				out.shrink(0);
				int fluid = inv.getFluidAmount();
				int taken = 0;

				if (stack.stackSize <= 16) {
					taken = 250;
					out.stackSize = stack.stackSize;
					stack.stackSize = 0;
				} else {
					while (stack.stackSize > 0 && taken <= fluid) {
						--stack.stackSize;
						++out.stackSize;
						if (out.stackSize % 16 == 0)
							taken += 250;
					}
				}

				if (out.stackSize > 0) {
					final double x = getPos().getX();
					final double y = getPos().getY() + 1D;
					final double z = getPos().getZ();
					final EntityItem item = new EntityItem(world, x + 0.5D, y, z + 0.5D, out);
					item.motionX = world.rand.nextDouble() * 2 - 1;
					item.motionZ = world.rand.nextDouble() * 2 - 1;
					item.motionY = 0.1D;
					item.setPickupDelay(0);
					world.spawnEntity(item);

					play(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1F, 1F);
					for (int i = 0; i < 4; i++) {
						PacketHandler.spawnParticle(ParticleF.STEAM, world, x + world.rand.nextFloat(), getParticleLevel(), z + world.rand.nextFloat(), 5, 0, 0, 0);
					}

					inv.drain(taken, true);
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public void itemRitualLogic() {
		Optional<KettleItemRecipe> optional = KettleRegistry.getKettleItemRituals().stream().filter(
				i -> i.matches(ingredients)
		).findAny();
		if (optional.isPresent()) {
			KettleItemRecipe recipe = optional.get();
			setRitual(new RitualHolder<>(recipe.getRitual()));
			if (ritual.canPerform(this, world, getPos())) {
				setMode(Mode.RITUAL);
				markDirty();
			} else {
				failHorribly();
			}
		}
	}

	public void failHorribly() {
		play(SoundEvents.ENTITY_GENERIC_EXPLODE, 1F, 1F);
		particleServerSide(EnumParticleTypes.EXPLOSION_HUGE, 0.5, 0.5, 0.5, 0, 0, 0, 5);
		inv.setFluid(null);
		onLiquidChange();

		world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(getPos()).expandXyz(2))
				.forEach(entity -> entity.attackEntityFrom(DamageSource.MAGIC, ingredients.length / 2));
	}

	//------------------------------------Crafting Logic------------------------------------//

	public enum Mode {
		NORMAL,
		RITUAL,
		POTION,
		CUSTOM
	}
}
