package pl.dev4lazy.linksapi.shorts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/links")
public class LinkController {
    private LinkService shortLinkService;

    public LinkController(LinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @PostMapping
    ResponseEntity<Link> saveLink(@RequestBody LinkCreateDto linkCreateDtoToSave) {
        Link linkSaved = shortLinkService.saveLink(linkCreateDtoToSave);
        URI savedLinkUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(linkSaved.getId())
                .toUri();
        return ResponseEntity.created(savedLinkUri).body(linkSaved);
    }

    @GetMapping("/{id}")
    ResponseEntity<LinkInfoDto> getLinkinfo(@PathVariable String id) {
        return shortLinkService
                .getLinkInfoDtoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
