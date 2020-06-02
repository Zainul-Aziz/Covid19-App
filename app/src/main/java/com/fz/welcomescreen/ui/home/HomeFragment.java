package com.fz.welcomescreen.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fz.welcomescreen.HomeActivity;
import com.fz.welcomescreen.R;

import org.json.JSONException;
import org.json.JSONObject;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        /*final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://corona.lmao.ninja/v2/all", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Log.d("myapp" , "The response is " + response.getString("cases"));
                    //Log.d("cases",cases);
                    String totalCases = response.getString("cases");
                    TextView totalCase = root.findViewById(R.id.totalCases);
                    totalCase.setText(totalCases);
                    String newCases = response.getString("todayCases");
                    TextView newCase = root.findViewById(R.id.newCases);
                    newCase.setText(newCases);
                    String totalDeaths = response.getString("deaths");
                    TextView totalDeath = root.findViewById(R.id.totaldeaths);
                    totalDeath.setText(totalDeaths);
                    String newDeaths = response.getString("todayDeaths");
                    TextView newDeath = root.findViewById(R.id.newDeaths);
                    newDeath.setText(newDeaths);
                    String recoveredCases = response.getString("recovered");
                    TextView recovered = root.findViewById(R.id.recoveredCases);
                    recovered.setText(recoveredCases);
                    String activeCases = response.getString("active");
                    TextView active = root.findViewById(R.id.activeCases);
                    active.setText(activeCases);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","Something went wrong");
            }
        });
        requestQueue.add(jsonObjectRequest);
    return root;
    }

    }

