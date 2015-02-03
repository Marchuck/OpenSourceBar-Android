package com.polidea.getditlibs.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gif {

    @SerializedName("title")
    String title;

    @SerializedName("thumbnail")
    String thumbnail;

    @SerializedName("permalink")
    String permalink;

    @SerializedName("url")
    String url;

}
