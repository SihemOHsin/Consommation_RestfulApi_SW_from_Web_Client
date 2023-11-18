package main.java;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Operation implements Serializable {
    private int id;
    private int operande1;
    private int operande2;
    private String operateur;

    //private int result;

    public Operation() {
    }

    public Operation(String operateur) {
        this.operateur = operateur;
    }

    public Operation(int id, int operande1, int operande2, String operateur) {
        super();
        this.id = id;
        this.operande1 = operande1;
        this.operande2 = operande2;
        this.operateur = operateur;
    }

    public Operation(int operande1, int operande2, String operateur) {
        super();
        this.operande1 = operande1;
        this.operande2 = operande2;
        this.operateur = operateur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOperande1() {
        return operande1;
    }

    public void setOperande1(int operande1) {
        this.operande1 = operande1;
    }

    public int getOperande2() {
        return operande2;
    }

    public void setOperande2(int operande2) {
        this.operande2 = operande2;
    }

    public String getOperateur() {
        return operateur;
    }

    public void setOperateur(String operateur) {
        this.operateur = operateur;
    }

    @JsonProperty("result")
    public int getResult() {
        int res = 0;
        switch (operateur) {
            case "Ajout":
                res = operande1 + operande2;
                break;
            case "Soustraction":
                res = operande1 - operande2;
                break;
            case "Multiplication":
                res = operande1 * operande2;
                break;
            case "Division":
                if (operande2 == 0) {
                    System.out.println("Division by zero is not allowed");
                } else {
                    res = operande1 / operande2;
                }
                break;
            default:
                System.out.println("Invalid operation.");
        }

        return res;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + operande1;
        result = prime * result + operande2;
        result = prime * result + ((operateur == null) ? 0 : operateur.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Operation other = (Operation) obj;
        if (id != other.id)
            return false;
        if (operande1 != other.operande1)
            return false;
        if (operande2 != other.operande2)
            return false;
        if (operateur == null) {
            if (other.operateur != null)
                return false;
        } else if (!operateur.equals(other.operateur))
            return false;
        return true;
    }
}
