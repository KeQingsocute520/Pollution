package keqing.pollution.loaders.recipes;

import gregtech.api.GTValues;
import gregtech.api.recipes.ModHandler;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.unification.material.MarkerMaterials;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.UnificationEntry;
import gregtech.common.items.MetaItems;
import gregtech.common.metatileentities.MetaTileEntities;
import keqing.pollution.api.unification.PollutionMaterials;
import keqing.pollution.common.metatileentity.PollutionMetaTileEntities;

import static gregtech.api.unification.material.Materials.Titanium;
import static gregtech.api.unification.material.Materials.TungstenSteel;
import static gregtech.loaders.recipe.CraftingComponent.*;
import static gregtech.loaders.recipe.CraftingComponent.ROTOR;
import static gregtech.loaders.recipe.MetaTileEntityLoader.registerMachineRecipe;

public class CompoundAspectRecipes {
    public static void init(){
        aspect();
    }

    private static void aspect(){
        //这里注册所有要用到的复合要素的配方喵
        //用不到的就不负责喵
        //群友说要用搅拌机 我就用搅拌机喵
        //水晶
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_air.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_earth.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_crystal.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //生命
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_earth.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_water.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_life.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //死亡
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_water.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_entropy.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_death.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //灵魂
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_life.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_death.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_soul.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //武器
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_soul.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_entropy.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_weapon.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //金属
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_earth.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_order.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_metal.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //能量
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_order.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_fire.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_energy.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //工具
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_metal.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_energy.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_instrument.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
        //交换
        RecipeMaps.MIXER_RECIPES.recipeBuilder()
                .fluidInputs(PollutionMaterials.infused_order.getFluid(1000))
                .fluidInputs(PollutionMaterials.infused_entropy.getFluid(1000))
                .fluidOutputs(PollutionMaterials.infused_exchange.getFluid(2000))
                .duration(200)
                .EUt(30)
                .buildAndRegister();
    }
}