package nl.brickx.domain.Models.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TestAuthentication {

    @Expose
    @SerializedName("title")
    private String title;

    public TestAuthentication() {
    }

    public TestAuthentication(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
