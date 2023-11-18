package main.java;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class OperationServlet
 */
@WebServlet("/OperationServlet")
public class OperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String serviceUrl = "http://localhost:8080/MyWebsiteCalcul/rest/operations";
    
    private List<Operation> getOperations(String baseURI) {
        // Make an HTTP request to the RESTful service to get the list of operations
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURI);
        return target.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Operation>>() {});
    }
    
    private List<Operation> getOperationsByOperator(String baseURI, String operator) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(baseURI).path(operator);
        return target.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Operation>>() {});
    }
    
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OperationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	       // Check if the request contains the operator parameter
        String operator = request.getParameter("operator");

        if (operator != null && !operator.isEmpty()) {
            // If operator parameter is present, get operations by operator
            List<Operation> operations = getOperationsByOperator(serviceUrl, operator);
            request.setAttribute("operations", operations);
        } else {
            // Otherwise, get all operations
            List<Operation> operations = getOperations(serviceUrl);
            request.setAttribute("operations", operations);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/listOperations.jsp");
        dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}



}
