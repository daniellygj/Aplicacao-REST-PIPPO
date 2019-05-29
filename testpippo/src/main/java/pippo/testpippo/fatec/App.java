package pippo.testpippo.fatec;

import java.io.IOException;

import java.util.Optional;

import ro.pippo.core.Pippo;

/**
 * Hello world!
 *
 */
public class App // port 8338
{
	
	public static Optional<String> port = Optional.ofNullable(System.getenv("PORT"));
	
    public static void main( String[] args ) throws IOException {
        Pippo pippo = new Pippo(new BasicApplication());
        pippo.start();

        pippo.start(Integer.parseInt(port.orElse("8083")));
    }
}
