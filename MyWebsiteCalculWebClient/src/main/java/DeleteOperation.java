package main.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Servlet implementation class DeleteOperation
 */
@WebServlet("/DeleteOperation")
public class DeleteOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    String serviceUrl = "http://localhost:8080/MyWebsiteCalcul/rest/operations";

    private void deleteOperation(String baseURI, int id) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURI).path(String.valueOf(id));
        target.request().delete();
    }
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOperation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String idParam = request.getParameter("id");

		    if (idParam != null && !idParam.isEmpty()) {
		        try {
		            // Convert the ID parameter to an integer
		            int id = Integer.parseInt(idParam);

		            // Call the deleteOperation method to send the DELETE request
		            deleteOperation(serviceUrl, id);
		            request.setAttribute("deleteMessage", "Supprimer avec succès");

		            // Redirect to a success page or handle as needed
		            response.sendRedirect(request.getContextPath() + "/OperationServlet");
		        } catch (NumberFormatException e) {
		            // Handle the case where the ID parameter is not a valid integer
		            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid 'id' parameter");
		        }
		    } else {
		        // Handle the case where the ID parameter is missing or empty
		        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or empty 'id' parameter");
		    }
		}	
	

}
