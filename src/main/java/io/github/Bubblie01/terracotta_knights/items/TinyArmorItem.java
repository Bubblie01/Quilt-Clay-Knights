package io.github.Bubblie01.terracotta_knights.items;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;

public class TinyArmorItem extends ArmorItem implements TerracottaItemFlag {

	public TinyArmorItem(ArmorMaterial material, ArmorSlot slot, Settings settings) {
		super(material, slot, settings);
	}
}
