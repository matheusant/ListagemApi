package br.com.local.listagemapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerApi;
    private Button buttonParse;
    private RequestQueue queue;
    private List<Hero> heroi;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerApi = findViewById(R.id.recyclerApi);
        buttonParse = findViewById(R.id.buttonParse);

        queue = Volley.newRequestQueue(getApplicationContext());


        heroi = new ArrayList<>();


        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerApi.setLayoutManager(layoutManager);
                recyclerApi.setHasFixedSize(true);

                adapter = new RecyclerAdapter(MainActivity.this, heroi );
                recyclerApi.setAdapter(adapter);

                //listaHeroi();

                parse();

                // buttonParse.setVisibility(View.GONE);

            }
        });
    }

    public void parse(){
        String url = "http://192.168.15.28/HeroApi/v1/Api.php?apicall=getheroes";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("heroes");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);

                                int id = object.getInt("id");
                                String name = object.getString("name");
                                String realname = object.getString("realname");
                                int rating = object.getInt("rating");
                                String editora = object.getString("editora");

                                Hero hero = new Hero();

                                hero.setNome(realname);
                                hero.setHeroi(name);
                                hero.setRating(rating);
                                hero.setEditora(editora);

                                heroi.add( hero );
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        queue.add(request);

    }






    // Teste RecyclerView
    public void listaHeroi() {

        Hero hero = new Hero("Batman", "Bruce Wayne", 5, "DC");
        this.heroi.add(hero);
        hero = new Hero("Super Man", "Clark Kent", 3, "DC");
        this.heroi.add(hero);
        hero = new Hero("Homem Aranha", "Peter Parker", 5, "Marvel");
        this.heroi.add(hero);
        hero = new Hero("Homem de Ferro", "Tony Stark", 5, "Marvel");
        this.heroi.add(hero);

    }




}

















