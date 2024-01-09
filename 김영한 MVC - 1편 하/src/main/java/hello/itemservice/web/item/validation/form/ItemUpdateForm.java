package hello.itemservice.web.item.validation.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    public String itemName;

    @NotNull
    @Range(min = 1000, max = 100000)
    public Integer price;

    // 수정에서는 수량을 자유롭게 변경할 수 있다.
    public Integer quantity;
}
