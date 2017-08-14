package com.example.aki.jpat;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aki on 8/13/2017.
 */

public class Trending extends Fragment {
    private static final int STAGGERED_GRID_SPAN = 2;
    public List<Data> items;
    private String url;
    RecyclerView.Adapter rva;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        items=new ArrayList<>();

        url="https://newsapi.org/v1/articles?source=mtv-news&sortBy=top&apiKey=100b563ce9af4be18f01a196129732aa";
        new aTask().execute(url);
        RecyclerView rv = (RecyclerView) inflater.inflate(
                R.layout.news, container, false);
        rv.setLayoutManager(new StaggeredGridLayoutManager(STAGGERED_GRID_SPAN, StaggeredGridLayoutManager.VERTICAL));
        rva=new StaggeredRecyclerAdapter(items,getContext());
        rv.setAdapter(rva);
        return rv;
    }



    public class aTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {

            String result="";
            URL url;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(urls[0]);
                urlConnection=(HttpURLConnection)url.openConnection();
                String respone=ss(urlConnection.getInputStream());
                parse(respone);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            rva.notifyDataSetChanged();
        }
    }
    String ss(InputStream stream) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(stream));
        String data;
        String result="";
        while ((data=br.readLine())!=null)
        {
            result+=data;
        }
        if (null!=stream)
        {
            stream.close();
        }
        return result;
    }
    private void parse(String result)
    {
        JSONObject response= null;
        try {
            response = new JSONObject(result);
            JSONArray articles=response.optJSONArray("articles");
            for (int i=0;i<articles.length();i++){
                JSONObject article=articles.optJSONObject(i);
                String author=article.optString("author");
                String title=article.optString("title");
                String description=article.optString("description");
                String time=article.optString("publishedAt");
                String iurl=article.optString("urlToImage");
                String purl=article.optString("url");
                items.add(i,new Data(author,title,description,time,iurl,purl));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
