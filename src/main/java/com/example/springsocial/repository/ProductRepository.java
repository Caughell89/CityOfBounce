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
            + "product_type IN (:pt) AND " 
            + "price BETWEEN :min AND :max AND "
            + "product_id IN (SELECT DISTINCT(p.product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code  "
            + "JOIN order_products op ON op.product_id=p.product_id "
            + "JOIN orders o ON o.order_id = op.order_id "
            + "WHERE l.place=:city "
            + "AND l.state_abbr = :state AND instant_book = :instantBook "
            + "AND DATE(event_date) != :date "
            + "AND c.is_active != 0) "
            + "ORDER BY created_on DESC LIMIT :row, :limit ;";

    @Query(value = FIND_FILTERED_PRODUCTS_SORTED_BY_CREATED_ON, nativeQuery = true)
    public List<Product> findProductsBySearchFiltersSortedByCreatedOn(List<String> pt, 
            String city, String state, boolean instantBook, double min, double max,
            String date,
            int row, int limit);

    public static final String FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_ASC = "SELECT * FROM products WHERE "
            + "product_type IN (:pt) AND " 
            + "price BETWEEN :min AND :max AND "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=:city "
            + "AND l.state_abbr = :state AND instant_book = :instantBook) ORDER BY price LIMIT :row, :limit ;";

    @Query(value = FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_ASC, nativeQuery = true)
    public List<Product> findProductsBySearchFiltersPriceAsc(List<String> pt, 
            String city, String state, boolean instantBook, double min, double max,
            int row, int limit);

    public static final String FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_DESC = "SELECT * FROM products WHERE "
            + "product_type IN (:pt) AND " 
            + "price BETWEEN :min AND :max AND "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=:city "
            + "AND l.state_abbr = :state AND instant_book = :instantBook) ORDER BY price DESC LIMIT :row, :limit ;";

    @Query(value = FIND_FILTERED_PRODUCTS_SORTED_BY_PRICE_DESC, nativeQuery = true)
    public List<Product> findProductsBySearchFiltersSortedByPriceDesc(List<String> pt,
            String city, String state, boolean instantBook, double min, double max,
            int row, int limit);

    
    public static final String FIND_FILTERED_PRODUCTS = "SELECT * FROM products WHERE "
            + "product_type IN (:pt) AND " 
            + "price BETWEEN :min AND :max AND "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=:city "
            + "AND l.state_abbr = :state AND instant_book = :instantBook) LIMIT :row, :limit ;";
    @Query(value = FIND_FILTERED_PRODUCTS, nativeQuery = true)
    public List<Product> findProductsBySearchFilters(List<String> pt, String city, 
            String state, boolean instantBook, double min, double max, int row, int limit);

    public static final String GET_MAX_PRICE = "SELECT * FROM products WHERE "
            + "product_type IN (:pt) AND " 
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=:city "
            + "AND l.state_abbr = :state AND instant_book = :instantBook) LIMIT :row, :limit ;";
    @Query(value = GET_MAX_PRICE, nativeQuery = true)
    public float getMaxPrice(List<String> pt, String city, String state, boolean instantBook, 
            int row, int limit);

    public static final String GET_PRODUCT_COUNT = "SELECT COUNT(*) FROM products WHERE "
            + "product_type IN (:pt) AND " 
            + "price BETWEEN :min AND :max AND "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=:city "
            + "AND l.state_abbr = :state);";
    @Query(value = GET_PRODUCT_COUNT, nativeQuery = true)
    public int getProductCount(List<String> pt, String city, String state, 
            double min, double max);

    public static final String GET_ALL_PRODUCT_COUNT = "SELECT COUNT(*) FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=? "
            + "AND l.state_abbr = ?);";
    @Query(value = GET_ALL_PRODUCT_COUNT, nativeQuery = true)
    public int getAllProductCount(String city, String state);
    
    public static final String GET_ALL_MAX_PRICE = "SELECT MAX(price) FROM products WHERE "
            + "product_id IN (SELECT DISTINCT(product_id) FROM products p "
            + "JOIN companies c ON p.company_id=c.company_id "
            + "JOIN companies_areas ca ON c.company_id=ca.company_id "
            + "JOIN locations l ON ca.zip_id=l.zip_code WHERE l.place=:city "
            + "AND l.state_abbr = :state);";
    @Query(value = GET_ALL_MAX_PRICE, nativeQuery = true)
    public Integer getAllMaxPrice(String city, String state);

    
}
