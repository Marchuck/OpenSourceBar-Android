package com.polidea.getditlibs.model;

import com.google.gson.annotations.SerializedName;
import java.util.Collection;
import lombok.Getter;

public class RData {

    @Getter
    @SerializedName("children")
    Collection<RChildren> gifs;
}
