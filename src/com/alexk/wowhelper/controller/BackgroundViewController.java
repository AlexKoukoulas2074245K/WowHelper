package com.alexk.wowhelper.controller;

import com.alexk.wowhelper.view.BackgroundView;
import com.alexk.wowhelper.view.IView;

import javax.swing.*;

public class BackgroundViewController implements IController
{
    private final BackgroundView mBackgroundView;
    private final LoadingProgressController mLoadingProgressController;

    public BackgroundViewController(final IView parentView)
    {
        mBackgroundView = new BackgroundView();
        parentView.addSubView(mBackgroundView);

        mLoadingProgressController = new LoadingProgressController(mBackgroundView);
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
