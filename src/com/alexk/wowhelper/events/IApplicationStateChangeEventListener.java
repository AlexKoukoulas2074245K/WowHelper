package com.alexk.wowhelper.events;

import com.alexk.wowhelper.model.ApplicationState;

public interface IApplicationStateChangeEventListener
{
    void OnLoadingProgressUpdateEvent(final ApplicationState newApplicationState);
}
