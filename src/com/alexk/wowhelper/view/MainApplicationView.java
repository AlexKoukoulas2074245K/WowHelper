package com.alexk.wowhelper.view;

import javax.swing.*;
import java.awt.*;

public class MainApplicationView extends JPanel implements IView
{
    public MainApplicationView()
    {
        super(new BorderLayout());
    }

    @Override
    public void addSubView(JPanel subView)
    {
        add(subView);
    }
}
