package com.agea.altoque.screen.story;

import android.util.Log;

import com.agea.altoque.model.Information;
import com.agea.altoque.model.Story;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by marcos on 26/05/15.
 */
public class InformationController {
    private InformationScreenManager screenManager;
    private RequestQueue requestQueue;

    String URL_ALTOQUE = "http://api-editoriales.clarin.com/files/altoque/altoque.json";

    public InformationController(InformationScreenManager manager, RequestQueue request) {
        this.screenManager=manager;
        this.requestQueue = request;
        findInformation();
    }

    private void findInformation() {
        findStories();
        //findWeather();
        //findHoroscopo();
        //findCosasLocas();
    }

    private void findStories() {
        requestQueue.add(new JsonArrayRequest(URL_ALTOQUE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.e("response: ", response.get(0).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                List<Information> informations = parseResponseToStories(response);
                screenManager.updateStories(informations);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }));
    }

    private List<Information> parseResponseToStories(JSONArray response) {

        List<Information> stories = new ArrayList<Information>();
        Story story = null;



        for (int i = 0; i < response.length() ; i++) {

            try {
                JSONObject nota = response.getJSONObject(i);
                String descripcion = nota.getString("Descripcion");
                JSONArray images = nota.getJSONArray("Fotos");
                String video = nota.getString("Video");
                String fecha = nota.getString("Fecha");
                //String image = images.length() > 0 ? (String) images.get(0) : "";

                story = new Story();
                story.setDescription(descripcion);
                //story.setImageName(image);
                story.setImages(images);
                story.setVideo(video);
                story.setDate(fecha);

                story.defineViewType();


            } catch (Exception e){
                throw new RuntimeException("PARSE",e);
            }
            stories.add(story);
        }
        return stories;
    }
}
