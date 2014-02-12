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

package com.ambenavente.origins.gameplay.world.level.generator;

import org.newdawn.slick.geom.Rectangle;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/11/14
 */
public class Room {
    private int x;
    private int y;
    private int width;
    private int height;
    private Rectangle bounds;
    private boolean hasConnection;

    public Room() {
        this(0, 0, 0, 0);
    }

    public Room(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(x, y, width, height);
        this.hasConnection = false;
        updateBounds();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
        updateBounds();
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
        updateBounds();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        updateBounds();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        updateBounds();
    }

    private void updateBounds() {
        bounds.setX(x);
        bounds.setY(y);
        bounds.setWidth(width);
        bounds.setHeight(height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean getConnected() {
        return hasConnection;
    }

    public void setHasConnection(boolean hasConnection) {
        this.hasConnection = hasConnection;
    }
}
