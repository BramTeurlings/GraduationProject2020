package nl.brickx.domain.Models;

import java.io.Serializable;
import java.util.List;

public class StockTransferDto implements Serializable {

    String ProductScan;
    String FromLocationScanTag;
    String ToLocationScanTag;
    Double Quantity;

    List<BatchNumberSelectionModelDto> SelectedSerialNumbers;

    public StockTransferDto(List<BatchNumberSelectionModelDto> selectedSerialNumbers) {
        SelectedSerialNumbers = selectedSerialNumbers;
    }

    public StockTransferDto() {
    }

    public String getProductScan() {
        return ProductScan;
    }

    public void setProductScan(String productScan) {
        ProductScan = productScan;
    }

    public String getFromLocationScanTag() {
        return FromLocationScanTag;
    }

    public void setFromLocationScanTag(String fromLocationScanTag) {
        FromLocationScanTag = fromLocationScanTag;
    }

    public String getToLocationScanTag() {
        return ToLocationScanTag;
    }

    public void setToLocationScanTag(String toLocationScanTag) {
        ToLocationScanTag = toLocationScanTag;
    }

    public Double getQuantity() {
        return Quantity;
    }

    public void setQuantity(Double quantity) {
        Quantity = quantity;
    }

    public List<BatchNumberSelectionModelDto> getSelectedSerialNumbers() {
        return SelectedSerialNumbers;
    }

    public void setSelectedSerialNumbers(List<BatchNumberSelectionModelDto> selectedSerialNumbers) {
        SelectedSerialNumbers = selectedSerialNumbers;
    }
}
