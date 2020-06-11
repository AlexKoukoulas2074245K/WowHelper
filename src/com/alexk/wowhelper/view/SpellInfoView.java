package com.alexk.wowhelper.view;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.alexk.wowhelper.model.MainOptionType;
import com.alexk.wowhelper.model.SpellInfo;
import com.alexk.wowhelper.model.SpellInfoModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;

public class SpellInfoView extends JPanel implements ActionListener
{
    private static final String INFO_BOX_IMAGE_PATH    = "/info_box.png";
    private static final String GOLD_ICON_IMAGE_PATH   = "/gold_icon.png";
    private static final String SILVER_ICON_IMAGE_PATH = "/silver_icon.png";
    private static final String COPPER_ICON_IMAGE_PATH = "/copper_icon.png";

    private static final Dimension GRID_LAYOUT_DIMENSION = new Dimension(3, 11);
    private final SpellInfoModel mSpellInfoModel;
    private Image mInfoBoxImage, mGoldIconImage, mSilverIconImage, mCopperIconImage;

    private int[] mSelectedSpellCost;
    private int mSelectedSpellLevel;

    private JComboBox mSpellComboBox;
    private JPanel mSpellInfoPanel;

    public SpellInfoView(final SpellInfoModel spellInfoModel)
    {
        super(new GridLayout(GRID_LAYOUT_DIMENSION.height, GRID_LAYOUT_DIMENSION.width));

        loadImages();

        mSpellInfoModel = spellInfoModel;
        mSpellInfoPanel = new JPanel();

        mSelectedSpellCost = new int[]{1, 0, 99};
        mSelectedSpellLevel = 10;

        repopulateComponents();
    }

    public void repopulateComponents()
    {
        removeAll();

        for (int i = 0; i < GRID_LAYOUT_DIMENSION.height * GRID_LAYOUT_DIMENSION.width; ++i)
        {
            if (i == 16)
            {
                Object[] elements = mSpellInfoModel.getAllSpellNames();
                Arrays.sort(elements);
                mSpellComboBox = new JComboBox(elements);
                mSpellComboBox.addActionListener(this);
                add(mSpellComboBox);
            }
            else if (i == 19)
            {
                mSpellInfoPanel = new JPanel()
                {
                    @Override
                    public void paintComponent(Graphics g)
                    {
                        g.drawImage(mInfoBoxImage, 0,0, getWidth(), getHeight(), null);
                        g.setColor(Color.white);
                        g.drawString("Trainable at level " + mSelectedSpellLevel, 10, 20);

                        String goldCost = "  ";
                        if (mSelectedSpellCost[0] != 0)
                            goldCost = String.valueOf(mSelectedSpellCost[0]);

                        String silverCost = "  ";
                        if (mSelectedSpellCost[1] != 0)
                            silverCost = String.valueOf(mSelectedSpellCost[1]);

                        String copperCost = "  ";
                        if (mSelectedSpellCost[2] != 0)
                            copperCost = String.valueOf(mSelectedSpellCost[2]);

                        g.drawString("Cost    " + goldCost + "       " + silverCost + "       " + copperCost, 10, 40);

                        if (mSelectedSpellCost[0] != 0)
                            g.drawImage(mGoldIconImage, 70, 29, g.getFont().getSize(), g.getFont().getSize(), null);

                        if (mSelectedSpellCost[1] != 0)
                            g.drawImage(mSilverIconImage, 110, 29, g.getFont().getSize(), g.getFont().getSize(), null);

                        if (mSelectedSpellCost[2] != 0)
                            g.drawImage(mCopperIconImage, 150, 29, g.getFont().getSize(), g.getFont().getSize(), null);
                    }
                };
                mSpellInfoPanel.setVisible(false);
                add(mSpellInfoPanel);
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
    public void actionPerformed(final ActionEvent e)
    {
        mSpellInfoPanel.setVisible(true);
        SpellInfo spellInfo = mSpellInfoModel.getSpellInfo(mSpellComboBox.getSelectedItem().toString());
        mSelectedSpellLevel = spellInfo.getTrainableAtLevel();
        mSelectedSpellCost[0] = spellInfo.getCost() / 10000;
        mSelectedSpellCost[1] = (spellInfo.getCost() % 10000)/100;
        mSelectedSpellCost[2] = spellInfo.getCost() % 100;
        repaint();
    }

    private void loadImages()
    {
        try
        {
            mInfoBoxImage    = ImageIO.read(this.getClass().getResourceAsStream(INFO_BOX_IMAGE_PATH));
            mGoldIconImage   = ImageIO.read(this.getClass().getResourceAsStream(GOLD_ICON_IMAGE_PATH));
            mSilverIconImage = ImageIO.read(this.getClass().getResourceAsStream(SILVER_ICON_IMAGE_PATH));
            mCopperIconImage = ImageIO.read(this.getClass().getResourceAsStream(COPPER_ICON_IMAGE_PATH));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
