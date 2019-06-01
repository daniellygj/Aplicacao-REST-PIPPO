package pippo.testpippo.fatec;

import ro.pippo.core.Application;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;


public class Service extends Application {

    public static void executePython() throws IOException {
        String python_path = System.getenv("PYTHON_PATH");
        System.out.println(python_path);

        Process p = Runtime.getRuntime().exec(python_path + " teste.py");

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
                InputStreamReader(p.getErrorStream()));

        // read the output from the command
        System.out.println("Here is the standard output of the command:\n");
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }

        // read any errors from the attempted command
        System.out.println("Here is the standard error of the command (if any):\n");
        while ((s = stdError.readLine()) != null) {
            System.out.println(s);
        }
    }
}
