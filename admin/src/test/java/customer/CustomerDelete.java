package customer;

import edu.sm.dto.Customer;
import edu.sm.service.CustomerService;

public class CustomerDelete {
    public static void main(String[] args) {
        CustomerService customerService = new CustomerService();
        int id = 15;
        Customer cust = Customer.builder()
                .cid(id)
                .build();

        try {
            boolean result = customerService.remove(cust.getCid());
            if (result) {
                System.out.println("=====================================");
                System.out.println("       [ 고객 삭제 성공 ]             ");
                System.out.println("-------------------------------------");
                System.out.println("  삭제된 고객 ID : " + cust.getCid());
                System.out.println("-------------------------------------");
                System.out.println("고객 정보가 성공적으로 삭제되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("        [ 고객 삭제 실패 ]            ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 고객을 찾을 수 없습니다: " + cust.getCid());
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("    [ 고객 삭제 중 오류 발생 ]        ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
