package com.alexk.wowhelper.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class CustomButtonWrapper extends JButton implements MouseListener, ActionListener
{
    private static final String BUTTON_IDLE_IMAGE_SUFFIX    = "_button.png";
    private static final String BUTTON_PRESSED_IMAGE_SUFFIX = "_button_pressed.png";

    private final String mButtonName;
    private final ICustomButtonPressedListener mButtonPressedListener;
    private Image mIdleButtonImage, mPressedButtonImage;
    private Image mCurrentImage;

    public CustomButtonWrapper(
            final String buttonName,
            final ICustomButtonPressedListener buttonPressedListener
    )
    {
        super();

        mButtonName = buttonName;

        loadImages();

        mCurrentImage = mIdleButtonImage;

        mButtonPressedListener = buttonPressedListener;

        addActionListener(this);
        addMouseListener(this);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(mCurrentImage, 0,0, getWidth(), getHeight(), null);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        mCurrentImage = mPressedButtonImage;
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        mCurrentImage = mIdleButtonImage;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        mButtonPressedListener.OnCustomButtonPressed(mButtonName);
    }


    private void loadImages()
    {
        try
        {
            mIdleButtonImage    = ImageIO.read(this.getClass().getResourceAsStream("/" + mButtonName + BUTTON_IDLE_IMAGE_SUFFIX));
            mPressedButtonImage = ImageIO.read(this.getClass().getResourceAsStream("/" + mButtonName + BUTTON_PRESSED_IMAGE_SUFFIX));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
