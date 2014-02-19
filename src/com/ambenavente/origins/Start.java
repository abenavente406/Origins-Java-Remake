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

package com.ambenavente.origins;

import com.ambenavente.origins.main.OriginsGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/9/14
 */
public class Start {

    public static void main(String[] args) throws SlickException {

        OriginsGame originsGame = new OriginsGame("Origins",
                                                  "Anthony Benavente");

        if (args[0].equals("debug")) {
            originsGame.setDebug(true);
        }

        AppGameContainer container = new AppGameContainer(originsGame);
        container.setDisplayMode(640, 480, false);
        container.setTargetFrameRate(60);
        container.start();
    }

}
