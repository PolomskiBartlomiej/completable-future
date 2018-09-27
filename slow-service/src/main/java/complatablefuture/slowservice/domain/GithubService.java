package complatablefuture.slowservice.domain;

import complatablefuture.slowservice.domain.model.Follower;
import complatablefuture.slowservice.domain.model.User;
import complatablefuture.slowservice.domain.port.GithubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubRepository repository;

    public User findUser(String user) {
        return repository.findUser(user);
    }

    public List<Follower> findUserFollowers(String user) {
        return repository.findUserFollowers(user);
    }

}
