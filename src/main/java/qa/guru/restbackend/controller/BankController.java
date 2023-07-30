package qa.guru.restbackend.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import qa.guru.restbackend.domain.LoginInfo;
import qa.guru.restbackend.domain.UserInfo;
import qa.guru.restbackend.exception.InvalidUsernameException;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class BankController {
    private final Map<String, UserInfo> users = Map.of(
            "Oleg", UserInfo.builder().userName("Oleg").build(),
            "Olga", UserInfo.builder().userName("Olga").build(),
            "Sergey", UserInfo.builder().userName("Sergey").build()
    );

    @PostMapping("user/login")
    @ApiOperation("авторизация")
    public UserInfo doLogin(@RequestBody LoginInfo loginInfo) {
        if (loginInfo.getUserName().equals("Oleg")) {
            return UserInfo.builder()
                    .loginDate(new Date())
                    .userName(loginInfo.getUserName())
                    .build();
        } else {
            throw new InvalidUsernameException();
        }
    }

    @GetMapping("user/getAll")
    @ApiOperation("получение всех пользователей")
    public List<UserInfo> getAllUsersInfo() {
        return users.entrySet()
                .stream()
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
}
