package com.example.bitfit

import com.google.gson.annotations.SerializedName

/**
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class Entry {
    @SerializedName("food")
    var food: String? = null

    @SerializedName("calories")
    var calories: String? = null
}