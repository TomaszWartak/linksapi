package pl.dev4lazy.linksapi.shorts;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.dev4lazy.linksapi.utils.*;
import pl.dev4lazy.linksapi.utils.exceptions.InvalidPasswordException;
import pl.dev4lazy.linksapi.utils.exceptions.LinkNotFoundException;

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
    // V2
    @Transactional
    public void updateLink( LinkUpdateDto linkUpdateDto) {
        Optional<Link> linkToUpdateOptional = linkRepository.findById( linkUpdateDto.getId() );
        linkToUpdateOptional.orElseThrow( LinkNotFoundException::new );
        // todo: czy tutaj zaktualizuje si??? Bo nie ma jawnego zapisu.
        // cyt.: "Wa??ne jest to, ??eby metoda by??a publiczna i opatrzona adnotacj?? @Transactional,
        // poniewa?? bez niej wywo??anie metody setName() na obiekcie Link nie mia??oby ??adnego efektu w bazie danych."
        linkToUpdateOptional
                .filter( link -> isLinkPasswordCorrect( linkUpdateDto.getPassword(), link.getPassword() ))
                .orElseThrow( InvalidPasswordException::new )
                .setName( linkUpdateDto.getName() );
    }

    public boolean isLinkPasswordCorrect(String passwordFromClient, String passwordFromDb ) {
        return (passwordFromClient != null) && (passwordFromClient.equals( passwordFromDb ));
    }

    // V3
    @Transactional
    void deleteLinkById( String id, String passwordFromClient ) {
        Optional<Link> linkToDeleteOptional = linkRepository.findById( id );
        linkToDeleteOptional.orElseThrow( LinkNotFoundException::new );
        if (linkToDeleteOptional.isPresent()) {
            Link linkToDelete = linkToDeleteOptional
                    .filter( link -> isLinkPasswordCorrect( passwordFromClient, link.getPassword() ) )
                    .orElseThrow( InvalidPasswordException::new );
            linkRepository.delete( linkToDelete );
        }
    }
}
