package com.alexk.wowhelper.controller;

import com.alexk.wowhelper.events.*;
import com.alexk.wowhelper.model.ApplicationState;
import com.alexk.wowhelper.model.MainOptionType;
import com.alexk.wowhelper.model.SpellInfoModel;
import com.alexk.wowhelper.view.MainOptionsView;
import com.alexk.wowhelper.view.SpellInfoView;
import org.json.simple.JSONArray;

import javax.swing.*;

public class MainOptionsController implements
        IController,
        IApplicationStateChangeEventListener,
        IMainOptionSelectedEventListener,
        ILoadedSpellArrayEntryEventListener
{
    private final JPanel mParentView;
    private final MainOptionsView mMainOptionsView;
    private final SpellInfoModel mSpellInfoModel;
    private final SpellInfoView mSpellInfoView;

    private MainOptionType mCurrentlySelectedMainOptionType;

    public MainOptionsController(final JPanel parentView)
    {
        mParentView      = parentView;
        mMainOptionsView = new MainOptionsView();
        mSpellInfoModel  = new SpellInfoModel();
        mSpellInfoView   = new SpellInfoView(mSpellInfoModel);

        EventSystem.subscribeToEvent(ApplicationStateChangeEvent.class, this);
        EventSystem.subscribeToEvent(MainOptionSelectedEvent.class, this);
        EventSystem.subscribeToEvent(LoadedSpellArrayEntryEvent.class, this);
    }

    @Override
    public JPanel getView()
    {
        return mMainOptionsView;
    }

    @Override
    public void update() {}

    @Override
    public void onMainOptionSelectedEvent(MainOptionType mainOptionSelected)
    {
        mCurrentlySelectedMainOptionType = mainOptionSelected;
    }

    @Override
    public void onApplicationStateChangeEvent(ApplicationState newApplicationState)
    {
        if (newApplicationState == ApplicationState.MAIN_OPTIONS)
        {
            mParentView.add(mMainOptionsView);
            mParentView.repaint();
        }
        else if (newApplicationState == ApplicationState.IN_OPTION)
        {
            mMainOptionsView.setVisible(false);

            switch (mCurrentlySelectedMainOptionType)
            {
                case SPELL_INFO:
                {
                    mSpellInfoView.repopulateComponents();
                    mParentView.add(mSpellInfoView);
                } break;

            }

            mParentView.repaint();
        }
    }

    @Override
    public void onLoadedSpellArrayEntryEventListener(JSONArray spellArray)
    {
        mSpellInfoModel.addSpellArray(spellArray);
    }
}
