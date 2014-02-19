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
import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public class Label extends Control {

    private Vector2f lastMousePos;
    private Vector2f currMousePos;

    public Label(String title, String text) {
        super(title);

        setText(text);
        lastMousePos = new Vector2f(0, 0);
        currMousePos = new Vector2f(0, 0);
    }

    @Override
    public void update(Input input, int delta) {
        // Do stuff
        currMousePos.set(input.getMouseX(), input.getMouseY());

        // Set the last pos last
        lastMousePos.set(input.getMouseX(), input.getMouseY());
    }

    @Override
    public void render(Graphics g) {
        Font tmpFont = g.getFont();
        setWidth(tmpFont.getWidth(getText()));
        setHeight(tmpFont.getHeight(getText()));
        if (getFont() != null) {
            g.setFont(getFont());
            setWidth(getFont().getWidth(getText()));
            setHeight(getFont().getHeight(getText()));
        }
        g.drawString(getText(), getX(), getY());
        g.setFont(tmpFont);
    }
}
