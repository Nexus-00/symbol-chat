package net.replaceitem.symbolchat.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class SymbolSearchBar extends TextFieldWidget {

    public static final Text HINT_TEXT = Text.translatable("symbolchat.search");

    public SymbolSearchBar(int x, int y, int width, int height) {
        super(MinecraftClient.getInstance().textRenderer, x, y, width, height, Text.empty());
        this.setDrawsBackground(false);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        RenderSystem.disableDepthTest();
        matrices.push();
        matrices.translate(0.0, 0.0, 200.0f);

        if(this.getText().isEmpty()) {
            MinecraftClient.getInstance().textRenderer.drawWithShadow(matrices, HINT_TEXT, this.getX(), this.getY(), 0xa0a0a0a0);
        }

        super.render(matrices, mouseX, mouseY, delta);

        int lineY = this.getY() + this.height - 1;
        fill(matrices, this.getX(), lineY, this.getX() + this.width - 1, lineY+1, this.isActive() || this.isHovered() ? 0x99FFFFFF : 0x99A0A0A0);

        matrices.pop();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        boolean b = super.mouseClicked(mouseX, mouseY, button);
        if(b) this.onClick(mouseX, mouseY);
        return b;
    }
}
