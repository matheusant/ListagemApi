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


        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                queue = Volley.newRequestQueue(getApplicationContext());

                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerApi.setLayoutManager(layoutManager);
                recyclerApi.setHasFixedSize(true);

                heroi = new ArrayList<>();


                //listaHeroi();

                jsonParse();

                // buttonParse.setVisibility(View.GONE);

            }
        });
    }

    private void jsonParse() {

        String url = "http://192.168.15.28/HeroApi/v1/Api.php?apicall=getheroes";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i = 0; i < response.length(); i++) {

                            Hero hero = new Hero();

                            try {


                                JSONObject heroe = response.getJSONObject(i);


                                hero.setHeroi(heroe.getString("name"));
                                hero.setNome(heroe.getString("realname"));
                                hero.setRating(heroe.getInt("rating"));
                                hero.setEditora(heroe.getString("editora"));


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            heroi.add(hero);
                        }

                        adapter = new RecyclerAdapter(MainActivity.this, heroi );
                        recyclerApi.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);


    }






/*
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


 */


}

















