package keqing.pollution.common.metatileentity.multiblock;

import gregicality.multiblocks.api.render.GCYMTextures;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.IFastRenderMetaTileEntity;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.GTTransferUtils;
import gregtech.api.util.RelativeDirection;
import gregtech.api.util.interpolate.Eases;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.IRenderSetup;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;
import gregtech.client.shader.postprocessing.BloomEffect;
import gregtech.client.shader.postprocessing.BloomType;
import gregtech.client.utils.*;
import gregtech.common.ConfigHolder;
import gregtech.common.blocks.BlockGlassCasing;
import gregtech.common.blocks.BlockTurbineCasing;
import gregtech.common.blocks.MetaBlocks;
import keqing.pollution.api.metatileentity.POMultiblockAbility;
import keqing.pollution.api.unification.PollutionMaterials;
import keqing.pollution.client.textures.POTextures;
import keqing.pollution.common.block.PollutionMetaBlock.POMagicBlock;
import keqing.pollution.common.block.PollutionMetaBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.essentia.TileJarFillable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class MetaTileEntityTankTest extends MultiblockWithDisplayBase implements IBloomEffect , IFastRenderMetaTileEntity {
    int aX = 0;
    int aY = 0;
    int aZ = 0;
    public Aspect al;
    public String name = null;
    public int storage = 0;
    public int number = 0;
    FluidStack AIR_STACK;
    FluidStack FIRE_STACK;
    FluidStack WATER_STACK;
    FluidStack ERATH_STACK;
    FluidStack ORDER_STACK;
    FluidStack ENTROPY_STACK;
    int RadomTime=0;
    protected IMultipleTankHandler outputFluidInventory;
    public MetaTileEntityTankTest(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.AIR_STACK = PollutionMaterials.infused_air.getFluid(1);
        this.FIRE_STACK = PollutionMaterials.infused_fire.getFluid(1);
        this.WATER_STACK = PollutionMaterials.infused_water.getFluid(1);
        this.ERATH_STACK = PollutionMaterials.infused_earth.getFluid(1);
        this.ORDER_STACK = PollutionMaterials.infused_order.getFluid(1);
        this.ENTROPY_STACK = PollutionMaterials.infused_entropy.getFluid(1);
    }
    protected void initializeAbilities() {
        this.outputFluidInventory = new FluidTankList(this.allowSameFluidFillForOutputs(), this.getAbilities(MultiblockAbility.EXPORT_FLUIDS));
    }
    protected boolean allowSameFluidFillForOutputs() {
        return true;
    }
    private void resetTileAbilities() {
        this.outputFluidInventory = new FluidTankList(true, new IFluidTank[0]);
    }

    public void invalidateStructure() {
        super.invalidateStructure();
        this.resetTileAbilities();
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityTankTest(this.metaTileEntityId);
    }
    public boolean fillTanks(FluidStack stack, boolean simulate) {
        return GTTransferUtils.addFluidsToFluidHandler(this.getOutputFluidInventory(), simulate, Collections.singletonList(stack));
    }
    public IMultipleTankHandler getOutputFluidInventory() {
        return this.outputFluidInventory;
    }
    public void updateFormedValid() {
        super.update();
        if (this.getWorld().getTileEntity(this.getPos().add(0, 2, 0)) instanceof TileJarFillable) {
            TileJarFillable s = (TileJarFillable)this.getWorld().getTileEntity(this.getPos().add(0, 2, 0));
            this.al = s.getEssentiaType(s.getFacing());
            this.storage = s.getEssentiaAmount(s.getFacing());
            if (this.al != null) {
                this.name = this.al.getName();
            }
        }

        if (this.number == 0) {
            this.number = this.storage;
            this.clearInfused();
            this.name = null;
        }

        this.doFillTank();

        if(RadomTime<10)RadomTime++;
        else RadomTime=0;
        setFusionRingColor(0xFF000000+RadomTime*100);
    }

    private void doFillTank() {
        if (Objects.equals(this.name, "Aer") && this.number > 0) {
            this.fillTanks(this.AIR_STACK, false);
            --this.number;
        }

        if (Objects.equals(this.name, "Terra") && this.number > 0) {
            this.fillTanks(this.ERATH_STACK, false);
            --this.number;
        }

        if (Objects.equals(this.name, "Aqua") && this.number > 0) {
            this.fillTanks(this.WATER_STACK, false);
            --this.number;
        }

        if (Objects.equals(this.name, "Ignis") && this.number > 0) {
            this.fillTanks(this.FIRE_STACK, false);
            --this.number;
        }

        if (Objects.equals(this.name, "Ordo") && this.number > 0) {
            this.fillTanks(this.ORDER_STACK, false);
            --this.number;
        }

        if (Objects.equals(this.name, "Perdition") && this.number > 0) {
            this.fillTanks(this.ENTROPY_STACK, false);
            --this.number;
        }

    }

    public void clearInfused() {
        if (this.getWorld().getTileEntity(this.getPos().add(0, 2, 0)) instanceof TileJarFillable) {
            TileJarFillable s = (TileJarFillable)this.getWorld().getTileEntity(this.getPos().add(0, 2, 0));
            s.amount = 0;
        }

    }

    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        this.initializeAbilities();
    }

    protected void addDisplayText(List<ITextComponent> textList) {
        super.addDisplayText(textList);
        if (this.isStructureFormed()) {
            textList.add(new TextComponentTranslation("Infused: %s  Amount: %s", new Object[]{this.name, this.storage}));
        }

        textList.add(new TextComponentTranslation("Infused: %s  Amount: %s", new Object[]{this.name, this.number}));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        data.setInteger("number", this.number);
        return super.writeToNBT(data);
    }

    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.number = data.getInteger("number");
    }

    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        buf.writeInt(this.number);
    }

    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.number = buf.readInt();
    }
    @Override
    protected  BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("XXXXX","X###X", "X###X", "X###X", "#####", "#####")
                .aisle("XXXXX","#XXX#", "#XXX#", "#X#X#", "#X#X#", "#X#X#")
                .aisle("XXXXX","#XXX#", "#XXX#", "#####", "#####", "#####")
                .aisle("XXXXX","#XSX#", "#XFX#", "#X#X#", "#X#X#", "#X#X#")
                .aisle("XXXXX","X###X", "X###X", "X###X", "#####", "#####")
                .where('S', selfPredicate())
                .where('X', states(getCasingState()).setMinGlobalLimited(25).or(autoAbilities()))
                .where('#', any())
                .where('F', abilities(POMultiblockAbility.TANK_HATCH).setMaxGlobalLimited(1).setPreviewCount(1))
                .build();
    }

    private static IBlockState getCasingState() {
        return PollutionMetaBlocks.MAGIC_BLOCK
                .getState(POMagicBlock.MagicBlockType.VOID_PRISM);
    }

    private static IBlockState getCasingState2() {
        return MetaBlocks.TURBINE_CASING.getState(BlockTurbineCasing.TurbineCasingType.STEEL_GEARBOX);
    }

    private static IBlockState getCasingState3() {
        return MetaBlocks.TRANSPARENT_CASING.getState(BlockGlassCasing.CasingType.TEMPERED_GLASS);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return POTextures.VOID_PRISM;
    }

    @Override
    protected  OrientedOverlayRenderer getFrontOverlay() {
        return GCYMTextures.LARGE_BENDER_OVERLAY;
    }

    protected static final int NO_COLOR = 0;
    private int fusionRingColor = NO_COLOR;
    private boolean registeredBloomRenderTicket;
    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }
    protected int getFusionRingColor() {
        return this.fusionRingColor;
    }
    protected boolean hasFusionRingColor() {
        return true;
    }
    protected void setFusionRingColor(int fusionRingColor) {
        if (this.fusionRingColor != fusionRingColor) {
            this.fusionRingColor = fusionRingColor;
            //writeCustomData(GregtechDataCodes.UPDATE_COLOR, buf -> buf.writeVarInt(fusionRingColor));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderMetaTileEntity(double x, double y, double z, float partialTicks) {
        if (this.hasFusionRingColor() && !this.registeredBloomRenderTicket) {
            this.registeredBloomRenderTicket = true;
            BloomEffectUtil.registerBloomRender(FusionBloomSetup.INSTANCE, getBloomType(), this, this);
        }
    }
    private static BloomType getBloomType() {
        ConfigHolder.FusionBloom fusionBloom = ConfigHolder.client.shader.fusionBloom;
        return BloomType.fromValue(fusionBloom.useShader ? fusionBloom.bloomStyle : -1);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void renderBloomEffect(BufferBuilder buffer, EffectRenderContext context) {
        int color = RenderUtil.interpolateColor(this.getFusionRingColor(), -1, Eases.QUAD_IN.getInterpolation(
                Math.abs((Math.abs(getOffsetTimer() % 50) + context.partialTicks()) - 25) / 25));
        float a = (float) (color >> 24 & 255) / 255.0F;
        float r = (float) (color >> 16 & 255) / 255.0F;
        float g = (float) (color >> 8 & 255) / 255.0F;
        float b = (float) (color & 255) / 255.0F;
        EnumFacing relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(),
                isFlipped());
        EnumFacing.Axis axis = RelativeDirection.UP.getRelativeFacing(getFrontFacing(), getUpwardsFacing(), isFlipped())
                .getAxis();

        RenderBufferHelper.renderRing(buffer,
                getPos().getX() - context.cameraX() + relativeBack.getXOffset() * 1 + 0.5,
                getPos().getY() - context.cameraY() + relativeBack.getYOffset() * 1 + 8,
                getPos().getZ() - context.cameraZ() + relativeBack.getZOffset() * 1 + 0.5,
                1, 1, 10, 20,
                r, g, b, a, axis);

        RenderBufferHelper.renderRing(buffer,
                getPos().getX() - context.cameraX() + relativeBack.getXOffset() * 1 + 0.5,
                getPos().getY() - context.cameraY() + relativeBack.getYOffset() * 1 + 8,
                getPos().getZ() - context.cameraZ() + relativeBack.getZOffset() * 1 + 0.5,
                1, 1, 10, 20,
                r, g, b, a, EnumFacing.Axis.X);

        RenderBufferHelper.renderRing(buffer,
                getPos().getX() - context.cameraX() + relativeBack.getXOffset() * 1 + 0.5,
                getPos().getY() - context.cameraY() + relativeBack.getYOffset() * 1 + 8,
                getPos().getZ() - context.cameraZ() + relativeBack.getZOffset() * 1 + 0.5,
                1, 1, 10, 20,
                r, g, b, a, EnumFacing.Axis.Z);


        RenderBufferHelper.renderRing(buffer,
                getPos().getX() - context.cameraX() + relativeBack.getXOffset() * 1 + 0.5,
                getPos().getY() - context.cameraY() + relativeBack.getYOffset() * 1 + 8,
                getPos().getZ() - context.cameraZ() + relativeBack.getZOffset() * 1 + 0.5,
                RadomTime, 0.2, 10, 20,
                r, g, b, a, EnumFacing.Axis.Y);

    }
    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldRenderBloomEffect( EffectRenderContext context) {
        return this.hasFusionRingColor();
    }
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        EnumFacing relativeRight = RelativeDirection.RIGHT.getRelativeFacing(getFrontFacing(), getUpwardsFacing(),
                isFlipped());
        EnumFacing relativeBack = RelativeDirection.BACK.getRelativeFacing(getFrontFacing(), getUpwardsFacing(),
                isFlipped());

        return new AxisAlignedBB(
                this.getPos().offset(relativeBack).offset(relativeRight, 6),
                this.getPos().offset(relativeBack, 13).offset(relativeRight.getOpposite(), 6));
    }

    @Override
    public boolean shouldRenderInPass(int pass) {
        return pass == 0;
    }

    @Override
    public boolean isGlobalRenderer() {
        return true;
    }
    @SideOnly(Side.CLIENT)
    private static final class FusionBloomSetup implements IRenderSetup {

        private static final FusionBloomSetup INSTANCE = new FusionBloomSetup();

        float lastBrightnessX;
        float lastBrightnessY;

        @Override
        public void preDraw( BufferBuilder buffer) {
            BloomEffect.strength = (float) ConfigHolder.client.shader.fusionBloom.strength;
            BloomEffect.baseBrightness = (float) ConfigHolder.client.shader.fusionBloom.baseBrightness;
            BloomEffect.highBrightnessThreshold = (float) ConfigHolder.client.shader.fusionBloom.highBrightnessThreshold;
            BloomEffect.lowBrightnessThreshold = (float) ConfigHolder.client.shader.fusionBloom.lowBrightnessThreshold;
            BloomEffect.step = 1;

            lastBrightnessX = OpenGlHelper.lastBrightnessX;
            lastBrightnessY = OpenGlHelper.lastBrightnessY;
            GlStateManager.color(1, 1, 1, 1);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
            GlStateManager.disableTexture2D();

            buffer.begin(GL11.GL_QUAD_STRIP, DefaultVertexFormats.POSITION_COLOR);
        }

        @Override
        public void postDraw( BufferBuilder buffer) {
            Tessellator.getInstance().draw();

            GlStateManager.enableTexture2D();
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);
        }
    }
}