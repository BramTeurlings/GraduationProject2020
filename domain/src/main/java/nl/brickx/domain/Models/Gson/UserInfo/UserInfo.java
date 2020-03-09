package nl.brickx.domain.Models.Gson.UserInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("GetUserInfoResult")
    @Expose
    GetUserInfoResult userInfoResult;

    public UserInfo(GetUserInfoResult userInfoResult) {
        this.userInfoResult = userInfoResult;
    }

    public UserInfo() {
    }

    public GetUserInfoResult getUserInfoResult() {
        return userInfoResult;
    }

    public void setUserInfoResult(GetUserInfoResult userInfoResult) {
        this.userInfoResult = userInfoResult;
    }
}

