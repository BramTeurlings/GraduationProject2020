package nl.brickx.domain.Models.Gson.UserInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nl.brickx.domain.Models.Gson.ApiUserRightsEnum;

public class ApiUserRight {

    @SerializedName("Access")
    @Expose
    private Boolean access;

    @SerializedName("ApiRight")
    @Expose
    private ApiUserRightsEnum apiRight;

    @SerializedName("Id")
    @Expose
    private Integer id;

    public Boolean getAccess() {
        return access;
    }

    public void setAccess(Boolean access) {
        this.access = access;
    }

    public ApiUserRightsEnum getApiRight() {
        return apiRight;
    }

    public void setApiRight(ApiUserRightsEnum apiRight) {
        this.apiRight = apiRight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}