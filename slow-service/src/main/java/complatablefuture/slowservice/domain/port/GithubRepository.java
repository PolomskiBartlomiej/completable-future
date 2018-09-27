package complatablefuture.slowservice.domain.port;


import complatablefuture.slowservice.domain.model.Follower;
import complatablefuture.slowservice.domain.model.User;

import java.util.List;

public interface GithubRepository {

    User findUser(String user);
    List<Follower> findUserFollowers(String user);
}
