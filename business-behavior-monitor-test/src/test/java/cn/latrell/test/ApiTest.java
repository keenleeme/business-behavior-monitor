package cn.latrell.test;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@SpringBootConfiguration
public class ApiTest {

    private UserEntity user = null;

    @Before
    public void init() {
        user = new UserEntity();
        user.setUserAge("18");
        user.setUserId("1");
        user.setUserName("test");
        user.setUserSex("male");
    }

    @Test
    public void test_log_01() throws InterruptedException {
        log.info("测试日志 {} {} {} {}", user.getUserId(), user.getUserName(), user.getUserAge(), user.getUserSex());

        new CountDownLatch(1).await();
    }

    @Data
    static class UserEntity {
        private String userId;
        private String userName;
        private String userAge;
        private String userSex;
    }

}
