package com.alexk.wowhelper.controller;

import com.alexk.wowhelper.view.BackgroundView;

import javax.swing.*;

public class BackgroundViewController implements IController
{
    private final BackgroundView mBackgroundView;
    private final LoadingProgressController mLoadingProgressController;
    private final MainOptionsController mMainOptionsController;

    public BackgroundViewController(final JPanel parentView)
    {
        mBackgroundView = new BackgroundView();
        parentView.add(mBackgroundView);

        mLoadingProgressController = new LoadingProgressController(mBackgroundView);
        mMainOptionsController = new MainOptionsController(mBackgroundView);
    }

    @Override
    public JPanel getView()
    {
        return mBackgroundView;
    }

    @Override
    public void update()
    {
        mLoadingProgressController.update();
    }
}
