package practice.restapispringboot.models.repositories;

import org.springframework.data.repository.CrudRepository;

import practice.restapispringboot.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long>{
    
}
