package com.github.davidmoten.guavamini;

public final class Preconditions {

    private Preconditions() {
        // prevent instantiation
    }

    public static <T> T checkNotNull(T t) {
        return checkNotNull(t, null);
    }

    public static <T> T checkNotNull(T t, String message) {
        if (t == null)
            throw new NullPointerException(message);
        return t;
    }
    
    public static <T> T checkArgumentNotNull(T t) {
        return checkArgumentNotNull(t, "argument");
    }
    
    public static <T> T checkArgumentNotNull(T t, String parameterName) {
        if (t == null) {
            throw new IllegalArgumentException(parameterName + " cannot be null");
        } 
        return t;
    }

    public static void checkArgument(boolean b, String message) {
        if (!b)
            throw new IllegalArgumentException(message);
    }

    public static void checkArgument(boolean b) {
        if (!b)
            throw new IllegalArgumentException();
    }

}
