package nl.brickx.domain.Models;

public class BoolResultDto {

    Boolean Result;
    String Description;

    public BoolResultDto() {
    }

    public BoolResultDto(Boolean result, String description) {
        Result = result;
        Description = description;
    }

    public Boolean getResult() {
        return Result;
    }

    public void setResult(Boolean result) {
        Result = result;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
