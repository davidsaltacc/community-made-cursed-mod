package net.justacoder.communitymade;

import com.mojang.blaze3d.platform.GlDebugInfo;
import com.mojang.blaze3d.systems.RenderSystem;
import net.justacoder.communitymade.block.CustomBlocks;
import net.justacoder.communitymade.mixininterface.IItem;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.gl.SimpleFramebuffer;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.client.util.ScreenshotRecorder;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.nio.ByteBuffer;

public abstract class Main {

    public static Logger LOGGER = LoggerFactory.getLogger("CommunityMade");
    public static String MOD_ID = "communitymade";

    public static KeyBinding giantScreenshitBind = new KeyBinding("giant_screenshot", GLFW.GLFW_KEY_F6, "communitymade");

    public static void initialize() {
        LOGGER.info("Starting Cursedcraft...");
        Registries.ITEM.forEach(item -> ((IItem) item).setMaxItemCount(67));
        CustomBlocks.addBlocks();
        LOGGER.info("Finished starting Cursedcraft");
    }

    public static void onTick(ServerWorld world) {
        world.getPlayers().forEach(player -> {
            if (player.isTouchingWater() && !EnchantmentHelper.hasFrostWalker(player)) {
                world.setBlockState(player.getBlockPos(), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(0, 1, 0), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(0, 0, 1), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(0, 1, 1), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(1, 0, 0), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(1, 1, 0), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(1, 0, 1), Blocks.AIR.getDefaultState());
                world.setBlockState(player.getBlockPos().add(1, 1, 1), Blocks.AIR.getDefaultState());
            }
        });
    }

    public static void clientTick() {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (giantScreenshitBind.wasPressed()) {
            System.out.println("giant screenshot ");
            int width = mc.getFramebuffer().viewportWidth;
            int height = mc.getFramebuffer().viewportHeight;
            try {
                ByteBuffer byteBuffer = GlDebugInfo.allocateMemory(width * height * 3);
                ScreenshotRecorder screenshotRecorder = new ScreenshotRecorder(mc.runDirectory, width * 10, height * 10, height);
                float f = (float) (width * 10) / (float) width;
                float g = (float) (height * 10) / (float) height;
                float h = f > g ? f : g;
                for (int i = (height * 10 - 1) / height * height; i >= 0; i -= height) {
                    for (int j = 0; j < width * 10; j += width) {
                        RenderSystem.setShaderTexture(0, SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE);
                        float k = (float) (width * 10 - width) / 2.0f * 2.0f - (float) (j * 2);
                        float l = (float) (height * 10 - height) / 2.0f * 2.0f - (float) (i * 2);
                        mc.gameRenderer.renderWithZoom(h, k /= (float) width, l /= (float) height);
                        byteBuffer.clear();
                        RenderSystem.pixelStore(3333, 1);
                        RenderSystem.pixelStore(3317, 1);
                        RenderSystem.readPixels(0, 0, width, height, 32992, 5121, byteBuffer);
                        screenshotRecorder.getIntoBuffer(byteBuffer, j, i, width, height);
                    }
                    screenshotRecorder.writeToStream();
                }
                File file = screenshotRecorder.finish();
                GlDebugInfo.freeMemory(byteBuffer);
                MutableText text = Text.literal(file.getName()).formatted(Formatting.UNDERLINE).styled(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.OPEN_FILE, file.getAbsolutePath())));
                mc.inGameHud.getChatHud().addMessage(Text.translatable("screenshot.success", text));
            } catch (Exception exception) {
                LOGGER.warn("Couldn't save screenshot", exception);
                mc.inGameHud.getChatHud().addMessage(Text.translatable("screenshot.failure", exception.getMessage()));
            }
        }
    }

    // I just found out there's unused code in minecraft that takes a panorama screenshot
}