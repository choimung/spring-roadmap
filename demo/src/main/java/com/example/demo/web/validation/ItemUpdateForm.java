package com.example.demo.web.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter @Setter
public class ItemUpdateForm {

    @NotNull
    private Long id;

    @NotBlank
    public String itemName;

    @NotNull
    @Range(min = 1000, max = 100000)
    public Integer price;

    public Integer quantity;
}
