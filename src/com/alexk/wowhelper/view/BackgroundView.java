package com.alexk.wowhelper.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class BackgroundView extends JPanel
{
    private static final String BACKGROUND_IMAGE_PATH = "/background.png";

    private Image mBackgroundImage;

    public BackgroundView()
    {
        super(new BorderLayout());
        loadImages();
    }

    @Override
    public void paintComponent(final Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(),null);
    }

    private void loadImages()
    {
        try
        {
            mBackgroundImage = ImageIO.read(this.getClass().getResourceAsStream(BACKGROUND_IMAGE_PATH));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
