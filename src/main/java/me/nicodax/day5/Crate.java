package me.nicodax.day5;

public record Crate(Character label) {

    public static Crate from(String crate) {
        return new Crate(crate.charAt(1));
    }
}
