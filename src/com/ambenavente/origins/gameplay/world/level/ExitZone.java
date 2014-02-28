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

import org.newdawn.slick.geom.Rectangle;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/21/14
 */
public class ExitZone {

    private transient TiledMap parent;

    private boolean     inUse;
    private int         x;
    private int         y;
    private int         tilesWide;
    private int         tilesTall;
    private int         targetId;
    private Rectangle   bounds;

    public ExitZone(TiledMap parent,
                    int targetId,
                    int x,
                    int y,
                    int tilesWide,
                    int tilesTall) {
        this.parent     = parent;
        this.targetId   = targetId;
        this.x          = x;
        this.y          = y;
        this.tilesWide  = tilesWide;
        this.tilesTall  = tilesTall;
        this.bounds     = new Rectangle(x * parent.getTileWidth(),
                                        y * parent.getTileHeight(),
                                        tilesWide * parent.getTileHeight(),
                                        tilesTall * parent.getTileHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getTargetId() {
        return targetId;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }
}
