package an.dpr.ecv.resources;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.ecv.model.Activity;
import an.dpr.ecv.services.ActivityService;

@Path("activity")
@RequestScoped
public class ActivityResource {

	@Inject
	private ActivityService service;
	private static final Logger log = LoggerFactory.getLogger(MemberResource.class);

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(operationId = "newActivity")
	public Response newActivity(Activity activity) {
		activity.id = null;
		log.debug("parameters:" + activity);
		service.persist(activity);
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "findActivity")
	public Response getActivity(@Parameter(name = "id") @PathParam("id") Long id) {
		Activity activity = service.findById(id);
		if (activity != null) 
			return Response.ok().entity(activity).build();
		else
			return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "listAll")
	public Response listAll() {
		List<Activity> activities = service.listAll();
		return Response.ok().entity(activities).build();
	}

	@GET
	@Path("/query")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "find")
	public Response find(@Parameter(name = "location") @QueryParam("location") String location) {
		List<Activity> list = service.findByLocation(location);
		return Response.ok().entity(list).build();
	}
}
