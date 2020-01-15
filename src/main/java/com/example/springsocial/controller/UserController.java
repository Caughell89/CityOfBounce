package com.example.springsocial.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.exception.ResourceNotFoundException;
import com.example.springsocial.model.Company;
import com.example.springsocial.model.Employee;
import com.example.springsocial.model.Location;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.EmployeeRepository;
import com.example.springsocial.repository.LocationRepository;
import com.example.springsocial.repository.UserRepository;
import com.example.springsocial.security.CurrentUser;
import com.example.springsocial.security.UserPrincipal;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private EmployeeRepository employeeRepository;

    Cloudinary cloud = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "city-of-bounce",
            "api_key", "456612825945274",
            "api_secret", "wYS53-0M8fvrhqB2m_JXbBdpW-w"));

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {

        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User updateCurrentUser(@RequestBody User user) {

        Optional<User> optional = userRepository.findById(user.getId());
        User foundUser = optional.get();
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setEmail(user.getEmail());
        foundUser.setPhone(user.getPhone());
        foundUser.setImageUrl(foundUser.getImageUrl());
        userRepository.save(foundUser);

        return user;
    }

    @RequestMapping(value = "user/updatePhoto", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public User setProfilePicByEmail(@RequestBody User user) throws IOException, Exception {
        System.out.println("Saving image");
        Optional<User> optional = userRepository.findById(user.getId());
        User foundUser = optional.get();
        int count = 0;

        Map params = ObjectUtils.asMap("public_id", "User/"+ foundUser.getEmail() + "/profilePic");
        cloud.uploader().upload(user.getImageUrl(), params);
        Map result = cloud.api().resource("User/"+user.getEmail() + "/profilePic", ObjectUtils.emptyMap());
        for (Object value : result.values()) {
            count++;
            if (count == 4) {
                foundUser.setImageUrl(value.toString());
            }
        }
        if(foundUser.getCompany().getCompanyId()!=null){
        Optional<Employee> optionalE = employeeRepository.findByEmployeeEmail(user.getEmail());
        Employee employee = optionalE.get();
        employee.setEmployeePhoto(foundUser.getImageUrl());
        employeeRepository.save(employee);
        }
        System.out.println("attempting to save");
        userRepository.save(foundUser);
        return foundUser;

    }

    

}
