package com.polidea.opensourcebar.rxsample.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(suppressConstructorProperties = true)
@ToString
public class MessageModel {

    @Getter
    private CharSequence phoneNumber;

    @Getter
    private CharSequence messageBody;
}
