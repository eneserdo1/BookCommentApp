package com.eneserdogan.bookcommentapp.LoginUI.ui.LoginSignUp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.eneserdogan.bookcommentapp.LoginUI.model.User
import com.eneserdogan.bookcommentapp.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.loginpage_signup_fragment.*
import kotlinx.android.synthetic.main.selectphoto_dialog.view.*
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.jar.Manifest
import java.util.regex.Pattern

class LoginPageSignupFragment : Fragment() {

    private lateinit var viewModel: LoginPageSignupViewModel
    private var filePath: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUpButtonsListener()
        userPhotoBtnListener()
    }
    private fun userPhotoBtnListener() {
        signup_ımage_user.setOnClickListener {
            showCustomAlertDialog()
        }
    }


    private fun signUpButtonsListener() {
        signup_btn.setOnClickListener {
            println("doğruluk kontroll " + isEmpty(signup_edittext_email))
            if (isEmpty(signup_edittext_email) || isEmpty(signup_edittext_name) ||
                isEmpty(signup_edittext_surname) || isEmpty(signup_edittext_email)
                || isEmpty(signup_edittext_passwordtwo)
            ) {
                signup_progressbar.visibility = View.VISIBLE
                Toast.makeText(context, "Lütfen Gerekli Alanları Doldurunuz", Toast.LENGTH_LONG)
                    .show()

            } else if (signup_edittext_password.text.toString() == signup_edittext_passwordtwo.text.toString()) {

                var user = User(
                    signup_edittext_name.text.toString() + " " + signup_edittext_surname.text.toString(),
                    signup_edittext_email.text.toString(),
                    signup_edittext_password.text.toString(),
                    "degersiz",
                    "boş",
                    true,
                    "1997 doğumlu Bilgisayar Mühendisi"

                )

                if (isEmailValid(signup_edittext_email.text.toString()) && filePath != null) {
                    viewModel.userSignUp(requireActivity(), user, requireContext(), filePath)
                    Navigation.findNavController(it)
                        .navigate(R.id.action_loginPageSignupFragment_to_loginPageSigninFragment)

                } else {
                    Toast.makeText(
                        context,
                        "Lütfen Geçerli Bir Mail Adresi Giriniz ve Fotoğraf Seçiniz",
                        Toast.LENGTH_LONG
                    )
                        .show()
                }
            } else {
                Toast.makeText(
                    context,
                    "Şifreleriniz Eşleşmiyor, Lütfen Kontrol Ediniz",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.loginpage_signup_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(LoginPageSignupViewModel::class.java)
        return root
    }


    private fun isEmpty(editText: EditText): Boolean {
        return editText.text.toString() == ""
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }


    private fun showCustomAlertDialog() {
        val myDialog = LayoutInflater.from(context).inflate(R.layout.selectphoto_dialog, null)
        val alert = AlertDialog.Builder(context).setView(myDialog)
        val myAlertDialog = alert.show()
        myDialog.selectedPhoto_btn_gallery.setOnClickListener {
            selectPhotoWithGallery()

        }

        myDialog.selectedPhoto_btn_camera.setOnClickListener {

        }
    }

    private fun selectPhotoWithGallery() {

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
                            IMAGE_PICK_CODE
                        )

                    }
                }
            }).check()

    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            filePath = data?.data
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, filePath)
                signup_ımage_user.setImageBitmap(bitmap)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    companion object {

        val IMAGE_PICK_CODE = 1000
        val PERMISSION_CODE = 1001
    }


}