package customer;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

public class CustomerUpdate {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        int id = 20;

        // Customer 객체 생성
        Customer cust = Customer.builder()
                .cid(id)
                .cname("홍길동")           // 이름 수정
                .phone("01012341234")     // phone 필드 수정
                .email("test@naver.com")  // 이메일 수정
                .nick_name("낄똥")        // 닉네임 수정
                .build();

        try {
            Customer isUpdated = customerService.modify(cust);  // 수정 메서드 호출

            if (isUpdated != null) {
                System.out.println("=====================================");
                System.out.println("        [ 고객 정보 수정 성공 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("  고객 ID      : " + cust.getCid());
                System.out.println("  이름         : " + cust.getCname());
                System.out.println("  이메일       : " + cust.getEmail());
                System.out.println("  전화번호     : " + cust.getPhone());
                System.out.println("  닉네임       : " + cust.getNick_name());
                System.out.println("-------------------------------------");
                System.out.println("고객 정보가 성공적으로 수정되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 고객 정보 수정 실패 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 고객을 찾을 수 없습니다: " + cust.getCid());
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("    [ 고객 정보 수정 중 오류 발생 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
