package pippo.testpippo.fatec;

import pippo.testpippo.fatec.App;
import pippo.testpippo.fatec.Contact;
import ro.pippo.core.Application;

import java.io.File;

import static ro.pippo.core.route.Route.GET;

public class EventApplication extends Application {

    protected void onInit() {
        // send 'Hello World' as response
        GET("/", routeContext -> routeContext.send("Hello World"));

        // send a file as response
        GET("/file", routeContext -> routeContext.send(new File("pom.xml")));

        // send a json as response
        GET("/json", routeContext -> {
            Contact contact = createContact();
            routeContext.json().send(contact);
        });

        // send xml as response
        GET("/xml", routeContext -> {
            Contact contact = createContact();
            routeContext.xml().send(contact);
        });

        // send an object and negotiate the Response content-type, default to XML
        GET("/negotiate", routeContext -> {
            Contact contact = createContact();
            routeContext.xml().negotiateContentType().send(contact);
        });

        // send a template as response
        GET("/template", routeContext -> {
            routeContext.setLocal("greeting", "Hello");
            routeContext.render("hello");
        });
    }

    private Contact createContact() {
        return new Contact(12345, "John", "0733434435", "Sunflower Street, No. 6");
    }

}