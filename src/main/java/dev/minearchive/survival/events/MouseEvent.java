package dev.minearchive.survival.events;

import dev.minearchive.survival.util.input.InputAction;

public class MouseEvent {

    public record MouseButtonEvent(int button, InputAction action)  { }
    public record MouseScrollEvent(double h, double v)  { }

}
