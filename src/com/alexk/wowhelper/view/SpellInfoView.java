package com.alexk.wowhelper.view;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.alexk.wowhelper.model.SpellInfoModel;

import javax.swing.*;
import java.awt.*;

public class SpellInfoView extends JPanel
{
    private static final Dimension GRID_LAYOUT_DIMENSION = new Dimension(3, 11);

    private final SpellInfoModel mSpellInfoModel;

    public SpellInfoView(final SpellInfoModel spellInfoModel)
    {
        super(new GridLayout(GRID_LAYOUT_DIMENSION.height, GRID_LAYOUT_DIMENSION.width));

        mSpellInfoModel = spellInfoModel;

        repopulateComponents();
    }

    public void repopulateComponents()
    {
        removeAll();

        for (int i = 0; i < GRID_LAYOUT_DIMENSION.height * GRID_LAYOUT_DIMENSION.width; ++i)
        {
            if (i == 16)
            {
                JComboBox comboBox = new JComboBox();
                Object[] elements = mSpellInfoModel.getAllSpellNames();

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        AutoCompleteSupport.install(comboBox, GlazedLists.eventListOf(elements));
                    }
                });

                add(comboBox);
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
}
