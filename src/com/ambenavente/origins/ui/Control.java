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
import com.ambenavente.origins.ui.events.ActionArgs;
import com.ambenavente.origins.ui.events.ActionDoer;
import com.ambenavente.origins.ui.events.MouseEventArgs;
import com.ambenavente.origins.ui.events.MouseEventListener;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
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
    private Rectangle bounds;
    protected ActionDoer doer;

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
        this.bounds     = new Rectangle(pos.x, pos.y, width, height);
    }

    public abstract void update(Input input, int delta);

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Vector2f getPos() {
        return pos;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
        updateBounds();
    }

    public float getX() {
        return pos.x;
    }

    public void setX(float x) {
        pos.x = x;
        updateBounds();
    }

    public float getY() {
        return pos.y;
    }

    public void setY(float y) {
        pos.y = y;
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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isHasFocus() {
        return hasFocus;
    }

    public void setHasFocus(boolean hasFocus) {
        this.hasFocus = hasFocus;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getForeColor() {
        return foreColor;
    }

    public void setForeColor(Color foreColor) {
        this.foreColor = foreColor;
    }

    public Color getBackColor() {
        return backColor;
    }

    public void setBackColor(Color backColor) {
        this.backColor = backColor;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void updateBounds() {
        bounds.setBounds(pos.x, pos.y, width, height);
    }

    public void setActionDoer(ActionDoer doer) {
        this.doer = doer;
    }

    protected boolean mouseInBounds(Vector2f mousePos) {
        return bounds.contains(mousePos.x, mousePos.y);
    }
}
