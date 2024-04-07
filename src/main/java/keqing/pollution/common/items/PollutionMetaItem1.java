package keqing.pollution.common.items;

import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.items.metaitem.StandardMetaItem;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import keqing.pollution.common.CommonProxy;

public class PollutionMetaItem1  extends StandardMetaItem {

    public PollutionMetaItem1() {
        this.setRegistryName("pollution_meta_item_1");
        setCreativeTab(GregTechAPI.TAB_GREGTECH);
    }
    public void registerSubItems() {
        PollutionMetaItems.TEST=this.addItem(1, "test").setMaxStackSize(64).setCreativeTabs(CommonProxy.Pollution_TAB);

        //空白催化核心，需要注魔合成
        PollutionMetaItems.BLANKCORE=this.addItem(2, "blank_catalyst_core").setMaxStackSize(64).setCreativeTabs(CommonProxy.Pollution_TAB);
        //燃能催化核心，用于高温催化
        PollutionMetaItems.HOTCORE=this.addItem(3, "hot_catalyst_core").setMaxStackSize(64).setCreativeTabs(CommonProxy.Pollution_TAB);
        //冷寂催化核心，用于低温催化
        PollutionMetaItems.COLDCORE=this.addItem(4, "cold_catalyst_core").setMaxStackSize(64).setCreativeTabs(CommonProxy.Pollution_TAB);
        //凝聚催化核心，用于物质的聚合催化
        PollutionMetaItems.INTEGRATECORE=this.addItem(5, "integration_catalyst_core").setMaxStackSize(64).setCreativeTabs(CommonProxy.Pollution_TAB);
        //分离催化核心，用于物质的分解催化
        PollutionMetaItems.SEGREGATECORE=this.addItem(6, "segregation_catalyst_core").setMaxStackSize(64).setCreativeTabs(CommonProxy.Pollution_TAB);

        //魔法系列电路板
        PollutionMetaItems.MAGIC_CIRCUIT_ULV = this.addItem(565, "magic_circuit.ulv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ULV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_LV = this.addItem(566, "magic_circuit.lv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.LV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_MV = this.addItem(567, "magic_circuit.mv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_HV = this.addItem(568, "magic_circuit.hv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.HV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_EV = this.addItem(569, "magic_circuit.ev").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.EV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_IV = this.addItem(570, "magic_circuit.iv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.IV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_LuV = this.addItem(571, "magic_circuit.luv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.LuV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_ZPM = this.addItem(572, "magic_circuit.zpm").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.ZPM).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_UV = this.addItem(573, "magic_circuit.uv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_UHV = this.addItem(574, "magic_circuit.uhv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UHV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_UEV = this.addItem(575, "magic_circuit.uev").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UEV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_UIV = this.addItem(576, "magic_circuit.uiv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UIV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_UXV = this.addItem(577, "magic_circuit.uxv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.UXV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_OpV = this.addItem(578, "magic_circuit.opv").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.OpV).setCreativeTabs(CommonProxy.Pollution_TAB);
        PollutionMetaItems.MAGIC_CIRCUIT_MAX = this.addItem(579, "magic_circuit.max").setUnificationData(OrePrefix.circuit, MarkerMaterials.Tier.MAX).setCreativeTabs(CommonProxy.Pollution_TAB);
    }

}
