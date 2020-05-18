package nl.brickx.domain.Models;

import java.util.ArrayList;
import java.util.List;

public class StockMutationDto {

    String ProductScan;
    String FromLocationScanTag;
    double Quantity;
    String Reason;
    List<BatchNumberSelectionModelDto> SelectedSerialNumbers = new ArrayList<>();

    public StockMutationDto() {
    }

    public StockMutationDto(String productScan, String fromLocationScanTag, double quantity, String reason, List<BatchNumberSelectionModelDto> selectedSerialNumbers) {
        ProductScan = productScan;
        FromLocationScanTag = fromLocationScanTag;
        Quantity = quantity;
        Reason = reason;
        SelectedSerialNumbers = selectedSerialNumbers;
    }

    public StockMutationDto(String productScan, String fromLocationScanTag, double quantity, String reason) {
        ProductScan = productScan;
        FromLocationScanTag = fromLocationScanTag;
        Quantity = quantity;
        Reason = reason;
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

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public List<BatchNumberSelectionModelDto> getSelectedSerialNumbers() {
        return SelectedSerialNumbers;
    }

    public void setSelectedSerialNumbers(List<BatchNumberSelectionModelDto> selectedSerialNumbers) {
        SelectedSerialNumbers = selectedSerialNumbers;
    }
}
