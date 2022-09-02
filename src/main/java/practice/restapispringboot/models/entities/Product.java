package practice.restapispringboot.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity // to mark this class as entity

/**
 * @Table(name = "products")
 * 
 * when application run
 * jpa will check, is there any table called products or not
 * if not exist yet, jpa will generate the table with name products
 * and jpa will connect this class Product with table products
 */
@Table(name = "products")
public class Product implements Serializable{

    /**
     * because this class implement Serializable interface
     * so we're suggested to write serial version UID below
     */
    private static final long serialVersionUID = 1L;

    @Id // to mark this properties as primary key id column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // to make this primary key id column able to auto increment
    private Long id;

    /**
     * @NotEmpty
     * used for validating column that must have value and can't be null
     */
    @NotEmpty(message = "name is required")
    @Column(name = "product_name", length = 100)
    private String name;

    @NotEmpty(message = "description is required")
    @Column(length = 500)
    private String description;

    private Double price;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
}
