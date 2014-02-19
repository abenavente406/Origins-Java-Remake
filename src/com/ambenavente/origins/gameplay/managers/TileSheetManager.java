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

package com.ambenavente.origins.gameplay.managers;

import com.ambenavente.origins.gameplay.world.level.TileSheet;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.*;
import java.security.InvalidKeyException;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/12/14
 */
public class TileSheetManager {

    private Hashtable<Integer, TileSheet> tileSheets;
    private String directory;

    public TileSheetManager() {
        this("res/tilesheets");
    }

    public TileSheetManager(String path) {
        this.directory = path;
        this.tileSheets = new Hashtable<Integer, TileSheet>();
        initTileSheets();
    }

    private void initTileSheets() {
        File file = new File(directory);

        for (File f : file.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        })) {
            if (!addTileSheet(f)) {
                System.out.println("Error: failed to add tile sheet.");
            }
        }
    }

    private boolean addTileSheet(File f) {
        String fileName = f.getName();

        boolean success = true;

        int id = -999;
        int tileWidth = 0;
        int tileHeight = 0;
        String imagePath = null;

        FileInputStream stream = null;
        BufferedReader reader = null;

        try {
            stream = new FileInputStream(f);
            reader = new BufferedReader(
                    new InputStreamReader(stream)
            );

            id = Integer.parseInt(reader.readLine().substring(0, 1));
            tileWidth = Integer.parseInt(reader.readLine());
            tileHeight = Integer.parseInt(reader.readLine());
            imagePath = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        } finally {
            if (stream != null) try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (id != -999 && imagePath != null) {
            if (!tileSheets.containsKey(id)) {
                tileSheets.put(id, new TileSheet(id, imagePath,
                        tileWidth, tileHeight));
            } else {
                throw new KeyAlreadyExistsException();
            }
        } else {
            success = false;
        }

        return success;
    }

    public TileSheet get(int id) throws InvalidKeyException {
        if (tileSheets.containsKey(id)) {
            return tileSheets.get(id);
        } else {
            throw new InvalidKeyException();
        }
    }

    public void initTileSheetImages() {
        for (TileSheet t : tileSheets.values()) {
            t.loadImage();
        }
    }
}
