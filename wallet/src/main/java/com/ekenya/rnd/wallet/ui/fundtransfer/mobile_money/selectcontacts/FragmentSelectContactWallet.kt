package com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.selectcontacts

import android.Manifest
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ekenya.rnd.wallet.R
import com.ekenya.rnd.wallet.data.ContactListWalletModel
import com.ekenya.rnd.wallet.databinding.FragmentSelectContactWalletBinding
import com.ekenya.rnd.wallet.ui.adapters.listeners.OnContactItemClickedListener
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter.AdapterSelectContactWallet
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter.AddBeneficiaryMobileMoneyWalletAdapter
import com.ekenya.rnd.wallet.ui.fundtransfer.mobile_money.adapter.FrequentMobileMoneyWalletAdapter
import com.ekenya.rnd.wallet.utils.BaseWalletFragment
import com.ekenya.rnd.wallet.utils.setBackButton
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import javax.inject.Inject

class FragmentSelectContactWallet :
    BaseWalletFragment<FragmentSelectContactWalletBinding>(FragmentSelectContactWalletBinding::inflate),
    LoaderManager.LoaderCallbacks<Cursor> {

    // private var selectedcontactListWalletModel: ArrayList<ContactListWalletModel> = ArrayList()

    private val addBeneficiaryMobileMoneyWalletAdapter by lazy {
        AddBeneficiaryMobileMoneyWalletAdapter(
            onContactItemClickedListener
        )
    }

    // init select contact adapter
    private val mAdapter: AdapterSelectContactWallet by lazy {
        AdapterSelectContactWallet(
            onContactSelected = { onContactSelected(it) },
            contains = { contains(it) }
        )
    } // init select contact adapter
    private val frequentMobileMoneyWalletAdapter: FrequentMobileMoneyWalletAdapter by lazy {
        FrequentMobileMoneyWalletAdapter(
            onContactSelected = { onContactSelected(it) },
            contains = { contains(it) }
        )
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        checkContactsPermissions()
        initBindings()
    }

    private fun initBindings() {
        inflateRVs()

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.fragmentSplitSendToMobileMoneyWallet)
        }

        binding.cvEnterNewPhoneNumber.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)

            val view = layoutInflater.inflate(R.layout.item_add_account_mobile_money_wallet, null)

            val btnClose = view.findViewById<Button>(R.id.btnContinue)
            val edtPhoneNumber = view.findViewById<TextInputEditText>(R.id.edtPhoneNumber)

            btnClose.setOnClickListener {
                if (edtPhoneNumber.text.toString().isNotEmpty()) {
                    contactHashSet.add(
                        ContactListWalletModel(
                            titleContactName = "",
                            phoneContact = edtPhoneNumber.text.toString().trim(),
                            initialsContact = ""
                        )
                    )
                    addBeneficiaryMobileMoneyWalletAdapter.submitList(contactHashSet.toList())
                    dialog.dismiss()
                } else {
                    edtPhoneNumber.error = "Enter Phone number"
                }
            }
            // dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }

        binding.clToolBar.toolbar.setBackButton(
            title = "Send to Many",
            context = requireActivity()
        )
    }

    private fun inflateRVs() {
        binding.rvAddBeneficiary.apply {
            adapter = addBeneficiaryMobileMoneyWalletAdapter
            layoutManager =
                object : LinearLayoutManager(context, HORIZONTAL, false) {
                    override fun canScrollVertically(): Boolean = false
                }
            addBeneficiaryMobileMoneyWalletAdapter.notifyDataSetChanged()
            // LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    // check if has permission read contacts
    private fun checkContactsPermissions() {
        // read contacts if has permission
        if (hasPermission(Manifest.permission.READ_CONTACTS)) {
            // initialize the contacts recycler adapter
            initContactsAdapter()
        } else {
            // request  permissions if none
            requestContactsPermission()
        }
    }

    // set up contacts recyclerview
    private fun initContactsAdapter() {
        binding.rvAllContacts.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.rvFrequent.apply {
            adapter = frequentMobileMoneyWalletAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        // start loading contacts
        LoaderManager.getInstance(requireActivity())
            .initLoader(CONTACTS_LOADER_ID, null, this)
    }

    // Add or remove contact to a hashset when received
    private fun onContactSelected(contact: ContactListWalletModel): Boolean {
        // remove if set contains the contact
        if (contactHashSet.contains(contact)) {
            contactHashSet.remove(contact)
        } else {
            // add if it does not
            contactHashSet.add(contact)

            inflateRVs()
        }
        addBeneficiaryMobileMoneyWalletAdapter.submitList(contactHashSet.toList())
        return contactHashSet.contains(contact)
    }

    private fun contains(contact: ContactListWalletModel) = contactHashSet.contains(contact)

    // requests for permissions
    private fun requestContactsPermission() {
        if (shouldShowRequestPermissionRationale(
                Manifest.permission.READ_CONTACTS
            )
        ) {
            // show dialog
            AlertDialog.Builder(requireContext())
                .setTitle("Permission needed")
                .setMessage("This permission is needed for you to add contacts to split your bill")
                .setPositiveButton(
                    "ok"
                ) { _, _ ->
                    requestPermissions(
                        arrayOf(Manifest.permission.READ_CONTACTS),
                        0
                    )
                }
                .setNegativeButton(
                    "cancel"
                ) { dialog, _ -> dialog.dismiss() }
                .create().show()
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.READ_CONTACTS),
                0
            )
        }
    }

    // check if a particular permission is granted
    private fun hasPermission(permission: String) =
        ActivityCompat.checkSelfPermission(
            requireContext(),
            permission
        ) == PackageManager.PERMISSION_GRANTED

    // callback after user interacts with permission dialog
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initContactsAdapter()
            } else {
                Toast.makeText(requireContext(), "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor?> {
        return contactsLoader()
    }

    override fun onLoadFinished(loader: Loader<Cursor?>, data: Cursor?) {
        mAdapter.swapCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor?>) {
        mAdapter.swapCursor(null)
    }

    private fun contactsLoader(): Loader<Cursor?> {
        val contactsUri: Uri =
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI // The content URI of the phone contacts

        val projection = arrayOf(
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        val selection: String? = null // Selection criteria
        val selectionArgs = arrayOf<String>() // Selection criteria
        val sortOrder: String =
            ContactsContract.Contacts.DISPLAY_NAME + " COLLATE NO CASE ASC" // The sort order for the returned rows
        return CursorLoader(
            requireContext(),
            contactsUri,
            projection,
            selection,
            selectionArgs,
            sortOrder
        )
    }

    companion object {
        val contactHashSet = HashSet<ContactListWalletModel>()
        private const val CONTACTS_LOADER_ID = 1
    }

    // -----------------------------------------------------------------------------------------
    private val onContactItemClickedListener: OnContactItemClickedListener =
        object : OnContactItemClickedListener {
            override fun onClickedItem(view: View, model: ContactListWalletModel) {
                when (view.id) {
                    R.id.mcVRoot -> {
//                    if (selectedcontactListWalletModel.isNotEmpty()) {
//                        selectedcontactListWalletModel.remove(model)
//                        addBeneficiaryMobileMoneyWalletAdapter.submitList(selectedcontactListWalletModel)
//                    }
                        contactHashSet.remove(model)
                        addBeneficiaryMobileMoneyWalletAdapter.submitList(contactHashSet.toList())
                        mAdapter.notifyDataSetChanged()
                    }
                }
            }
        }

    // -----------------------------------------------------------------------------------------
}
