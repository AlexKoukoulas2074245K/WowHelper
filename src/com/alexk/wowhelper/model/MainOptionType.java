package com.alexk.wowhelper.model;

public enum MainOptionType
{
    SPELL_INFO("spell_info");

    private final String mOptionTypeName;

    MainOptionType(final String optionTypeName)
    {
        mOptionTypeName = optionTypeName;
    }

    @Override
    public String toString()
    {
        return mOptionTypeName;
    }
}
