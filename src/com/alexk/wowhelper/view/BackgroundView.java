package com.alexk.wowhelper.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class BackgroundView extends JPanel implements MouseListener
{
    private static final String BACKGROUND_IMAGE_PATH        = "/background.png";
    private static final String LOADING_BAR_IMAGE_PATH       = "/loading_bar.png";
    private static final String LOADING_BAR_BLOCK_IMAGE_PATH = "/loading_bar_block.png";

    private static final Dimension DEFAULT_LOADING_BAR_DIMENSION       = new Dimension(1521, 66);
    private static final Dimension DEFAULT_LOADING_BAR_BLOCK_DIMENSION = new Dimension(37, 37);
    private static final Point DEFAULT_LOADING_BAR_BLOCK_OFFSET        = new Point(60, 16);
    private static final int DEFAULT_LOADING_BAR_BLOCK_MAX_WIDTH       = 1412;

    private Image mBackgroundImage, mLoadingBarImage, mLoadingBarBlockImage;
    private float mLoadingProgress;
    private boolean mIsLoading;

    public BackgroundView()
    {
        super();
        addMouseListener(this);

        loadImages();

        mIsLoading = true;
        mLoadingProgress = 0.0f;
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        g.drawImage(mBackgroundImage, 0, 0, getWidth(), getHeight(),null);

        if (mIsLoading)
        {
            renderLoadingBar(g);
        }
    }

    private void loadImages()
    {
        try
        {
            mBackgroundImage      = ImageIO.read(this.getClass().getResourceAsStream(BACKGROUND_IMAGE_PATH));
            mLoadingBarImage      = ImageIO.read(this.getClass().getResourceAsStream(LOADING_BAR_IMAGE_PATH));
            mLoadingBarBlockImage = ImageIO.read(this.getClass().getResourceAsStream(LOADING_BAR_BLOCK_IMAGE_PATH));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void renderLoadingBar(final Graphics g)
    {
        float loadingBarHeight = getHeight()/15;
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
        float targetBlockWidth = (1.0f - mLoadingProgress) * DEFAULT_LOADING_BAR_BLOCK_DIMENSION.width + (mLoadingProgress) * DEFAULT_LOADING_BAR_BLOCK_MAX_WIDTH;
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
}
