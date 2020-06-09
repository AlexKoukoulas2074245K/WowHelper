package com.alexk.wowhelper.model;

public enum ClassType
{
    DRUID("druid"),
    HUNTER("hunter"),
    MAGE("mage"),
    PALADIN("paladin"),
    PRIEST("priest"),
    ROGUE("rogue"),
    SHAMAN("shaman"),
    WARLOCK("warlock"),
    WARRIOR("warrior");

    private final String mClassName;
    private ClassType(final String className)
    {
        mClassName = className;
    }

    @Override
    public String toString()
    {
        return mClassName;
    }
}
