package com.betterclever.icethrill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by betterclever on 24/11/16.
 */

public class FirebaseHelper implements Net.HttpResponseListener {

    private final String DB_URL = "https://ice-thril.firebaseio.com/Scores.json";
    private URL url;
    private URLConnection connection;

    private ArrayList<User> users;

    public FirebaseHelper(){

        users = new ArrayList<User>();
        try {
            url = new URL(DB_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {

        System.out.println(httpResponse.getResultAsString());

    }

    public List<User> getUsersFromString(String string){
        return null;
    }

    public boolean addScore(User user){

        getHighScores();

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.PUT);
        httpPost.setUrl(DB_URL);
        httpPost.setHeader("Content-Type", "application/json");

        users.add(user);

        Gson gson =  new Gson();
        httpPost.setContent(gson.toJson(users));
        Gdx.net.sendHttpRequest(httpPost,FirebaseHelper.this);

        return false;
    }

    public boolean getHighScores(){

        System.out.println("I am called");

        Net.HttpRequest httpGet = new Net.HttpRequest(Net.HttpMethods.GET);
        httpGet.setUrl(DB_URL);
        httpGet.setHeader("Content-Type", "application/json");
        Gdx.net.sendHttpRequest(httpGet, FirebaseHelper.this);
        return false;
    }

    @Override
    public void failed(Throwable t) {

    }

    @Override
    public void cancelled() {

    }

}
