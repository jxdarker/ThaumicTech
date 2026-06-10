package com.suicidarker.thaumictech.items;

import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.suicidarker.thaumictech.RegisterHandler;
import com.suicidarker.thaumictech.ThaumicTech;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.api.power.IEnergyItemInfo;
import reborncore.common.powerSystem.ExternalPowerSystems;
import reborncore.common.powerSystem.PowerSystem;
import reborncore.common.powerSystem.PoweredItemContainerProvider;
import reborncore.common.powerSystem.forge.ForgePowerItemManager;
import reborncore.common.util.ItemUtils;
import techreborn.config.ConfigTechReborn;
import techreborn.init.ModItems;
import techreborn.items.tools.ItemChainsaw;
import techreborn.utils.TechRebornCreativeTab;
import thaumcraft.common.items.tools.ItemElementalAxe;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;

public class ChainsawOfTheStream extends ItemChainsaw{
	
	public String name;
	
	public ChainsawOfTheStream(String name) {
		super(ToolMaterial.DIAMOND, name, 400000, 1F);
		this.setCreativeTab(ThaumicTech.tabThaumicTech);
		this.addPropertyOverride(new ResourceLocation("thaumictech:animated"), new IItemPropertyGetter() {
			@Override
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				if (!stack.isEmpty() && new ForgePowerItemManager(stack).getEnergyStored() >= cost
						&& entityIn != null && entityIn.getHeldItemMainhand().equals(stack)) {
					return 1.0F;
				}
				return 0.0F;
			}
		});
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.transferLimit = 1000;
		this.cost = 250;
		RegisterHandler.itemSet.add(this);
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> itemList) {
		if (!isInCreativeTab(par2CreativeTabs)) {
			return;
		}
		ItemStack stack = new ItemStack(RegisterHandler.CHAINSAW_OF_THE_STREAM);
        EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.BURROWING, 1);
        EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.COLLECTOR, 1);
		ItemStack charged = stack.copy();
		ForgePowerItemManager capEnergy = new ForgePowerItemManager(charged);
		capEnergy.setEnergyStored(capEnergy.getMaxEnergyStored());

		itemList.add(stack);
		itemList.add(charged);
	}

	@Override
	public boolean canHarvestBlock(IBlockState blockIn) {
		return Items.DIAMOND_AXE.canHarvestBlock(blockIn);
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		if ((!player.isSneaking())&&(new ForgePowerItemManager(itemstack).getEnergyStored() >= cost)) {
			if (!player.world.isRemote) {
				new ForgePowerItemManager(itemstack).extractEnergy(cost, false);
			}
			return true;
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}
}
