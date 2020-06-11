package com.alexk.wowhelper.controller;

import com.alexk.wowhelper.events.ApplicationStateChangeEvent;
import com.alexk.wowhelper.events.EventSystem;
import com.alexk.wowhelper.events.IApplicationStateChangeEventListener;
import com.alexk.wowhelper.events.LoadedSpellArrayEntryEvent;
import com.alexk.wowhelper.model.ApplicationState;
import com.alexk.wowhelper.model.ClassType;
import com.alexk.wowhelper.model.LoadingProgressModel;
import com.alexk.wowhelper.parsing.SpellParser;
import com.alexk.wowhelper.view.LoadingProgressView;
import org.json.simple.JSONArray;

import javax.swing.*;

public class LoadingProgressController implements IController, IApplicationStateChangeEventListener
{
    private LoadingProgressModel mLoadingProgressModel;
    private LoadingProgressView mLoadingProgressView;

    public LoadingProgressController(final JPanel parentView)
    {
        mLoadingProgressModel = new LoadingProgressModel();
        mLoadingProgressView = new LoadingProgressView();

        parentView.add(mLoadingProgressView);

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
        Thread[] parsingThreads = new Thread[classTypes.length];

        for (int i = 0; i < classTypes.length; ++i)
        {
            JSONArray spellArray = new SpellParser(classTypes[i]).parse();
            EventSystem.dispatchEvent(new LoadedSpellArrayEntryEvent(spellArray));

            mLoadingProgressModel.setLoadingProgress((float)(i + 1)/classTypes.length);
        }
    }

    @Override
    public void onApplicationStateChangeEvent(ApplicationState newApplicationState)
    {
        if (newApplicationState != ApplicationState.LOADING)
        {
            mLoadingProgressView.setVisible(false);
        }
    }
}
