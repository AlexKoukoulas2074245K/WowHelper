package com.alexk.wowhelper.events;

public class LoadingProgressUpdateEvent implements IEvent<ILoadingProgressUpdateEventListener>
{
    private final float mLoadingProgress;

    public LoadingProgressUpdateEvent(final float newLoadingProgress)
    {
        mLoadingProgress = newLoadingProgress;
    }

    @Override
    public void notify(ILoadingProgressUpdateEventListener listener)
    {
        listener.onLoadingProgressUpdateEvent(mLoadingProgress);
    }
}
