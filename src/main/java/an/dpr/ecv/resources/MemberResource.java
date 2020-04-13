package an.dpr.ecv.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.ecv.entities.Member;
import an.dpr.ecv.resources.dto.MemberDTO;
import an.dpr.ecv.resources.dto.MemberMapper;
import an.dpr.ecv.services.MemberService;

@Path("/member")
@RequestScoped
public class MemberResource {

	private static final Logger log = LoggerFactory.getLogger(MemberResource.class);
    
    @Inject
	MemberService service;
	
	@Inject
	MemberMapper mapper;
    
	@GET
	@Path("/{id}")
	// @PermitAll
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMember(@PathParam("id") Integer id) {
		log.info("Parameters:" + id);
		MemberDTO memberDto = mapper.map(service.getMember(id));
		if (memberDto == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok(memberDto).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Operation(operationId = "findMembers", summary = "find members")
	public List<MemberDTO> findMembers(
			@Parameter(name = "name", description = "find by name") @PathParam("name") String name,
			@Parameter(name = "entryDate", description = "find by entry date") @PathParam("entryDate") String entryDate) {
        log.info("parameters: " + name + "," + entryDate);
        return mapper.map(service.findMembers());
	}

	@POST
	// @RolesAllowed("JUNTERO")
	@Operation(operationId = "newMember", summary = "create new member")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response newMember(
			@Parameter(name = "member", description = "Member info in json", required = true) MemberDTO dto) {
		log.info("-Parameters: " + dto);
		if (dto.getId() != null) {
			return Response.status(Status.CONFLICT).build();
		}
		try {
			dto.setId(null);// is a new member, id should be null
			service.saveMember(mapper.map(dto));
			return Response.ok().build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@PUT
	@Operation(operationId = "updateMember", summary = "update member")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMember(
			@Parameter(name = "member", description = "Member info in json", required = true) Member member) {
		log.info("Parameters: " + member);
		try {
			if (service.existsMember(member.getId())) {
				service.saveMember(member);
				return Response.ok().build();
			} else {
				return Response.status(Status.NOT_FOUND).entity("Member doesn't exists, you should use POST method")
						.build();
			}
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}

	@DELETE
	@Operation(operationId = "updateMember", summary = "update member")
	public Response deleteMember(@Parameter(name = "id", description = "id to delete", required = true) Integer id) {
		log.info("parameters: " + id);
		try {
			Boolean delete = service.deleteMember(id);
			return Response.status(Status.OK).entity(delete).build();
		} catch (Exception e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
	}
}
