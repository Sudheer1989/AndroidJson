package com.sudheer.sweety.androidjson;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


//new  commit 123
public class MainActivity extends AppCompatActivity {
        JSONObject jsonobject;
        JSONArray jsonarray;
        ListView listview;
        ListViewAdapter adapter;
        ProgressDialog mProgressDialog;
        ArrayList<HashMap<String, String>> arraylist;
        static String TITLE = "title";
        static String DESCRIPTION = "description";
        static String IMAGEHREF = "imageHref";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // Get the view from listview_main.xml
            setContentView(R.layout.listview_main);
            // Execute DownloadJSON AsyncTask
            new DownloadJSON().execute();
        }

        // DownloadJSON AsyncTask
        private class DownloadJSON extends AsyncTask<Void, Void, Void> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                /*// Create a progressdialog
                mProgressDialog = new ProgressDialog(MainActivity.this);
                // Set progressdialog title
                mProgressDialog.setTitle("WAIT LOADING....");
                // Set progressdialog message
                mProgressDialog.setMessage("Loading...");
                mProgressDialog.setIndeterminate(false);
                // Show progressdialog
                mProgressDialog.show();*/
            }

            @Override
            protected Void doInBackground(Void... params) {
                // Create an array
                arraylist = new ArrayList<HashMap<String, String>>();
                // Retrieve JSON Objects from the given URL address
                jsonobject = JSONfunctions
                        .getJSONfromURL("https://dl.dropboxusercontent.com/u/746330/facts.json");

                try {
                    // Locate the array name in JSON
                    jsonarray = jsonobject.getJSONArray("rows");

                    for (int i = 0; i < jsonarray.length(); i++) {
                        HashMap<String, String> map = new HashMap<String, String>();
                        jsonobject = jsonarray.getJSONObject(i);
                        // Retrive JSON Objects
                        map.put("title", jsonobject.getString("title"));
                        map.put("description", jsonobject.getString("description"));
                       // map.put("", jsonobject.getString("population"));
                        map.put("imageHref", jsonobject.getString("imageHref"));
                        // Set the JSON Objects into the array
                        arraylist.add(map);
                    }
                } catch (JSONException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void args) {
                // Locate the listview in listview_main.xml
                listview = (ListView) findViewById(R.id.listview);
                // Pass the results into ListViewAdapter.java
                adapter = new ListViewAdapter(MainActivity.this, arraylist);
                // Set the adapter to the ListView
                listview.setAdapter(adapter);
                // Close the progressdialog
               /* mProgressDialog.dismiss();*/
            }
        }
    }

