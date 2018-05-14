package com.ljedesign.landonhall.finalproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by lhall4 on 5/10/2018.
 */

/**
 * Class to format Fb Data
 */
public class fbDataUtils extends SocialFragment {

    JSONObject jsonObject;
    JSONArray jsonArray;
    PostAdapter postAdapter;
    ListView listView;

    /**
     * Method to parse the FB JSON data
     * @param context
     * @param view
     * @param jsonString
     */

    public void parseFBData(Context context, View view, String jsonString) {
        DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        m_ISO8601Local.setTimeZone(TimeZone.getTimeZone("UTC"));

        postAdapter = new PostAdapter(context, R.layout.fb_row_layout);
        listView = view.findViewById(R.id.fbListView);
        listView.setAdapter(postAdapter);
        try  {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("data");

            int count = 0;

            String postDate, postContent;

            while (count < jsonArray.length()) {

                JSONObject JO = jsonArray.getJSONObject(count);
                if(JO.has("message")) {
                    try {
                        postDate = m_ISO8601Local.parse(JO.getString("created_time")).toString();
                    } catch (ParseException e) {
                        e.printStackTrace();
                        postDate = null;
                    }

                    postContent = JO.getString("message");

                    Posts posts = new Posts(postContent, postDate);
                    postAdapter.add(posts);
                }

                    count++;


            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
