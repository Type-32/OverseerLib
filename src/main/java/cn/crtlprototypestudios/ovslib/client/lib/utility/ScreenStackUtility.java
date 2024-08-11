package cn.crtlprototypestudios.ovslib.client.lib.utility;

import cn.crtlprototypestudios.ovslib.client.lib.screens.OverseerScreen;
import net.minecraft.client.MinecraftClient;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.Nullable;

import java.util.Stack;

public class ScreenStackUtility {
    private static Stack<OverseerScreen> screens = new Stack<>();
    private static OverseerScreen DEFAULT_SCREEN;

    public static OverseerScreen to(OverseerScreen screen) {
        return render(screens.push(screen)); // Runs push() method first then returns the pushed stack result.
    }

    public static OverseerScreen back() {
        OverseerScreen screen = screens.pop();
        System.out.println(screen.getClass().getSimpleName() + " has been popped from the stack.");
        return render(screens.peek()); // Runs pop() method first then returns the popped stack result.
    }

    public static OverseerScreen home() throws Exception {
        screens.clear();
        if(DEFAULT_SCREEN == null) {
            exit();
            throw new Exception("Default Screen is null. Please set the default screen using ScreenStackUtility.setDefaultScreen().");
        }
        return to(DEFAULT_SCREEN);
    }

    public static OverseerScreen render(OverseerScreen screen){
        MinecraftClient.getInstance().setScreen(screen);
        if (screens.size() <= 0) {
//            MinecraftClient.getInstance().setScreen(new MainMenuScreen());
            return null;
        }
        return screen;
    }

    public static OverseerScreen refresh() {
        return render(screens.peek());
    }

    public static void clear() {
        screens.clear();
    }

    public static void exit(){
        clear();
        MinecraftClient.getInstance().setScreen(null);
    }

    public static OverseerScreen getCurrent() {
        if (screens.size() <= 0) return null;
        return screens.peek();
    }

    public static void setDefaultScreen(OverseerScreen screen) {
        DEFAULT_SCREEN = screen;
    }
}
