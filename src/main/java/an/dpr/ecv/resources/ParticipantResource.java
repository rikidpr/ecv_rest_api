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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.ecv.resources.dto.ParticipantDTO;
import an.dpr.ecv.resources.dto.ParticipantMapper;
import an.dpr.ecv.services.ParticipantService;
import an.dpr.exception.EcvException;

@Path("/participant")
@RequestScoped
public class ParticipantResource {
	
	private static Logger log = LoggerFactory.getLogger(ParticipantResource.class);
	@Inject ParticipantService service;
	@Inject ParticipantMapper mapper;
	
	@GET
	@Path("/activity/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId="getActivityParticipants")
	public Response getEventParticipants(@Parameter(name = "activityId")@PathParam("activityId") Long activityId) {
		log.debug("parameters: "+activityId);
		return Response.status(Status.BAD_GATEWAY).entity("not implemented yet").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "listAllParticipants")
	public List<ParticipantDTO> listAllParticipants() {
		log.debug("called");
		return mapper.map(service.listAll());
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Operation(operationId="addParticipant")
	public Response addParticipant(ParticipantDTO participant) {
		log.debug("called with "+participant);
		try {
			service.create(participant);
			return Response.ok().build();
		} catch (EcvException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
}
