package mctourney.plugins.shared.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TickEvent extends Event {
    private static final HandlerList handlers = new HandlerList();

    public TickEvent() { }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}