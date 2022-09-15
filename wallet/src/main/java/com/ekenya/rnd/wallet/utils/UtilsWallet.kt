package com.ekenya.rnd.wallet.utils

import android.Manifest
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.ContactsContract
import android.provider.MediaStore
import android.text.method.PasswordTransformationMethod
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.widget.*
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.ekenya.rnd.common.abstractions.BaseDaggerFragment
import com.ekenya.rnd.common.auth.AuthResult
import com.ekenya.rnd.common.dialogs.dialog_confirm.ConfirmDialogCallBacks
import com.ekenya.rnd.wallet.R
import com.google.android.material.appbar.MaterialToolbar
import es.dmoral.toasty.Toasty
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

fun Activity.loadActivity(goToClass: Class<*>) {
    startActivity(Intent(this, goToClass))
}

fun View.toasty(goToClass: String) {
    Timber.e(goToClass)
}

fun getHasMapData(): HashMap<String, String> {
    var hashMap: java.util.HashMap<String, String> = HashMap()
    hashMap["UserDan"] = "Ajay"
    return hashMap
}

fun MaterialToolbar.setBackButton(
    title: Int,
    context: Activity,
    color: Int? = Color.WHITE,
    setNavIcon: Int = R.drawable.ic_arrow_back_wallet,
    action: (() -> Unit)? = null
) {
    this.setNavigationIcon(setNavIcon)
    this.setTitle(title)
    this.setTitleTextColor(color!!)
    this.setNavigationOnClickListener { view1: View? ->
        if (action == null) {
            context.onBackPressed()
        } else {
            action.invoke()
        }
    }
}

fun MaterialToolbar.setBackButton(
    title: String,
    context: Activity,
    color: Int? = Color.WHITE,
    setNavIcon: Int = R.drawable.ic_arrow_back_wallet,
    action: (() -> Unit)? = null
) {
    this.setNavigationIcon(setNavIcon)
    this.title = title
    this.setTitleTextColor(color!!)
    this.setNavigationOnClickListener { view1: View? ->
        if (action == null) {
            context.onBackPressed()
        } else {
            action.invoke()
        }
    }
}

// extension function to convert dp to equivalent pixels
fun Int.dpToPixels(context: Context): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    context.resources.displayMetrics
)

fun Fragment.makeCameraPermissionsRequest() {
    requestPermissions(
        arrayOf(Manifest.permission.CAMERA),
        CAPTURE_CAMERA
    )
}

fun Fragment.openCameraForPickingImage(code: Int) {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
        // startActivityForResult(Intent.createChooser(this, getString(R.string.select_file)), code)
        startActivityForResult(this, code)
    }
}

/**
 * Convert Bitmap to file
 */
fun Fragment.convertBitmapToFile(fileName: String, bitmap: Bitmap): Uri {
    // val file = File(requireActivity().cacheDir, fileName)
    /* val file = File(
         Environment.getExternalStorageDirectory().toString() + File.separator + fileName
     )*/
    val file = File(context!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "$fileName")

    Timber.d("FILE !!!!!   ${file.toUri()}")
    Timber.d("FILE !!!!!   ${file.name}")
    try {
        // Convert bitmap to byte array
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos)
        val bitMapData = bos.toByteArray()

        Timber.d("FILE Utils 1  $bitmap")
        Timber.d("FILE Utils 1  $fileName")
        Timber.d("FILE Utils 1  $file")
        if (!file.exists()) {
            file.createNewFile()
            file.mkdir()
        }
        // file.createNewFile()
        Timber.d("11 SUCCESS write the bytes in file")
        // write the bytes in file
        val fos = FileOutputStream(file)
        Timber.d("1 SUCCESS write the bytes in file")
        fos.write(bitMapData)
        fos.flush()
        fos.close()
        Timber.d("2 SUCCESS write the bytes in file")
    } catch (e: Exception) {
        Timber.d("IOException  ${e.message}")
        e.printStackTrace()
    }
    Timber.d("FILE Utils  ${file.absoluteFile}")
    return file.toUri()
}

fun String.getFirstLetter(): String {
    val array = this.split(" ")
    return if (array.size == 1) {
        array[0].substring(0, 1)
    } else {
        array[0].substring(0, 1) + array[1].substring(0, 1)
    }
}

// SetAdapter on a spinner
fun AutoCompleteTextView.setUpSpinner(
    arrayResource: Int,
    onItemClick: (
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) -> Unit
) {
    val providers = resources.getStringArray(arrayResource)
    val providerAdapter = ArrayAdapter(
        context,
        R.layout.text_layout_wallet,
        providers
    )
    setAdapter(providerAdapter)
    setOnFocusChangeListener { v, hasFocus ->
    }
    setOnItemClickListener { parent, view, position, id ->
        onItemClick(parent, view, position, id)
    }
}

fun Fragment.toasty(message: String) {
    Toasty.info(requireContext(), message, Toast.LENGTH_SHORT, true).show()
}

fun timber(message: String) {
    Timber.e("{{{{{{{{{{{{{{{{{{{{{{{ $message")
}

