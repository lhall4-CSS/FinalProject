package com.ljedesign.landonhall.finalproject;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;

public class SocialFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new FetchFBData().execute();

    }
        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_social, container, false);
    }

    public class FetchFBData extends AsyncTask<String, Void, String> {

        // COMPLETED (18) Within your AsyncTask, override the method onPreExecute and show the loading indicator
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... params) {

            URL fbURL = networkUtils.buildUrl();

            try {
                String jsonFBResponse = networkUtils
                        .getResponseFromHttpUrl(fbURL);




                //Log.v("FB Data", String.valueOf(fbDataArray));

                return jsonFBResponse;
               // new fbDataUtils().parseFBData(jsonFBResponse);

            } catch (Exception e) {
                e.printStackTrace();
                //return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(String FBData) {
            // COMPLETED (19) As soon as the data is finished loading, hide the loading indicator
            // mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (FBData != null) {
                fbDataUtils fbData = new fbDataUtils();
                fbData.parseFBData(getContext(), getView(), FBData);
                // COMPLETED (11) If the weather data was not null, make sure the data view is visible
                //fbDataUtils fbData = new fbDataUtils();
                //fbData.parseFBData(FBData);
                /*
                 * Iterate through the array and append the Strings to the TextView. The reason why we add
                 * the "\n\n\n" after the String is to give visual separation between each String in the
                 * TextView. Later, we'll learn about a better way to display lists of data.
                 */
            }
        }

    }



    public static void FetchFBPostData(String postID) {
        URL fbPostURL = networkUtils.buildUrl(postID);
        try {
            String jsonFBResponse = networkUtils
                    .getResponseFromHttpUrl(fbPostURL);
            JSONObject FBJSON = new JSONObject(jsonFBResponse);

            //String[] simpleJsonWeatherData = OpenWeatherJsonUtils
            //.getSimpleWeatherStringsFromJson(MainActivity.this, jsonWeatherResponse);
            JSONArray fbDataArray = FBJSON.getJSONArray("data");

            Log.v("FB POST Data", String.valueOf(FBJSON));

            //return jsonFBResponse;

        } catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
    }
}