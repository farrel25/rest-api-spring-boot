package practice.restapispringboot.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * similar to the supplier entity class, but depending on the requirements
 * This DTO class will be used to wrap the data from client that will be added to the supplier table
 * This DTO class only include the data needed when creating new data
 */
public class SupplierData {

    private Long id;
    
    @NotEmpty(message = "Name is required")
    private String name;
    
    @NotEmpty(message = "Address is required")
    private String address;

    @NotEmpty(message = "Email is required")
    @Email(message = "Email is not valid")
    private String email;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
