package model;

public class Resource {
    private Long id;
    private String title;
    private String content;
    private String link;
    private ResourceCategory category;

    public Resource() {
    }

    public Resource(Long id, String title, String content, String link, ResourceCategory category) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.link = link;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("====================================\n");
        sb.append("Resource ID   : ").append(id).append("\n");
        sb.append("Title         : ").append(title).append("\n");
        sb.append("Category      : ").append(category).append("\n");
        sb.append("Content       : ").append(content).append("\n");
        if (link != null) {
            sb.append("Link          : ").append(link).append("\n");
        }
        sb.append("====================================");
        return sb.toString();
    }


}
