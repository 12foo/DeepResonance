package mcjty.deepresonance.blocks.gencontroller;

import elec332.core.world.WorldHelper;
import mcjty.deepresonance.DeepResonance;
import mcjty.deepresonance.blocks.generator.GeneratorConfiguration;
import net.minecraft.block.Block;
import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GeneratorLoopSound extends MovingSound {
    private final World world;
    private final BlockPos pos;

    private float scaleDown = 1.0f;

    public GeneratorLoopSound(World world, int x, int y, int z) {
        super(SoundEvent.soundEventRegistry.getObject(new ResourceLocation(DeepResonance.MODID + ":engine_loop")), SoundCategory.BLOCKS);
        this.world = world;
        this.pos = new BlockPos(x, y, z);

        this.xPosF = x;
        this.yPosF = y;
        this.zPosF = z;

        this.attenuationType = AttenuationType.LINEAR;
        this.repeat = true;
        this.repeatDelay = 0;
    }

    @Override
    public void update() {
        Block block = WorldHelper.getBlockAt(world, pos);
        if (block != GeneratorControllerSetup.generatorControllerBlock) {
            donePlaying = true;
            return;
        }
        volume = GeneratorConfiguration.baseGeneratorVolume * scaleDown;
        if (scaleDown > GeneratorConfiguration.loopVolumeFactor) {
            scaleDown -= 0.01f;
        }
    }
}