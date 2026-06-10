package com.suicidarker.thaumictech.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.suicidarker.thaumictech.RegisterHandler;
import com.suicidarker.thaumictech.ThaumicTech;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.api.power.IEnergyItemInfo;
import reborncore.common.powerSystem.ExternalPowerSystems;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.powerSystem.PoweredItemContainerProvider;
import reborncore.common.powerSystem.forge.ForgePowerItemManager;
import reborncore.common.util.ItemUtils;
import techreborn.items.tools.ItemDrill;
import techreborn.utils.TechRebornCreativeTab;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;

public class DrillOfTheDestoryer extends ItemPickaxe  implements IEnergyItemInfo{
	
	public String name;
	public int maxCharge = 400000;
	public int cost = 250;
	public float unpoweredSpeed = 0.5F;
	public double transferLimit = 1000;

	public DrillOfTheDestoryer(String name) {
		super(ToolMaterial.DIAMOND);
		this.efficiency = 15F;
		this.setMaxStackSize(1);
		this.setCreativeTab(ThaumicTech.tabThaumicTech);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		RegisterHandler.itemSet.add(this);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> itemList) {
		if (!isInCreativeTab(par2CreativeTabs)) {
			return;
		}
		ItemStack stack = new ItemStack(RegisterHandler.DRILL_OF_THE_DESTORYER);
        EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.REFINING, 2);
		ItemStack charged = stack.copy();
		ForgePowerItemManager capEnergy = new ForgePowerItemManager(charged);
		capEnergy.setEnergyStored(capEnergy.getMaxEnergyStored());

		itemList.add(stack);
		itemList.add(charged);
	}
	
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return Items.IRON_PICKAXE.canHarvestBlock(blockIn);
	}
	// ItemPickaxe
	@Override
	public float getDestroySpeed(ItemStack stack, IBlockState state) {
		if (new ForgePowerItemManager(stack).getEnergyStored() < cost) {
			return unpoweredSpeed;
		}
		if (Items.WOODEN_PICKAXE.getDestroySpeed(stack, state) > 1.0F
				|| Items.WOODEN_SHOVEL.getDestroySpeed(stack, state) > 1.0F) {
			return efficiency;
		} else {
			return super.getDestroySpeed(stack, state);
		}
	}
	
	// ItemTool
	@Override
	public boolean onBlockDestroyed(ItemStack stack, World worldIn, IBlockState blockIn, BlockPos pos, EntityLivingBase entityLiving) {
		Random rand = new Random();
		if (rand.nextInt(EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, stack) + 1) == 0) {
			ForgePowerItemManager capEnergy = new ForgePowerItemManager(stack);

			capEnergy.extractEnergy(cost, false);
			ExternalPowerSystems.requestEnergyFromArmor(capEnergy, entityLiving);
		}
		return true;
	}
	
	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase entityliving1) {
		return true;
	}
	
	//Item
	@Override
	public boolean isRepairable() {
		return false;
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return 1 - ItemUtils.getPowerForDurabilityBar(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return PowerSystem.getDisplayPower().colour;
	}

	@Override
	@Nullable
	public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
		return new PoweredItemContainerProvider(stack);
	}

	@Override
	public boolean shouldCauseBlockBreakReset(ItemStack oldStack, ItemStack newStack) {
		return !(newStack.isItemEqual(oldStack));
	}

	// IEnergyItemInfo
	@Override
	public double getMaxPower(ItemStack stack) {
		return maxCharge;
	}

	@Override
	public boolean canAcceptEnergy(ItemStack stack) {
		return true;
	}

	@Override
	public boolean canProvideEnergy(ItemStack stack) {
		return false;
	}

	@Override
	public double getMaxTransfer(ItemStack stack) {
		return transferLimit;
	}
}
