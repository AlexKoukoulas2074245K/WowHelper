package com.alexk.wowhelper.view;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.alexk.wowhelper.events.EventSystem;
import com.alexk.wowhelper.events.MainOptionSelectedEvent;
import com.alexk.wowhelper.model.MainOptionType;
import com.alexk.wowhelper.util.CustomButtonWrapper;
import com.alexk.wowhelper.util.ICustomButtonPressedListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainOptionsView extends JPanel implements ICustomButtonPressedListener
{
    private static final Dimension GRID_LAYOUT_DIMENSION = new Dimension(4, 12);

    private static final String BUTTON_IDLE_IMAGE_SUFFIX = "_button.png";
    private static final String BUTTON_PRESSED_IMAGE_SUFFIX = "_button_pressed.png";

    private Image mSpellInfoButtonIdleImage, mSpellInfoButtonPressedImage;

    public MainOptionsView()
    {
        super(new GridLayout(GRID_LAYOUT_DIMENSION.height, GRID_LAYOUT_DIMENSION.width));

        loadImages();

        for (int i = 0; i < GRID_LAYOUT_DIMENSION.height * GRID_LAYOUT_DIMENSION.width; ++i)
        {
            if (i == 9)
            {
                add(new CustomButtonWrapper(MainOptionType.SPELL_INFO.toString(), mSpellInfoButtonIdleImage, mSpellInfoButtonPressedImage, this));
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

    private void loadImages()
    {
        try
        {
            mSpellInfoButtonIdleImage = ImageIO.read(this.getClass().getResourceAsStream("/" + MainOptionType.SPELL_INFO.toString() + BUTTON_IDLE_IMAGE_SUFFIX));
            mSpellInfoButtonPressedImage = ImageIO.read(this.getClass().getResourceAsStream("/" + MainOptionType.SPELL_INFO.toString() + BUTTON_PRESSED_IMAGE_SUFFIX));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
