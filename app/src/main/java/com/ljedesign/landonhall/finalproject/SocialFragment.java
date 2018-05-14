package com.ljedesign.landonhall.finalproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.net.URL;

/**
 * Public Class SocialFragment
 * Fragment for Social Tab
 */

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

    /**
     * Async public class FetchFBData
     * Calls Facebook Api and recieves JSON.
     */
    public class FetchFBData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //Build the URL
            URL fbURL = networkUtils.buildUrl();

            try {
                //Get the response
                String jsonFBResponse = networkUtils
                        .getResponseFromHttpUrl(fbURL);
                //return the response
                return jsonFBResponse;

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String FBData) {
            if (FBData != null) {
                //Initialize
                fbDataUtils fbData = new fbDataUtils();
                //Parse and show the data
                fbData.parseFBData(getContext(), getView(), FBData);
            }
        }

    }
}