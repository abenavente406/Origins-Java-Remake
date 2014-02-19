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

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import java.io.*;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/16/14
 */
public class SpriteSheetManager {

    public static final int PLAYER_SHEET_ID = 0;
    public static final int ENTITY_SHEET_ID = 1;

    private static final String PATH = "res/spritesheets";
    private static Hashtable<Integer, SpriteSheet> spriteSheets;

    public static void init() {
        spriteSheets = new Hashtable<Integer, SpriteSheet>();

        File file = new File(PATH);

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

    private static boolean addTileSheet(File f) {
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
            try {
                closeAll(stream, reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (id != -999 && imagePath != null) {
            try {
                spriteSheets.put(id, new SpriteSheet(imagePath, tileWidth,
                        tileHeight));
            } catch (SlickException e) {
                e.printStackTrace();
            }
        } else {
            success = false;
        }

        return success;
    }

    private static void closeAll(Closeable... args) throws IOException {
        for (Closeable c : args) {
            c.close();
        }
    }

    public static SpriteSheet get(int id) {
        return spriteSheets.containsKey(id) ? spriteSheets.get(id) : null;
    }
}
