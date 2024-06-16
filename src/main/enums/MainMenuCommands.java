package main.enums;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ;
    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String command) {
        return Pattern.compile(this.pattern).matcher(command);
    }
}
