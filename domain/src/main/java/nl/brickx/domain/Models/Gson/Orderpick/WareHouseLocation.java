
package nl.brickx.domain.Models.Gson.Orderpick;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WareHouseLocation {

    @SerializedName("BulkLocation")
    @Expose
    private Boolean bulkLocation;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("IncomingLocation")
    @Expose
    private Boolean incomingLocation;
    @SerializedName("LevelName")
    @Expose
    private String levelName;
    @SerializedName("LevelPriority")
    @Expose
    private Integer levelPriority;
    @SerializedName("LocationName")
    @Expose
    private String locationName;
    @SerializedName("LocationPriority")
    @Expose
    private Integer locationPriority;
    @SerializedName("PickLocation")
    @Expose
    private Boolean pickLocation;
    @SerializedName("ReturnLocation")
    @Expose
    private Boolean returnLocation;
    @SerializedName("RowName")
    @Expose
    private String rowName;
    @SerializedName("RowPriority")
    @Expose
    private Integer rowPriority;
    @SerializedName("ScanLocationTag")
    @Expose
    private String scanLocationTag;
    @SerializedName("ShelveName")
    @Expose
    private String shelveName;
    @SerializedName("ShelvePriority")
    @Expose
    private Integer shelvePriority;
    @SerializedName("TransferLocation")
    @Expose
    private Boolean transferLocation;

    public Boolean getBulkLocation() {
        return bulkLocation;
    }

    public void setBulkLocation(Boolean bulkLocation) {
        this.bulkLocation = bulkLocation;
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

    public Boolean getIncomingLocation() {
        return incomingLocation;
    }

    public void setIncomingLocation(Boolean incomingLocation) {
        this.incomingLocation = incomingLocation;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getLevelPriority() {
        return levelPriority;
    }

    public void setLevelPriority(Integer levelPriority) {
        this.levelPriority = levelPriority;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Integer getLocationPriority() {
        return locationPriority;
    }

    public void setLocationPriority(Integer locationPriority) {
        this.locationPriority = locationPriority;
    }

    public Boolean getPickLocation() {
        return pickLocation;
    }

    public void setPickLocation(Boolean pickLocation) {
        this.pickLocation = pickLocation;
    }

    public Boolean getReturnLocation() {
        return returnLocation;
    }

    public void setReturnLocation(Boolean returnLocation) {
        this.returnLocation = returnLocation;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

    public Integer getRowPriority() {
        return rowPriority;
    }

    public void setRowPriority(Integer rowPriority) {
        this.rowPriority = rowPriority;
    }

    public String getScanLocationTag() {
        return scanLocationTag;
    }

    public void setScanLocationTag(String scanLocationTag) {
        this.scanLocationTag = scanLocationTag;
    }

    public String getShelveName() {
        return shelveName;
    }

    public void setShelveName(String shelveName) {
        this.shelveName = shelveName;
    }

    public Integer getShelvePriority() {
        return shelvePriority;
    }

    public void setShelvePriority(Integer shelvePriority) {
        this.shelvePriority = shelvePriority;
    }

    public Boolean getTransferLocation() {
        return transferLocation;
    }

    public void setTransferLocation(Boolean transferLocation) {
        this.transferLocation = transferLocation;
    }

}
