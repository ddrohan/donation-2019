package ie.wit.main

import android.app.Application
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ie.wit.api.DonationService
import ie.wit.models.DonationModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class DonationApp : Application(), AnkoLogger {

    lateinit var donationService: DonationService
    var donations = ArrayList<DonationModel>()

    lateinit var auth: FirebaseAuth
    lateinit var database: DatabaseReference

    override fun onCreate() {
        super.onCreate()
        info("Donation App started")
        donationService = DonationService.create()
        info("Donation Service Created")
    }
}

