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

import com.ambenavente.origins.ui.Label;
import com.ambenavente.origins.ui.LinkLabel;
import com.ambenavente.origins.ui.events.ActionArgs;
import com.ambenavente.origins.ui.events.ActionDoer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class StateMainMenu extends StateBase {

    private Animation background;
    private Image title;
    private Label label;
    private LinkLabel linkLabel;

    public StateMainMenu(StateBasedGame parent) {
        super(parent);
    }

    @Override
    public void init(GameContainer container,
                     StateBasedGame game) throws SlickException {
        initBackground();
        initTitle();
        label = new Label("lblTest", "Hello");
        label.setX(200);
        label.setY(100);
        linkLabel = new LinkLabel("lblTest2", "New Game");
        linkLabel.setActionDoer(new ActionDoer() {
            @Override
            public void doAction(ActionArgs e) {
                getParent().enterState(EnumState.GAMEPLAY,
                        new FadeOutTransition(), new FadeInTransition());
            }
        });
    }

    private void initTitle() throws SlickException {
        title = new Image("res/images/title.png");
    }

    private void initBackground() throws SlickException {
        Image[] images = new Image[8];
        for (int i = 0; i < images.length; i++) {
            images[i] = new Image("res/images/waterfall/water" + i + ".png");
        }
        background = new Animation(images, 100);
    }

    @Override
    public void render(GameContainer container,
                       StateBasedGame game,
                       Graphics g) throws SlickException {
        background.draw(0, 0);
        drawTitle(container, g);
        label.render(g);
        linkLabel.render(g);
        super.render(container, game, g);
    }

    private void drawTitle(GameContainer container,
                           Graphics g) {
        int centeredX = container.getWidth() / 2 - title.getWidth() / 2;
        g.drawImage(title, centeredX, 50);
    }

    @Override
    public void update(GameContainer container,
                       StateBasedGame game,
                       int delta) throws SlickException {
        label.update(container.getInput(), delta);
        linkLabel.update(container.getInput(), delta);
    }

    @Override
    public int getID() {
        return EnumState.MENU.getId();
    }

}
