package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DonationModel(
    var _id: String = "N/A",
    val paymenttype: String = "N/A",
    val amount: Int = 0,
    val message: String = "a message",
    val upvotes: Int = 0,
    val email: String = "joe@bloggs.com")
                        : Parcelable


