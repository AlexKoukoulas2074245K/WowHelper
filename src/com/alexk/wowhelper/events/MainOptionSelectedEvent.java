package com.alexk.wowhelper.events;

import com.alexk.wowhelper.model.MainOptionType;

public class MainOptionSelectedEvent implements IEvent<IMainOptionSelectedEventListener>
{
    private MainOptionType mMainOptionSelected;

    public MainOptionSelectedEvent(final MainOptionType mainOptionType)
    {
        mMainOptionSelected = mainOptionType;
    }

    @Override
    public void notify(IMainOptionSelectedEventListener listener)
    {
        listener.onMainOptionSelectedEvent(mMainOptionSelected);
    }
}
