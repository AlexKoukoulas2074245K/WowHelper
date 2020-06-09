package com.alexk.wowhelper.parsing;

import com.alexk.wowhelper.model.ClassType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import java.io.IOException;

public class SpellParser implements IHtmlParser
{
    private static final String CLASS_ABILITIES_URL   = "https://classic.wowhead.com/";
    private static final String SPELL_LIST_BEGIN_HINT = "listviewspells";
    private static final String SPELL_LIST_END_HINT   = "new Listview";

    private final ClassType mClassType;

    public SpellParser(final ClassType classType)
    {
        mClassType = classType;
    }

    @Override
    public JSONArray parse()
    {
        try
        {
            String html = Jsoup.connect(CLASS_ABILITIES_URL + mClassType + "-abilities").get().html();
            String htmlListViewLineString = html.substring(html.indexOf(SPELL_LIST_BEGIN_HINT));
            String htmlListViewActualJsonString = htmlListViewLineString.substring(htmlListViewLineString.indexOf('['), htmlListViewLineString.indexOf(SPELL_LIST_END_HINT) - 2);

            // Repair quotes on popularity element
            htmlListViewActualJsonString = htmlListViewActualJsonString.replace("popularity", "\"popularity\"");

            JSONParser parser = new JSONParser();
            JSONArray parsedJson = (JSONArray) parser.parse(htmlListViewActualJsonString);

            System.out.println(parsedJson.size());

            return parsedJson;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
