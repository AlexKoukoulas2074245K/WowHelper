package com.alexk.wowhelper.model;

public class SpellInfo
{
    private final int mTrainableAtLevel;
    private final int mCost;

    public SpellInfo(final int trainableAtLevel, final int cost)
    {
        mTrainableAtLevel = trainableAtLevel;
        mCost = cost;
    }

    public int getTrainableAtLevel() { return mTrainableAtLevel; }
    public int getCost() { return mCost; }
}
