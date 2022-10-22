package pl.dev4lazy.linksapi.shorts;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LinkService {
    private LinkRepository linkRepository;

    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }
    @Transactional
    public Link saveLink(LinkCreateDto linkCreateDto) {
        String randomId;
        do {
            randomId = ShortlinkValueGenerator.generateShortLinkValue();
        } while (linkRepository.existsById( randomId ) );
        Link linkToSave = LinkDtoMapper.map(linkCreateDto);
        linkToSave.setId( randomId );
        linkToSave.setRedirectUrl( buildRedirectUrlFromId( linkToSave.getId() ) );
        linkToSave.setVisits(0L);
        return linkRepository.save(linkToSave);
    }

    private static String buildRedirectUrlFromId( String id ) {
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/redir/{id}")
                .buildAndExpand(id)
                .toUriString();
    }

    public Optional<LinkInfoDto> getLinkInfoDtoById(String id ){
        return linkRepository.findById( id )
            .map( LinkDtoMapper::map );
    }

    @Transactional
    public Optional<LinkInfoDto> getLinkInfoDtoWithIncrementedVisitsById(String id ) {
        Optional<Link> linkOptional = linkRepository.findById( id );
        linkOptional.ifPresent( link -> link.setVisits( link.getVisits()+1 ));
        linkOptional.ifPresent( link -> linkRepository.save(link) );
        return linkOptional.map( LinkDtoMapper::map );
    }

}
