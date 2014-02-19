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

import com.ambenavente.origins.ui.events.MouseEventArgs;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public class LinkLabel extends Label {

    private MouseEventArgs lastMouseEvents;
    private MouseEventArgs currMouseEvents;
    private Vector2f lastMousePos;
    private Vector2f currMousePos;
    private Color highlightColor;

    public LinkLabel(String title, String text) {
        super(title, text);

        setText(text);
        lastMousePos = new Vector2f(0, 0);
        currMousePos = new Vector2f(0, 0);
        highlightColor = Color.blue;
    }

    @Override
    public void update(Input input, int delta) {
        // Do stuff
        currMousePos.set(input.getMouseX(), input.getMouseY());
        currMouseEvents = new MouseEventArgs(input);

        if (!mouseInBounds(lastMousePos) && mouseInBounds(currMousePos)) {
            onMouseEnter(currMouseEvents);
        }

        if (!mouseInBounds(currMousePos) && mouseInBounds(lastMousePos)) {
            onMouseLeave(currMouseEvents);
        }

        if (mouseInBounds(lastMousePos) && mouseInBounds(currMousePos)) {
            onHover(currMouseEvents);
        }

        if (mouseInBounds(currMousePos) && currMouseEvents.isLeftClick() &&
                !lastMouseEvents.isLeftClick()) {
            onClick(currMouseEvents);
        }

        // Set the last pos last
        lastMousePos.set(input.getMouseX(), input.getMouseY());
        lastMouseEvents = currMouseEvents;
    }

    @Override
    public void render(Graphics g) {
        Color tmp = g.getColor();
        if (isHasFocus()) {
            g.setColor(highlightColor);
        } else {
            g.setColor(getForeColor());
        }
        super.render(g);
        g.setColor(tmp);
    }

    @Override
    public void onClick(MouseEventArgs e) {
        if (doer != null) {
            doer.doAction(e);
        } else {
            System.out.println("No action set!");
        }
    }

    public Color getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(Color highlightColor) {
        this.highlightColor = highlightColor;
    }
}
