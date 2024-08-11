package cn.crtlprototypestudios.ovslib.client.lib.screens;

import cn.crtlprototypestudios.ovslib.OverseerLib;
import cn.crtlprototypestudios.ovslib.client.lib.components.QuickActionsBarContainer;
import io.wispforest.owo.ui.base.BaseUIModelScreen;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class OverseerScreen extends BaseUIModelScreen<FlowLayout> {
    private String menuName = "Overseer Menu Screen";
    @Nullable
    protected FlowLayout quickActionBarHolder = null;

    private boolean implementQuickActionBar = true;

    public OverseerScreen(String path, String name, boolean hasQuickActionsBar) {
        this(OverseerLib.MOD_ID, path, name, hasQuickActionsBar);
    }

    public OverseerScreen(String namespace, String path, String name, boolean hasQuickActionsBar) {
        super(FlowLayout.class, Identifier.of(namespace, path));
        menuName = name;
        implementQuickActionBar = hasQuickActionsBar;
    }

    @Override
    protected void init(){
        super.init();
        if (implementQuickActionBar) initializeQuickActionsBar();
    }

    @Override
    protected void build(FlowLayout rootComponent){
        // It is impossible to get the quick actions bar in this build() method
        // The reason? I don't know why, but the build() method seems to run before init(), which is where the quick action bar is initialized
    }

    static {
        UIParsing.registerFactory(OverseerLib.of("quick-actions-bar-container"), element -> new QuickActionsBarContainer());
    }

    private void setMenuName(){
        if(quickActionBarHolder == null) return;
        quickActionBarHolder.<FlowLayout>configure(component -> {
            component.clearChildren();
            component.child(this.model.expandTemplate(QuickActionsBarContainer.class, OverseerLib.template("quick-actions-bar", Identifier.of(OverseerLib.MOD_ID, "components/quick_actions_bar")), Map.of(
                    "current-menu", menuName
            )));
        });
    }

    public boolean quickActionsBarInitialized() { return quickActionBarHolder != null; }

    private void initializeQuickActionsBar(){
        if (this.uiAdapter != null) {
            try {
                quickActionBarHolder = this.uiAdapter.rootComponent.childById(FlowLayout.class, "bar-holder");
            } catch (Exception e) {
                OverseerLib.LOGGER.error("Error initializing QuickActionsBar", e);
            }
        }

        setMenuName();
    }

    public String getMenuName(){
        return menuName;
    }

    public boolean isImplementQuickActionsBar(){
        return implementQuickActionBar;
    }
}
