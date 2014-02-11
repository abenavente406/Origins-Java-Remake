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

package com.ambenavente.origins.gameplay.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/10/14
 */
public abstract class StaticEntity extends Entity {

    private Image[] avatars;

    public StaticEntity(float x, float y) {
        super(x, y);

        this.avatars = new Image[Direction.values().length];
    }


    @Override
    public void render(Graphics g) {
        Image image = avatars[getDirection().ordinal()];
        if (image != null) {
            image.draw(getX(), getY());
        } else {
            Entity.NO_TEX.draw(getX(), getY());
        }
    }

    /* ------------------------ */
    /* Methods to be overloaded */
    /* ------------------------ */
    @Override
    public abstract void update(GameContainer container, int delta);
    /* ------------------------ */
}