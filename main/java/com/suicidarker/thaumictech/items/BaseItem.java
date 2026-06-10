package com.suicidarker.thaumictech.items;

import com.suicidarker.thaumictech.RegisterHandler;
import com.suicidarker.thaumictech.ThaumicTech;

import net.minecraft.item.Item;

public class BaseItem extends Item{
	public BaseItem(String name) {
		super();
		this.setRegistryName(name);
		this.setUnlocalizedName(name);
		this.setCreativeTab(ThaumicTech.tabThaumicTech);
		RegisterHandler.itemSet.add(this);
	}
}
