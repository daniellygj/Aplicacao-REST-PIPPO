package pippo.testpippo.fatec;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Optional;

import ro.pippo.core.Application;
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
    	//Service.executePython();

    	
       /*Chamada da classe das rotas para inicialização da aplicação*/	
       Pippo pippo = new Pippo(new EventApplication());
       pippo.start(Integer.parseInt(port.orElse("8080")));

       
    }
}
