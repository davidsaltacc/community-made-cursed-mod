package net.justacoder.communitymade.mixin;

import net.minecraft.client.util.Window;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Window.class)
public abstract class WindowMixin {
    /**
     * @author why
     * @reason why
     */
    @Overwrite
    public void setTitle(String title) {
        GLFW.glfwSetWindowTitle(((Window) (Object) this).getHandle(), title.replace("Minecraft", "Cursedcraft"));
    }
}
