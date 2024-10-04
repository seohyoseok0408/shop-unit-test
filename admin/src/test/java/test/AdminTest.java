package test;

import edu.sm.dao.*;
import edu.sm.dto.*;
import edu.sm.frame.ConnectionPool;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderDao orderDao = new OrderDao();
        ProductDao productDao = new ProductDao();
        ReviewDao reviewDao = new ReviewDao();
        CustomerDao customerDao = new CustomerDao();
        DiscountDao discountDao = new DiscountDao();
        AccessLogDao accessLogDao = new AccessLogDao();
        ConnectionPool connectionPool = null;

        try {

            connectionPool = ConnectionPool.create();

            try (Connection conn = connectionPool.getConnection()) {
                conn.setAutoCommit(false);

                while (true) {
                    System.out.println("===== 관리자 시스템 =====");
                    System.out.println("1. 상품 관리");
                    System.out.println("2. 주문 목록 조회");
                    System.out.println("3. 배송 목록 조회");
                    System.out.println("4. 회원 정보 관리");
                    System.out.println("5. 접속자 통계 확인");
                    System.out.println("6. 주문 통계 확인");
                    System.out.println("7. 리뷰 관리");
                    System.out.println("0. 종료");
                    System.out.print("원하는 기능을 선택하세요: ");
                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1:

                            System.out.println("1. 상품 등록");
                            System.out.println("2. 상품 정보 수정");
                            System.out.println("3. 재고 관리");
                            int productChoice = scanner.nextInt();
                            switch (productChoice) {
                                case 1:

                                    System.out.print("상품명을 입력하세요: ");
                                    String pname = scanner.next();
                                    System.out.print("카테고리 ID를 입력하세요: ");
                                    int categoryId = scanner.nextInt();
                                    System.out.print("할인 ID를 입력하세요 (없으면 0): ");
                                    Integer disId = scanner.nextInt();
                                    if (disId == 0) {
                                        disId = null;
                                    }
                                    System.out.print("가격을 입력하세요: ");
                                    int price = scanner.nextInt();
                                    System.out.print("재고 수량을 입력하세요: ");
                                    int cnt = scanner.nextInt();
                                    System.out.print("공개 여부 (true/false): ");
                                    boolean isPublic = scanner.nextBoolean();

                                    Product newProduct = new Product(0, categoryId, disId, pname, price, cnt,
                                            "default_img1.jpg", "default_img2.jpg",
                                            "default_img3.jpg", "default_img4.jpg",
                                            "default_content", null, isPublic);
                                    productDao.insert(newProduct, conn);
                                    conn.commit();
                                    System.out.println("상품이 등록되었습니다.");
                                    break;
                                case 2:

                                    System.out.print("수정할 상품 ID를 입력하세요: ");
                                    int pid = scanner.nextInt();
                                    System.out.print("수정할 상품명을 입력하세요: ");
                                    String updatedPname = scanner.next();
                                    System.out.print("수정할 카테고리 ID를 입력하세요: ");
                                    int updatedCategoryId = scanner.nextInt();
                                    System.out.print("수정할 할인 ID를 입력하세요 (없으면 0): ");
                                    Integer updatedDisId = scanner.nextInt();
                                    if (updatedDisId == 0) {
                                        updatedDisId = null;
                                    }
                                    System.out.print("수정할 가격을 입력하세요: ");
                                    int updatedPrice = scanner.nextInt();
                                    System.out.print("수정할 재고 수량을 입력하세요: ");
                                    int updatedCnt = scanner.nextInt();
                                    System.out.print("공개 여부 (true/false): ");
                                    boolean updatedIsPublic = scanner.nextBoolean();


                                    Product updatedProduct = new Product(pid, updatedCategoryId, updatedDisId, updatedPname, updatedPrice, updatedCnt,
                                            "default_img1.jpg", "default_img2.jpg",
                                            "default_img3.jpg", "default_img4.jpg",
                                            "default_content", null, updatedIsPublic);
                                    productDao.update(updatedProduct, conn);
                                    conn.commit();
                                    System.out.println("상품 정보가 수정되었습니다.");
                                    break;

                                case 3:

                                    System.out.print("재고를 확인할 상품 ID를 입력하세요: ");
                                    int stockPid = scanner.nextInt();
                                    Product productStock = productDao.select(stockPid, conn);
                                    if (productStock != null) {
                                        System.out.println("현재 재고 수량: " + productStock.getCnt());
                                        System.out.print("재고 수량을 변경하시겠습니까? (y/n): ");
                                        String confirmStockChange = scanner.next();
                                        if (confirmStockChange.equalsIgnoreCase("y")) {
                                            System.out.print("변경할 재고 수량을 입력하세요: ");
                                            int newStock = scanner.nextInt();
                                            productStock.setCnt(newStock);
                                            productDao.update(productStock, conn);
                                            conn.commit();
                                            System.out.println("재고가 업데이트되었습니다.");
                                        } else {
                                            System.out.println("재고 변경이 취소되었습니다.");
                                        }
                                    } else {
                                        System.out.println("해당 상품이 존재하지 않습니다.");
                                    }
                                    break;
                            }
                            break;

                        case 2:

                            System.out.print("회원 ID를 입력하세요: ");
                            int cid = scanner.nextInt();
                            List<Order> orders = orderDao.selectByCid(cid, conn);
                            System.out.println("회원의 주문 목록:");
                            for (Order order : orders) {
                                System.out.println(order.toString());
                            }
                            System.out.println("입금 상태를 변경하시겠습니까? (1: 대기 -> 완료)");
                            int paymentChoice = scanner.nextInt();
                            if (paymentChoice == 1) {
                                System.out.print("변경할 주문 ID를 입력하세요: ");
                                int oid = scanner.nextInt();
                                orderDao.updateOrderStatus(oid, "입금완료", conn);
                                conn.commit();
                                System.out.println("입금 상태가 '완료'로 변경되었습니다.");
                            }
                            break;

                        case 3:

                            System.out.println("1. 배송 중인 목록 조회");
                            System.out.println("2. 배송 완료 목록 조회");
                            int shippingChoice = scanner.nextInt();
                            switch (shippingChoice) {
                                case 1:
                                    List<Order> shippingOrders = orderDao.selectOrdersByStatus("배송 중", conn);
                                    System.out.println("배송 중인 주문 목록:");
                                    for (Order order : shippingOrders) {
                                        System.out.println(order.toString());
                                    }
                                    break;
                                case 2:
                                    List<Order> completedOrders = orderDao.selectOrdersByStatus("배송 완료", conn);
                                    System.out.println("배송 완료된 주문 목록:");
                                    for (Order order : completedOrders) {
                                        System.out.println(order.toString());
                                    }
                                    break;
                            }
                            break;

                        case 4:

                            System.out.println("1. 회원 정보 조회");
                            System.out.println("2. 회원 정보 수정");
                            System.out.println("3. 회원 탈퇴");
                            int memberChoice = scanner.nextInt();
                            switch (memberChoice) {
                                case 1:
                                    System.out.print("조회할 회원 ID를 입력하세요: ");
                                    int customerId = scanner.nextInt();
                                    Customer customer = customerDao.select(customerId, conn);

                                    if (customer != null) {
                                        System.out.println("회원 정보: " + customer.toString());
                                    } else {
                                        System.out.println("해당 ID의 회원 정보가 존재하지 않습니다.");
                                    }
                                    break;
                                case 2:
                                    System.out.print("수정할 회원 ID를 입력하세요: ");
                                    int updateCid = scanner.nextInt();
                                    System.out.print("수정할 회원명을 입력하세요: ");
                                    String updatedCname = scanner.next();
                                    System.out.print("수정할 닉네임을 입력하세요: ");
                                    String updatedNickName = scanner.next();
                                    System.out.print("수정할 이메일을 입력하세요: ");
                                    String updatedEmail = scanner.next();
                                    System.out.print("수정할 전화번호를 입력하세요: ");
                                    String updatedPhone = scanner.next();

                                    Customer updatedCustomer = new Customer(updateCid, null, updatedCname, updatedEmail, updatedPhone, null, updatedNickName, 0, null);
                                    customerDao.update(updatedCustomer, conn);
                                    conn.commit();
                                    System.out.println("회원 정보가 수정되었습니다.");
                                    break;
                                case 3:
                                    System.out.print("탈퇴할 회원 ID를 입력하세요: ");
                                    int deleteCid = scanner.nextInt();
                                    customerDao.delete(deleteCid, conn);
                                    conn.commit();
                                    System.out.println("회원 탈퇴가 완료되었습니다.");
                                    break;
                            }
                            break;

                        case 5:

                            System.out.println("===== 접속 통계 =====");
                            System.out.println("1. 시간별 접속 통계");
                            System.out.println("2. 일별 접속 통계");
                            System.out.println("3. 요일별 접속 통계");
                            System.out.println("4. 월별 접속 통계");
                            System.out.println("5. 연도별 접속 통계");
                            int statChoice = scanner.nextInt();

                            switch (statChoice) {
                                case 1:
                                    List<String> hourlyStats = accessLogDao.selectHourlyStats(conn);
                                    System.out.println("시간별 접속 통계:");
                                    for (String stat : hourlyStats) {
                                        System.out.println(stat);
                                    }
                                    break;
                                case 2:
                                    List<String> dailyStats = accessLogDao.selectDailyStats(conn);
                                    System.out.println("일별 접속 통계:");
                                    for (String stat : dailyStats) {
                                        System.out.println(stat);
                                    }
                                    break;
                                case 3:
                                    List<String> weeklyStats = accessLogDao.selectWeeklyStats(conn);
                                    System.out.println("요일별 접속 통계:");
                                    for (String stat : weeklyStats) {
                                        System.out.println(stat);
                                    }
                                    break;
                                case 4:
                                    List<String> monthlyStats = accessLogDao.selectMonthlyStats(conn);
                                    System.out.println("월별 접속 통계:");
                                    for (String stat : monthlyStats) {
                                        System.out.println(stat);
                                    }
                                    break;
                                case 5:
                                    List<String> yearlyStats = accessLogDao.selectYearlyStats(conn);
                                    System.out.println("연도별 접속 통계:");
                                    for (String stat : yearlyStats) {
                                        System.out.println(stat);
                                    }
                                    break;
                                default:
                                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                                    break;
                            }
                            break;

                        case 6:

                            System.out.println("1. 일별 주문 통계");
                            System.out.println("2. 월별 주문 통계");
                            int orderStatChoice = scanner.nextInt();
                            switch (orderStatChoice) {
                                case 1:
                                    List<Map<String, Object>> dailyOrderStats = orderDao.selectDailyOrderStats(conn);
                                    for (Map<String, Object> stat : dailyOrderStats) {
                                        System.out.println(stat.toString());
                                    }
                                    break;
                                case 2:
                                    List<Map<String, Object>> monthlyOrderStats = orderDao.selectMonthlyOrderStats(conn);
                                    for (Map<String, Object> stat : monthlyOrderStats) {
                                        System.out.println(stat.toString());
                                    }
                                    break;
                            }
                            break;

                        case 7:

                            System.out.println("1. 리뷰 조회");
                            System.out.println("2. 리뷰 삭제");
                            int reviewChoice = scanner.nextInt();
                            switch (reviewChoice) {
                                case 1:
                                    List<Review> reviews = reviewDao.select(conn);
                                    for (Review review : reviews) {
                                        System.out.println(review.toString());
                                    }
                                    break;
                                case 2:
                                    System.out.print("삭제할 리뷰 ID를 입력하세요: ");
                                    int deleteRid = scanner.nextInt();
                                    reviewDao.delete(deleteRid, conn);
                                    conn.commit();
                                    System.out.println("리뷰가 삭제되었습니다.");
                                    break;
                            }
                            break;

                        case 0:
                            System.out.println("프로그램을 종료합니다.");
                            return;

                        default:
                            System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
                            break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (connectionPool != null) {
                try (Connection conn = connectionPool.getConnection()) {
                    conn.rollback();
                } catch (Exception rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (connectionPool != null) {
                scanner.close();
            }
        }
    }
}
