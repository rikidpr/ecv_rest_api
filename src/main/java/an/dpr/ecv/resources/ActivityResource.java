package an.dpr.ecv.resources;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Metric;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.ecv.entities.Activity;
import an.dpr.ecv.resources.dto.ActivityDTO;
import an.dpr.ecv.services.ActivityService;

@Path("activity")
@RequestScoped
public class ActivityResource {

	private static final Logger log = LoggerFactory.getLogger(MemberResource.class);
	@Inject
	ActivityService service;

	@Metric
	Counter counterExample;

	@GET
	// @Produces(MediaType.APPLICATION_JSON)
	@Counted(name = "activity/next", description = "How many times called")
	@Path("/next")
	@Timed(name = "next activity",
		description = "next activity timed",
		unit = MetricUnits.MICROSECONDS,
		absolute = true)
	@Operation(operationId = "activity/next", description = "next activity in calendar")
	public Response nextActivity() {
		counterExample.inc();
		return Response.ok().entity("Next Activity: TODO/not yet implemented").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(operationId = "newActivity")
	public Response newActivity(ActivityDTO activityDTO) {
		log.debug("parameters:" + activityDTO);
		Optional<Activity> activity = service.findById(activityDTO.getId());
		if (activity.isPresent())  { 
			return Response.status(Status.CONFLICT).build();
		} else {
			service.save(service.convertToActivity(activityDTO));
			return Response.ok().build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(operationId = "updateActivity")
	public Response updateActivity(ActivityDTO activityParam) {
		log.debug("parameters:" + activityParam);
		if (activityParam.getId() != null)  {
			service.save(service.convertToActivity(activityParam));
			return Response.ok().build();
		 } else 
			return Response.status(Status.NOT_FOUND).build();
	}

	@DELETE
	@Operation(operationId = "deleteActivity")
	@Path("/{id}")
	public Response deleteActivity(@Parameter(name = "id") @PathParam("id") Long id) {
		log.debug("parameters:" + id);
		Optional<Activity> activity = service.findById(id);
		if (activity.isPresent())  { 
			if (service.delete(id)) return Response.ok().build();
			else return Response.status(Status.NOT_MODIFIED).build();
		} else 
		    return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "findActivity")
	public Response getActivity(@Parameter(name = "id") @PathParam("id") Long id) {
		Optional<Activity> activity = service.findById(id);
		if (activity.isPresent())  
			return Response.ok().entity(service.getActivityDTO(activity)).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "listAll")
	public Response listAll() {
		List<ActivityDTO> activities = service.listAll();
		return Response.ok().entity(activities).build();
	}

	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "find")
	public Response find(@Parameter(name = "location") @QueryParam("location") String location) {
		List<ActivityDTO> list = service.findByLocation(location);
		return Response.ok().entity(list).build();
	}
}
