package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.Authorities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    private final Map<User, List<Authorities>> usersAuthoritiesList;

    public UserRepository() {
        usersAuthoritiesList = new HashMap<>();
        List<Authorities> fullAccess = new ArrayList<>();
        fullAccess.add(Authorities.READ);
        fullAccess.add(Authorities.WRITE);
        fullAccess.add(Authorities.DELETE);
        List<Authorities> rwAccess = new ArrayList<>();
        rwAccess.add(Authorities.READ);
        rwAccess.add(Authorities.WRITE);
        List<Authorities> rAccess = new ArrayList<>();
        rAccess.add(Authorities.READ);

        User user1 = new User("Igor", "123");
        User user2 = new User("Roman", "456");
        User user3 = new User("Ivan", "789");

        usersAuthoritiesList.put(user2, fullAccess);
        usersAuthoritiesList.put(user1, rwAccess);
        usersAuthoritiesList.put(user3, rAccess);
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> resp = new ArrayList<>();
        for (Map.Entry<User, List<Authorities>> pair : usersAuthoritiesList.entrySet()) {
            if (pair.getKey().getUser().equals(user) && pair.getKey().getPassword().equals(password))  {
                resp = pair.getValue();
                break;
            }
        }
        return resp;
    }
}
