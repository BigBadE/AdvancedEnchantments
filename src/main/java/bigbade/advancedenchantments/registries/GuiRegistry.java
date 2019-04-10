package bigbade.advancedenchantments.registries;

import bigbade.advancedenchantments.blocks.simpleenchanter.SimpleEnchanterContainer;
import bigbade.advancedenchantments.blocks.simpleenchanter.SimpleEnchanterGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;
import java.util.Objects;

public class GuiRegistry implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == 0) {
            return new SimpleEnchanterContainer(player.inventory, world, new BlockPos(x, y, z));
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == 0) {
            return new SimpleEnchanterGUI(player.inventory, world, Objects.requireNonNull(Objects.requireNonNull(world.getTileEntity(new BlockPos(x, y, z))).getDisplayName()).getFormattedText());
        }
        return null;
    }
}
