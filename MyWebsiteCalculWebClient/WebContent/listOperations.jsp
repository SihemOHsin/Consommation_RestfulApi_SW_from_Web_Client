<%@ page import="java.util.List" %>
<%@ page import="main.java.Operation" %>
<%@include file="shared.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Operation liste</title>

    <!-- Custom Styles -->
    <style>
        body {
            background-color: #f8f9fa; /* Light gray background color */
            background-image: url('cool-background.png'); 
            
        }

        .container {
            margin-top: 50px; /* Add some top margin for spacing */
        }

        h1 {
            color: #5eccd1; /* Blue color for the heading */
        }

        form {
            margin-bottom: 20px; /* Add bottom margin to the forms */
        }
        
        .button {
            background-color: #007bff; /* Blue color for the button */
            color: #fff; /* White text for the button */
            padding: 8px 16px; /* Adjust padding for better appearance */
            border: none; /* Remove border */
            cursor: pointer;
        }
        table {
            width: 100%;
            margin-top: 20px; /* Add top margin for spacing */
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            border: 1px solid #dee2e6; /* Light gray border */
        }

        th {
            background-color: #007bff; /* Blue background for table header */
            color: #fff; /* White text for table header */
        }

        p {
            color: #6c757d; /* Gray color for the paragraph */
        }
    </style>
</head>

<body>
    <div class="container text-center">
        <h1>La consultation de tous les objets Operation</h1>
<hr>
<div class="row">
    <div class="col-md-6">
        <form action="OperationServlet" method="GET" class="form-inline">
            <label for="operator">Recherche par operateur:</label>
            <select name="operator" id="operator" class="form-control mr-2">
                <option value="Ajout">Ajout</option>
                <option value="Soustraction">Soustraction</option>
                <option value="Multiplication">Multiplication</option>
                <option value="Division">Division</option>
            </select>
            <input type="submit" class="btn btn-primary" value="Rechercher" />
        </form>
    </div>
    <div class="col-md-6">
        <form action="add.jsp" method="GET">
            <input type="submit" class="btn btn-info" value="Ajouter">
        </form>
    </div>
</div>


        <% List<Operation> operations = (List<Operation>) request.getAttribute("operations");
        if (operations != null && !operations.isEmpty()) { %>
    <table id="example" class="table table-striped" style="width:100%">      
        <thead>
                <tr>
                    <th>Operand 1</th>
                    <th>Operand 2</th>
                    <th>Operator</th>
                    <th>Resultat</th>
                    <th>Action</th>
                </tr>
                        </thead>
        <tbody>
                <% for (Operation operation : operations) { %>
                    <tr>
                        <td><%= operation.getOperande1() %></td>
                        <td><%= operation.getOperande2() %></td>
                        <td><%= operation.getOperateur() %></td>
                        <td><%= operation.getResult() %></td>
                        <td>
                            <form action="DeleteOperation" method="POST">
                                <input type="hidden" name="id" value="<%= operation.getId() %>">
                                <input type="submit" class="button" value="Delete">
                            </form>
                            
    <% String deleteMessage = (String)request.getAttribute("deleteMessage");
       if (deleteMessage != null && !deleteMessage.isEmpty()) { %>
           <p><%= deleteMessage %></p>
    <% } %>
    
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
        <% } else { %>
            <p>No operations available</p>
        <% } %>

        <!-- Add any additional content or forms as needed -->
    </div>
    <script>
$('#example').DataTable({
    "paging": true, 
    "pageLength": 2, 
    "searching": true, 
    "ordering": true, 
    
});
</script>
</body>
</html>
