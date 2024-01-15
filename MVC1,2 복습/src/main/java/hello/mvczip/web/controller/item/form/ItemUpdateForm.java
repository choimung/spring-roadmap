package hello.mvczip.web.controller.item.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
public class ItemUpdateForm {

    @NotNull
    private long id;

    @NotBlank
    private String itemName;
    
    @NotNull
    @Range(min = 1000, max = 100000)
    private Integer price;

    private Integer quantity;
}
