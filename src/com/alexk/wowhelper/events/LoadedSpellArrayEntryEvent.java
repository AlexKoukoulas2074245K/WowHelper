package com.alexk.wowhelper.events;

import org.json.simple.JSONArray;

public class LoadedSpellArrayEntryEvent implements IEvent<ILoadedSpellArrayEntryEventListener>
{
    private final JSONArray mLoadedSpellArray;

    public LoadedSpellArrayEntryEvent(final JSONArray loadedSpellArray)
    {
        mLoadedSpellArray = loadedSpellArray;
    }

    @Override
    public void notify(ILoadedSpellArrayEntryEventListener listener)
    {
        listener.onLoadedSpellArrayEntryEventListener(mLoadedSpellArray);
    }
}
