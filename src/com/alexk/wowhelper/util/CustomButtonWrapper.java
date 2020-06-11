package com.alexk.wowhelper.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButtonWrapper extends JButton implements MouseListener, ActionListener
{
    private final String mButtonName;
    private final Image mIdleButtonImage, mPressedButtonImage;
    private final ICustomButtonPressedListener mButtonPressedListener;
    private Image mCurrentImage;

    public CustomButtonWrapper(
            final String buttonName,
            final Image idleButtonImage,
            final Image pressedButtonImage,
            final ICustomButtonPressedListener buttonPressedListener
    )
    {
        super();

        mButtonName = buttonName;

        mIdleButtonImage    = idleButtonImage;
        mPressedButtonImage = pressedButtonImage;
        mCurrentImage       = mIdleButtonImage;

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
}
