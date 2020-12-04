package com.example.springsocial.repository;

import com.example.springsocial.model.Order;
import com.example.springsocial.model.OrderProducts;
import com.example.springsocial.model.PartyRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
    public static final String INSERT_ORDER_PRODUCTS = "INSERT INTO order_products (order_id, product_id, price,quantity) values (?,?,?,?);";
    
    @Transactional
    @Modifying
    @Query(value = INSERT_ORDER_PRODUCTS, nativeQuery = true)
    public void saveOrderProducts(Long order_id, int product_id, double price, int quantity);
    
    public static final String GET_ORDERS_BY_COMPANY_ID_AND_DATE = "SELECT DISTINCT o.order_id,o.customer_name, o.user_id, o.address, o.city, o.state, o.zipcode, o.special_instructions, o.email, o.phone, o.created_on, o.event_date FROM products p JOIN order_products op ON p.product_id=op.product_id JOIN orders o ON o.order_id=op.order_id WHERE p.company_id = ? AND DATE(o.event_date) = ?;";
    
    @Query(value = GET_ORDERS_BY_COMPANY_ID_AND_DATE, nativeQuery = true)
    public ArrayList<Order> getOrdersByCompanyIdAndDate(Long companyId, String date);

    
    public static final String GET_ORDERS_BY_COMPANY_ID_AND_EMAIL = "SELECT DISTINCT o.order_id,o.customer_name, o.user_id, o.address, o.city, o.state, o.zipcode, o.special_instructions, o.email, o.phone, o.created_on, o.event_date FROM products p JOIN order_products op ON p.product_id=op.product_id JOIN orders o ON o.order_id=op.order_id WHERE p.company_id = ? AND email = ? ;";
    
    @Query(value = GET_ORDERS_BY_COMPANY_ID_AND_EMAIL, nativeQuery = true)
    public ArrayList<Order> getOrdersByCompanyIdAndEmail(Long companyId, String email);
    
    public static final String GET_ORDERS_BY_COMPANY_ID_AND_NAME = "SELECT DISTINCT o.order_id,o.customer_name, o.user_id, o.address, o.city, o.state, o.zipcode, o.special_instructions, o.email, o.phone, o.created_on, o.event_date FROM products p JOIN order_products op ON p.product_id=op.product_id JOIN orders o ON o.order_id=op.order_id WHERE p.company_id = ? AND customer_name = ? ;";
    
    @Query(value = GET_ORDERS_BY_COMPANY_ID_AND_NAME, nativeQuery = true)
    public ArrayList<Order> getOrdersByCompanyIdAndName(Long companyId, String name);
    
    public static final String GET_ORDERS_BY_COMPANY_ID_AND_ORDER_ID = "SELECT DISTINCT o.order_id,o.customer_name, o.user_id, o.address, o.city, o.state, o.zipcode, o.special_instructions, o.email, o.phone, o.created_on, o.event_date FROM products p JOIN order_products op ON p.product_id=op.product_id JOIN orders o ON o.order_id=op.order_id WHERE p.company_id = ? AND o.order_id = ? ;";
    
    @Query(value = GET_ORDERS_BY_COMPANY_ID_AND_ORDER_ID, nativeQuery = true)
    public ArrayList<Order> getOrdersByCompanyIdAndOrderId(Long companyId, Long orderId);

    public List<Order> getOrdersByUserId(Long userId);
    
    public static final String GET_BLOCKED_DATES_BY_PRODUCT_ID = "SELECT date(event_date) "
            + "FROM orders o JOIN order_products op ON op.order_id= o.order_id "
            + "WHERE op.product_id = ? AND event_date >= CURDATE();";

    @Query(value = GET_BLOCKED_DATES_BY_PRODUCT_ID, nativeQuery = true)
    public ArrayList<String> getBlockedDates(Long productId);

    
    public static final String GET_ORDERS_BY_DATE1 = "SELECT o.order_id,o.customer_name, o.user_id, o.address, o.city, o.state, o.zipcode, o.special_instructions, o.email, o.phone, o.created_on, o.event_date FROM products p JOIN order_products op ON p.product_id=op.product_id JOIN orders o ON o.order_id=op.order_id WHERE DATE(o.event_date) = ?;";
    
        //public static final String GET_ORDERS_BY_DATE = "SELECT * FROM orders o WHERE DATE(o.event_date) = ?;";

    //@Query(value = GET_ORDERS_BY_DATE, nativeQuery = true)
    public ArrayList<Order> findByEventDate(LocalDateTime date);
}
