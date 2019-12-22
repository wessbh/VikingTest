package com.wassimbh.vikingtest.models;

public class Article {

    private String id;
    private String name;
    private String subtitle;
    private String external_link;
    private String created;
    private String thumbnail;

    public Article() {
    }

    public Article(String name, String subtitle, String external_link, String created, String thumbnail) {
        this.name = name;
        this.subtitle = subtitle;
        this.external_link = external_link;
        this.created = created;
        this.thumbnail = thumbnail;
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

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getExternal_link() {
        return external_link;
    }

    public void setExternal_link(String external_link) {
        this.external_link = external_link;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", external_link='" + external_link + '\'' +
                ", created='" + created + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
