package dev.unchk.userservice;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserServiceApplicationTests {

    @Test
    void contextLoads() {
        assertThat(1).isEqualTo(1);
    }

}
