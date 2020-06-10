package com.alexk.wowhelper.model;

import com.alexk.wowhelper.events.ApplicationStateChangeEvent;
import com.alexk.wowhelper.events.EventSystem;

public class MainApplicationModel
{
    private ApplicationState mApplicationState;

    public MainApplicationModel()
    {
        mApplicationState = ApplicationState.LOADING;
    }

    public ApplicationState getApplicationState()
    {
        return mApplicationState;
    }

    public void setApplicationState(final ApplicationState applicationState)
    {
        mApplicationState = applicationState;
        EventSystem.dispatchEvent(new ApplicationStateChangeEvent(mApplicationState));
    }
}
