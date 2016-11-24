package com.betterclever.icethrill.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.betterclever.icethrill.FirebaseHelper;
import com.betterclever.icethrill.User;

/**
 * Created by betterclever on 23/11/16.
 */

public class Scoreboard implements Screen {

    @Override
    public void show() {

        FirebaseHelper helper = new FirebaseHelper();
        helper.addScore(new User("a",10));
        helper.addScore(new User("b",20));
        helper.getHighScores();

        /*HttpRequestBuilder requestBuilder = new HttpRequestBuilder();
        final Net.HttpRequest httpRequest = requestBuilder.newRequest().method(Net.HttpMethods.GET).url("https://ice-thril.firebaseio.com/Scores.json").build();


        Net.HttpResponseListener listener = new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                System.out.println(httpResponse.getResultAsString());
            }

            @Override
            public void failed(Throwable t) {

            }

            @Override
            public void cancelled() {

            }
        };

        Gdx.net.sendHttpRequest(httpRequest, listener);

        HttpRequestBuilder requestBuilder1 = new HttpRequestBuilder();
        final Net.HttpRequest postRequest = requestBuilder.newRequest().method(Net.HttpMethods.POST).url("https://ice-thril.firebaseio.com/Scores.json").build();
        postRequest.setContent("{\"Pranjal\":100}");

        Gdx.net.sendHttpRequest(postRequest,listener);
*/
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
