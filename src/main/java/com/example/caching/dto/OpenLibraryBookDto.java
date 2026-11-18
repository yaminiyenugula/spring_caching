
package com.example.caching.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

//Tells Jackson to safely ignore any extra fields in the JSON response that are not explicitly defined in this Java class,
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryBookDto {

    private String title;

    @JsonProperty("author_name")
    private List<String> authorName;

    @JsonProperty("first_publish_year")
    private Integer firstPublishYear;

    private String key;

    @JsonProperty("cover_i")
    private Integer coverId;

    public OpenLibraryBookDto() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthorName() {
        return authorName;
    }

    public void setAuthorName(List<String> authorName) {
        this.authorName = authorName;
    }

    public Integer getFirstPublishYear() {
        return firstPublishYear;
    }

    public void setFirstPublishYear(Integer firstPublishYear) {
        this.firstPublishYear = firstPublishYear;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getCoverId() {
        return coverId;
    }

    public void setCoverId(Integer coverId) {
        this.coverId = coverId;
    }
}
