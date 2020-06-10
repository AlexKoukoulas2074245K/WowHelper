package com.alexk.wowhelper.controller;

import com.alexk.wowhelper.events.ApplicationStateChangeEvent;
import com.alexk.wowhelper.events.EventSystem;
import com.alexk.wowhelper.events.IApplicationStateChangeEventListener;
import com.alexk.wowhelper.model.ApplicationState;
import com.alexk.wowhelper.model.ClassType;
import com.alexk.wowhelper.model.LoadingProgressModel;
import com.alexk.wowhelper.parsing.SpellParser;
import com.alexk.wowhelper.view.IView;
import com.alexk.wowhelper.view.LoadingProgressView;
import org.json.simple.JSONArray;

import javax.swing.*;

public class LoadingProgressController implements IController, IApplicationStateChangeEventListener
{
    private LoadingProgressModel mLoadingProgressModel;
    private LoadingProgressView mLoadingProgressView;

    public LoadingProgressController(final IView parentView)
    {
        mLoadingProgressModel = new LoadingProgressModel();
        mLoadingProgressView = new LoadingProgressView();

        parentView.addSubView(mLoadingProgressView);

        EventSystem.subscribeToEvent(ApplicationStateChangeEvent.class, this);
    }

    @Override
    public JPanel getView()
    {
        return mLoadingProgressView;
    }

    @Override
    public void update()
    {
        loadData();
    }

    private void loadData()
    {
        ClassType[] classTypes = ClassType.values();
        for (int i = 0; i < classTypes.length; ++i)
        {
            JSONArray spellArray = new SpellParser(classTypes[i]).parse();
            System.out.println("Extracted " + spellArray.size() + " for " + classTypes[i].toString());
            mLoadingProgressModel.setLoadingProgress((float)(i + 1)/classTypes.length);
        }
    }

    @Override
    public void OnLoadingProgressUpdateEvent(ApplicationState newApplicationState)
    {
        if (newApplicationState == ApplicationState.MAIN_OPTIONS)
        {
            mLoadingProgressView.setVisible(false);
        }
    }
}
