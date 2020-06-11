package com.alexk.wowhelper.controller;

import com.alexk.wowhelper.events.*;
import com.alexk.wowhelper.model.ApplicationState;
import com.alexk.wowhelper.model.MainApplicationModel;
import com.alexk.wowhelper.model.MainOptionType;
import com.alexk.wowhelper.view.MainApplicationView;

import javax.swing.*;

public class MainApplicationController implements IController, ILoadingProgressUpdateEventListener, IMainOptionSelectedEventListener
{
    private final MainApplicationView mMainApplicationView;
    private final MainApplicationModel mMainApplicationModel;
    private final BackgroundViewController mBackgroundViewController;

    private boolean mIsLoading;

    public MainApplicationController()
    {
        mMainApplicationModel = new MainApplicationModel();
        mMainApplicationView  = new MainApplicationView();

        mBackgroundViewController = new BackgroundViewController(mMainApplicationView);

        mIsLoading = true;

        EventSystem.subscribeToEvent(LoadingProgressUpdateEvent.class, this);
        EventSystem.subscribeToEvent(MainOptionSelectedEvent.class, this);
    }

    @Override
    public JPanel getView()
    {
        return mMainApplicationView;
    }

    @Override
    public void update()
    {
        if (mIsLoading)
        {
            mBackgroundViewController.update();
        }
    }

    @Override
    public void onLoadingProgressUpdateEvent(float newLoadingProgress)
    {
        if (newLoadingProgress > 0.99f)
        {
            mMainApplicationModel.setApplicationState(ApplicationState.MAIN_OPTIONS);
        }
    }

    @Override
    public void onMainOptionSelectedEvent(MainOptionType mainOptionSelected)
    {
        mMainApplicationModel.setApplicationState(ApplicationState.IN_OPTION);
    }
}
