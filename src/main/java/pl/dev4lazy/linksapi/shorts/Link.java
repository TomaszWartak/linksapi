package pl.dev4lazy.linksapi.shorts;

import javax.persistence.*;

@Entity
public class Link {
    @Id
    private String id;
    private String name;
    private String targetUrl;
    private String redirectUrl;
    private Long visits;
    // V2
    private String password;

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

    // V2
    public String getPassword() {
        return password;
    }
    // V2
    public void setPassword(String password) {
        this.password = password;
    }
}
