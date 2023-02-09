package com.samfrosh.dictionary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Dictionary extends AppCompatActivity {
String examples,meanings,partofspeech;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Loading loading = new Loading(this);
    ImageView searchicon;
    private int shortAnimationDuration;
    private ArrayList<Words> userList = new ArrayList<>();
    SearchView searchingview;
    EditText searchword;
    TextView meaning,example,word,speech,phonectics;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        speech = findViewById(R.id.partofspeechdistionary);
        meaning = findViewById(R.id.Definitiondistionary);
        word = findViewById(R.id.word);
        phonectics = findViewById(R.id.phonectics);
        example = findViewById(R.id.exampledistionary);
        searchingview = findViewById(R.id.searchingview);
        searchicon = findViewById(R.id.searchicon);


        searchicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchingview.setAlpha(0f);
                searchingview.setVisibility(View.VISIBLE);

                // Animate the content view to 100% opacity, and clear any animation
                // listener set on the view.
                searchingview.animate()
                        .alpha(1f)
                        .setDuration(shortAnimationDuration)
                        .setListener(null);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setHasFixedSize(false);

//THIS IS THE SEARCHPLACEHOLDER THAT COLLECTS EVERY TEXT
        searchingview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.equals("")){
                    Toast.makeText(Dictionary.this, "Empty", Toast.LENGTH_SHORT).show();
                }else{
                    searchword(query);
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
               return false;
            }
        });



    }

    private void searchword(String getsearchword) {
        loading.showLoadingDialog();

        //THIS IS THE API RESPONSIBLE FOR THE REQUEST AND A VARIABLE FOR THE WORD TO SEARCH
        String dictionaryapi = "https://api.dictionaryapi.dev/api/v2/entries/en/"+getsearchword;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, dictionaryapi, new Response.Listener<String>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        JSONArray jsonArray1 = jsonObject.getJSONArray("meanings");
                        String wording = jsonObject.getString("word");
                        String phoneticss = jsonObject.getString("phonetic");
                        word.setText(wording);
                        phonectics.setText(phoneticss);
                        for (int j = 0; j < jsonArray1.length(); j++){
                            JSONObject jsonObject1 = jsonArray1.getJSONObject(0);
                            JSONArray jsonArray2 = jsonObject1.getJSONArray("definitions");
                             partofspeech = jsonObject1.getString("partOfSpeech");
                             speech.setText(partofspeech);

                            for (int jj = 0; jj < jsonArray2.length(); jj++){
                                JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
                                 meanings = jsonObject2.getString("definition");
                               meaning.setText(meanings);

//                                Words word = new Words(meanings,"",partofspeech,"hi");
//                                userList.add(word);
                                loading.dismissDialog();
                            }

                            for (int a =0; a < jsonArray2.length(); a++){
                                JSONObject jsonObject2 = jsonArray2.getJSONObject(0);
                                 examples = jsonObject2.getString("example");
                                example.setText(examples);
                            }
                        }
                    }
//                    adapter = new RecyclerAdapter(userList);
//                    recyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();


                } catch (Exception e) {
                    loading.dismissDialog();
                    Toast.makeText(Dictionary.this, "We Could not process your request", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismissDialog();
                Toast.makeText(Dictionary.this, "An error occurred", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void goback(View view) {
        onBackPressed();
    }
}