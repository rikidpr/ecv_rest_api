package an.dpr.ecv.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

@Liveness
@ApplicationScoped
public class VersionHealthCheck implements HealthCheck{
    
    @ConfigProperty(name = "ecv.version")
    String version;

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse
        .named("ECV version:" + version);
        // TODO write live health check logic
        responseBuilder.up();
        return responseBuilder.build(); 
    }

    
}