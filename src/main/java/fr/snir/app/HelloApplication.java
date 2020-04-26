package fr.snir.app;

import fr.snir.rest.RestService;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
public class HelloApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public HelloApplication() {
        // Register our temp service
        singletons.add(new RestService());
    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}