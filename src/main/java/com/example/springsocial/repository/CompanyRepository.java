package com.example.springsocial.repository;

import com.example.springsocial.model.Company;
import com.example.springsocial.model.Location;
import com.example.springsocial.model.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Boolean existsByCompanyUrl(String url);
    
    public static final String LINK_COMPANIES_WITH_USER = "INSERT INTO companies_employees (company_id, employees_id) VALUES (:company_id, :user_id)";

    @Transactional
    @Modifying
    @Query(value = LINK_COMPANIES_WITH_USER, nativeQuery = true)
    public void linkCompanyWithUser(@Param("company_id") int company_id, @Param("user_id") int user_id);
    
    public static final String LINK_COMPANIES_WITH_ZIP = "INSERT INTO companies_areas (company_id, zip_id) VALUES (:company_id, :zip_id)";

    @Transactional
    @Modifying
    @Query(value = LINK_COMPANIES_WITH_ZIP, nativeQuery = true)
    void saveLocation(@Param("company_id") Long companyId, @Param("zip_id") String zipCode);
    
    
    public static final String LINK_PENDING_EMPLOYEE_WITH_COMPANY = "INSERT INTO pending_employees (company_id, email) VALUES (:company_id, :email)";

    @Transactional
    @Modifying
    @Query(value = LINK_PENDING_EMPLOYEE_WITH_COMPANY, nativeQuery = true)
    public void linkPendingEmployeeWithCompany(@Param("company_id") Long company_id, @Param("email") String email);

    public static final String DELETE_PENDING_EMPLOYEE_WITH_COMPANY = "DELETE FROM pending_employees WHERE company_id = ? AND email = ?;";
    
    @Transactional
    @Modifying
    @Query(value = DELETE_PENDING_EMPLOYEE_WITH_COMPANY, nativeQuery = true)
    public void deletePendingEmployeeWithCompany(@Param("company_id") Long company_id, @Param("email") String email);

    public static final String DELETE_ACTIVE_EMPLOYEE = "DELETE FROM employees WHERE user_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = DELETE_ACTIVE_EMPLOYEE, nativeQuery = true)
    public void deleteActiveEmployee(@Param("user_id") Long user_id);

    public static final String FIND_ZIPS_BY_PLACE_AND_COMPANY_ID = "SELECT zip_id FROM companies c JOIN companies_areas ca ON c.company_id=ca.company_id JOIN locations l ON ca.zip_id = l.zip_code WHERE c.company_id = ? AND place = ? AND l.state_abbr = ?;";
    
    @Query(value = FIND_ZIPS_BY_PLACE_AND_COMPANY_ID, nativeQuery = true)
    public List<String> findZipsByPlaceAndCompanyId(Long companyId, String place, String stateAbbr);
    

    public static final String DELETE_LOCATION_BY_COMPANY_ID_AND_ZIP = "DELETE FROM companies_areas WHERE company_id = ? AND zip_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = DELETE_LOCATION_BY_COMPANY_ID_AND_ZIP, nativeQuery = true)
    public void removeLocationsByCompanyIdAndZip(Long companyId, String zip);

    
    public static final String FIND_COMPANY_BY_COMPANY_URL = "SELECT * FROM companies WHERE company_url = ?;";
    
    @Query(value = FIND_COMPANY_BY_COMPANY_URL, nativeQuery = true)
    public Optional<Company> findByCompanyUrl(String companyUrl);

    
    
    
    public static final String SET_PAYMENT_ID = "UPDATE companies SET payment_id = ? WHERE company_id = ?;";
    
    @Transactional
    @Modifying
    @Query(value = SET_PAYMENT_ID, nativeQuery = true)
    public void setPaymentId(String paymentId, Long companyId);

    public Company getCompanyByCompanyId(Long companyId);


}
