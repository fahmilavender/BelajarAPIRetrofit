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
    TeamAdapter teamAdapter;
    ProgressBar pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String leagueName = getIntent().getStringExtra("LEAGUE");

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);
        rvTeam = (RecyclerView) findViewById(R.id.rvTeam);
        rvTeam.setLayoutManager(new LinearLayoutManager(this));

        TeamApi api = RetrofitClient.getInstance().create(TeamApi.class);

        if (leagueName.equals("LALIGA")) {
            fetchEnglishPremierLeagueTeams(api);
        } else if (leagueName.equals("EPL")) {
            fetchLaligaTeams(api);
        }
    }

    private void fetchLaligaTeams(TeamApi api) {
        api.getEnglishPremierLeague("English Premier League").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ItemModel> teams = response.body().getTeams();
                    teamAdapter = new TeamAdapter(teams);
                    rvTeam.setAdapter(teamAdapter);
                    rvTeam.setVisibility(View.VISIBLE);
                    pbLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchEnglishPremierLeagueTeams(TeamApi api) {
        api.getLaliga("Spanish La Liga").enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ItemModel> teams = response.body().getTeams();
                    teamAdapter = new TeamAdapter(teams);
                    rvTeam.setAdapter(teamAdapter);
                    rvTeam.setVisibility(View.VISIBLE);
                    pbLoading.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                Log.e("API_ERROR", t.getMessage());
                Toast.makeText(MainActivity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
