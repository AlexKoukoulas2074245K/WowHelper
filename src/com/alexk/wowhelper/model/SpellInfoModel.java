package com.alexk.wowhelper.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SpellInfoModel
{
    private class SpellInfo
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

    private Map<String, SpellInfo> mSpellInfoMap;

    public SpellInfoModel()
    {
        mSpellInfoMap = new HashMap<>();
    }

    public Object[] getAllSpellNames() { return mSpellInfoMap.keySet().toArray(); }

    public void addSpellArray(final JSONArray spellArray)
    {
        for (Object entry: spellArray)
        {
            JSONObject spellEntry = (JSONObject)entry;

            String spellName     = spellEntry.get("name") + " (" + spellEntry.get("rank") + ")";
            int trainableAtLevel = ((Long)(spellEntry.get("level"))).intValue();
            int cost             = 0;

            if (spellEntry.containsKey("trainingcost"))
            {
                cost = ((Long)(spellEntry.get("trainingcost"))).intValue();
            }

            mSpellInfoMap.put(spellName, new SpellInfo(trainableAtLevel, cost));
        }
    }

}
