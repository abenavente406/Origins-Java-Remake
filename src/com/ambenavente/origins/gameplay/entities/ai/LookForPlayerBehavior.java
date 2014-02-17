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

package com.ambenavente.origins.gameplay.entities.ai;

import com.ambenavente.origins.gameplay.entities.Entity;
import com.ambenavente.origins.gameplay.entities.interfaces.Behavior;
import com.ambenavente.origins.gameplay.entities.player.Player;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Anthony Benavente
 * @version 2/17/14
 */
public class LookForPlayerBehavior implements Behavior {

    private int lookingRange;
    private Entity owner;

    public LookForPlayerBehavior(Entity owner,
                                 int lookingRange) {
        this.owner = owner;
        this.lookingRange = lookingRange;
    }

    @Override
    public void behave() {
        // TODO: Be on the lookout for the player
    }

    public int getLookingRange() {
        return lookingRange;
    }

    public void setLookingRange(int lookingRange) {
        this.lookingRange = lookingRange;
    }
}
