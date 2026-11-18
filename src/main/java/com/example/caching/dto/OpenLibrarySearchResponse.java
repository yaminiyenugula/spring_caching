
package com.example.caching.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

 
//Tells Jackson to ignore extra fields in the API response that aren't defined in this class so your application doesn't crash.
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibrarySearchResponse {

    private Integer start;


    //Allows flexibility during JSON parsing
    @JsonAlias({"num_found", "numFound"})
    private Integer numFound;
    
    //It maps the JSON array named "docs" into a Java list containing your previously defined OpenLibraryBookDto book objects.
    private List<OpenLibraryBookDto> docs;

    public OpenLibrarySearchResponse() {
    }


    //Provide the public channels for frameworks (like Spring and Jackson) to read from and write to these fields.
    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public List<OpenLibraryBookDto> getDocs() {
        return docs;
    }

    public void setDocs(List<OpenLibraryBookDto> docs) {
        this.docs = docs;
    }
}
