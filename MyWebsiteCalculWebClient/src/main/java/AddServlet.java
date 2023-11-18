package main.java;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;


/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String serviceUrl = "http://localhost:8080/MyWebsiteCalcul/rest/operations";
    
    private void addOperation(String baseURI, Operation operation) {
    	ClientConfig config = new ClientConfig();
		Client client = ClientBuilder.newClient(config);
    	
        WebTarget target = client.target(baseURI);
        

        // Sending a POST request and receiving a Response
        Response response = target
                .request()
                .post(Entity.entity(operation, MediaType.APPLICATION_JSON), Response.class);

        // Check if the response is not null
        if (response != null) {
            // Check if the status code indicates success (2xx range)
            if (response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
                // Get the location URI
                URI location = response.getLocation();

                // Check if the location is not null
                if (location != null) {
                    System.out.println("Operation added successfully. Location: " + location.toString());
                } else {
                    System.out.println("Location is null in the response.");
                }
            } else {
                // Print an error message if the status code indicates an error
                System.out.println("HTTP request failed with status code: " + response.getStatus());
                System.out.println("Response Entity: " + response.readEntity(String.class));
            }

            // Always close the response to release resources
            response.close();
        } else {
            System.out.println("Response is null. Check for errors in the HTTP request.");
        }
    }

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddServlet() {
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
		 int operande1 = Integer.parseInt(request.getParameter("operande1"));
		    int operande2 = Integer.parseInt(request.getParameter("operande2"));
		    String operateur = request.getParameter("operateur");

		    Operation newOperation = new Operation(operande1, operande2, operateur);
		    addOperation(serviceUrl, newOperation);
		    

		    // After adding the new operation, redirect to the doGet method to display the updated list
		    response.sendRedirect(request.getContextPath() + "/OperationServlet");	}

}
