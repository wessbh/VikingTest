package com.wassimbh.vikingtest.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.wassimbh.vikingtest.MainActivity;
import com.wassimbh.vikingtest.R;
import com.wassimbh.vikingtest.models.Article;
import com.wassimbh.vikingtest.utilities.CustomRVItemTouchListener;
import com.wassimbh.vikingtest.utilities.RecyclerAdapter;
import com.wassimbh.vikingtest.utilities.RecyclerViewItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerFragment extends Fragment {

    private List<Article> articleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private RequestQueue mQueue;
    private ActionBar abar;

    public RecyclerFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycler, container, false);


        MainActivity activity = (MainActivity) getActivity();
        abar = activity.getAbar();
        activity.getTextviewTitle().setText(R.string.app_name);
        abar.setDisplayHomeAsUpEnabled(false);

        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recyclerAdapter);
        mQueue = Volley.newRequestQueue(getActivity());
        getArticles();
        recyclerView.addOnItemTouchListener(new CustomRVItemTouchListener(getContext(), recyclerView, new RecyclerViewItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Article article_clicked = articleList.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("external_link", article_clicked.getExternal_link());
                bundle.putString("name", article_clicked.getName());
                MainActivity activity = (MainActivity ) getActivity();
                Fragment articleFragment = new ArticleFragment();
                articleFragment.setArguments(bundle);
                activity.changeFragment(articleFragment);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        return view;
    }


    public void getArticles() {
        Log.d("mriGel", "i'm in getArticle()");
        String url = "https://mcc.hu/api/app/article_types/5729fc387fdea7e267fa9761";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.d("mriGel", "i'm in response");
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject article_json = response.getJSONObject(i);
                                String id = article_json.getString("id");
                                String name = article_json.getString("name");
                                String subtitle = article_json.getString("subtitle");
                                String external_link = article_json.getString("external_link");
                                String created = article_json.getString("created");
                                String thumbnail = article_json.getString("thumbnail");
                                Article article = new Article();
                                article.setId(id);
                                article.setName(name);
                                article.setSubtitle(subtitle);
                                article.setCreated(created);
                                article.setExternal_link(external_link);
                                article.setThumbnail(thumbnail);
                                articleList.add(article);
                            }
                            recyclerAdapter = new RecyclerAdapter(getActivity(), articleList);
                            recyclerView.setAdapter(recyclerAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("mriGel", "i'm error");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

}
