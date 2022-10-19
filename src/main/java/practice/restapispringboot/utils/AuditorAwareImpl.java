package practice.restapispringboot.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import practice.restapispringboot.models.entities.AppUser;

/**
 * wajib implements AuditorAware interface.
 * kita perlu masukkan parameter generic nya sebagai String,
 * karena nantinya akan return email atau nama si user
 * 
 * Untuk memberitahu JPA siapa yg melakukan suatu aksi semacam CRUD atau lainnya
 * kita perlu membuat bean dari AuditorAwareImpl di main class (RestApiSpringBootApplication)
 */
public class AuditorAwareImpl implements AuditorAware<String>{

    /**
     * this method return siapa user yg login saat ini, diambil dari spring security context nya
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        // TODO Auto-generated method stub
        // return Optional.empty();

        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getEmail());
    }
    
}
