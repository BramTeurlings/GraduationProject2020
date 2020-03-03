package nl.brickx.domain.Models.Gson;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetUserInfoResult {

    @SerializedName("ApiUserRights")
    @Expose
    private List<ApiUserRight> apiUserRights = null;

    @SerializedName("Id")
    @Expose
    private Integer id;

    @SerializedName("UserName")
    @Expose
    private String userName;

    public List<ApiUserRight> getApiUserRights() {
        return apiUserRights;
    }

    public void setApiUserRights(List<ApiUserRight> apiUserRights) {
        this.apiUserRights = apiUserRights;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}