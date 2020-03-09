
package nl.brickx.domain.Models.Gson.ProductInfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("Category")
    @Expose
    private Category_ category;
    @SerializedName("Id")
    @Expose
    private Integer id;

    public Category_ getCategory() {
        return category;
    }

    public void setCategory(Category_ category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
