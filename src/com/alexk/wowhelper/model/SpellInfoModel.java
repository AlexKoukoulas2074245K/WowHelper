package com.alexk.wowhelper.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SpellInfoModel
{
    private Map<String, SpellInfo> mSpellInfoMap;

    public SpellInfoModel()
    {
        mSpellInfoMap = new HashMap<>();
    }

    public Object[] getAllSpellNames() { return mSpellInfoMap.keySet().toArray(); }

    public SpellInfo getSpellInfo(final String spellName) { return mSpellInfoMap.get(spellName); }

    public void addSpellArray(final JSONArray spellArray)
    {
        for (Object entry: spellArray)
        {
            JSONObject spellEntry = (JSONObject)entry;

            String spellName = spellEntry.get("name").toString();

            if (spellEntry.containsKey("rank") && !spellEntry.get("rank").toString().equals("null"))
            {
                spellName += " (" + spellEntry.get("rank") + ")";
            }

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
