package pl.dev4lazy.linksapi.utils;

public class LinkCreateDto {

    private String name;
    private String targetUrl;
    // V2
    private String password;

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

    // V2
    public String getPassword() {
        return password;
    }
    // V2
    public void setPassword(String password) {
        this.password = password;
    }
}
