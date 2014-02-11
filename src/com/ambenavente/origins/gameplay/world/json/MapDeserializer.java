/*
 * Copyright (c) 2014 Anthony Benavente
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ambenavente.origins.gameplay.world.json;

import com.ambenavente.origins.gameplay.world.level.LevelMap;
import com.google.gson.Gson;

import java.io.*;

/**
 * Reads a json file and spits out a LevelMap object.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class MapDeserializer {

    /**
     * The Gson object used for reading json
     */
    private Gson gson;

    /**
     * Creates a MapDeserializer object
     */
    public MapDeserializer() {
        gson = new Gson();
    }

    /**
     * Reads a json file and creates a LevelMap object from it
     *
     * @param path The path to the json file
     * @return The level map that is created from the json file that was
     * just read
     */
    public LevelMap readFromJsonFile(String path) {
        FileInputStream stream;
        InputStreamReader inputStreamReader;
        BufferedReader reader;
        String line = "";
        String json = "";

        try {
            stream = new FileInputStream(path);
            inputStreamReader = new InputStreamReader(stream);
            reader = new BufferedReader(inputStreamReader);

            while ((line = reader.readLine()) != null) {
                json += line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return readFromJson(json);
    }

    /**
     * Creates a LevelMap object from json that is passed in
     *
     * @param json The json to convert to a LevelMap object
     * @return The LevelMap object converted from json
     */
    public LevelMap readFromJson(String json) {
        LevelMap map = gson.fromJson(json, LevelMap.class);
        return map;
    }
}
