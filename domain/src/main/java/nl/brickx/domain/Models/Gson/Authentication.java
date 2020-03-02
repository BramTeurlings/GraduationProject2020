package nl.brickx.domain.Models.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication {

    @Expose
    @SerializedName("title")
    private String title;

    public Authentication() {
    }

    public Authentication(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
