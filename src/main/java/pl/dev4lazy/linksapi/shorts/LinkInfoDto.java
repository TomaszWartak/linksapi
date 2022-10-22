package pl.dev4lazy.linksapi.shorts;

public class LinkInfoDto {
    private String id;
    private String name;
    private String targetUrl;
    private String redirectUrl;
    private Long visits;

    public LinkInfoDto(Link link ) {
        this.id = link.getId();
        this.name = link.getName();
        this.targetUrl = link.getTargetUrl();
        this.redirectUrl = link.getRedirectUrl();
        this.visits = link.getVisits();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }
}
