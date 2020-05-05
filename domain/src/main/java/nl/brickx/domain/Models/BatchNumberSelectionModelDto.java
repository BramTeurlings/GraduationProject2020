package nl.brickx.domain.Models;

public class BatchNumberSelectionModelDto {

    String BatchNumber;

    public BatchNumberSelectionModelDto(String batchNumber) {
        this.BatchNumber = batchNumber;
    }

    public BatchNumberSelectionModelDto() {
    }

    public String getBatchNumber() {
        return BatchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.BatchNumber = batchNumber;
    }
}
