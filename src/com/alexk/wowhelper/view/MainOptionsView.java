package com.alexk.wowhelper.view;

import com.alexk.wowhelper.events.EventSystem;
import com.alexk.wowhelper.events.MainOptionSelectedEvent;
import com.alexk.wowhelper.model.MainOptionType;
import com.alexk.wowhelper.util.CustomButtonWrapper;
import com.alexk.wowhelper.util.ICustomButtonPressedListener;

import javax.swing.*;
import java.awt.*;

public class MainOptionsView extends JPanel implements ICustomButtonPressedListener
{
    private static final Dimension GRID_LAYOUT_DIMENSION = new Dimension(4, 12);

    public MainOptionsView()
    {
        super(new GridLayout(GRID_LAYOUT_DIMENSION.height, GRID_LAYOUT_DIMENSION.width));

        for (int i = 0; i < GRID_LAYOUT_DIMENSION.height * GRID_LAYOUT_DIMENSION.width; ++i)
        {
            if (i == 9)
            {
                add(new CustomButtonWrapper(MainOptionType.SPELL_INFO.toString(), this));
            }
            else
            {
                JPanel emptyPanel = new JPanel();
                emptyPanel.setVisible(false);
                add(emptyPanel);
            }
        }

        setOpaque(false);
    }

    @Override
    public void OnCustomButtonPressed(String buttonName)
    {
        if (buttonName.equals(MainOptionType.SPELL_INFO.toString()))
        {
            EventSystem.dispatchEvent(new MainOptionSelectedEvent(MainOptionType.SPELL_INFO));
        }
    }
}
