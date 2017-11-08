package com.example.logonrm.starwarsapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero;
    private TextView tvNome;
    private TextView tvFilmes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero = (EditText) findViewById(R.id.etNumero);
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvFilmes = (TextView) findViewById(R.id.tvFilmes);
    }

    public void pesquisar(View view) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final StarWarsService service = retrofit.create(StarWarsService.class);

        service.buscarPessoa(etNumero.getText().toString())
                .enqueue(new Callback<StarWars>() {
                    @Override
                    public void onResponse(Call<StarWars> call, Response<StarWars> response) {
                        if(response.isSuccessful()){
                            StarWars starWars = response.body();
                            tvNome.setText(starWars.getName());
                            final StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < starWars.getFilms().size(); i++) {
                                String replaceStr = starWars.getFilms().get(i);
                                replaceStr = replaceStr.replaceAll("\\D+","");
                                service.buscarFilme(replaceStr).enqueue(new Callback<StarWars>() {
                                    @Override
                                    public void onResponse(Call<StarWars> call, Response<StarWars> response) {
                                        if(response.isSuccessful()){
                                            sb.append(response.body().getTitle());
                                            sb.append("\n");
                                            tvFilmes.setText(sb.toString());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<StarWars> call, Throwable t) {

                                    }
                                });
                            }

                        } else {
                            Toast.makeText(MainActivity.this, "Erro de API",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<StarWars> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Erro de requisição",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
