package edu.sm.frame;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectionPool {
    // 밑 3개의 커넥션 만들어 놓고, 사용하고 반납하고를 반복
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 3;

    static ResourceBundle rb;
    static { // 클래스가 요청될때마다 한번 실행되는 부분
        rb = null;
        rb = ResourceBundle.getBundle("mysql", Locale.KOREA); // mysql이라는 이름의 properties 파일을 읽겠다.
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("OK");
            System.out.println(rb.getString("url"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool create() throws SQLException { // create 호출시 커넥션 생성

        String url = rb.getString("url");
        String user = rb.getString("user");
        String password = rb.getString("password");

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) { // 3개만큼 커넥션을 생성함
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(pool);
    }

    public ConnectionPool(List<Connection> connectionPool) {
        this.connectionPool = connectionPool;
    }


    public Connection getConnection() { // 커넥션 필요하면 이걸로 가져옴
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public boolean releaseConnection(Connection connection) { // 커넥션 쓰고나서 반납 코드
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }
    public int getUseSize() {
        return connectionPool.size();
    }

}