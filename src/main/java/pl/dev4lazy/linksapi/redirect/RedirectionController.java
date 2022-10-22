package pl.dev4lazy.linksapi.redirect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.dev4lazy.linksapi.shorts.LinkInfoDto;
import pl.dev4lazy.linksapi.shorts.LinkService;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/redir")
public class RedirectionController {
    private LinkService shortLinkService;

    public RedirectionController(LinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @GetMapping("/{id}")
    ResponseEntity<?> redirectToLink(@PathVariable String id) {
        return shortLinkService.getLinkInfoDtoWithIncrementedVisitsById(id)
                .map( LinkInfoDto::getTargetUrl )
                .map( targetUrl -> ResponseEntity
                        .status(HttpStatus.FOUND)
                        .location(URI.create(targetUrl))
                        .build())
                .orElse(ResponseEntity.notFound().build());
    }

}

