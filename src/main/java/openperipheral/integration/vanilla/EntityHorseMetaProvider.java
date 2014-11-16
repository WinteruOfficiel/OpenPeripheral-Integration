package openperipheral.integration.vanilla;

import java.util.Map;

import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.Vec3;
import openmods.reflection.ReflectionHelper;
import openmods.utils.InventoryUtils;
import openperipheral.api.helpers.EntityMetaProviderSimple;

import com.google.common.collect.Maps;

@SuppressWarnings("serial")
public class EntityHorseMetaProvider extends EntityMetaProviderSimple<EntityHorse> {

	@Override
	public String getKey() {
		return "horse";
	}

	@Override
	public Object getMeta(EntityHorse target, Vec3 relativePos) {
		Map<String, Object> map = Maps.newHashMap();

		map.put("eatingHaystack", target.isEatingHaystack());
		map.put("hasReproduced", target.getHasReproduced());
		map.put("bred", target.func_110205_ce());
		map.put("horseType", target.getHorseType());
		map.put("horseVariant", target.getHorseVariant());
		map.put("horseTemper", target.getTemper());
		map.put("horseTame", target.isTame());
		map.put("ownerUUID", target.func_152119_ch());

		final boolean chested = target.isChested();
		map.put("chestedHorse", chested);
		if (chested) {
			IInventory invent = (IInventory)ReflectionHelper.getProperty("", target, "horseChest", "field_110296_bG");
			map.put("chest", InventoryUtils.getAllItems(invent));
		}

		return map;
	}

}
