package com.example.springsocial.repository;

import com.example.springsocial.model.Product;
import com.example.springsocial.model.ProductReview;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public static final String FIND_PRODUCTS_BY_ZIP = "SELECT * "
            + "FROM products p JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE ca.zip_id = ?;";

    @Query(value = FIND_PRODUCTS_BY_ZIP, nativeQuery = true)
    public List<Product> getProductsByZip(int zip);

    public static final String FIND_PRODUCTS = "SELECT * FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=? "
            + "AND l.state_abbr = ?);";

    @Query(value = FIND_PRODUCTS, nativeQuery = true)
    public List<Product> getProductsBySearchString(String city, String state);

    public static final String FIND_PRODUCTS_PHOTOS = "SELECT * "
            + "FROM products_photos WHERE product_id = ?;";

    @Query(value = FIND_PRODUCTS_PHOTOS, nativeQuery = true)
    public List<String> getProductPhotosById(Long productId);

    public static final String FIND_PRODUCT_REVIEWS_BY_PRODUCT_ID = "SELECT * "
            + "FROM product_reviews WHERE product_id = ?;";

    @Query(value = FIND_PRODUCT_REVIEWS_BY_PRODUCT_ID, nativeQuery = true)
    public List<ProductReview> getProductReviewsByProductId(Long productId);

    public static final String FIND_PRODUCTS_BY_ORDER_ID = "SELECT * FROM products WHERE order_id = ?;";

    @Query(value = FIND_PRODUCTS_BY_ORDER_ID, nativeQuery = true)
    public List<Product> findProductsByOrderId(Long orderId);
    
//  SORTING QUERIES
//  ================
    
    public static final String FIND_FILTERED_PRODUCTS_SORTED_BY_CREATED_ON = "SELECT * FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=? "
            + "AND l.state_abbr = ? AND instant_book = ?) ORDER BY created_on DESC LIMIT ?, ? ;";

    @Query(value = FIND_FILTERED_PRODUCTS_SORTED_BY_CREATED_ON, nativeQuery = true)
    public List<Product> findProductsBySearchFiltersSortedByCreatedOn(String city, String state, boolean instantBook, int row, int i);

    public static final String FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_ASC = "SELECT * FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=? "
            + "AND l.state_abbr = ? AND instant_book = ?) ORDER BY price LIMIT ?, ? ;";

    @Query(value = FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_ASC, nativeQuery = true)
    public List<Product> findProductsBySearchFiltersPriceAsc(String city, String state, boolean instantBook, int row, int limit);

    public static final String FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_DESC = "SELECT * FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=? "
            + "AND l.state_abbr = ? AND instant_book = ?) ORDER BY price DESC LIMIT ?, ? ;";

    @Query(value = FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_DESC, nativeQuery = true)
    public List<Product> findProductsBySearchFiltersSortedByPriceDesc(String city, String state, boolean instantBook, int row, int limit);

    
    public static final String FIND_FILTERED_PRODUCTS = "SELECT * FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=? "
            + "AND l.state_abbr = ? AND instant_book = ?) LIMIT ?, ? ;";
    @Query(value = FIND_FILTERED_PRODUCTS, nativeQuery = true)
    public List<Product> findProductsBySearchFilters(String city, String state, boolean instantBook, int row, int i);

}
