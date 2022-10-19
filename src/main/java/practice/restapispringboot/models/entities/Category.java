package practice.restapispringboot.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * By extending BaseEntity<String>, this Category Entity
 * will automatically have the properties in BaseEntity.
 * 
 * Next we need to implement auditor aware. JPA can easily get data
 * createdDate & updatedDate from the system or machine timestamp.
 * To tell the jpa who creates and who updates, we can do it by
 * implementing AuditorAware interface. To implement it, we will create
 * the impl class in the package utils.
 */
@Entity
@Table(name = "product_categories")
public class Category extends BaseEntity<String> implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    public Category() {
    }

    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
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

    
    
}
