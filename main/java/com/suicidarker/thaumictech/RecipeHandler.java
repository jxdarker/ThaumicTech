package com.suicidarker.thaumictech;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.GameData;
import techreborn.api.recipe.Recipes;
import techreborn.init.ModItems;
import techreborn.init.recipes.RecipeMethods.Type;
import techreborn.items.ingredients.ItemParts;
import techreborn.items.ingredients.ItemPlates;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.internal.CommonInternals;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigRecipes;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

public class RecipeHandler {
	public static void thaumcraftInfusionRecipe() {
		ItemStack stack;

		stack = new ItemStack(RegisterHandler.DRILL_OF_THE_DESTORYER);
		EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.REFINING, 2);
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumictech:DrillOfTheDestoryer"),
				new InfusionRecipe("THAUMATORIUM", stack, 2,
						new AspectList().add(Aspect.FIRE, 30).add(Aspect.METAL, 30),
						new ItemStack(ModItems.STEEL_DRILL, 1),
						new Object[] { ConfigItems.FIRE_CRYSTAL, ConfigItems.ENTROPY_CRYSTAL,
								new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(ItemsTC.ingots, 1, 0),
								new ItemStack(ItemsTC.ingots, 1, 0),
								new ItemStack(RegisterHandler.THAUMIC_CIRCUIT, 1) }));

		stack = new ItemStack(RegisterHandler.CHAINSAW_OF_THE_STREAM);
		EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.COLLECTOR, 1);
		EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.BURROWING, 1);
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumictech:ChainsawOfTheStream"),
				new InfusionRecipe("THAUMATORIUM", stack, 2,
						new AspectList().add(Aspect.WATER, 30).add(Aspect.PLANT, 30),
						new ItemStack(ModItems.STEEL_CHAINSAW, 1),
						new Object[] { ConfigItems.WATER_CRYSTAL, ConfigItems.WATER_CRYSTAL,
								new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(ItemsTC.ingots, 1, 0),
								new ItemStack(ItemsTC.ingots, 1, 0),
								new ItemStack(RegisterHandler.THAUMIC_CIRCUIT, 1) }));

		stack = new ItemStack(RegisterHandler.JACKHAMMER_OF_THE_CORE);
		EnumInfusionEnchantment.addInfusionEnchantment(stack, EnumInfusionEnchantment.SOUNDING, 2);
		ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumictech:JackhammerOfTheCore"),
				new InfusionRecipe("THAUMATORIUM", stack, 2,
						new AspectList().add(Aspect.SENSES, 30).add(Aspect.EARTH, 30),
						new ItemStack(ModItems.STEEL_JACKHAMMER, 1),
						new Object[] { ConfigItems.FIRE_CRYSTAL, ConfigItems.EARTH_CRYSTAL,
								new ItemStack(ItemsTC.nuggets, 1, 10), new ItemStack(ItemsTC.ingots, 1, 0),
								new ItemStack(ItemsTC.ingots, 1, 0),
								new ItemStack(RegisterHandler.THAUMIC_CIRCUIT, 1) }));


	}

	public static void techrebornAssemblingMachineRecipe() {
		Recipes.assemblingMachine.createRecipe().withInput("circuitBasic").withInput(new ItemStack(ItemsTC.plate, 2, 2))
				.withOutput(new ItemStack(RegisterHandler.THAUMIC_CIRCUIT_BOARD, 1)).withEnergyCostPerTick(1)
				.withOperationDuration(9600).register();
		Recipes.assemblingMachine.createRecipe().withInput("plateSilicon").withInput(new ItemStack(ItemsTC.plate, 4, 2))
				.withOutput(new ItemStack(RegisterHandler.THAUMIC_CIRCUIT_BOARD, 2)).withEnergyCostPerTick(1)
				.withOperationDuration(9600).register();
		Recipes.assemblingMachine.createRecipe().withInput(new ItemStack(Items.BLAZE_POWDER))
				.withInput(new ItemStack(ItemsTC.salisMundus))
				.withOutput(new ItemStack(RegisterHandler.THAUMIC_CIRCUIT_PARTS, 2)).withEnergyCostPerTick(1)
				.withOperationDuration(6000).register();
		Recipes.assemblingMachine.createRecipe().withInput(new ItemStack(RegisterHandler.THAUMIC_CIRCUIT_BOARD, 1))
				.withInput(new ItemStack(RegisterHandler.THAUMIC_CIRCUIT_PARTS, 2))
				.withOutput(new ItemStack(RegisterHandler.THAUMIC_CIRCUIT, 1)).withEnergyCostPerTick(2)
				.withOperationDuration(160).register();

	}

	public static void vanillaCraftingTableRecipe() {
		ForgeRegistry<IRecipe> recipeRegistry = (ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES;
		ArrayList<IRecipe> recipes = Lists.newArrayList(recipeRegistry.getValuesCollection());

		for (IRecipe r : recipes) {
			if (r.getRecipeOutput().getItem() == ItemsTC.plate && r.getRecipeOutput().getItemDamage() != 3)
				recipeRegistry.remove(r.getRegistryName());
		}

	}

	public static void forgeOreDictionary() {

	}

	public static void brassRecipeTweaker() {
		
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:Goggles"));
		ThaumcraftApi.getCraftingRecipes().remove(new ResourceLocation("thaumcraft:brassingot"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:mechanism_simple"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:sanitychecker"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:modvision"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:modaggression"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:Tube"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:ArcaneEar"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:MorphicResonator"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:Alembic"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:EssentiaSmelter"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:EssentiaSmelterThaumium"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:EssentiaSmelterVoid"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:PotionSprayer"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:SmelterAux"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:SmelterVent"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:EssentiaTransportIn"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:EssentiaTransportOut"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:MindClockwork"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:GrappleGunTip"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:GrappleGun"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:Condenser"));
		ThaumcraftApi.getCraftingRecipes().remove(new ResourceLocation("thaumcraft:ArcaneBore"));
		ThaumcraftApi.getCraftingRecipes().remove(new ResourceLocation("thaumcraft:CHARMUNDYING"));
		ThaumcraftApi.getCraftingRecipes().remove(new ResourceLocation("thaumcraft:VoidSiphon"));
		
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:babuleamulet"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:babulering"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:babulegirdle"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:BrassBrace"));
		
		ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("thaumictech:brassingot"),
				new CrucibleRecipe("METALLURGY@1",new ItemStack(ItemsTC.ingots, 1, 2) , new ItemStack(ModItems.INGOTS, 1, 1),
						new AspectList().merge(Aspect.TOOL, 5)));
		
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:brasstonuggets"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:nuggetstobrass"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:brassingotstoblock"));
		((ForgeRegistry<IRecipe>) ForgeRegistries.RECIPES).remove(new ResourceLocation("thaumcraft:brassblocktoingots"));
		GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech", "brasstonuggets"), new ResourceLocation(""), new ItemStack(ItemsTC.nuggets, 9, 8), new Object[] { "#", '#', new ItemStack(ItemsTC.ingots, 1, 2) });
		GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech", "nuggetstobrass"), new ResourceLocation(""), new ItemStack(ItemsTC.ingots, 1, 2), new Object[] { "###","###","###", '#', new ItemStack(ItemsTC.nuggets, 1, 8) });
		GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech", "brassingotstoblock"), new ResourceLocation(""), new ItemStack(BlocksTC.metalBlockBrass, 1), new Object[] { "###","###","###", '#', new ItemStack(ItemsTC.ingots, 1, 2) });
		GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech", "brassblocktoingots"), new ResourceLocation(""), new ItemStack(ItemsTC.ingots, 9, 2), new Object[] { "#", '#', new ItemStack(BlocksTC.metalBlockBrass, 1) });

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:mechanism_simple"), new ShapedArcaneRecipe(new ResourceLocation(""), "BASEARTIFICE", 10, new AspectList().add(Aspect.FIRE, 1).add(Aspect.WATER, 1), ItemsTC.mechanismSimple, new Object[] { " B ", "ISI", " B ", 'B', new ItemStack(ItemsTC.plate,1,0), 'I', "plateIron", 'S', "stickWood" }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:sanitychecker"), new ShapedArcaneRecipe(new ResourceLocation(""), "WARP", 20, new AspectList().add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1), ItemsTC.sanityChecker, new Object[] { "BN ", "M N", "BN ", 'N', new ItemStack(ItemsTC.nuggets, 1, 8), 'B', new ItemStack(ItemsTC.brain), 'M', new ItemStack(ItemsTC.mirroredGlass) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:Goggles"), new ShapedArcaneRecipe(new ResourceLocation(""), "UNLOCKARTIFICE", 50, null, new ItemStack(ItemsTC.goggles), new Object[] { "LGL", "L L", "TGT", 'T', new ItemStack(ItemsTC.thaumometer), 'G', new ItemStack(ItemsTC.ingots, 1, 2), 'L', "leather" }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:modvision"), new ShapedArcaneRecipe(new ResourceLocation(""), "GOLEMVISION", 50, new AspectList().add(Aspect.WATER, 1), new ItemStack(ItemsTC.modules, 1, 0), new Object[] { "B B", "E E", "PGP", 'B', new ItemStack(Items.GLASS_BOTTLE), 'E', new ItemStack(Items.FERMENTED_SPIDER_EYE), 'P', new ItemStack(ItemsTC.plate,1,0), 'G', new ItemStack(ItemsTC.mechanismSimple) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:modaggression"), new ShapedArcaneRecipe(new ResourceLocation(""), "SEALGUARD", 50, new AspectList().add(Aspect.FIRE, 1), new ItemStack(ItemsTC.modules, 1, 1), new Object[] { " R ", "RTR", "PGP", 'R', "paneGlass", 'T', new ItemStack(Items.BLAZE_POWDER), 'P', new ItemStack(ItemsTC.plate,1,0), 'G', new ItemStack(ItemsTC.mechanismSimple) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:Tube"), new ShapedArcaneRecipe(new ResourceLocation(""), "TUBES", 10, null, new ItemStack(BlocksTC.tube, 8, 0), new Object[] { " Q ", "IGI", " B ", 'I', "plateIron", 'B', new ItemStack(ItemsTC.nuggets, 1, 8), 'G', "blockGlass", 'Q', "nuggetQuicksilver" }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:ArcaneEar"), new ShapedArcaneRecipe(new ResourceLocation(""), "ARCANEEAR", 15, new AspectList().add(Aspect.AIR, 1), new ItemStack(BlocksTC.arcaneEar), new Object[] { "P P", " G ", "WRW", 'W', "slabWood", 'R', Items.REDSTONE, 'G', new ItemStack(ItemsTC.mechanismSimple), 'P', new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:MorphicResonator"), new ShapedArcaneRecipe(new ResourceLocation(""), "BASEALCHEMY", 50, new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ItemStack(ItemsTC.morphicResonator), new Object[] { " G ", "BSB", " G ", 'G', "paneGlass", 'B', new ItemStack(ItemsTC.plate,1,0), 'S', new ItemStack(ItemsTC.nuggets, 1, 10) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:Alembic"), new ShapedArcaneRecipe(new ResourceLocation(""), "ESSENTIASMELTER", 50, new AspectList().add(Aspect.WATER, 1), new ItemStack(BlocksTC.alembic), new Object[] { "WFW", "SBS", "WFW", 'W', new ItemStack(BlocksTC.plankGreatwood), 'B', Items.BUCKET, 'F', new ItemStack(ItemsTC.filter), 'S', new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:EssentiaSmelter"), new ShapedArcaneRecipe(new ResourceLocation(""), "ESSENTIASMELTER@2", 50, new AspectList().add(Aspect.FIRE, 1), new ItemStack(BlocksTC.smelterBasic), new Object[] { "BCB", "SFS", "SSS", 'C', new ItemStack(BlocksTC.crucible), 'F', new ItemStack(Blocks.FURNACE), 'S', "cobblestone", 'B', new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:EssentiaSmelterThaumium"), new ShapedArcaneRecipe(new ResourceLocation(""), "ESSENTIASMELTERTHAUMIUM", 250, new AspectList().add(Aspect.FIRE, 2), new ItemStack(BlocksTC.smelterThaumium), new Object[] { "BFB", "IGI", "III", 'F', new ItemStack(BlocksTC.smelterBasic), 'G', new ItemStack(BlocksTC.metalAlchemical), 'I', "plateThaumium", 'B', new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:EssentiaSmelterVoid"), new ShapedArcaneRecipe(new ResourceLocation(""), "ESSENTIASMELTERVOID", 750, new AspectList().add(Aspect.FIRE, 3), new ItemStack(BlocksTC.smelterVoid), new Object[] { "BFB", "IGI", "III", 'F', new ItemStack(BlocksTC.smelterBasic), 'G', new ItemStack(BlocksTC.metalAlchemicalAdvanced), 'I', "plateVoid", 'B', new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:PotionSprayer"), new ShapedArcaneRecipe(new ResourceLocation(""), "POTIONSPRAYER", 75, new AspectList().add(Aspect.WATER, 1).add(Aspect.FIRE, 1), new ItemStack(BlocksTC.potionSprayer), new Object[] { "BDB", "IAI", "ICI", 'B', new ItemStack(ItemsTC.plate,1,0), 'I', "plateIron", 'A', new ItemStack(Items.BREWING_STAND), 'D', new ItemStack(Blocks.DISPENSER), 'C', new ItemStack(BlocksTC.metalAlchemical) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:SmelterAux"), new ShapedArcaneRecipe(new ResourceLocation(""), "IMPROVEDSMELTING", 100, new AspectList().add(Aspect.AIR, 1).add(Aspect.EARTH, 1), new ItemStack(BlocksTC.smelterAux), new Object[] { "WTW", "RGR", "IBI", 'W', new ItemStack(BlocksTC.plankGreatwood), 'B', new ItemStack(BlocksTC.bellows), 'R', new ItemStack(ItemsTC.plate,1,0), 'T', new ItemStack(BlocksTC.tubeFilter), 'I', "plateIron", 'G', new ItemStack(BlocksTC.metalAlchemical) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:SmelterVent"), new ShapedArcaneRecipe(new ResourceLocation(""), "IMPROVEDSMELTING2", 150, new AspectList().add(Aspect.AIR, 1), new ItemStack(BlocksTC.smelterVent), new Object[] { "IBI", "MGF", "IBI", 'I', "plateIron", 'B', new ItemStack(ItemsTC.plate,1,0), 'F', new ItemStack(ItemsTC.filter), 'M', new ItemStack(ItemsTC.filter), 'G', new ItemStack(BlocksTC.metalAlchemical) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:EssentiaTransportIn"), new ShapedArcaneRecipe(new ResourceLocation(""), "ESSENTIATRANSPORT", 100, new AspectList().add(Aspect.AIR, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.essentiaTransportInput), new Object[] { "   ", "BQB", "IGI", 'I', "plateIron", 'B', new ItemStack(ItemsTC.plate,1,0), 'Q', new ItemStack(Blocks.DISPENSER), 'G', new ItemStack(BlocksTC.metalAlchemical) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:EssentiaTransportOut"), new ShapedArcaneRecipe(new ResourceLocation(""), "ESSENTIATRANSPORT", 100, new AspectList().add(Aspect.AIR, 1).add(Aspect.WATER, 1), new ItemStack(BlocksTC.essentiaTransportOutput), new Object[] { "   ", "BQB", "IGI", 'I', "plateIron", 'B', new ItemStack(ItemsTC.plate,1,0), 'Q', new ItemStack((Block)Blocks.HOPPER), 'G', new ItemStack(BlocksTC.metalAlchemical) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:MindClockwork"), new ShapedArcaneRecipe(new ResourceLocation(""), "MINDCLOCKWORK@2", 25, new AspectList().add(Aspect.FIRE, 1).add(Aspect.ORDER, 1), new ItemStack(ItemsTC.mind, 1, 0), new Object[] { " P ", "PGP", "BCB", 'G', new ItemStack(ItemsTC.mechanismSimple), 'B', new ItemStack(ItemsTC.plate,1,0), 'P', "paneGlass", 'C', new ItemStack(Items.COMPARATOR) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:GrappleGunTip"), new ShapedArcaneRecipe(new ResourceLocation(""), "GRAPPLEGUN", 25, new AspectList().add(Aspect.EARTH, 1), new ItemStack(ItemsTC.grappleGunTip), new Object[] { "BRB", "RHR", "BRB", 'B', new ItemStack(ItemsTC.plate,1,0), 'R', new ItemStack(ItemsTC.nuggets, 1, 10), 'H', new ItemStack((Block)Blocks.TRIPWIRE_HOOK) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:GrappleGun"), new ShapedArcaneRecipe(new ResourceLocation(""), "GRAPPLEGUN", 75, new AspectList().add(Aspect.AIR, 1).add(Aspect.FIRE, 1), new ItemStack(ItemsTC.grappleGun), new Object[] { "  S", "TII", " BW", 'B', new ItemStack(ItemsTC.plate,1,0), 'I', "plateIron", 'T', new ItemStack(ItemsTC.grappleGunTip), 'W', "plankWood", 'S', new ItemStack(ItemsTC.grappleGunSpool) }));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("thaumictech:Condenser"), new ShapedArcaneRecipe(new ResourceLocation(""), "FLUXCLEANUP", 500, new AspectList().add(Aspect.AIR, 5).add(Aspect.WATER, 5).add(Aspect.ENTROPY, 5), new ItemStack(BlocksTC.condenser), new Object[] { "BCB", "WMW", "BTB", 'T', new ItemStack(BlocksTC.tube), 'C', new ItemStack(ItemsTC.morphicResonator), 'W', "plankWood", 'M', new ItemStack(ItemsTC.mechanismComplex), 'B', new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumictech:ArcaneBore"), new InfusionRecipe("ARCANEBORE", new ItemStack(ItemsTC.turretPlacer, 1, 2), 4, new AspectList().add(Aspect.ENERGY, 25).add(Aspect.EARTH, 25).add(Aspect.MECHANISM, 100).add(Aspect.VOID, 25).add(Aspect.MOTION, 25), new ItemStack(ItemsTC.turretPlacer), new Object[] { new ItemStack(BlocksTC.plankGreatwood), new ItemStack(BlocksTC.plankGreatwood), new ItemStack(ItemsTC.mechanismComplex), new ItemStack(ItemsTC.plate,1,0), Ingredient.fromItem(Items.DIAMOND_PICKAXE), Ingredient.fromItem(Items.DIAMOND_SHOVEL), new ItemStack(ItemsTC.morphicResonator), new ItemStack(ItemsTC.nuggets, 1, 10) }));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumictech:CHARMUNDYING"), new InfusionRecipe("CHARMUNDYING", new ItemStack(ItemsTC.charmUndying), 2, new AspectList().add(Aspect.LIFE, 25), new ItemStack(Items.TOTEM_OF_UNDYING), new Object[] { new ItemStack(ItemsTC.plate,1,0) }));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("thaumictech:VoidSiphon"), new InfusionRecipe("VOIDSIPHON", new ItemStack(BlocksTC.voidSiphon), 7, new AspectList().add(Aspect.ELDRITCH, 50).add(Aspect.ENTROPY, 50).add(Aspect.VOID, 100).add(Aspect.CRAFT, 50), new ItemStack(BlocksTC.metalBlockVoid), new Object[] { new ItemStack(BlocksTC.stoneArcane), new ItemStack(BlocksTC.stoneArcane), new ItemStack(ItemsTC.mechanismComplex), new ItemStack(ItemsTC.plate,1,0), new ItemStack(ItemsTC.plate,1,0), new ItemStack(Items.NETHER_STAR) }));
        GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech","babuleamulet"), new ResourceLocation(""), new ItemStack(ItemsTC.baubles, 1, 0), new Object[] { " S ", "S S", " I ", 'S', "string", 'I', new ItemStack(ItemsTC.ingots, 1, 2) });
        GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech","babulering"), new ResourceLocation(""), new ItemStack(ItemsTC.baubles, 1, 1), new Object[] { "NNN", "N N", "NNN", 'N', new ItemStack(ItemsTC.nuggets, 1, 8) });
        GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech","babulegirdle"), new ResourceLocation(""), new ItemStack(ItemsTC.baubles, 1, 2), new Object[] { " L ", "L L", " I ", 'L', "leather", 'I', new ItemStack(ItemsTC.ingots, 1, 2) });
        GameRegistry.addShapedRecipe(new ResourceLocation("thaumictech","BrassBrace"), new ResourceLocation(""), new ItemStack(ItemsTC.jarBrace, 2), new Object[] { "NSN", "S S", "NSN", 'N', new ItemStack(ItemsTC.nuggets, 1, 8), 'S', "stickWood" });  

		for (ItemStack s : OreDictionary.getOres("ingotBrass")) {
			if (s.getItem() == ItemsTC.ingots) {
				OreDictionary.getOres("ingotBrass").remove(s);
			}
		}
		for (ItemStack s : OreDictionary.getOres("nuggetBrass")) {
			if (s.getItem() == ItemsTC.nuggets) {
				OreDictionary.getOres("nuggetBrass").remove(s);
			}
		}
		for (ItemStack s : OreDictionary.getOres("plateBrass")) {
			if (s.getItem() == ItemsTC.plate) {
				OreDictionary.getOres("plateBrass").remove(s);
			}
		}
		
		Recipes.plateBendingMachine.createRecipe()
		.withInput(new ItemStack(ItemsTC.ingots, 1, 2))
		.withOutput(new ItemStack(ItemsTC.plate, 1 , 0))
		.withEnergyCostPerTick(25)
		.withOperationDuration(40)
		.register();
		
	}
}