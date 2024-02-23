package org.example;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "spring-finder", description = "demo cli application to find spring on a system")
public class DemoFinder implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {

        System.out.println("Hello World");

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder
                .inheritIO()
//                .command("bash", "-c", "source ~/.bashrc")
//                .command("bash", "-c", "echo $PATH")
                .command("bash", "-c", "which spring");
        Process process = processBuilder.start();

        StreamGobbler streamGobbler = new StreamGobbler(process.getInputStream(), System.out::println);

        new Thread(streamGobbler).start();

        int exitCode = process.waitFor();

        System.out.printf("process result: %s\n" , exitCode);

        return 0;
    }
}
