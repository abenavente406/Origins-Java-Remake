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
import com.ambenavente.origins.ui.events.BasicEventArgs;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public class ControlManager implements Renderable {

    private List<Control> controls;

    public ControlManager() {
        controls = new ArrayList<Control>();
    }

    public ControlManager(int maxSize) {
        controls = new ArrayList<Control>(maxSize);
    }

    @Override
    public void render(Graphics g) {
        for (int i = controls.size() - 1; i >= 0; i--) {
            controls.get(i).render(g);
        }
    }

    public void update(Input input, int delta) {
        for (int i = controls.size() - 1; i >= 0; i--) {
            controls.get(i).update(input, delta);
        }
    }

    public void add(Control c) {
        controls.add(c);
    }

    public void remove(Control c) {
        if (controls.contains(c)) {
            remove(controls.indexOf(c));
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < controls.size()) {
            controls.remove(index);
        }
    }
}
