
package nl.brickx.domain.Models.Gson.ProductInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category_ {

    @SerializedName("Active")
    @Expose
    private Boolean active;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IdInWebShop")
    @Expose
    private Integer idInWebShop;
    @SerializedName("Level")
    @Expose
    private Integer level;
    @SerializedName("ParentId")
    @Expose
    private Integer parentId;
    @SerializedName("Position")
    @Expose
    private Integer position;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdInWebShop() {
        return idInWebShop;
    }

    public void setIdInWebShop(Integer idInWebShop) {
        this.idInWebShop = idInWebShop;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

}
