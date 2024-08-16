package net.kyrptonaught.lemclienthelper.TakeEverything;

import dev.isxander.controlify.api.ControlifyApi;
import dev.isxander.controlify.api.bind.ControlifyBindApi;
import dev.isxander.controlify.api.bind.InputBindingSupplier;
import dev.isxander.controlify.api.entrypoint.ControlifyEntrypoint;
import dev.isxander.controlify.api.event.ControlifyEvents;
import dev.isxander.controlify.bindings.BindContext;
import net.kyrptonaught.lemclienthelper.LEMClientHelperMod;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;



public class ControlifyCompat implements ControlifyEntrypoint {

    public static InputBindingSupplier takeEverythingBinding;

    @Override
    public void onControlifyInit(ControlifyApi controlifyApi) {
        var bindings = ControlifyBindApi.get();

        takeEverythingBinding = bindings.registerBinding(builder -> builder
                .id(new Identifier(LEMClientHelperMod.MOD_ID + ".controlify.takeeverything"))
                .addKeyCorrelation(TakeEverythingMod.takeEverythingKey)                                 // Try as I might, the original Take Everything Bind remains in the menu. Why?
                .category(Text.translatable("controlify.category.lemclienthelper.takeeverything")) // Crashes when in the same category as the existing LCH binds, Why? This shouldn't need a new category, and the original bind shouldn't be shown.
                .name(Text.translatable("controlify.action.lemclienthelper.takeeverything"))       // Everything else works fine, I can only assume it does this to spite me.
                .allowedContexts(BindContext.CONTAINER)                                                 // ALSO, WHY DO THE AUTO GENERATED KEYBINDS, GET A HIGHER POSITION IN THE MENU, THAN MANUALLY GENERATED ONES!?!?!?!?
        );                                                                                              // The ramblings of a completely sane individual.

        // On Mod Init, create a new callback when the controller state is updated
        ControlifyEvents.ACTIVE_CONTROLLER_TICKED.register(this::ControllerStateUpdate);
    }

    // Whenever the controller state is updated, run the takeEverything() function.
    private void ControllerStateUpdate(ControlifyEvents.ControllerStateUpdate controllerStateUpdate) {
        takeEverything();
    }

    @Override
    public void onControllersDiscovered(ControlifyApi controlifyApi) {
    }

    // Checks that A) Controllify Exists, B) The controller Exists, C) The button is currently pressed
    // Alot of this seems redundant and can probably be removed, But it works now and I will not touch it again.
    public static boolean isControllerBindPressed() {
        var controlifyApi = ControlifyApi.get();
        if (controlifyApi == null) return false;

        if (controlifyApi.getCurrentController().isPresent()) {
            return takeEverythingBinding.on(controlifyApi.getCurrentController().get()).digitalNow();
        } else {return false;}
    }

    // Ditto above, but checks if the previous controller update also had it pressed
    // This prevents packet spam, and saves my ears when console sounds are enabled.
    public static boolean wasControllerBindPressed() {
        var controlifyApi = ControlifyApi.get();
        if (controlifyApi == null) return false;

        if (controlifyApi.getCurrentController().isPresent()) {
            return takeEverythingBinding.on(controlifyApi.getCurrentController().get()).digitalPrev();
        } else {return false;}
    }

    //The thing, Does what it says.
    public void takeEverything() {
        if (isControllerBindPressed() && !wasControllerBindPressed()) {
            TakeEverythingNetworking.sendTakeEverythingPacket();
        }
    }
}
