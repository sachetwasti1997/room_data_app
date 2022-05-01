package com.sachet.appdataroom.view_model

import androidx.lifecycle.*
import com.sachet.appdataroom.db.Subscriber
import com.sachet.appdataroom.db.SubscriberRepository
import kotlinx.coroutines.launch

class SubscriberViewModel(private val subscriberRepository: SubscriberRepository): ViewModel() {

    val inputName = MutableLiveData<String>()
    val inputEmail = MutableLiveData<String>()

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

    fun saveOrUpdate(){
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(Subscriber(id = 0, name, email))
            inputName.value = ""
            inputEmail.value = ""
    }

    fun clearAllOrDelete(){
        deleteAll()
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












