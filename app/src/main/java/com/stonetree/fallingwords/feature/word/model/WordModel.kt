package com.stonetree.fallingwords.feature.word.model

import com.google.gson.annotations.SerializedName

class WordModel {

    @SerializedName("text_eng")
    var english: String? = null

    @SerializedName("text_spa")
    var spanish: String? = null
}