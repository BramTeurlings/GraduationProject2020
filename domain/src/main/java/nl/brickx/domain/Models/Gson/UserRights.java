
package nl.brickx.domain.Models.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import nl.brickx.domain.Models.Gson.UserInfo.GetUserInfoResult;

public class UserRights {

    @SerializedName("GetUserInfoResult")
    @Expose
    private GetUserInfoResult getUserInfoResult;

    public GetUserInfoResult getGetUserInfoResult() {
        return getUserInfoResult;
    }

    public void setGetUserInfoResult(GetUserInfoResult getUserInfoResult) {
        this.getUserInfoResult = getUserInfoResult;
    }

}