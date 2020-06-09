package com.alexk.main;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Application extends JFrame
{
    private static final Dimension DEFAULT_DIMENSION = new Dimension(800, 600);
    private static final String WINDOW_TITLE = "Wow Helper";

    public Application()
    {
        super(WINDOW_TITLE);

        setPreferredSize(DEFAULT_DIMENSION);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        try
        {
            String html = Jsoup.connect("https://classic.wowhead.com/mage-abilities").get().html();
            String htmlListViewLineString = html.substring(html.indexOf("listviewspells"));
            String htmlListViewActualJsonString = htmlListViewLineString.substring(htmlListViewLineString.indexOf('['), htmlListViewLineString.indexOf("new Listview") - 2);
            htmlListViewActualJsonString = htmlListViewActualJsonString.replace("popularity", "\"popularity\"");

            JSONParser parser = new JSONParser();
            JSONArray listArray = (JSONArray)(parser.parse(htmlListViewActualJsonString));

            System.out.println(listArray.size());
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
