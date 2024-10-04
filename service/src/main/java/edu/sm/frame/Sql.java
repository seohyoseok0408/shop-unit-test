package edu.sm.frame;

public class Sql {

    // 리뷰
    public static final String INSERT_REVIEW =
            "INSERT INTO review (pid, cid, rate, title, content, img) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_REVIEW =
            "UPDATE review SET pid=?, cid=?, rate=?, title=?, content=?, img=? WHERE rid=?";
    public static final String DELETE_REVIEW =
            "DELETE FROM review WHERE rid=?";
    public static final String SELECT_REVIEWS_BY_PRODUCT_ID =
            "SELECT * FROM review WHERE pid = ?";
    public static final String CHECK_DUPLICATE_REVIEW =
            "SELECT COUNT(*) FROM review WHERE cid = ? AND pid = ?";

    // 상품
    public static final String SELECT_PRODUCT_BY_ID =
            "SELECT * FROM product WHERE pid=?";
    public static final String SELECT_PUBLIC_PRODUCTS =
            "SELECT * FROM product WHERE is_public = true";
    public static final String SELECT_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM product WHERE category_id = ?";
    public static final String SELECT_PRODUCT_BY_NAME =
            "SELECT * FROM product WHERE pname LIKE ?";
    public static final String SELECT_PRODUCT_PRICE =
            "SELECT price FROM product WHERE pid = ?";

    // 정렬 sql
    public static final String SELECT_PRODUCT_ORDER_BY_PRICE_ASC =
            "SELECT * FROM product WHERE (category_id = ? OR ? IS NULL) ORDER BY price ASC";
    public static final String SELECT_PRODUCT_ORDER_BY_PRICE_DESC =
            "SELECT * FROM product WHERE (category_id = ? OR ? IS NULL) ORDER BY price DESC";
    public static final String SELECT_PRODUCT_ORDER_BY_REVIEW_COUNT =
            "SELECT p.*, COUNT(r.rid) AS review_count " +
                    "FROM product p LEFT JOIN review r ON p.pid = r.pid " +
                    "WHERE (p.category_id = ? OR ? IS NULL) " +
                    "GROUP BY p.pid ORDER BY review_count DESC";
    public static final String SELECT_PRODUCT_ORDER_BY_SALES_COUNT =
            "SELECT p.*, COUNT(od.pid) AS sales_count " +
                    "FROM product p " +
                    "LEFT JOIN order_detail od ON p.pid = od.pid " +
                    "WHERE (p.category_id = ? OR ? IS NULL) " +
                    "GROUP BY p.pid " +
                    "ORDER BY sales_count DESC";
    public static final String SELECT_PUBLIC_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM product WHERE category_id = ? AND is_public = true";
    public static final String SELECT_PUBLIC_PRODUCT_BY_NAME =
            "SELECT * FROM product WHERE pname LIKE ? AND is_public = true";


    // 장바구니
    public static final String INSERT_CART =
            "INSERT INTO cart (cid, pid, cnt, price) VALUES (?, ?, ?, ?)"; // price 필드 추가
    public static final String UPDATE_CART_CNT =
            "UPDATE cart SET cnt = ?, price = ? WHERE cid = ? AND pid = ?"; // 수량과 함께 가격도 업데이트
    public static final String DELETE_CART_BY_CID_PID =
            "DELETE FROM cart WHERE cid = ? AND pid = ?";
    public static final String SELECT_CART_BY_CID =
            "SELECT * FROM cart WHERE cid = ?"; // price 필드도 함께 SELECT 됨

    // 주문
    public static final String INSERT_ORDERS =
            "INSERT INTO orders (cid, oname, address, address_detail, zip_code, phone, msg, price) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ORDER_BY_ID =
            "SELECT * FROM orders WHERE oid = ?";
    public static final String SELECT_ORDERS_BY_CID =
            "SELECT * FROM orders WHERE cid = ?";
    public static final String UPDATE_ORDERS_OSTATUS =
            "UPDATE orders SET ostatus = ? WHERE oid = ?";

    // 회원
    public static final String INSERT_CUSTOMER =
            "INSERT INTO customer (pwd, cname, email, phone, birth_date, nick_name, join_date) VALUES (?, ?, ?, ?, ?,  ?, CURRENT_TIMESTAMP)";
    public static final String DELETE_CUSTOMER =
            "DELETE FROM customer WHERE cid=?";
    public static final String SELECT_CUSTOMER_BY_NAME =
            "SELECT cid, cname, email, phone, birth_date, nick_name, grade, join_date FROM customer WHERE cname LIKE ?";
    public static final String SELECT_CUSTOMER_BY_ID =
            "SELECT cid, cname, email, phone, birth_date, nick_name, grade, join_date FROM customer WHERE cid = ?";
    public static final String SELECT_CUSTOMER_EMAIL =
            "SELECT * FROM customer WHERE email = ?";

    // 카테고리
    public static final String SELECT_ALL_CATEGORIES =
            "SELECT * FROM category";

    // 결제
    public static final String INSERT_PAY =
            "INSERT INTO pay (oid, pay_price, pay_method, card) VALUES (?, ?, ?, ?)";
    public static final String SELECT_PAY_BY_ORDER_ID =
            "SELECT * FROM pay WHERE oid = ?";

    // 상세주문
    public static final String SELECT_ORDER_DETAIL_BY_ID =
            "SELECT * FROM order_detail WHERE order_detail_id = ?";
    public static final String SELECT_ORDER_DETAILS_BY_OID =
            "SELECT od.order_detail_id, od.oid, od.pid, od.item_cnt, od.od_price, p.pname " +
                    "FROM order_detail od " +
                    "JOIN product p ON od.pid = p.pid WHERE od.oid = ?";

    // 주소
    public static final String INSERT_ADDRESS =
            "INSERT INTO address (cid, aname, address, address_detail, zip_code, phone) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_ADDRESSES =
            "SELECT * FROM address";
    public static final String SELECT_ADDRESSES_BY_CID =
            "SELECT * FROM address WHERE cid = ?";
    public static final String SELECT_ADDRESS_BY_ID =
            "SELECT * FROM address WHERE aid = ?";
    public static final String UPDATE_ADDRESS =
            "UPDATE address SET aname = ?, address = ?, address_detail = ?, zip_code = ?, phone = ? WHERE aid = ?";
    public static final String DELETE_ADDRESS =
            "DELETE FROM address WHERE aid = ?";
}