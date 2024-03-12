package com.beantastic.logging;

import java.io.PrintStream;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Logger {

    private final PrintStream out;

    public Logger(PrintStream out) {
        this.out = out;
    }

    public void write(String text) {
        IntStream.range(0, text.length())
                .mapToObj(text::charAt)
                .forEachOrdered(c -> {
                    this.print(c);
                    try {
                        Thread.sleep(ThreadLocalRandom.current().nextInt(10,  10+1)); // Adjust the delay as needed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
    }

    public void  writeln(String text) {
        this.write(text);
        this.println(); // Move to the next line after printing the text
    }

    public void print(Object object) {
        out.print(object);
    }

    public void printBreak(){
        out.println("-------------------------------------------");
    }

    public void println() {
        out.println();
    }

    public void println(Object object){
        out.println(object);
    }
}
