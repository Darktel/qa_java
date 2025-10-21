package praktikum.courier;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Courier {
    private final String login;
    private final String password;
    private final String firstName;
}
