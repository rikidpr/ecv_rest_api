package an.dpr.ecv.resources;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import an.dpr.ecv.model.Member;
import an.dpr.ecv.services.MemberService;

@Path("member")
@RequestScoped
public class MemberResource {

	@Inject
	private MemberService service;

	private Logger log = LogManager.getLogger(MemberResource.class);

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId="getMember", summary = "Get user by id", description = "Member getter, you can get users by the id ")
	@APIResponse(responseCode = "400", description = "User not found")
	public Response getMember(
			@Parameter(description = "Member's id to search", required = true) @PathParam("id") String id) {
		Member member = service.getMember(id);
		if (member == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(member).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId="findMembers", summary="find members")
	public List<Member> findMembers(
			@Parameter(name = "name", description = "find by name") @PathParam("name") String name,
			@Parameter(name = "entryDate", description = "find by entry date") @PathParam("entryDate") String entryDate) {
		log.info("find members");
		return service.findMembers();
	}

	@POST
	@Operation(operationId="newMember", summary = "create new member")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newMember(
			@Parameter(name = "member", description = "Member info in json", required = true) Member member) {
		try {
			service.saveMember(member);
			return Response.status(Status.METHOD_NOT_ALLOWED).entity("still not implemented").build();
		} catch (Exception e) {
			log.error(e.getCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Operation(operationId="updateMember", summary = "update member")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMember(@Parameter(name = "id", description = "id to delete", required = true) String id) {
		try {
			Boolean delete = service.deleteMember(id);
			return Response.status(Status.OK).entity(delete).build();
		} catch (Exception e) {
			log.error(e.getCause());
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
