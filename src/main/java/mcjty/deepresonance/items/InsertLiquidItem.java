package mcjty.deepresonance.items;

import elec332.core.world.WorldHelper;
import mcjty.deepresonance.blocks.tank.TileTank;
import mcjty.deepresonance.fluid.LiquidCrystalFluidTagData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class InsertLiquidItem extends GenericDRItem {

    public InsertLiquidItem() {
        super("insert_liquid");
        setMaxStackSize(1);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean advancedToolTip) {
        super.addInformation(itemStack, player, list, advancedToolTip);
        list.add("Creative only item to inject 100mb of liquid");
        list.add("crystal to a tank");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float sx, float sy, float sz) {
        if (!world.isRemote) {
            TileEntity te = WorldHelper.getTileAt(world, pos);
            if (te instanceof TileTank) {
                TileTank tileTank = (TileTank) te;
                FluidStack fluidStack = LiquidCrystalFluidTagData.makeLiquidCrystalStack(100, 1.0f, 0.1f, 0.1f, 0.1f);
                tileTank.fill(null, fluidStack, true);
            } else {
                player.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.YELLOW + "This is not a tank!"));
            }
        }
        return true;
    }
}
