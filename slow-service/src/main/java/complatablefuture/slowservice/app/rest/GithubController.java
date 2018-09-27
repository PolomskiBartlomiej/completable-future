package complatablefuture.slowservice.app.rest;

import complatablefuture.slowservice.domain.GithubService;
import complatablefuture.slowservice.domain.model.Follower;
import complatablefuture.slowservice.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService service;

    @GetMapping("/{user}")
    User getUser(@PathVariable("user") String user) {
        return service.findUser(user);
    }

    @GetMapping("/{user}/followers")
    List<Follower> getUserFollowes(@PathVariable("user") String user) {
        return service.findUserFollowers(user);
    }

}