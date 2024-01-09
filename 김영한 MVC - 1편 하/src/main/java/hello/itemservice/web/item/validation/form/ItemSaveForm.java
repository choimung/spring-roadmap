package hello.itemservice.web.item.validation.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemSaveForm {
    @NotBlank
    public String itemName;

    @NotNull
    @Range(min = 1000, max = 100000)
    public Integer price;

    @NotNull
    @Max(9999)
    public Integer quantity;
}
