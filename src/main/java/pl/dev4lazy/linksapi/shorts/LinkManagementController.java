package pl.dev4lazy.linksapi.shorts;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.dev4lazy.linksapi.utils.LinkCreateDto;
import pl.dev4lazy.linksapi.utils.LinkInfoDto;
import pl.dev4lazy.linksapi.utils.LinkUpdateDto;
import pl.dev4lazy.linksapi.utils.exceptions.InvalidPasswordException;
import pl.dev4lazy.linksapi.utils.exceptions.LinkNotFoundException;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/links")
public class LinkManagementController {
    private LinkService shortLinkService;

    public LinkManagementController(LinkService shortLinkService) {
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
    ResponseEntity<LinkInfoDto> getLinkInfo(@PathVariable String id) {
        return shortLinkService
                .getLinkInfoDtoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    ResponseEntity<LinkInfoDto> updateLink(
            @RequestBody LinkUpdateDto linkUpdateDto,
            @PathVariable String id) {
        linkUpdateDto.setId( id );
        try {
            shortLinkService.updateLink( linkUpdateDto );
            return ResponseEntity.noContent().build();
        } catch (LinkNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("Reason", e.getMessage())
                    .build();
        }
    }

}
