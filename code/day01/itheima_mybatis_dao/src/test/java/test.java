import com.itheima.domain.User;
import com.itheima.service.UserService;
import com.itheima.service.impl.UserServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class test {

    UserService userService = new UserServiceImpl();

    @Test
    public void test1() throws IOException {
        User user = new User();
        user.setId(13);
        user.setUsername("dalong");
        user.setPassword("hhh");
        List<User> userList = userService.find();
        System.out.println(userList);
        userService.add(user);
        userService.del(14);
        userService.up(user);
    }

}
