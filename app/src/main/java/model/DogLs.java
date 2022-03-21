package model;

import com.google.gson.annotations.SerializedName;

public class DogLs {

    @SerializedName("bred_for")
    private String bred_for;

    @SerializedName("name")
    private String name;

    public String getBred_for() {
        return bred_for;
    }

    public void setBred_for(String bred_for) {
        this.bred_for = bred_for;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @SerializedName("url")
    private String url;

    public DogLs(String bred_for, String name, String url) {
        this.bred_for = bred_for;
        this.name = name;
        this.url = url;
    }
}
