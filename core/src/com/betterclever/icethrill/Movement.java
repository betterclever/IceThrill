package com.betterclever.icethrill;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by betterclever on 21/11/16.
 */

public interface Movement {

    void updateVelocity(float delta);
    void updatePosition(float delta);
    void updateAngle(float delta);
}
