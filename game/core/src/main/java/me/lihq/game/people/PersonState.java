package me.lihq.game.people;

/**
 * The state of the person explains what they are currently doing.
 * <li>{@link #WALKING}</li>
 * <li>{@link #STANDING}</li>
 */

public enum PersonState {

        /**
         * Person is walking.
         */
        WALKING,

        /**
         * Person is standing still.
         */
        STANDING,

        /**
         * Person is walking in fixed position
         */
        FIXED_WALKING

}
