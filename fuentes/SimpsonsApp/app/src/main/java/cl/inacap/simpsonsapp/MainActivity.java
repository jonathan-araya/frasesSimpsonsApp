package cl.inacap.simpsonsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.simpsonsapp.Adapter.PersonajesAdapter;
import cl.inacap.simpsonsapp.dto.Personaje;

public class MainActivity extends AppCompatActivity {

    private List<Personaje> personajes = new ArrayList<>();
    private ListView personajesList;
    private PersonajesAdapter personajesAdapter;
    private RequestQueue queue;
    Button btn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.solicitarbtn);
        spinner = (Spinner)findViewById(R.id.numero_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numero, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        this.personajesList = findViewById(R.id.main_list_view);

        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                metodoJson();
            }
        });

    }
    private void metodoJson(){
        queue = Volley.newRequestQueue(this);
        this.personajesList = findViewById(R.id.main_list_view);
        this.personajesAdapter = new PersonajesAdapter(this,
                R.layout.list_personajes, personajes);
        this.personajesList.setAdapter(this.personajesAdapter);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                "https://thesimpsonsquoteapi.glitch.me/quotes?count=1",
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (response.length() > 0){
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        agarrardataso(jsonArray);
                    }catch (Exception ex){
                        personajes.clear();
                    }finally {
                        Log.e("PERSONAJES QUOTE", "Error de peticion");
                        personajesAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                personajes.clear();
                Log.e("PERSONAJES QUOTE", "Error de respuesta");
                personajesAdapter.notifyDataSetChanged();
            }
        }
        );
        queue.add(jsonObjectRequest);
    }

    private String agarrarUrl() {
        String numero = spinner.getSelectedItem().toString();
        int numero2 = Integer.parseInt(numero);
        String url;

        if (numero2 == 1){
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=1";
        }else if (numero2 == 2){
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=2";
        }else if (numero2 == 3){
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=3";
        }else if (numero2 == 4){
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=4";
        }else if (numero2 == 5){
        url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=5";
        }else if (numero2 == 6){
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=6";
        }else if (numero2 == 7) {
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=7";
        }else if (numero2 == 8) {
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=8";
        }else if (numero2 == 9) {
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=9";
        }else {
            url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=10";
        }
        return url;
    }

    public void agarrardataso(JSONArray jsonArray){
        try {
            for (int i = 0; i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String imagen = jsonObject.getString("image");
                String nombre = jsonObject.getString("character");
                String frase = jsonObject.getString("quote");
                Personaje p = new Personaje();
                p.setCharacter(nombre);
                p.setImage(imagen);
                p.setQuote(frase);
                personajes.add(p);

            }
        }catch (Exception ex){
            personajes.clear();
            Log.e("PERSONAJES QUOTE", "Error de dataso");
        }
    }


}