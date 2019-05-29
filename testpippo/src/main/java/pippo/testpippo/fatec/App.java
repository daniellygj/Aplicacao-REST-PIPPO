package pippo.testpippo.fatec;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import ro.pippo.core.Pippo;

/**
 * Hello world!
 *
 */
public class App // port 8338
{                   // inputStream outputStream
                    // reader | writer
                    // classe File
	
	public static Optional<String> port = Optional.ofNullable(System.getenv("PORT"));
	
    public static void main( String[] args ) throws IOException {


//
//        Pippo pippo = new Pippo(new BasicApplication());
//        pippo.start();
//
//        pippo.start(Integer.parseInt(port.orElse("8083")));
    }
}
