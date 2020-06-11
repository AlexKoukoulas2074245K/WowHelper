package com.alexk.wowhelper.events;

import com.alexk.wowhelper.model.ApplicationState;

public class ApplicationStateChangeEvent implements IEvent<IApplicationStateChangeEventListener>
{
    private final ApplicationState mApplicationState;

    public ApplicationStateChangeEvent(final ApplicationState newApplicationState)
    {
        mApplicationState = newApplicationState;
    }

    @Override
    public void notify(IApplicationStateChangeEventListener listener)
    {
        listener.onApplicationStateChangeEvent(mApplicationState);
    }
}
