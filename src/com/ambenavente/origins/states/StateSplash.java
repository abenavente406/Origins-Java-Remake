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

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/19/14
 */
public class StateSplash extends StateBase {

    /**
     * This is the length of time that the game will stay in the splash state
     * (in milliseconds)
     */
    private final static float DURATION = 2500f;

    /**
     * This is the amount of time that the game has spent in this state
     */
    private int elapsed;

    private Image title;

    public StateSplash(StateBasedGame parent) {
        super(parent);
    }

    @Override
    public void init(GameContainer container,
                     StateBasedGame game) throws SlickException {
        elapsed = 0;
        title = new Image("res/images/title.png");
    }

    @Override
    public void render(GameContainer container,
                       StateBasedGame game,
                       Graphics g) throws SlickException {
        super.render(container, game, g);
        // TODO: Draw the logo/studio credits

        Font font = g.getFont();
        String author = getParent().getAuthor();

        int centeredTitleX = container.getWidth() / 2
                - title.getWidth() / 2;
        int centeredTitleY = container.getHeight() / 2
                - title.getHeight() / 2;

        int centeredAuthorX = container.getWidth() / 2
                - font.getWidth(author) / 2;
        int centeredAuthorY = container.getHeight() / 2
                - font.getHeight(author) / 2
                + title.getHeight();

        int combinedHeight = title.getHeight() + font.getHeight(author);

        g.drawImage(title, centeredTitleX, centeredTitleY - combinedHeight / 2);
        //g.drawString(author, centeredAuthorX, centeredAuthorY - combinedHeight / 2);
    }

    @Override
    public void update(GameContainer container,
                       StateBasedGame game,
                       int delta) throws SlickException {
        // ------------------------------------------------------------------
        // Adding the milliseconds since the last frame to duration.
        // This increases elapsed at a constant rate no matter machine speed
        // ------------------------------------------------------------------
        if ((elapsed += delta) >= DURATION) {
            // Move on to the next state
            elapsed = 0;
            getParent().enterState(EnumState.MENU,
                    new FadeOutTransition(),
                    new FadeInTransition());
        }
    }

    @Override
    public int getID() {
        return EnumState.SPLASH.getId();
    }
}
