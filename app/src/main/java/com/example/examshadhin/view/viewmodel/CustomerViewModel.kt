package com.example.examshadhin.view.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examshadhin.view.model.CustomerListResponse
import com.example.examshadhin.view.model.CustomerRegResponse
import com.example.examshadhin.view.model.RegistrationModel
import com.example.examshadhin.view.model.api_config.ApiService
import com.example.examshadhin.view.util.Constant
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CustomerViewModel : ViewModel(){
    private val apiService = ApiService()
    private val disposable = CompositeDisposable()

    var customerRegistrationResponse = MutableLiveData<CustomerRegResponse>()
    var customerListResponse = MutableLiveData<List<CustomerListResponse>>()
    var error_response = MutableLiveData<Boolean>();

    fun reqForUserRegistration(model: RegistrationModel) {

        disposable.add(apiService.reqForRegistration(model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CustomerRegResponse>() {
                override fun onSuccess(model: CustomerRegResponse) {
                    model?.let {
                        customerRegistrationResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    if(Constant.logCheck){
                        Log.e("error_reqForUserReg", e.message.toString())
                    }
                    e.printStackTrace()
                    error_response.value = true
                }
            })
        )
    }
    fun getCustomerList() {
        disposable.add(apiService.getCustomerList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CustomerListResponse>>() {
                override fun onSuccess(model: List<CustomerListResponse>) {
                    model?.let {
                        customerListResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    if(Constant.logCheck){
                        Log.e("error_getCustomerList", e.message.toString())
                    }
                    e.printStackTrace()
                    error_response.value = true
                }
            })
        )
    }
    fun updateUserInfo(model: RegistrationModel) {

        disposable.add(apiService.updateUserInfo(model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CustomerRegResponse>() {
                override fun onSuccess(model: CustomerRegResponse) {
                    model?.let {
                        customerRegistrationResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    if(Constant.logCheck){
                        Log.e("error_updateUserInfo", e.message.toString())
                    }
                    e.printStackTrace()
                    error_response.value = true
                }
            })
        )
    }
}