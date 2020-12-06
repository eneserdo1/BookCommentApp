package com.eneserdogan.bookcommentapp.LoginUI.ui.profil

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.eneserdogan.bookcommentapp.LoginUI.model.Book
import com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignUp.LoginPageSignupFragment
import com.eneserdogan.bookcommentapp.R
import com.google.firebase.auth.FirebaseAuth
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.fragment_add_book.*
import kotlinx.android.synthetic.main.loginpage_signup_fragment.*
import java.lang.Exception
import java.util.*
import kotlin.concurrent.timer


class AddBookFragment : Fragment() {
    private lateinit var viewModel: AddBookViewModel
    private var filePath: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_add_book, container, false)

        viewModel = ViewModelProviders.of(this).get(AddBookViewModel::class.java)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonsListener()


    }

    private fun buttonsListener() {
        addBookFragment_book_ımage.setOnClickListener {
            selectPhoto()
        }

        addBookFragment_save_button.setOnClickListener {
            if (isEmpty(addBookFragment_bookName_edittext) && isEmpty(
                    addBookFragment_bookAuthor_edittext
                ) &&
                isEmpty(addBookFragment_bookYear_edittext) && isEmpty(
                    addBookFragment_category_edittext
                ) && isEmpty(addBookFragment_description_edittext)
                && filePath != null
            ) {
                var bookId = UUID.randomUUID()

                var book = Book(
                    addBookFragment_bookName_edittext.text.toString(),
                    addBookFragment_bookAuthor_edittext.text.toString(),
                    addBookFragment_bookYear_edittext.text.toString().toInt(),
                    addBookFragment_description_edittext.text.toString(),
                    addBookFragment_category_edittext.text.toString(),
                    FirebaseAuth.getInstance().uid,
                    filePath.toString(),
                    bookId.toString()
                )
                viewModel.addBook(book, filePath!!, requireContext())
                clearText(
                    addBookFragment_bookAuthor_edittext,
                    addBookFragment_bookName_edittext,
                    addBookFragment_bookYear_edittext,
                    addBookFragment_category_edittext,
                    addBookFragment_description_edittext
                )


            } else {
                Toast.makeText(context, "Lütfen Gerekli Alanları Doldurunuz", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }


    private fun selectPhoto() {
        Dexter.withActivity(requireActivity()).withPermissions(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>?,
                    token: PermissionToken?
                ) {
                    token?.continuePermissionRequest()
                }

                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report == null) return
                    if (report.areAllPermissionsGranted()) { //İzinler tamam ise
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = "image/*"
                        intent.action = Intent.ACTION_GET_CONTENT
                        startActivityForResult(
                            Intent.createChooser(intent, "Fotoğraf Seçiniz"),
                            LoginPageSignupFragment.IMAGE_PICK_CODE
                        )

                    }
                }
            }).check()
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == LoginPageSignupFragment.IMAGE_PICK_CODE) {
            filePath = data?.data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, filePath)
                addBookFragment_book_ımage.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString() != ""


    }

    private fun clearText(vararg editTexts: EditText) {
        for (newEt in editTexts) {
            newEt.setText("")
        }
    }


}