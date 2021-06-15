package com.ibnsina.notes.features.login

import android.content.Context
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ibnsina.entites.Resource
import com.ibnsina.entites.login.LoginStatus
import com.ibnsina.notes.R
import com.ibnsina.notes.databinding.FragmentLoginBinding
import com.ibnsina.utils.base.BaseFragment
import com.ibnsina.utils.getColorRes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var loginBinding: FragmentLoginBinding
    private val viewModel : LoginViewModel by viewModels()
    override fun doBinding(container: ViewGroup?, inflater: LayoutInflater): View {
        loginBinding = FragmentLoginBinding.inflate(inflater,container,false)
        loginBinding.viewModel = viewModel
        return loginBinding.root
    }
    override fun initData(data: Bundle) {
    }

    override fun initVariables(context: Context) {
    }

    override fun initViewModel() {
        viewModel.stateLiveData.observe(this){
            when(it.status){
                Resource.Status.LOADING -> messagesHandler.showProgressBar()
                Resource.Status.ERROR -> messagesHandler.showMessageDialog(it.message.toString())
                Resource.Status.SUCCESS -> {
                    messagesHandler.hideDialog()
                    it.data?.let { loginResponse ->
                        when (loginResponse.loginStatus) {
                            LoginStatus.LOGIN_SUCCESS -> {
                                findNavController().navigate(R.id.action_login_notes)
                            }
                            LoginStatus.EMAIL_ERROR -> messagesHandler.showToast(R.string.email_not_exist)
                            else -> messagesHandler.showToast(R.string.wrong_credintial)
                        }
                    }
                }
            }
        }
    }

    override fun initViews() {
        loginBinding.login.setOnClickListener {
            login()
        }
        setFocusListener(loginBinding.password, loginBinding.passwordLayout)
        setFocusListener(loginBinding.mobile, loginBinding.phoneLayout)
        loginBinding.mobile.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            loginBinding.password.requestFocus()
            false
        })
        loginBinding.password.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            login()
            false
        })

    }

    private fun login() {
        viewModel.login(loginBinding.mobile.text.toString(),loginBinding.password.text.toString())
    }

    override fun saveInstanceState(savedInstanceState: Bundle) {
    }
    fun setFocusListener(editText: TextInputEditText, textInputLayout: TextInputLayout) {
        editText.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) textInputLayout.setEndIconDrawable(R.drawable.ic_active_input_transparent) else textInputLayout.setEndIconDrawable(
                R.drawable.ic_active_input
            )
            textInputLayout.setEndIconActivated(hasFocus)
            textInputLayout.setEndIconTintList(ColorStateList.valueOf(getColorRes(R.color.colorAccent,this.requireContext())))

        }
    }
}