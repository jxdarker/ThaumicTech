package com.suicidarker.thaumictech;

import java.util.HashSet;

import com.suicidarker.thaumictech.items.BaseItem;
import com.suicidarker.thaumictech.items.ChainsawOfTheStream;
import com.suicidarker.thaumictech.items.DrillOfTheDestoryer;
import com.suicidarker.thaumictech.items.JackhammerOfTheCore;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = ThaumicTech.MODID)
public class RegisterHandler {

	public static HashSet<Item> itemSet = new HashSet<Item>();
	
	public static Item THAUMIC_CIRCUIT;
	public static Item THAUMIC_CIRCUIT_BOARD;
	public static Item THAUMIC_CIRCUIT_PARTS;
	public static Item INGOT_OF_THE_THEORY;
	public static Item CHAINSAW_OF_THE_STREAM;
	public static Item JACKHAMMER_OF_THE_CORE;
	public static Item DRILL_OF_THE_DESTORYER;
	public static Item THEORITICAL_INGOT;
	public static Item THEORITICAL_NUGGET;
	public static Item THEORITICAL_PLATE;
	public static Item THEORITICAL_SUBSTANCE;
	
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		for (Item i : itemSet) {
			event.getRegistry().register(i);
		}
	}

	@SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
		for (Item i : itemSet) {
			registerRender(i);
		}
	}

	public static void init() {
		THAUMIC_CIRCUIT = new BaseItem("thaumic_circuit");
		THAUMIC_CIRCUIT_BOARD = new BaseItem("thaumic_circuit_board");
		THAUMIC_CIRCUIT_PARTS = new BaseItem("thaumic_circuit_parts");
		
		CHAINSAW_OF_THE_STREAM = new ChainsawOfTheStream("chainsaw_of_the_stream");
		JACKHAMMER_OF_THE_CORE = new JackhammerOfTheCore("jackhammer_of_the_core");
		DRILL_OF_THE_DESTORYER = new DrillOfTheDestoryer("drill_of_the_destoryer");
		
		THEORITICAL_INGOT = new BaseItem("theoritical_ingot");
		THEORITICAL_NUGGET = new BaseItem("theoritical_nugget");
		THEORITICAL_PLATE = new BaseItem("theoritical_plate"); 
		THEORITICAL_SUBSTANCE = new BaseItem("theoritical_substance");
	}

	private static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
}
