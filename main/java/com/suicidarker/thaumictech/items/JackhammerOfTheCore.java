package com.suicidarker.thaumictech.items;

import java.util.List;

import com.suicidarker.thaumictech.RegisterHandler;
import com.suicidarker.thaumictech.ThaumicTech;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reborncore.common.powerSystem.forge.ForgePowerItemManager;
import techreborn.config.ConfigTechReborn;
import techreborn.items.tools.ItemJackhammer;
import thaumcraft.Thaumcraft;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXScanSource;
import thaumcraft.common.lib.utils.BlockUtils;

public class JackhammerOfTheCore extends ItemJackhammer{
	
	EnumFacing side;
	public int searchCost = 1000;
	public int hitCost = 400;
	
	public JackhammerOfTheCore(String name) {
		super(ToolMaterial.DIAMOND, name, 400000);
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.transferLimit = 1000;
		this.cost = 100;
		this.efficiency = 16F;
		this.setCreativeTab(ThaumicTech.tabThaumicTech);
		RegisterHandler.itemSet.add(this);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(CreativeTabs par2CreativeTabs, NonNullList<ItemStack> itemList) {
		if (!isInCreativeTab(par2CreativeTabs)) {
			return;
		}
		ItemStack stack = new ItemStack(RegisterHandler.JACKHAMMER_OF_THE_CORE);
        EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.SOUNDING, 2);
		ItemStack charged = stack.copy();
		ForgePowerItemManager capEnergy = new ForgePowerItemManager(charged);
		capEnergy.setEnergyStored(capEnergy.getMaxEnergyStored());

		itemList.add(stack);
		itemList.add(charged);
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, IBlockState state, BlockPos pos, EntityLivingBase entityLiving)  {
		if (new ForgePowerItemManager(stack).getEnergyStored() >= cost) {
			new ForgePowerItemManager(stack).extractEnergy(cost, false);
		}
		return true;
	}

	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (player.isSneaking()&&!world.isRemote){
			if (new ForgePowerItemManager(player.getHeldItem(hand)).getEnergyStored() >= searchCost) {
				new ForgePowerItemManager(player.getHeldItem(hand)).extractEnergy(searchCost, false);
			}
		}
		return super.onItemUse(player, world, pos,hand, side, hitX, hitY, hitZ);
	}

    public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
        if (!player.world.isRemote) {
            if (!(entity instanceof EntityPlayer) || FMLCommonHandler.instance().getMinecraftServerInstance().isPVPEnabled()) {
                entity.setFire(2);
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }

	@Override
	public boolean hitEntity(ItemStack itemstack, EntityLivingBase entityliving, EntityLivingBase attacker) {
		if (new ForgePowerItemManager(itemstack).getEnergyStored() >= hitCost) {
			new ForgePowerItemManager(itemstack).extractEnergy(hitCost, false);
			entityliving.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12F);
		}
		return false;
	}

}
