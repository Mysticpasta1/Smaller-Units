package tfc.smallerunits.mixin.data;

import net.minecraft.core.Direction;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import tfc.smallerunits.client.access.tracking.FastCapabilityHandler;
import tfc.smallerunits.data.capability.ISUCapability;
import tfc.smallerunits.data.capability.SUCapabilityManager;

@Mixin(LevelChunk.class)
public abstract class FastCapabilityChunkMixin implements FastCapabilityHandler {
	@Unique
	ISUCapability capability = null;
	
	@Shadow
	public abstract <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side);
	
	@Override
	public ISUCapability getSUCapability() {
		if (capability == null)
			capability = this.getCapability(SUCapabilityManager.SU_CAPABILITY_TOKEN, null).orElse(null);
		return capability;
	}
}
