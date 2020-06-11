package com.alexk.wowhelper.view;

import com.alexk.wowhelper.events.EventSystem;
import com.alexk.wowhelper.events.ILoadingProgressUpdateEventListener;
import com.alexk.wowhelper.events.LoadingProgressUpdateEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class LoadingProgressView extends JPanel implements ILoadingProgressUpdateEventListener
{
    private static final String LOADING_BAR_IMAGE_PATH       = "/loading_bar.png";
    private static final String LOADING_BAR_BLOCK_IMAGE_PATH = "/loading_bar_block.png";

    private static final Dimension DEFAULT_LOADING_BAR_DIMENSION       = new Dimension(1521, 66);
    private static final Dimension DEFAULT_LOADING_BAR_BLOCK_DIMENSION = new Dimension(37, 37);
    private static final Point DEFAULT_LOADING_BAR_BLOCK_OFFSET        = new Point(60, 16);
    private static final int DEFAULT_LOADING_BAR_BLOCK_MAX_WIDTH       = 1412;

    private Image mLoadingBarImage, mLoadingBarBlockImage;
    private float mLoadingBarCompleteFraction;

    public LoadingProgressView()
    {
        loadImages();
        setTransparency();

        mLoadingBarCompleteFraction = 0.0f;

        EventSystem.subscribeToEvent(LoadingProgressUpdateEvent.class, this);
    }

    @Override
    public void onLoadingProgressUpdateEvent(float newLoadingProgress)
    {
        mLoadingBarCompleteFraction = newLoadingProgress;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        float loadingBarHeight = getHeight()/15.0f;
        float loadingBarOffset = 1.5f * loadingBarHeight;

        g.drawImage(
                mLoadingBarImage,
                0,
                (int)(getHeight() - loadingBarOffset),
                getWidth(),
                (int)(loadingBarHeight),
                null
        );

        float targetLoadingBarBlockX = (DEFAULT_LOADING_BAR_BLOCK_OFFSET.x * getWidth())/(float)DEFAULT_LOADING_BAR_DIMENSION.width;
        float targetLoadingBarBlockY = (DEFAULT_LOADING_BAR_BLOCK_OFFSET.y * loadingBarHeight)/(float)DEFAULT_LOADING_BAR_DIMENSION.height;
        float targetBlockWidth = (1.0f - mLoadingBarCompleteFraction) * DEFAULT_LOADING_BAR_BLOCK_DIMENSION.width + (mLoadingBarCompleteFraction) * DEFAULT_LOADING_BAR_BLOCK_MAX_WIDTH;
        float targetLoadingBarBlockWidth = (targetBlockWidth * getWidth())/(float)DEFAULT_LOADING_BAR_DIMENSION.width;
        float targetLoadingBarBlockHeight = (DEFAULT_LOADING_BAR_BLOCK_DIMENSION.height * loadingBarHeight)/(float)DEFAULT_LOADING_BAR_DIMENSION.height;

        g.drawImage(
                mLoadingBarBlockImage,
                (int)targetLoadingBarBlockX,
                (int)(getHeight() - loadingBarOffset + targetLoadingBarBlockY),
                (int)targetLoadingBarBlockWidth,
                (int)targetLoadingBarBlockHeight,
                null
        );
    }

    private void loadImages()
    {
        try
        {
            mLoadingBarImage      = ImageIO.read(this.getClass().getResourceAsStream(LOADING_BAR_IMAGE_PATH));
            mLoadingBarBlockImage = ImageIO.read(this.getClass().getResourceAsStream(LOADING_BAR_BLOCK_IMAGE_PATH));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void setTransparency()
    {
        setOpaque(false);
    }
}