fun BaseDaggerFragment.callSuccessFragment(): ConfirmDialogCallBacks {
    val dCallbackCustom = object : ConfirmDialogCallBacks {
        override fun confirm() {
            timber("Confirm")
            lifecycleScope.launch {
                showHideProgress(getString(R.string.sending_request_wallet))
                delay(2000)
                showHideProgress(null)
                findNavController().navigate(R.id.successfulFragmentWallet)
            }
        }

        override fun cancel() {
            timber("cancel")
        }
    }
    return dCallbackCustom
}

val maskTextInTextView = object : PasswordTransformationMethod() {
    override fun getTransformation(source: CharSequence, view: View): CharSequence {
        return PasswordCharSequence(source)
    }

    inner class PasswordCharSequence(private val source: CharSequence) : CharSequence {

        override val length: Int
            get() = source.length

        override fun get(index: Int): Char = 'X'

        override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
            return source.subSequence(startIndex, endIndex)
        }
    }
}

fun Fragment.setTransparentBackground() {
    val bottomSheet = (requireView().parent as View)
    bottomSheet.apply {
        backgroundTintMode = PorterDuff.Mode.CLEAR
        backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        setBackgroundColor(Color.TRANSPARENT)
    }
}

fun Fragment.contactsLoader(): Loader<Cursor?> {
    val contactsUri: Uri =
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI // The content URI of the phone contacts

    val projection = arrayOf(
        ContactsContract.Contacts.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )

    val selection: String? = null // Selection criteria
    val selectionArgs = arrayOf<String>() // Selection criteria
    val sortOrder: String =
        ContactsContract.Contacts.DISPLAY_NAME + " COLLATE NOCASE ASC" // The sort order for the returned rows
    return CursorLoader(
        requireContext(),
        contactsUri,
        projection,
        selection,
        selectionArgs,
        sortOrder
    )
}

fun BaseDaggerFragment.seTfragmentResultListener(createSuccessBundle: Bundle) {
    setFragmentResultListener("requestKey") { _, bundle ->
        // We use a String here, but any type that can be put in a Bundle is supported
        val result: AuthResult = bundle.get("authResult") as AuthResult

        when (result) {
            AuthResult.AUTH_SUCCESS -> {
                findNavController().navigate(
                    R.id.successfulFragmentWallet,
                    createSuccessBundle
                )
            }
            AuthResult.AUTH_ERROR -> {
                // handle error
            }
        }
    }
}

fun createSuccessBundle(
    title: String,
    subTitle: String,
    cardTitle: String,
    cardContent: String,
    hashMap: HashMap<String, String>
): Bundle {
    val bundle = Bundle()
    bundle.putString("title", title)
    bundle.putString("subtitle", subTitle)
    bundle.putString("cardTitle", cardTitle)
    bundle.putString("cardContent", cardContent)
    bundle.putSerializable("content", hashMap)
    return bundle
}

fun Fragment.contactDataList(data: Intent?): String {
    val contactData = data!!.data
    var number = ""
    val cursor: Cursor =
        requireActivity().contentResolver
            .query(contactData!!, null, null, null, null)!!
    cursor.moveToFirst()
    val hasPhone =
        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.HAS_PHONE_NUMBER))
    val contactId =
        cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
    if (hasPhone == "1") {
        val phones: Cursor = requireActivity().contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +
                " = " + contactId,
            null,
            null
        )!!
        while (phones.moveToNext()) {
            number =
                phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                    .replace("[-() ]".toRegex(), "")
        }
        phones.close()

        timber(number)

        // Do something with number
    } else {
        timber(number)
        toasty("This contact has no phone number")
        number = ""
    }
    cursor.close()

    return number
}

fun Context.showDialog(
    title: String = "",
    description: String = "",
    positiveButtonFunction: (() -> Unit)? = null,
    negativeButtonFunction: (() -> Unit)? = null
) {
    val dialog = Dialog(this, R.style.Theme_Dialog)
    dialog.window?.requestFeature(Window.FEATURE_NO_TITLE) // if you have blue line on top of your dialog, you need use this code
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(false)
//        val inflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val binding = CustomDialogLayoutBinding.inflate(inflater)
//        dialog.setContentView(binding.root)
    dialog.setContentView(R.layout.dialog_custom_unlink_account_wallet_layout)
    val dialogTitle = dialog.findViewById(R.id.tvUnlinkAccount) as TextView
    val dialogDescription =
        dialog.findViewById(R.id.tv_would_you_like_to_unlink_your_account) as TextView
    val dialogPositiveButton = dialog.findViewById(R.id.btnConfirm) as TextView
    val dialogNegativeButton = dialog.findViewById(R.id.btnCancel) as TextView
    dialogTitle.text = title
    dialogDescription.text = description
    // titleOfPositiveButton?.let { dialogPositiveButton.text = it } ?: dialogPositiveButton.makeGone()
    // titleOfNegativeButton?.let { dialogNegativeButton.text = it } ?: dialogNegativeButton.makeGone()
    dialogPositiveButton.setOnClickListener {
        positiveButtonFunction?.invoke()
        dialog.dismiss()
    }
    dialogNegativeButton.setOnClickListener {
        negativeButtonFunction?.invoke()
        dialog.dismiss()
    }
    dialog.show()
}

fun getLisOfIntTotal(items: ArrayList<Int>): Int {
    var totalPrice = 0
    for (i in items.indices) {
        totalPrice += items[i]
    }
    return totalPrice
}
