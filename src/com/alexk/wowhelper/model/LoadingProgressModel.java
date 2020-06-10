package com.alexk.wowhelper.model;

import com.alexk.wowhelper.events.EventSystem;
import com.alexk.wowhelper.events.LoadingProgressUpdateEvent;

public class LoadingProgressModel
{
    private float mLoadingProgress;

    public LoadingProgressModel()
    {
        mLoadingProgress = 0.0f;
    }

    public void setLoadingProgress(final float loadingProgress)
    {
        mLoadingProgress = Math.max(0.0f, Math.min(loadingProgress, 1.0f));
        EventSystem.dispatchEvent(new LoadingProgressUpdateEvent(mLoadingProgress));
    }
}
