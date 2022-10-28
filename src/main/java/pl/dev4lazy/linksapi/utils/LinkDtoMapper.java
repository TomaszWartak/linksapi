package pl.dev4lazy.linksapi.utils;

import pl.dev4lazy.linksapi.shorts.Link;

public class LinkDtoMapper {

    public static Link map(LinkCreateDto linkCreateDto) {
        Link link = new Link();
        link.setName( linkCreateDto.getName() );
        link.setTargetUrl(linkCreateDto.getTargetUrl() );
        // V2
        link.setPassword( linkCreateDto.getPassword() );
        return link;
    }

    public static LinkInfoDto map(Link link ) {
        return new LinkInfoDto( link );
    }
}
