package com.alexk.wowhelper.events;

public class InOptionBackButtonPressedEvent implements IEvent<IInOptionBackButtonPressedEventListener>
{
    @Override
    public void notify(IInOptionBackButtonPressedEventListener listener)
    {
        listener.onInOptionBackButtonPressedEvent();
    }
}
