package com.digital.coffeeorderapp.dto.request;

import com.digital.coffeeorderapp.dto.OrderItemDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    @NotNull
    private Long coffeeShopId;

    @NotNull
    private Long customerId;

    @NotEmpty
    private List<OrderItemDto> orderItemDto;
}
