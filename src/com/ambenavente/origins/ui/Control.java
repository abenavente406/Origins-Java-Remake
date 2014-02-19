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

package com.ambenavente.origins.ui;

import com.ambenavente.origins.gameplay.entities.interfaces.Renderable;
import com.ambenavente.origins.ui.events.MouseEventArgs;
import com.ambenavente.origins.ui.events.MouseEventListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public abstract class Control implements Renderable, MouseEventListener {

    private String name;
    private String text;
    private Vector2f pos;
    private int width;
    private int height;
    private Object value;
    private boolean hasFocus;
    private boolean isEnabled;
    private boolean isVisible;
    private Font font;
    private Color foreColor;
    private Color backColor;

    public Control() {
        this("");
    }

    public  Control(String name) {
        this(name, new Vector2f(0, 0));
    }

    public Control(String name, Vector2f pos) {
        this(name, pos, 0, 0);
    }

    public Control(String name,
                   Vector2f pos,
                   int width,
                   int height) {
        this.name       = name;
        this.pos        = pos;
        this.width      = width;
        this.height     = height;
        this.text       = "";
        this.value      = null;
        this.hasFocus   = false;
        this.isEnabled  = true;
        this.isVisible  = true;
        this.font       = null;
        this.foreColor  = Color.white;
        this.backColor  = Color.black;
    }

    public abstract void update(int delta);

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void onClick(MouseEventArgs e) {
    }

    @Override
    public void onHover(MouseEventArgs e) {
    }

    @Override
    public void onMouseLeave(MouseEventArgs e) {
        if (hasFocus) hasFocus = false;
    }

    @Override
    public void onMouseEnter(MouseEventArgs e) {
        if (!hasFocus) hasFocus = true;
    }

    @Override
    public void onMouseDown(MouseEventArgs e) {
    }
}
