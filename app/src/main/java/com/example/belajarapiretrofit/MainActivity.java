package com.example.belajarapiretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvTeam;
    ProgressBar pbLoading;
    TeamAdapter teamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTeam = findViewById(R.id.rvTeam);
        pbLoading = findViewById(R.id.pbLoading);

        rvTeam.setLayoutManager(new LinearLayoutManager(this));

        loadTeams();
    }

    private void loadTeams() {
        pbLoading.setVisibility(View.VISIBLE);

        TeamApi api = RetrofitClient.getInstance().create(TeamApi.class);
        Call<TeamResponse> call = api.getAllTeams("English Premier League");

        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                pbLoading.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    List<ItemModel> teams = response.body().getTeams();
                    teamAdapter = new TeamAdapter(teams);
                    rvTeam.setAdapter(teamAdapter);
                } else {
                    Toast.makeText(MainActivity.this, "Gagal mendapatkan data!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                pbLoading.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "GAGAL: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("API_ERROR", t.getMessage(), t);
            }
        });
    }
}
