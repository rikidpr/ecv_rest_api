package an.dpr.ecv.resources;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;


@Path("/faulteg")
public class FaultToleranceExampleResource {

    
    private static final Logger LOGGER = Logger.getLogger(FaultToleranceExampleResource.class);

    @GET
    @Retry(maxRetries = 4)
    public String getFaultEg() {
        maybeFail("Ojo que puede petar");
        return "Fault Tolerance example";
    }

    private void maybeFail(String failureLogMessage) {
        if (new Random().nextBoolean()) {
            LOGGER.error(failureLogMessage);
            throw new RuntimeException("Resource failure.");
        }
    }

    @GET
    @Path("/{id}/delayed")
    @Fallback(fallbackMethod = "delayedFallback")
    @Timeout(250)
    public String recommendations(@PathParam("id") int id) {
        long started = System.currentTimeMillis();
        final long invocationNumber = 1l;

        try {
            getBreakableLogic();
            randomDelay();
            LOGGER.infof("delayed invocation #%d returning successfully", invocationNumber);
            return "not delayed recomendation for " + id;
        } catch (InterruptedException e) {
            LOGGER.errorf("CoffeeResource#recommendations() invocation #%d timed out after %d ms",
                    invocationNumber, System.currentTimeMillis() - started);
            return "delay time too high, interrupted";
        }
    }

    private String delayedFallback(int id) {
        return "this is the fallback for a delayed recomendation for" + id;
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    } 

    @GET
    @Path("/break")
    @Fallback(fallbackMethod = "breakerFallback")
    public String breaker() {
        getBreakableLogic();
        return "breaker";
    }

    private String breakerFallback() {
        return "breakker fallback";
    }

    @CircuitBreaker(requestVolumeThreshold = 4, failureRatio=0.25)
    //TODO it should be in a service class
    public Integer getBreakableLogic() {
        maybeFail();
        return new Random().nextInt(30);
    }

    private void maybeFail() {
        // introduce some artificial failures
        if (new Random().nextBoolean()) {
            throw new RuntimeException("Service failed.");
        }
    }
}