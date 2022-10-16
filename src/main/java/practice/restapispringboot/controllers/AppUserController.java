package practice.restapispringboot.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import practice.restapispringboot.dto.AppUserData;
import practice.restapispringboot.dto.ResponseData;
import practice.restapispringboot.models.entities.AppUser;
import practice.restapispringboot.services.AppUserService;

@RestController
@RequestMapping(path = "/api/users")
public class AppUserController {
    
    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Actually, we have finished implementing the register process,
     * but we haven't configured Spring Security yet. So we need to
     * tell Spring that this endpoint "/register" doesn't need to be
     * protected and grant permission so that the endpoint can be
     * accessed without needing to authenticate or enter a username
     * and password.
     * 
     * To set up Spring Security configuration, we will create
     * WebSecurityConfig file as configuration class within security
     * package.
     * 
     * @param AppUserData
     * @return
     */
    @PostMapping(path = "/register")
    public ResponseEntity<ResponseData<AppUser>> register(@RequestBody AppUserData AppUserData) {

        ResponseData<AppUser> responseData = new ResponseData<>();

        AppUser appUser = modelMapper.map(AppUserData, AppUser.class);

        responseData.setPayload(appUserService.registerAppUser(appUser));
        responseData.setStatus(true);
        responseData.getMessages().add("AppUser saved!");

        return ResponseEntity.ok(responseData);
    }
}
