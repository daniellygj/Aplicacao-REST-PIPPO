package pippo.testpippo.fatec;

import java.util.Optional;

import ro.pippo.core.Application;
import ro.pippo.core.Pippo;

/**
 * Hello world!
 *
 */
public class App // port 8338
{
	
	public static Optional<String> port = Optional.ofNullable(System.getenv("PORT"));
	
    public static void main( String[] args )
    {
        Pippo pippo = new Pippo(new BasicApplication());
        pippo.start();

//        pippo.start(Integer.parseInt(port.orElse("8083")));
    }
}
