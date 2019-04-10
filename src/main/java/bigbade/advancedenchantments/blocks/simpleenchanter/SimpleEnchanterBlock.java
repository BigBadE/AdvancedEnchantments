package bigbade.advancedenchantments.blocks.simpleenchanter;

import bigbade.advancedenchantments.AdvancedEnchantments;
import bigbade.advancedenchantments.Reference;
import bigbade.advancedenchantments.tileentities.SimpleEnchanterTile;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class SimpleEnchanterBlock extends Block {

    private static final AxisAlignedBB AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.75D, 1.0D);

    public SimpleEnchanterBlock() {
        super(Material.ROCK, MapColor.BLUE);
        setUnlocalizedName("simple_table");
        setRegistryName(new ResourceLocation(Reference.MODID.toString(), "simple_table"));
        setCreativeTab(AdvancedEnchantments.TAB);
        setLightOpacity(0);
        setLightLevel(2.5f);
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AABB;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);

        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                if (i > -2 && i < 2 && j == -1) {
                    j = 2;
                }
                if (rand.nextInt(4) == 0) {
                    for (int k = 0; k <= 1; ++k) {
                        if (!worldIn.isAirBlock(pos.add(i/2, 0, j/2))) {
                            break;
                        }
                        worldIn.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) pos.getX()+0.5D, (double) pos.getY()+2.0D, (double) pos.getZ()+0.5D, (double) ((float) i+rand.nextFloat())-0.5D, (double) ((float) k-rand.nextFloat()-1.0F), (double) ((float) j+rand.nextFloat())-0.5D);
                    }
                }
            }
        }
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return face == EnumFacing.DOWN ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
    }

    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new SimpleEnchanterTile();
    }

    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        playerIn.openGui(AdvancedEnchantments.INSTANCE, 0, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
}
