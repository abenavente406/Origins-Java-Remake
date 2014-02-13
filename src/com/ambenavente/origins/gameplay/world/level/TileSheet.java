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

package com.ambenavente.origins.gameplay.world.level;

import com.google.gson.annotations.Expose;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

/**
 * A TileSheet is used for getting images for tiles that are stored in one
 * file.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public class TileSheet {

    /**
     * The image that contains all the images for the tiles in this
     * tile sheet
     */
    private transient Image sourceImage;

    private transient SpriteSheet spriteSheet;

    /**
     * The width of an individual tile in the tile sheet
     */
    private int tileWidth;

    /**
     * The height of an individual tile in the tile sheet
     */
    private int tileHeight;

    /**
     * The width of the tile sheet in tiles
     */
    private int width;

    /**
     * The height of the tile sheet in tiles
     */
    private int height;

    /**
     * The width of the tile sheet in pixels
     */
    private int realWidth;

    /**
     * The height of the tile sheet in pixels
     */
    private int realHeight;

    /**
     * The rectangles in the tile sheet.  Each rectangle represents the
     * location of a tile
     */
    private Rectangle[] sourceRects;

    /**
     * The file location of this tile sheet's image
     */
    private String path;

    /**
     * Creates a TileSheet
     *
     * @param id         The id of the tile sheet
     * @param path       The file location for this TileSheet's image
     * @param tileHeight The height of an individual tile in the sheet
     * @param tileWidth  The width of an individual tile in the sheet
     */
    public TileSheet(int id, String path, int tileHeight, int tileWidth) {
        this.tileHeight = tileHeight;
        this.tileWidth  = tileWidth;
        this.path       = path;
        init();
    }

    /**
     * Initializes the tile sheet
     */
    private void init() {

        loadImage();

        this.realWidth = sourceImage.getWidth();
        this.realHeight = sourceImage.getHeight();
        this.width = realWidth / tileWidth;
        this.height = realHeight / tileHeight;

        initTiles();
    }

    /**
     * Loads the tile sheet's image from the path passed into the
     * constructor
     *
     * @return If the load operation succeeded, the function will return
     * true
     */
    public boolean loadImage() {
        boolean success = true;
        if (path != null) {
            try {
                sourceImage = new Image(path);
                spriteSheet = new SpriteSheet(sourceImage,
                                              tileWidth,
                                              tileHeight);
                success = true;
            } catch (SlickException e) {
                e.printStackTrace();
                success = false;
            }
        }
        return success;
    }

    /**
     * Initializes the source rectangles in the tile sheet
     */
    private void initTiles() {
        int totalTiles = width * height;
        sourceRects = new Rectangle[totalTiles];

        int tile = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                sourceRects[tile++] = new Rectangle(x * tileWidth,
                                                    y * tileHeight,
                                                    tileWidth,
                                                    tileHeight);
            }
        }
    }

    /**
     * @return The width of an individual tile in the tile sheet
     */
    public int getTileWidth() {
        return tileWidth;
    }

    /**
     * @return The height of an individual tile in the tile sheet
     */
    public int getTileHeight() {
        return tileHeight;
    }

    /**
     * @return The number of tiles wide this tile sheet is
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return The number of tiles tall this tile sheet is
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return The width of this tile sheet in pixels
     */
    public int getRealWidth() {
        return realWidth;
    }

    /**
     * @return The height of this tile sheet in pixels
     */
    public int getRealHeight() {
        return realHeight;
    }

    /**
     * Gets a specific rectangle by the index in the tile sheet
     *
     * @param id The index of the rectangle to get.  If this id is out of
     *           range, the function throws in IndexOutOfBoundsException
     * @return The Rectangle containing the image for the id passed in
     * @throws java.lang.IndexOutOfBoundsException
     */
    public Rectangle getRect(int id) {
        if (id >= 0 && id < sourceRects.length){
            return sourceRects[id];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Image getImage(int id) {
        if (id >= 0 && id < sourceRects.length) {
            Rectangle rect = sourceRects[id];

            int tx = (int)rect.getX() / tileWidth;
            int ty = (int)rect.getY() / tileHeight;

            return spriteSheet.getSprite(tx, ty);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * @return The file location for this TileSheet's image
     */
    public String getPath() {
        return path;
    }

    /**
     * @return The image for which this tile sheet gets its tiles
     */
    public Image getImage() {
        return sourceImage;
    }
}
