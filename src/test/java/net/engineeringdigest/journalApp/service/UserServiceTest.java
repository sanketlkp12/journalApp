package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.User;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Disabled
    @Test
    public void testFindByUserName() {
        //assertEquals(4,2+2);
        //assertNotNull(userRepo.findByUserName("sanket"));
        User user = userRepo.findByUserName("sanket");
        assertTrue(!user.getJournalEntries().isEmpty());
    }

@Disabled
    @ParameterizedTest
    @ValueSource(strings = {
            "sanket",
            "vihaan",
            "tom"
    })
    public void testFindByUserName(String userName) {
        assertNotNull(userRepo.findByUserName(userName), "failed for user: " + userName);
    }


    @Disabled
    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user) {
        assertTrue(userService.saveNewUser(user));
    }
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a + b);
    }
}
