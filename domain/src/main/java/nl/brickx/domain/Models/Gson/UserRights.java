
package nl.brickx.domain.Models.Gson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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