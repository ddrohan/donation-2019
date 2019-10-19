package ie.wit.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DonationModel(var id: Long = 0,
                         val paymenttype: String = "N/A",
                         val amount: Int = 0,
                         val message: String = "a message") : Parcelable

