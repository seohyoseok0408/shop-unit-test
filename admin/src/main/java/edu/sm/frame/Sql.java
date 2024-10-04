package edu.sm.frame;

public class Sql {

    // 리뷰
  public static final String DELETE_REVIEW =
            "DELETE FROM review WHERE rid=?";
    public static final String SELECT_ONE_REVIEW =
            "SELECT * FROM review WHERE rid=?";
    public static final String SELECT_ALL_REVIEWS =
            "SELECT * FROM review";
    public static final String SELECT_REVIEWS_BY_PRODUCT_ID =
            "SELECT * FROM review WHERE pid = ?";

    // 상품
    public static final String INSERT_PRODUCT =
            "INSERT INTO product (category_id, dis_id, pname, price, cnt, img1, img2, img3, img4, content, is_public) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PRODUCT =
            "UPDATE product SET category_id=?, dis_id=?, pname=?, price=?, cnt=?, img1=?, img2=?, img3=?, img4=?, content=?, is_public=? " +
                    "WHERE pid=?";
    public static final String SELECT_PRODUCT_BY_ID =
            "SELECT * FROM product WHERE pid=?";
    public static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM product";
   public static final String SELECT_PRODUCTS_BY_CATEGORY =
            "SELECT * FROM product WHERE category_id = ?";
    public static final String SELECT_PRODUCT_PRICE=
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

    // 장바구니
//    public static final String INSERT_CART =
//            "INSERT INTO cart (cid, pid, cnt, price) VALUES (?, ?, ?, ?)"; // price 필드 추가
//    public static final String UPDATE_CART_CNT =
//            "UPDATE cart SET cnt = ?, price = ? WHERE cid = ? AND pid = ?"; // 수량과 함께 가격도 업데이트
//    public static final String DELETE_CART_BY_CID_PID =
//            "DELETE FROM cart WHERE cid = ? AND pid = ?";
//    public static final String SELECT_CART_BY_CID =
//            "SELECT * FROM cart WHERE cid = ?"; // price 필드도 함께 SELECT 됨

    // 주문
    public static final String SELECT_ALL_ORDERS =
            "SELECT * FROM orders";
   public static final String SELECT_ORDER_BY_ID =
            "SELECT * FROM orders WHERE oid = ?";
    public static final String SELECT_ORDERS_BY_CID =
            "SELECT * FROM orders WHERE cid = ?";
    public static final String SELECT_ORDERS_BY_STATUS=
            "SELECT oid, cid, ostatus, oname, address, address_detail, zip_code, phone, msg, odate, price FROM orders WHERE ostatus = ?";
    public static final String UPDATE_ORDERS_OSTATUS=
            "UPDATE orders SET ostatus = ? WHERE oid = ?";
    public static final String SELECT_DAILY_ORDER_STATS =
            "SELECT DATE(odate) AS order_date, COUNT(*) AS total_orders, SUM(price) AS total_sales " +
                    "FROM orders GROUP BY order_date";
    public static final String SELECT_MONTHLY_ORDER_STATS =
            "SELECT YEAR(odate) AS order_year, MONTH(odate) AS order_month, COUNT(*) AS total_orders, SUM(price) AS total_sales " +
                    "FROM orders GROUP BY order_year, order_month";

    // 회원
  public static final String UPDATE_CUSTOMER =
            "UPDATE customer SET cname=?, email=?, phone=?, nick_name=? WHERE cid=?";
    public static final String DELETE_CUSTOMER =
            "DELETE FROM customer WHERE cid=?";
    public static final String SELECT_CUSTOMER_BY_NAME =
            "SELECT cid, cname, email, phone, birth_date, nick_name, grade, join_date FROM customer WHERE cname LIKE ?";
    public static final String SELECT_CUSTOMER_ALL=
            "SELECT cid, cname, email,   phone, birth_date, nick_name, grade, join_date FROM customer";
    public static final String SELECT_CUSTOMER_EMAIL=
            "SELECT * FROM customer WHERE email = ?";

