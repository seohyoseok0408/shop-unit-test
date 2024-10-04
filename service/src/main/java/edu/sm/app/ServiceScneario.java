package edu.sm.app;
import edu.sm.dto.*;
import edu.sm.service.*;

import java.util.List;
import java.util.Scanner;

public class ServiceScneario {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Customer newCustomer = null; // 회원가입을 위함
            boolean isLoggedIn = false;
            Customer customer_loggedIn = null;

            CustomerService customerService = new CustomerService();
            ProductService productService = new ProductService();
            CartService cartService = new CartService();
            OrderService orderService = new OrderService();
            OrderDetailService orderDetailService = new OrderDetailService();
            ReviewService reviewService = new ReviewService();
            AddressService addressService = new AddressService();

            while (true) {
                System.out.println("=====================================");
                System.out.println("1. 회원가입(au1)");
                System.out.println("2. 로그인(au2)");
                System.out.println("3. 상품 조회(pr1)");
                System.out.println("4. 상품 상세(pr2)");
                System.out.println("5. 장바구니 상품 정보(ct1)");
                System.out.println("6. 주문 내역(mp_od1)");
                System.out.println("7. 종료");
                System.out.println("=====================================");
                System.out.print("원하는 작업 번호를 입력하세요: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // 개행 문자 제거

                switch (choice) {
                    case 1:
                        System.out.print("이름: ");
                        String name = scanner.nextLine();
                        System.out.print("이메일: ");
                        String email = scanner.nextLine();
                        System.out.print("비밀번호: ");
                        String pwd = scanner.nextLine();
                        System.out.print("전화번호: ");
                        String phone = scanner.nextLine();
                        System.out.print("닉네임: ");
                        String nickName = scanner.nextLine();

                        // 사용자 객체 생성
                        newCustomer = Customer.builder()
                                .email(email)
                                .pwd(pwd)
                                .cname(name)
                                .phone(phone)
                                .nick_name(nickName)
                                .build();
                        customerService.add(newCustomer);
                        System.out.println(newCustomer.getCname() + "님 환영합니다!");
                        break;
                    case 2:
                        if (isLoggedIn) {
                            System.out.println("이미 로그인 상태입니다.");
                        } else {
                            System.out.print("이메일: ");
                            String loginEmail = scanner.nextLine();
                            System.out.print("비밀번호: ");
                            String loginPwd = scanner.nextLine();

                            Customer customer = customerService.login(loginEmail, loginPwd);
                            if (customer != null) {
                                isLoggedIn = true;
                                customer_loggedIn = customer;
                                System.out.println(customer.getCname() + "님 로그인 성공");
                            } else {
                                System.out.println("이메일 또는 비밀번호가 올바르지 않습니다.");
                            }
                        }
                        break;
                    case 3:
                        List<Product> products = productService.getPublicProducts();
                        if (products.isEmpty()) {
                            System.out.println("상품이 없습니다.");
                        } else {
                            System.out.println("전체 상품목록을 조회합니다.");
                            for (Product product : products) {
                                System.out.println("상품 ID       : " + product.getPid());
                                System.out.println("카테고리 ID   : " + product.getCategoryId());
                                System.out.println("상품명        : " + product.getPname());
                                System.out.println("가격          : " + product.getPrice() + "원");
                                System.out.println("재고          : " + product.getCnt());
                                System.out.println("이미지1       : " + product.getImg1());
                                System.out.println("-------------------------------------");
                            }
                            System.out.println("총 " + products.size() + "개의 상품이 조회되었습니다.");
                            System.out.println("=====================================");
                        }
                        break;
                    case 4:
                        System.out.println("-------------------------------------");
                        System.out.println("선택한 상품 상세정보로 이동합니다.");
                        System.out.print("상품 id 입력: ");
                        int productId = scanner.nextInt();
                        Product product = productService.get(productId);

                        if(product != null) {
                            System.out.println("상품명: " + product.getPname());
                            System.out.println("상품 가격: " + product.getPrice());
                            System.out.println("상품 설명: " + product.getContent());
                            System.out.println("-------------------------------------");
                        } else {
                            System.out.println("해당 상품이 존재하지 않습니다.");
                        }

                        // 리뷰 조회
                        List<Review> reviews = reviewService.getByProductId(productId);
                        if (reviews.isEmpty()) {
                            System.out.println("해당 상품은 아직 리뷰가 없습니다.");
                        } else {
                            System.out.println(product.getPname() + " 상품에 대한 리뷰들 조회");

                            for (Review review : reviews) {
                                System.out.println("회원 ID     : " + review.getCid());
                                System.out.println("평점        : " + review.getRate() + "점");
                                System.out.println("제목        : " + review.getTitle());
                                System.out.println("내용        : " + review.getContent());
                                System.out.println("이미지      : " + review.getImg());
                                System.out.println("작성일      : " + review.getRdate());
                                System.out.println("-------------------------------------");
                            }
                        }

                        System.out.println("선택한 상품 장바구니 담기 : 1번");
                        System.out.print("입력: ");
                        int pchoice = scanner.nextInt();
                        if (pchoice == 1) {
                            if (!isLoggedIn) {
                                System.out.println("로그인 후 가능합니다.");
                            } else {
                                System.out.print("담을 갯수: ");
                                int cnt = scanner.nextInt();
                                Cart cart = Cart.builder()
                                        .cid(customer_loggedIn.getCid())
                                        .pid(productId)
                                        .cnt(cnt)
                                        .build();
                                cartService.add(cart);
                                System.out.println(product.getPname() + ", " + cnt + "개성공적으로 추가되었습니다.");
                            }
                        }
                        break;
                    case 5: // 장바구니 목록
                        if (!isLoggedIn) {
                            System.out.println("먼저 로그인 하셔야 합니다.");
                        } else {
                            List<Cart> cartItems = cartService.getCartByCid(customer_loggedIn.getCid());
                            if (cartItems.isEmpty()) {
                                System.out.println("장바구니에 상품이 없습니다.");
                            } else {
                                System.out.println("장바구니에 담긴 상품 목록: ");
                                for (Cart cart : cartItems) {
                                    System.out.println("장바구니 ID  : " + cart.getCartKey());
                                    System.out.println("상품 ID      : " + cart.getPid());
                                    System.out.println("수량         : " + cart.getCnt());
                                    System.out.println("금액         : " + cart.getPrice());
                                    System.out.println("-------------------------------------");
                                }

                                System.out.println("장바구니 물품을 전체 주문하려면 1번");
                                int cart_order_choice = scanner.nextInt();
                                if (cart_order_choice == 1) {
                                    for (Cart cart : cartItems) {
                                        Address address = addressService.get(1);
                                        Order order = Order.builder()
                                                .cid(customer_loggedIn.getCid())
                                                .oname(customer_loggedIn.getCname())
                                                .address(address.getAddress())
                                                .addressDetail(address.getAddressDetail())
                                                .zipCode(address.getZipCode())
                                                .phone(customer_loggedIn.getPhone())
                                                .msg("문 앞에 두고 가주세요")
                                                .price(cart.getPrice())
                                                .build();
                                        orderService.add(order);
                                    }
                                    System.out.println("주문 성공");
                                }
                            }
                        }
                        break;
                    case 6: // 주문내역보기
                        if (!isLoggedIn) {
                            System.out.println("먼저 로그인 하세요");
                        } else {
                            List<Order> orders = orderService.getByCustomerId(customer_loggedIn.getCid());
                            if (orders.isEmpty()) {
                                System.out.println("주문 내역이 없습니다.");
                            } else {
                                System.out.println("주문 내역 조회:");
                                for (Order order : orders) {
                                    System.out.println("주문 ID      : " + order.getOid());
                                    System.out.println("주문 상태    : " + order.getOstatus());
                                    System.out.println("주문자 이름  : " + order.getOname());
                                    System.out.println("주소         : " + order.getAddress());
                                    System.out.println("세부 주소    : " + order.getAddressDetail());
                                    System.out.println("우편번호     : " + order.getZipCode());
                                    System.out.println("전화번호     : " + order.getPhone());
                                    System.out.println("요청 사항    : " + order.getMsg());
                                    System.out.println("주문 날짜    : " + order.getOdate());
                                    System.out.println("금액         : " + order.getPrice() + "원");
                                    System.out.println("-------------------------------------");
                                }
                            }
                        }
                        break;
                    case 7:
                        System.out.println("쇼핑몰에서 나갑니다.");
                        return;
                    default:
                        System.out.println("잘못된 입력입니다. 다시 선택하세요.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}