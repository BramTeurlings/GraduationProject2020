package nl.brickx.domain.Models.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication {

    @Expose
    @SerializedName("CheckApiKeyResult")
    private Boolean apiKey;

    public Authentication() {
    }

    public Authentication(Boolean apiKey) {
        this.apiKey = apiKey;
    }

    public Boolean getApiKey() {
        return apiKey;
    }

    public void setApiKey(Boolean apiKey) {
        this.apiKey = apiKey;
    }
}
