package com.example.belajarapiretrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {
    @SerializedName("teams")
    private List<ItemModel> teams;

    public List<ItemModel> getTeams() {
        return teams;
    }
}
