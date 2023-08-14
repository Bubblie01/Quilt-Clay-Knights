package io.github.Bubblie01.terracotta_knights.client.models;// Made with Blockbench 4.2.5
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.Bubblie01.terracotta_knights.Main;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

//Code Created by Bubblie01 Under MPL 2.0 License
public class TinyPitchforkEntityModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart bb_main;

	public static final EntityModelLayer PITCHFORK = new EntityModelLayer(new Identifier(Main.MOD_ID, "pitchfork"), "pitchfork");
	public TinyPitchforkEntityModel(ModelPart root) {
		super(RenderLayer::getEntitySmoothCutout);
		this.bb_main = root;
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData partData = modelData.getRoot();

		ModelPartData bb_main = partData.addChild("bb_main", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, -17.0F, -0.5F, 1.0F, 17.0F, 1.0F, new Dilation(0.0f))
				.uv(4, 0).cuboid(-0.5F, -20.0F, 0.25F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F))
				.uv(4, 0).cuboid(-0.5F, -20.0F, -1.75F, 1.0F, 0.5F, 1.0F, new Dilation(0.0F))
				.uv(4, 0).cuboid(-0.5F, -20.0F, -0.75F, 1.0F, 1.5F, 1.0F, new Dilation(0.0F))
				.uv(8, 0).cuboid(-0.5F, -18.0F, -1.75F, 1.0F, 0.5F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 18.0F, 0.0F));

		return TexturedModelData.of(modelData, 16, 18);
	}


	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		bb_main.render(matrices, vertices, light, overlay, red, green, blue, alpha);
	}
}
