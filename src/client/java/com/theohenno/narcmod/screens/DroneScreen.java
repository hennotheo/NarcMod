package com.theohenno.narcmod.screens;

import com.theohenno.narcmod.entities.DroneScreenHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class DroneScreen extends HandledScreen<DroneScreenHandler> {
    private static final Identifier TEXTURE = Identifier.ofVanilla("textures/gui/container/shulker_box.png");

    public DroneScreen(DroneScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        ++this.backgroundHeight;
    }

    public ButtonWidget button1;
    public ButtonWidget button2;

//    @Override
//    protected void init() {
//        button1 = ButtonWidget.builder(Text.literal("Button 1"), button -> {
//                    NarcMod.LOGGER.info("eee");
//                })
//                .dimensions(width / 2 - 205, 20, 200, 20)
//                .tooltip(Tooltip.of(Text.literal("Tooltip of button1")))
//                .build();
//        button2 = ButtonWidget.builder(Text.literal("Button 2"), button -> {
//                    System.out.println("You clicked button2!");
//                })
//                .dimensions(width / 2 + 5, 20, 200, 20)
//                .tooltip(Tooltip.of(Text.literal("Tooltip of button2")))
//                .build();
//
//        addDrawableChild(button1);
//        addDrawableChild(button2);
//    }

    @Override
    protected void drawBackground(DrawContext context, float deltaTicks, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(RenderLayer::getGuiTextured, TEXTURE, i, j, 0.0F, 0.0F, this.backgroundWidth, this.backgroundHeight, 256, 256);
    }
}