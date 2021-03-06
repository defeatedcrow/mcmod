package ruby.bamboo.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ruby.bamboo.BambooCore;
import ruby.bamboo.BambooInit;
import ruby.bamboo.Config;
import ruby.bamboo.CustomRenderHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDelude extends Block implements IDelude {
    // 縦半か
    private final boolean isHeightSlab;
    private boolean isIconGrass = false;

    public BlockDelude(boolean par2) {
        super(Material.ground);
        isHeightSlab = par2;
        this.setLightOpacity(0);

        if (par2) {
            this.setBlockBounds(0.25F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
        setBlockTextureName(BambooCore.resourceDomain + "delude");
        setHardness(0.5F);
    }

    @Override
    public int getRenderType() {
        return CustomRenderHandler.deludeUID;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        if (this.isHeightSlab) {
            switch (par1IBlockAccess.getBlockMetadata(par2, par3, par4)) {
            case 5:
                this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);
                break;

            case 2:
                this.setBlockBounds(0.0F, 0.0F, 0.5F, 1.0F, 1.0F, 1.0F);
                break;

            case 3:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.5F);
                break;

            case 0:
                this.setBlockBounds(0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;

            case 1:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 0.5F, 1.0F, 1.0F);
                break;

            default:
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                break;
            }
        } else {
            boolean var5 = (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0;

            if (var5) {
                this.setBlockBounds(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            } else {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }

    @Override
    public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        if (this.isHeightSlab) {
            return super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
        } else if (par5 != 1 && par5 != 0 && !super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5)) {
            return false;
        } else {
            int var6 = par2 + Facing.offsetsXForSide[Facing.oppositeSide[par5]];
            int var7 = par3 + Facing.offsetsYForSide[Facing.oppositeSide[par5]];
            int var8 = par4 + Facing.offsetsZForSide[Facing.oppositeSide[par5]];
            boolean var9 = (par1IBlockAccess.getBlockMetadata(var6, var7, var8) & 8) != 0;
            return var9 ? (par5 == 0 ? true : (par5 == 1 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) ? true : !isBlockSingleSlab(par1IBlockAccess.getBlock(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) == 0)) : (par5 == 1 ? true : (par5 == 0 && super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5) ? true : !isBlockSingleSlab(par1IBlockAccess.getBlock(par2, par3, par4)) || (par1IBlockAccess.getBlockMetadata(par2, par3, par4) & 8) != 0));
        }
    }

    private boolean isBlockSingleSlab(Block par0) {
        return par0 == Blocks.stone_slab || par0 == Blocks.wooden_slab;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void setBlockBoundsForItemRender() {
        if (this.isHeightSlab) {
            this.setBlockBounds(0.25F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int par1, int par2) {
        return !isIconGrass ? getDefaultIcon() : Blocks.grass.getIcon(par1, par2);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public int onBlockPlaced(World par1World, int par2, int par3, int par4, int par5, float par6, float par7, float par8, int par9) {
        int var10 = 0;

        if (!isHeightSlab) {
            var10 = (par5 != 0 && (par5 == 1 || par7 <= 0.5D) ? par9 : par9 | 8);
        }

        int meta = par5;

        switch (par5) {
        case 0:
            meta = 4;
            break;

        case 1:
            meta = 5;
            break;

        case 4:
            meta = 0;
            break;

        case 5:
            meta = 1;
            break;
        }

        return var10 | meta;
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9) {
        return onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9, 0);
    }

    private boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9, int par10) {
        switch (getMetadata(par1World, par2, par3, par4)) {
        case 0:
            par2++;
            break;

        case 1:
            par2--;
            break;

        case 2:
            par4++;
            break;

        case 3:
            par4--;
            break;

        case 4:
            par3++;
            break;

        case 5:
            par3--;
            break;
        }

        if (isDeludeBlock(par1World, par2, par3, par4)) {
            return par10 < Config.deludeMaxReference ? onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9, par10 + 1) : false;
        } else {
            return par1World.getBlock(par2, par3, par4) != null ? par1World.getBlock(par2, par3, par4).onBlockActivated(par1World, par2, par3, par4, par5EntityPlayer, par6, par7, par8, par9) : false;
        }
    }

    @Override
    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        IIcon tex = getIcon(par1IBlockAccess, par2, par3, par4, par5, 0);
        return tex != null ? tex : getDefaultIcon();
    }

    private IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5, int par6) {
        switch (getMetadata(par1IBlockAccess, par2, par3, par4)) {
        case 0:
            par2++;
            break;

        case 1:
            par2--;
            break;

        case 2:
            par4++;
            break;

        case 3:
            par4--;
            break;

        case 4:
            par3++;
            break;

        case 5:
            par3--;
            break;
        }

        if (isDeludeBlock(par1IBlockAccess, par2, par3, par4)) {
            return par6 < Config.deludeTexMaxReference ? getIcon(par1IBlockAccess, par2, par3, par4, par5, par6 + 1) : getDefaultIcon();
        } else {
            return par1IBlockAccess.getBlock(par2, par3, par4) != null && par1IBlockAccess.getBlock(par2, par3, par4).getMaterial() != Material.water ? par1IBlockAccess.getBlock(par2, par3, par4).getIcon(par1IBlockAccess, par2, par3, par4, par5) : getDefaultIcon();
        }
    }

    private IIcon getDefaultIcon() {
        return blockIcon;
    }

    @Override
    public int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
        return colorMultiplier(par1IBlockAccess, par2, par3, par4, 0);
    }

    private int colorMultiplier(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        switch (getMetadata(par1IBlockAccess, par2, par3, par4)) {
        case 0:
            par2++;
            break;

        case 1:
            par2--;
            break;

        case 2:
            par4++;
            break;

        case 3:
            par4--;
            break;

        case 4:
            par3++;
            break;

        case 5:
            par3--;
            break;
        }

        if (isDeludeBlock(par1IBlockAccess, par2, par3, par4)) {
            return par5 < Config.deludeTexMaxReference ? colorMultiplier(par1IBlockAccess, par2, par3, par4, par5 + 1) : 0xFFFFFF;
        } else {
            return par1IBlockAccess.getBlock(par2, par3, par4) != null && par1IBlockAccess.getBlock(par2, par3, par4).getMaterial() != Material.water ? par1IBlockAccess.getBlock(par2, par3, par4).colorMultiplier(par1IBlockAccess, par2, par3, par4) : 0xFFFFFF;
        }
    }

    /*
        @Override
        public int getLightValue(IBlockAccess world, int x, int y, int z) {
            return getLightValue(world, x, y, z, 0);
        }

        private int getLightValue(IBlockAccess world, int x, int y, int z, int c) {
            switch (getMetadata(world, x, z, y)) {
            case 0:
                x++;
                break;

            case 1:
                x--;
                break;

            case 2:
                y++;
                break;

            case 3:
                y--;
                break;

            case 4:
                z++;
                break;

            case 5:
                z--;
                break;
            }
            if (isDeludeBlock(world, x, y, z)) {
                return c < Config.deludeTexMaxReference ? getLightValue(world, x, y, z, c + 1) : 0;
            } else {
                return world.getBlock(x, y, z) != null && world.getBlock(x, y, z).getMaterial() != Material.water ? world.getBlock(x, y, z).getLightValue(world, x, y, z) : 0;
            }
        }
    */
    @Override
    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block par5) {
    }

    private int getMetadata(IBlockAccess ib, int x, int y, int z) {
        if (ib.getBlock(x, y, z) == BambooInit.delude_stair) {
            int meta = ib.getBlockMetadata(x, y, z);
            return (meta & 8) != 8 ? meta & 3 : (meta & 12) == 8 ? 5 : 4;
        } else {
            return ib.getBlockMetadata(x, y, z) & 7;
        }
    }

    private boolean isDeludeBlock(IBlockAccess iba, int x, int y, int z) {
        return iba.getBlock(x, y, z) instanceof IDelude;
    }

    @Override
    public int getRenderBlockPass() {
        return 0;
    }

    @Override
    public void setIconGrass(boolean bool) {
        isIconGrass = bool;
    }

    @Override
    public int getOriginalRenderType() {
        return 0;
    }
}
