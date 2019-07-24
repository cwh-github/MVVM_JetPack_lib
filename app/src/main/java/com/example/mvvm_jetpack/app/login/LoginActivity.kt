package com.example.mvvm_jetpack.app.login

import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.mvvm_jetpack.BR
import com.example.mvvm_jetpack.R
import com.example.mvvm_jetpack.app.widget.ProgressDialogFragment
import com.example.mvvm_jetpack.app.widget.ProgressDialogFragment1
import com.example.mvvm_jetpack.databinding.LoginViewBinding
import com.example.mvvm_jetpack_lib.base.view.BaseActivity
import com.example.mvvm_jetpack_lib.base.widget.StateLayout
import com.example.mvvm_jetpack_lib.utils.ToastUtils

class LoginActivity : BaseActivity<LoginViewModel, LoginViewBinding>() {
    override val mViewModel: LoginViewModel by lazy {
        LoginViewModelFactory.instance(this@LoginActivity.application)
            .create(LoginViewModel::class.java)
    }
    override var layoutId: Int = R.layout.activity_login

    override var mViewModelVariableId: Int = BR.viewModel

    override var mProgressDialog: DialogFragment? = ProgressDialogFragment1.onNewInstance("tag")

    lateinit var mStateLayout: StateLayout

    override fun initDataBingView() {
        super.initDataBingView()
        //这里是可以的，主要是，DataBinding的主要作用是，获取view的id,将Id与view绑定，就算包裹一层，ID 也是不会变的
        mStateLayout = StateLayout(this).wrap(this).showContentView()
    }


    override fun initDataAndView() {
    }

    override fun initViewObserver() {
        mViewModel.tips.observe(this, Observer { it ->
            it.getContentIfNotHandled()?.let {
                ToastUtils.showToast(this@LoginActivity, it)
            }
        })

    }

}
