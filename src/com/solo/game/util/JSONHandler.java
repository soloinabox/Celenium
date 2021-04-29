package com.solo.game.util;

import com.solo.game.util.exceptions.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;

public class JSONHandler {

    public static JSONObject parseJson(String file) throws JSONException {

        String properties = "";

        // Get the properties from disk
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            while(br.ready()) {
                properties = properties.concat(br.readLine());
            }

            br.close();

        } catch (Exception e) {

            e.printStackTrace();
            throw new JSONException();

        }

        JSONParser parser = new JSONParser();

        try {

            Object object = parser.parse(properties);

            return (JSONObject)object;


        } catch (ParseException e) {

            e.printStackTrace();
            throw new JSONException();

        }

    }

}
