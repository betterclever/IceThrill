package com.betterclever.icethrill;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.betterclever.icethrill.objects.Life;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by betterclever on 24/11/16.
 */

public class FirebaseHelper implements Net.HttpResponseListener {

    private final String DB_URL = "https://ice-thril.firebaseio.com/Scores.json";
    private URL url;
    private URLConnection connection;

    public ArrayList<User> users;

    public FirebaseHelper(){

        users = new ArrayList<User>();
        try {
            url = new URL(DB_URL);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        getHighScores();
    }

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {

        String s = httpResponse.getResultAsString();
        System.out.println(s);
        users.clear();

        List<User> users1 = entry(s);
        for(User u:users1) {
            System.out.println(u.getName());
        }

        users.addAll(users1);
        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User user, User t1) {
                return t1.getScore() - user.getScore();
            }
        });

        //System.out.println(users.size());
    }


    public boolean addScore(User user){

        getHighScores();

        Net.HttpRequest httpPost = new Net.HttpRequest(Net.HttpMethods.POST);
        httpPost.setUrl(DB_URL);
        httpPost.setHeader("Content-Type", "application/json");

        //users.add(user);

        httpPost.setContent(reverse(user));
        Gdx.net.sendHttpRequest(httpPost,FirebaseHelper.this);

        return false;
    }

    public boolean getHighScores(){
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

    public List<User> entry(String full) {
        List<User> u = new ArrayList<User>();
        int i,j;
        i = 1;
        while(i < full.length()) {
            String na = "", sc = "";
            int sco = 0;
            while(full.charAt(i)!=':') {
                i++;
            }
            i++;
            if (full.charAt(i) == '{' ) {
                while(full.charAt(i) != ':') {
                    i++;
                }
                i += 2;
                while(full.charAt(i) != '"') {
                    na = na + full.charAt(i);
                    i++;
                }
                while(full.charAt(i) != ':') {
                    i++;
                }
                i++;
                while(full.charAt(i) != '}') {
                    sc = sc + full.charAt(i);
                    i++;
                }
                sco = Integer.parseInt(sc);
            }
            u.add(new User(na,sco));
            if(full.charAt(i+1) == '}') {
                break;
            }
            i += 2;
        }
        /*for(i = 0 ; i < u.size() ; i++)
            System.out.println(u.get(i).getName() + " " + u.get(i).getScore());
        */
        return u;
    }

    public static String reverse(User u) {
        int i, j;
        String full = "{";
        full+= "\"name\":\""+u.getName()+"\"," ;
        full+= "\"score\":"+u.getScore() ;
        full+="}";

        //System.out.println("full"+ full);
        return full;
    }

}
