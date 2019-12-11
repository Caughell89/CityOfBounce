package com.example.springsocial.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.springsocial.exception.BadRequestException;
import com.example.springsocial.model.Company;
import com.example.springsocial.model.Employee;
import com.example.springsocial.model.Location;
import com.example.springsocial.model.Product;
import com.example.springsocial.model.User;
import com.example.springsocial.repository.CompanyRepository;
import com.example.springsocial.repository.EmployeeRepository;
import com.example.springsocial.repository.LocationRepository;
import com.example.springsocial.repository.ProductRepository;
import com.example.springsocial.repository.UserRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    Cloudinary cloud = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "city-of-bounce",
            "api_key", "456612825945274",
            "api_secret", "wYS53-0M8fvrhqB2m_JXbBdpW-w"));

    @GetMapping("/company/{companyId}")
    @PreAuthorize("hasRole('USER')")
    public Company getCurrentCompany(@PathVariable Long companyId) {

        Optional<Company> optional = companyRepository.findById(companyId);
        Company foundCompany = optional.get();

        return foundCompany;
    }

    @GetMapping("/{location}/{companyName}")
    @PreAuthorize("hasRole('USER')")
    public Company getCompanyByUrl(@PathVariable String location, @PathVariable String companyName) {
        System.out.println("Here looking for a company");
        String companyUrl = location + "/" + companyName;
        System.out.println(companyUrl);
        Optional<Company> optional = companyRepository.findByCompanyUrl(companyUrl);
        Company foundCompany = optional.get();

        return foundCompany;
    }
    
    @GetMapping("/access")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public String getHome() {

        return "There is access";
    }

    @GetMapping("/{state}/Locations")
    @PreAuthorize("hasRole('USER')")
    public List<String> getCitiesForState(@PathVariable String state) {

        return locationRepository.findLocationsByState(state);
    }

    @RequestMapping(value = "/RegisterCompany/{userId}", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Company registerCompany(@RequestBody Company company, @PathVariable Long userId) {

        if (companyRepository.existsByCompanyUrl(company.getCompanyUrl())) {
            throw new BadRequestException("This company has already been registered.");
        }
        Company savedCompany = companyRepository.save(company);
        Optional<User> optional = userRepository.findById(userId);
        User foundUser = optional.get();
        foundUser.setCompany(savedCompany);
        foundUser.setRole("Owner");
        userRepository.save(foundUser);
        Employee employee = new Employee();
        employee.setCompanyId(savedCompany.getId());
        employee.setTitle("Owner");
        employee.setEmployeeFirstName(foundUser.getFirstName());
        employee.setEmployeeLastName(foundUser.getLastName());
        employee.setEmployeeEmail(foundUser.getEmail());
        employee.setEmployeePhoto(foundUser.getImageUrl());
        employee.setUserId(savedCompany.getId());
        employeeRepository.save(employee);

        ArrayList<Location> locations = locationRepository.findLocationByPlaceAndStateAbbr(savedCompany.getLocation(), savedCompany.getStateAbbr());

        for (int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i).getZipCode());
            companyRepository.saveLocation(savedCompany.getId(), locations.get(i).getZipCode());
        }

        return savedCompany;
    }

    @RequestMapping(value = "company/updateLogo", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Company setCompanyLogo(@RequestBody Company company) throws IOException, Exception {
        System.out.println("Saving logo!");
        System.out.println(company.getCompanyLogo());
        Optional<Company> optional = companyRepository.findById(company.getId());
        Company foundCompany = optional.get();
        int count = 0;
        if (!foundCompany.getCompanyLogo().equals(company.getCompanyLogo())) {
            Map params = ObjectUtils.asMap("public_id", "Company/" + foundCompany.getCompanyUrl() + "/companyLogo");
            Map uploadResult = cloud.uploader().upload(company.getCompanyLogo(), params);
            Map result = cloud.api().resource("Company/" + foundCompany.getCompanyUrl() + "/companyLogo", ObjectUtils.emptyMap());
            for (Object value : result.values()) {
                count++;
                if (count == 4) {
                    foundCompany.setCompanyLogo(value.toString());
                }
            }
        };

        count = 0;

        System.out.println("attempting to save");
        companyRepository.save(foundCompany);
        return foundCompany;

    }

    @RequestMapping(value = "company/addServiceArea", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Company addServiceArea(@RequestBody Company company) throws IOException, Exception {
        System.out.println(company.getId() + " Company Name: " + company.getCompanyName());
        System.out.println("Adding zipcodes for " + company.getLocation() + ", " + company.getStateAbbr());
        ArrayList<Location> locations = locationRepository.findLocationByPlaceAndStateAbbr(company.getLocation(), company.getStateAbbr());

        for (int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i).getZipCode());
            companyRepository.saveLocation(company.getId(), locations.get(i).getZipCode());
        }

        return company;

    }

    @RequestMapping(value = "/deletePendingEmployee")
    public Company deletePendingEmployee(@RequestBody Company company) {
        System.out.println("Deleteing pending employee");
        System.out.println(company.getCompanyName());
        System.out.println(company.getId());
        companyRepository.deletePendingEmployeeWithCompany(company.getId(), company.getCompanyName());

        return company;
    }

    @RequestMapping(value = "/updateEmployee/{title}/{userId}")
    public User deleteEmployee(@PathVariable String title, @PathVariable Long userId) {

        Optional<User> optional = userRepository.findById(userId);
        User employee = optional.get();
        employee.setRole(title);
        userRepository.save(employee);
        employeeRepository.updateEmployeeTitle(title, userId);

        return employee;
    }

    @RequestMapping(value = "/deleteEmployee/{userId}")
    public User deleteEmployee(@PathVariable Long userId) {

        Optional<User> optional = userRepository.findById(userId);
        User employee = optional.get();
        employee.setRole("User");
        userRepository.save(employee);
        userRepository.clearCompany(employee.getEmail());
        companyRepository.deleteActiveEmployee(userId);

        return employee;
    }

    @RequestMapping(value = "/removeArea/{place}/{companyId}")
    public Company removeArea(@PathVariable String place, @PathVariable Long companyId) {
        List<String> zips = companyRepository.findZipsByPlaceAndCompanyId(companyId, place);
        for (int i = 0; i < zips.size(); i++) {
            companyRepository.removeLocationsByCompanyIdAndZip(companyId, zips.get(i));
        }
        Optional<Company> optional = companyRepository.findById(companyId);
        Company foundCompany = optional.get();

        return foundCompany;
    }

    @RequestMapping(value = "company/addProduct", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Product addProduct(@RequestBody Product product) throws IOException, Exception {

        System.out.println("Saving Product!");
        Optional<Company> optional = companyRepository.findById(product.getCompanyId());
        Company foundCompany = optional.get();
        
        List<String> photoUrls = new ArrayList();
        int photoNumber = 0;
        for (String productPhoto : product.getProductPhotos()) {
        photoNumber++;  
        int count = 0;
        Map params = ObjectUtils.asMap("public_id", "Company/" + foundCompany.getCompanyUrl() + "/Products/" + product.getProductType().replaceAll("\\s+","")+"-"+photoNumber);

            Map uploadResult = cloud.uploader().upload(productPhoto, params);
            Map result = cloud.api().resource("Company/" + foundCompany.getCompanyUrl() + "/Products/" + product.getProductType().replaceAll("\\s+","")+"-"+photoNumber, ObjectUtils.emptyMap());
            for (Object value : result.values()) {
                count++;
                if (count == 4) {
                    productPhoto = (value.toString());
                    photoUrls.add(productPhoto);
                }
            }
            
        }
       
        product.setProductPhotos(photoUrls);
        
        productRepository.save(product);

        return product;

    }

}
