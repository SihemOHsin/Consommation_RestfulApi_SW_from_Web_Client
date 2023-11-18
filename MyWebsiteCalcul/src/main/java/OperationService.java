package main.java;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("/operations")
public class OperationService {
    private OperationDAO dao = OperationDAO.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Operation> list() {
        return dao.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Operation operation) throws URISyntaxException {
        int newOperationId = dao.add(operation);
        URI uri = new URI("/MyWebsiteCalcule/rest/Operations/" + newOperationId);
        return Response.created(uri).build();
    }

    @GET
    @Path("{operateur}")
    public Response getOperationsByOperator(@PathParam("operateur") String operateur) {
        List<Operation> operations = dao.getOperationsByOperator(operateur);

        if (!operations.isEmpty()) {
            return Response.ok(operations, MediaType.APPLICATION_JSON).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        if (dao.delete(id)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
