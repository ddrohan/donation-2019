package ie.wit.utils

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.tasks.OnFailureListener
import ie.wit.R
import ie.wit.main.DonationApp
import java.io.ByteArrayOutputStream
import java.lang.Exception

fun createLoader(activity: FragmentActivity) : AlertDialog {
    val loaderBuilder = AlertDialog.Builder(activity)
        .setCancelable(true) // 'false' if you want user to wait
        .setView(R.layout.loading)
    var loader = loaderBuilder.create()
    loader.setTitle(R.string.app_name)
    loader.setIcon(R.mipmap.ic_launcher_homer_round)

    return loader
}

fun showLoader(loader: AlertDialog, message: String) {
    if (!loader.isShowing()) {
        loader.setTitle(message)
        loader.show()
    }
}

fun hideLoader(loader: AlertDialog) {
    if (loader.isShowing())
        loader.dismiss()
}

fun uploadImageView(app: DonationApp, imageView: ImageView) {
    // Get the data from an ImageView as bytes
    val uid = app.auth.currentUser!!.uid
    val imageRef = app.storage.child("photos").child("${uid}.jpg")
    val bitmap = (imageView.drawable as BitmapDrawable).bitmap
    val baos = ByteArrayOutputStream()

    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
    val data = baos.toByteArray()

    var uploadTask = imageRef.putBytes(data)
//    uploadTask.addOnFailureListener { object : OnFailureListener {
//        override fun onFailure(error: Exception) {
//            Log.v("Donation", "uploadTask.exception" + error)
//        }
//    }
//    }.addOnSuccessListener {
//        uploadTask.continueWithTask { task -> imageRef.downloadUrl
//        }.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                val downloadUri = task.result
//
//                val url = downloadUri!!.toString()
//                Log.v("Donation", "uploadTask url" + url)
//                Log.v("seeThisUri", downloadUri.toString())// This is the one you should store
//            }
//        }
//    }
}