    // 접속 통계
    public static final String INSERT_ACCESS_LOG =
            "INSERT INTO access_log (cid) VALUES (?)";
    public static final String SELECT_ACCESS_LOG_BY_ID =
            "SELECT * FROM access_log WHERE log_id = ?";
    public static final String SELECT_ALL_ACCESS_LOGS =
            "SELECT * FROM access_log";
    public static final String SELECT_HOURLY_ACCESS_STATS =
            "SELECT HOUR(access_time) AS 접속시간, COUNT(*) AS 접속자수 FROM access_log GROUP BY HOUR(access_time) ORDER BY 접속시간";
    public static final String SELECT_DAILY_ACCESS_STATS =
            "SELECT DATE(access_time) AS 접속일자, COUNT(*) AS 접속자수 FROM access_log GROUP BY DATE(access_time)";
    public static final String SELECT_WEEKLY_ACCESS_STATS =
            "SELECT CASE DAYNAME(access_time) " +
                    "WHEN 'Sunday' THEN '일요일' WHEN 'Monday' THEN '월요일' WHEN 'Tuesday' THEN '화요일' " +
                    "WHEN 'Wednesday' THEN '수요일' WHEN 'Thursday' THEN '목요일' WHEN 'Friday' THEN '금요일' " +
                    "WHEN 'Saturday' THEN '토요일' END AS 요일, COUNT(*) AS 접속자수 " +
                    "FROM access_log GROUP BY 요일";
    public static final String SELECT_MONTHLY_ACCESS_STATS =
            "SELECT MONTH(access_time) AS 월, COUNT(*) AS 접속자수 FROM access_log GROUP BY MONTH(access_time)";
    public static final String SELECT_YEARLY_ACCESS_STATS =
            "SELECT YEAR(access_time) AS 연도, COUNT(*) AS 접속자수 FROM access_log GROUP BY YEAR(access_time)";

    // 카테고리
    public static final String INSERT_CATEGORY =
            "INSERT INTO category (category_name, category_detail) VALUES (?, ?)";
    public static final String UPDATE_CATEGORY =
            "UPDATE category SET category_name = ?, category_detail = ? WHERE category_id = ?";
    public static final String DELETE_CATEGORY =
            "DELETE FROM category WHERE category_id = ?";
    public static final String SELECT_CATEGORY_BY_ID =
            "SELECT * FROM category WHERE category_id = ?";
    public static final String SELECT_ALL_CATEGORIES =
            "SELECT * FROM category";

    // 결제
    public static final String INSERT_PAY =
            "INSERT INTO pay (oid, pay_price, pay_method, card) VALUES (?, ?, ?, ?)";
    public static final String SELECT_PAY_BY_ID =
            "SELECT * FROM pay WHERE pay_id = ?";
    public static final String SELECT_ALL_PAY =
            "SELECT * FROM pay";
    public static final String SELECT_PAY_BY_ORDER_ID =
            "SELECT * FROM pay WHERE oid = ?";

    // 상세주문
    public static final String INSERT_ORDER_DETAIL =
            "INSERT INTO order_detail (pid, oid, item_cnt, od_price) VALUES (?, ?, ?, ?)";
    public static final String SELECT_ORDER_DETAIL_BY_ID =
            "SELECT * FROM order_detail WHERE order_detail_id = ?";
    public static final String SELECT_ALL_ORDER_DETAILS =
            "SELECT * FROM order_detail";
    public static final String SELECT_ORDER_DETAILS_BY_OID =
            "SELECT od.order_detail_id, od.oid, od.pid, od.item_cnt, od.od_price, p.pname " +
                    "FROM order_detail od " +
                    "JOIN product p ON od.pid = p.pid WHERE od.oid = ?";

    // 할인
    public static final String INSERT_DISCOUNT =
            "INSERT INTO discount (dis_name, dis_rate, dis_start, dis_end) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_DISCOUNT =
            "UPDATE discount SET dis_name = ?, dis_rate = ?, dis_start = ?, dis_end = ? WHERE dis_id = ?";
    public static final String DELETE_DISCOUNT =
            "DELETE FROM discount WHERE dis_id = ?";
    public static final String SELECT_DISCOUNT_BY_ID =
            "SELECT * FROM discount WHERE dis_id = ?";
    public static final String SELECT_ALL_DISCOUNTS =
            "SELECT * FROM discount";
}
