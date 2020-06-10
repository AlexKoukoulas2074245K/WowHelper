package com.alexk.wowhelper.main;

import com.alexk.wowhelper.controller.MainApplicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Application extends JFrame implements ComponentListener
{
    public static final Dimension DEFAULT_DIMENSION = new Dimension(1040, 585);
    public static final float ASPECT_RATIO          = DEFAULT_DIMENSION.width/(float)DEFAULT_DIMENSION.height;

    private static final String WINDOW_TITLE = "Wow Helper";
    private MainApplicationController mMainApplicationController;

    public Application()
    {
        super(WINDOW_TITLE);

        addComponentListener(this);

        mMainApplicationController = new MainApplicationController();

        setPreferredSize(DEFAULT_DIMENSION);
        setContentPane(mMainApplicationController.getView());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        mMainApplicationController.update();
    }

    @Override
    public void componentResized(final ComponentEvent e)
    {
        Rectangle b = e.getComponent().getBounds();
        e.getComponent().setBounds(b.x, b.y, b.width, (int)(b.width/ASPECT_RATIO));
    }

    @Override
    public void componentMoved(final ComponentEvent e) {}

    @Override
    public void componentShown(final ComponentEvent e) {}

    @Override
    public void componentHidden(final ComponentEvent e) {}
}
