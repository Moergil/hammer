package eu.inloop.hammer;

import android.os.Bundle;

import java.util.NoSuchElementException;

public class Strict {
    public static void forceExtra(Bundle bundle, String extra) {
        if (!bundle.containsKey(extra)) {
            throw new NoSuchElementException("Missing extra: " + extra);
        }
    }
}
