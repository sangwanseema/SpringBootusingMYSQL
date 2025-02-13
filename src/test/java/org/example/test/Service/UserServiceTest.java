package org.example.test.Service;

import org.example.test.Respository.UserEntryRepository;
import org.example.test.Services.UserService;
import org.example.test.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserEntryRepository userEntryRepository;
    @Autowired
    private UserService userService;

    @ParameterizedTest
    // one method is treated as single test
    @CsvSource({
            "seemasangwan",
            "ritu",
            "Rohit",

    })

    public void testuserFindByUserName(String userName)
    {
       //assertEquals(6,3+3);
       // null pointer exception
        // because user repository is null
        // because spring application context start ni hua hai
        //components bane hi ni hai
        //to resolve this use spingbboottest
        // to start the complete application context
        //User user=userEntryRepository.findByUserName(userName);
       assertNotNull(userEntryRepository.findByUserName(userName),"Failed for :"+userName);
      // assertTrue(5>3);
     //  assertTrue(user.getDemoEntries().isEmpty()!=true);

    }

  /*   @ParameterizedTest
  @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewUser(User user) {
         //assertEquals(6,3+3);
         // null pointer exception
         // because user repository is null
         // because spring application context start ni hua hai
         //components bane hi ni hai
         //to resolve this use spingbboottest
         // to start the complete application context
         //User user=userEntryRepository.findByUserName(userName);
         //    assertNotNull(userEntryRepository.findByUserName(userName),"Failed for :"+userName);
         // assertTrue(5>3);
         //  assertTrue(user.getDemoEntries().isEmpty()!=true);
         assertTrue(userService.saveNewUser(user));
     }

   */
    @Test
    public void testAdd()
    {
        assertEquals(6,3+3);
    }
@ParameterizedTest
@CsvSource(
        {
                "1,1,2",
                "2,10,12",
                "3,3,6"
        }
)
    public void test(int a ,int b,int expected)
    {
        assertEquals(expected,a+b);
    }
}

