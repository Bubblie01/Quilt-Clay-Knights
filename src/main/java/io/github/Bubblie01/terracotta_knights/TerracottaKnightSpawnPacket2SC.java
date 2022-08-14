package io.github.Bubblie01.terracotta_knights;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.MobSpawnS2CPacket;

public class TerracottaKnightSpawnPacket2SC extends MobSpawnS2CPacket {
	private int color;

	public TerracottaKnightSpawnPacket2SC(TerracottaKnightEntity knight, int color) {
		super(knight);
		this.color = color;
	}

	@Override
	public void write(PacketByteBuf buf) {
		super.write(buf);
		buf.writeInt(color);
	}

	public int getColor() {
		return color;
	}
}
