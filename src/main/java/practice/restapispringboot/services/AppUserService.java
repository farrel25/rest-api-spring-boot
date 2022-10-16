package practice.restapispringboot.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import practice.restapispringboot.models.entities.AppUser;
import practice.restapispringboot.models.repositories.AppUserRepo;

/**
 * this AppUserService class must implement UserDetailsService interface from spring security core.
 * We need to do this so that we can inject this AppUserService into the spring security configuration.
 * Because we used UserDetailsService interface, we need to implement and override one method which is loadUserByUsername.
 */
@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        // return null;

        /**
         * when we call the findByEmail method, it could be that
         * the email we are looking for does not exist / has not
         * been registered / wrong so that the user is not found,
         * therefore we use orElseThrow to throw an exception if
         * user not found.
         */
        return appUserRepo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException(
                String.format("user with email '%s' not found", email)
            ));
    }

    public AppUser registerAppUser(AppUser appUser) {
        /**
         * Don't forget that we already used validation for the email must be unique,
         * So we need to validate it in this method. If we're not doing it, then there
         * will be exception from database when the email is not unique.
         */

        // check if the email is registered / user is already exist
        boolean userExist = appUserRepo.findByEmail(appUser.getEmail()).isPresent();

        // throw an exception if user already exist. Else, store to database
        if (userExist) {
            throw new RuntimeException(
                String.format("User with email '%s' already exist", appUser.getEmail())
            );
        }

        /**
         * before we store the user data to database, we must encode the user's password first.
         * In this case, we will user BCrypt. To use it, we will create configuration class
         * called PasswordEncoder with Bean object inside that class and place it inside utils
         * package.
         * 
         * After that, we need to inject the BCryptPasswordEncoder Bean from PasswordEncoder
         * Configuration class.
         */
        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);

        return appUserRepo.save(appUser);
    }
}
