package com.app.journalApp.service;


import com.app.journalApp.entity.User;
import com.app.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;


@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Disabled
    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("ram");
        AssertionErrors.assertTrue("value is corect or not", !user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,3",
            "4,4,8"
    })
    public void test(int a, int b, int expected){
        AssertionErrors.assertEquals("Is it equal or not ?", expected, a + b);
    }



}
