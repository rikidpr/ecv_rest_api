package an.dpr.ecv.health;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class SimpleReadyHealthCheck implements HealthCheck{

	@Override
	public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Simple readiness Health check");
        // TODO write ready health check logic
        responseBuilder.up();
        return responseBuilder.build(); 
	}

    
}