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

package com.ambenavente.origins.states;

import com.ambenavente.origins.main.OriginsGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public abstract class StateBase extends BasicGameState {

    private OriginsGame parent;

    public StateBase(StateBasedGame parent) {
        if (parent instanceof OriginsGame) {
            this.parent = (OriginsGame) parent;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public abstract void init(GameContainer container,
                              StateBasedGame game) throws SlickException;

    @Override
    public abstract void update(GameContainer container,
                                StateBasedGame game,
                                int delta) throws SlickException;

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (getParent().getDebug()) {
            g.drawString(EnumState.values()[getID()].toString(),
                    0,
                    container.getHeight() - g.getFont().getLineHeight());
        }
    }

    protected OriginsGame getParent() {
        return parent;
    }
}
