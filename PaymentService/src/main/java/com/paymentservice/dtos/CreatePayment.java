package com.paymentservice.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CreatePayment {
    @NotNull
    @Min(1)
    private Integer amount;
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
