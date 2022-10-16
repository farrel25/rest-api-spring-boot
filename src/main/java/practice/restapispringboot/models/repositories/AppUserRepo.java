package practice.restapispringboot.models.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import practice.restapispringboot.models.entities.AppUser;

public interface AppUserRepo extends PagingAndSortingRepository<AppUser, Long> {
    
    // get user data based on username (email)
    Optional<AppUser> findByEmail(String email);
}
