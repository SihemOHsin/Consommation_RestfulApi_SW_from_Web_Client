package main.java;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO {
    private static OperationDAO instance;
	private DaoInterface dao = null;	


    private OperationDAO() {
		 dao = new DaoImpl();

    }

    public static OperationDAO getInstance() {
        if (instance == null) {
            instance = new OperationDAO();
        }
        return instance;
    }

    // Retrieve all operations from the database
    public List<Operation> listAll() {
        List<Operation> operations = new ArrayList<>();

        try {

            String query = "SELECT * FROM operation";

            try (PreparedStatement ps = dao.connection.prepareStatement(query);
                 ResultSet rs = dao.lire(ps)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int operande1 = rs.getInt("operande1");
                    int operande2 = rs.getInt("operande2");
                    String operateur = rs.getString("operateur");

                    Operation operation = new Operation(id, operande1, operande2, operateur);
                    operations.add(operation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return operations;
    }

    // Add operation to the database
    public int add(Operation operation) {
        try {

        	String query = "INSERT INTO operation (operande1, operande2, operateur, result) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps = dao.connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, operation.getOperande1());
                ps.setInt(2, operation.getOperande2());
                ps.setString(3, operation.getOperateur());
                ps.setInt(4, operation.getResult());

                dao.ecrire(ps);

                // Retrieve the generated ID
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        operation.setId(newId);
                        return newId;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return -1; // Return -1 if insertion fails
    }

    // Retrieve operations by operator from the database
    public List<Operation> getOperationsByOperator(String operateur) {
        List<Operation> operationsByOperator = new ArrayList<>();

        try {

            String query = "SELECT * FROM operation WHERE operateur = ?";

            try (PreparedStatement ps = dao.connection.prepareStatement(query)) {
                ps.setString(1, operateur);

                try (ResultSet rs = dao.lire(ps)) {
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        int operande1 = rs.getInt("operande1");
                        int operande2 = rs.getInt("operande2");
                        String dbOperateur = rs.getString("operateur");

                        Operation operation = new Operation(id, operande1, operande2, dbOperateur);
                        operationsByOperator.add(operation);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return operationsByOperator;
    }

    // Delete operation by operator from the database
    public boolean delete(int id) {
        try {

            String query = "DELETE FROM operation WHERE id = ?";

            try (PreparedStatement ps = dao.connection.prepareStatement(query)) {
                ps.setInt(1, id);

                dao.ecrire(ps);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 

        return false;
    }
    
    
//resultat
    public int calculateAndStoreResult(Operation operation) {
        int result = operation.getResult();  

        try {
            String updateQuery = "UPDATE operation SET result = ? WHERE id = ?";

            try (PreparedStatement updatePs = dao.connection.prepareStatement(updateQuery)) {
                updatePs.setInt(1, result);
                updatePs.setInt(2, operation.getId());

                dao.ecrire(updatePs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }


}
