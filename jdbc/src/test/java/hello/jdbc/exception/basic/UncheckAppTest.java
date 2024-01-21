package hello.jdbc.exception.basic;

import hello.jdbc.exception.basic.CheckedAppTest.Controller;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckAppTest {


    @Test
    void unChecked() {
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(() -> controller.request())
                .isInstanceOf(Exception.class);
    }

    @Test
    void printEx() {
        Controller controller = new Controller();
        try{
            controller.request();
        }catch (Exception e){
            log.info("ex", e);
        }
    }

    static class controller{
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }
    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }
    static class NetworkClient{
        public void call() {
            throw new RuntimeConnectException("연결 실패");
        }
    }
    static class Repository{
        public void call () {
            try {
                runSql();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void runSql() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException {
        public RuntimeConnectException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException {
        public RuntimeSQLException(String message) {
            super(message);
        }
    }
}
