package com.pippo.fatec.projetofinal;

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
	
    public static void main( String[] args )
    {
    	
    	/*Chamada da classe das rotas para inicialização da aplicação*/	
        Pippo pippo = new Pippo(new EventApplication());
        pippo.start(Integer.parseInt(port.orElse("8083")));
     	
    }
}
