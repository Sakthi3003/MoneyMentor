package model;

public class Resource {
    private Long Id;
    private String title;
    private String content;
    private String link;
    private ResourceCategory category;

    public Resource() {
    }

    public Resource(Long id, String title, String content, String link, ResourceCategory category) {
        Id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.category = category;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ResourceCategory getCategory() {
        return category;
    }

    public void setCategory(ResourceCategory category) {
        this.category = category;
    }
}
