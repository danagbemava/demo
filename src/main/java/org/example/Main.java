package org.example;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        final var exitCode = new CommandLine(new DemoFinder()).execute();

        System.exit(exitCode);
    }
}