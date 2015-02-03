package com.polidea.getditlibs.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public class RResponse {

    @Getter
    @SerializedName("data")
    RData data;
}
