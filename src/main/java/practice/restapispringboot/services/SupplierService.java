package practice.restapispringboot.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import practice.restapispringboot.models.entities.Supplier;
import practice.restapispringboot.models.repositories.SupplierRepo;

@Service
@Transactional
public class SupplierService {
    
    @Autowired
    private SupplierRepo supplierRepo;

    public Supplier save(Supplier supplier) {
        return supplierRepo.save(supplier);
    }

    public Supplier findOne(Long id) {
        Optional<Supplier> supplier = supplierRepo.findById(id);
        return !supplier.isPresent() ? null : supplier.get();
    }

    public Iterable<Supplier> findAll() {
        return supplierRepo.findAll();
    }

    public void deleteOne(Long id) {
        supplierRepo.deleteById(id);
    }

    // Derived Method Implementation
    public Supplier findByEmail(String email) {
        return supplierRepo.findByEmail(email);
    }
    
    public List<Supplier> findByNameContainsOrderByIdDesc(String name) {
        return supplierRepo.findByNameContainsOrderByIdDesc(name);
    }
    
    public List<Supplier> findByNameStartsWith(String prefix) {
        return supplierRepo.findByNameStartsWith(prefix);
    }
    
    public List<Supplier> findByNameContainsOrEmailContains(String name, String email) {
        return supplierRepo.findByNameContainsOrEmailContains(name, email);
    }
}
