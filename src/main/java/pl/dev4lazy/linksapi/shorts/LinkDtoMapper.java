package pl.dev4lazy.linksapi.shorts;

public class LinkDtoMapper {

    static Link map( LinkCreateDto linkCreateDto) {
        Link link = new Link();
        link.setName(linkCreateDto.getName());
        link.setTargetUrl(linkCreateDto.getTargetUrl());
        return link;
    }

    static LinkInfoDto map(Link link ) {
        return new LinkInfoDto( link );
    }
}
