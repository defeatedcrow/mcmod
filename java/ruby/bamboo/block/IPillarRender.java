package ruby.bamboo.block;

import net.minecraft.world.IBlockAccess;

public interface IPillarRender {
    // Y+
    boolean setUpBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, boolean isSmallScale);

    // Y-
    boolean setDownBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, boolean isSmallScale);

    // Z+
    boolean setSouthBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, boolean isSmallScale);

    // Z-
    boolean setNorthBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, boolean isSmallScale);

    // X+
    boolean setEastBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, boolean isSmallScale);

    // X-
    boolean setWestBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, boolean isSmallScale);

    void setCoreBoundsBox(IBlockAccess par1IBlockAccess, int par2, int par3, int par4);

    boolean isLinkSkipp();

    boolean canDifferentMetaLink(int meta1, int meta2);

    float getSize();

    float getMinWidth();

    float getMaxWidth();

    float getMinHeight();

    float getMaxHeight();
}
