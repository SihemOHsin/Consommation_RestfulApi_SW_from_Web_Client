<%@ page import="java.util.List" %>
<%@ page import="main.java.Operation" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="shared.jsp" %>

<html>
<head>
    <title>Operations List</title>

    <!-- Custom Styles -->
    <style>
        body {
            background-color: #f8f9fa; /* Light gray background color */
            font-family: Arial, sans-serif; /* Use a common sans-serif font */
        }

        h1 {
            color: #007bff; /* Blue color for the heading */
            text-align: center;
            margin-top: 20px; /* Add top margin for spacing */
        }

        form {
            max-width: 400px; /* Set maximum width for the form */
            margin: 20px auto; /* Center the form horizontally */
            padding: 20px;
            background-color: #ffffff; /* White background for the form */
            border-radius: 10px; /* Add rounded corners */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add a subtle shadow */
        }

        form input {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            box-sizing: border-box;
        }

        form input[type="submit"] {
            background-color: #007bff; /* Blue color for the submit button */
            color: #fff; /* White text for the submit button */
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
            text-align: center;
            margin-top: 20px; /* Add top margin for spacing */
        }
    </style>
</head>

<body>
    <h1>Add a New Operation</h1>

    <!-- Add Operation Form -->
    <form action="AddServlet" method="POST">
        Operand 1: <input type="number" name="operande1" required /><br/>
        Operand 2: <input type="number" name="operande2" required /><br/>
        Operator: <input type="text" name="operateur" required /><br/>
        <input type="submit" value="Add Operation" />
    </form>

    <% List<Operation> operations = (List<Operation>) request.getAttribute("operations");
    if (operations != null && !operations.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>Operand 1</th>
                <th>Operand 2</th>
                <th>Operator</th>
                <th>Resultat</th>
            </tr>
            <% for (Operation operation : operations) { %>
                <tr>
                    <td><%= operation.getOperande1() %></td>
                    <td><%= operation.getOperande2() %></td>
                    <td><%= operation.getOperateur() %></td>
                    <td><%= operation.getResult() %></td>
                </tr>
            <% } %>
        </table>
    <% } else { %>
        <p>No operations available</p>
    <% } %>

    <!-- Add any additional content or forms as needed -->
</body>
</html>
