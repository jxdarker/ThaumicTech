package com.suicidarker.thaumictech;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;
import thaumcraft.api.ThaumcraftApi;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;

@Mod(modid = ThaumicTech.MODID, name = ThaumicTech.NAME, version = ThaumicTech.VERSION, dependencies = "required-after:techreborn;required-after:thaumcraft")
public class ThaumicTech {
	public static final String MODID = "thaumictech";
	public static final String NAME = "thaumictech";
	public static final String VERSION = "1.0.0";

	public static final CreativeTabs tabThaumicTech = new CreativeTabs("tabThaumicTech") {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(RegisterHandler.DRILL_OF_THE_DESTORYER);
		}

	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
			RegisterHandler.init();
			ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumictech", "research/tools"));


	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		RecipeHandler.forgeOreDictionary();
		RecipeHandler.thaumcraftInfusionRecipe();
		RecipeHandler.techrebornAssemblingMachineRecipe();
		RecipeHandler.vanillaCraftingTableRecipe();
		RecipeHandler.brassRecipeTweaker();
	}
	

}
