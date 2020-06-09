package com.alexk.wowhelper.main;

import com.alexk.wowhelper.model.ClassType;
import com.alexk.wowhelper.parsing.SpellParser;
import com.alexk.wowhelper.view.BackgroundView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Application extends JFrame implements ComponentListener
{
    public static final Dimension DEFAULT_DIMENSION = new Dimension(1040, 585);
    public static final float ASPECT_RATIO          = DEFAULT_DIMENSION.width/(float)DEFAULT_DIMENSION.height;

    private static final String WINDOW_TITLE = "Wow Helper";

    public Application()
    {
        super(WINDOW_TITLE);

        addComponentListener(this);

        setPreferredSize(DEFAULT_DIMENSION);
        setContentPane(new BackgroundView());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        new SpellParser(ClassType.PALADIN).parse();
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
