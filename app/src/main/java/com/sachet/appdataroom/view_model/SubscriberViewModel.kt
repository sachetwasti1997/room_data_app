package com.sachet.appdataroom.view_model

import androidx.lifecycle.*
import com.sachet.appdataroom.db.Subscriber
import com.sachet.appdataroom.db.SubscriberRepository
import kotlinx.coroutines.launch
import kotlin.math.log

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository): ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()
    private var isUpdate = false
    private lateinit var subscriberToUpdateOrDelete: Subscriber;

    val subscribersList = liveData {
        subscriberRepository.subscribers.collect{
            emit(it)
        }
    }

    val saveOrUpdateButtonText = MutableLiveData<String>()
    val deleteOrClearButtonText = MutableLiveData<String>()

    init {
        saveOrUpdateButtonText.value = "Save"
        deleteOrClearButtonText.value = "Clear All"
    }

    private fun setInitialValues(){
        inputName.value = ""
        inputEmail.value = ""
        saveOrUpdateButtonText.value = "Save"
        deleteOrClearButtonText.value = "Clear All"
        isUpdate = false
    }

    fun saveOrUpdate(){
        println(subscriberToUpdateOrDelete)
        if (!isUpdate){
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Subscriber(id = 0, name, email))
            inputName.value = ""
            inputEmail.value = ""
        }else{
            subscriberToUpdateOrDelete.name = inputName.value.toString()
            subscriberToUpdateOrDelete.email = inputEmail.value.toString()
            update(subscriberToUpdateOrDelete)
            setInitialValues()
        }
    }

    fun setUpdateDeleteTextView(subscriber: Subscriber){
        inputEmail.value = subscriber.email
        inputName.value = subscriber.name
        saveOrUpdateButtonText.value = "Update"
        deleteOrClearButtonText.value = "Delete"
        isUpdate = true
        subscriberToUpdateOrDelete = subscriber
    }

    fun clearAllOrDelete(){
        if (isUpdate){
            delete(subscriberToUpdateOrDelete)
            setInitialValues()
        }else{
            deleteAll()
        }
    }

    fun insert(subscriber: Subscriber){
        viewModelScope.launch {
            subscriberRepository.insert(subscriber)
        }
    }

    fun update(subscriber: Subscriber){
        viewModelScope.launch {
            subscriberRepository.update(subscriber)
        }
    }

    fun delete(subscriber: Subscriber){
        viewModelScope.launch {
            subscriberRepository.delete(subscriber)
        }
    }

    fun deleteAll(){
        viewModelScope.launch {
            subscriberRepository.deleteAll()
        }
    }

}












