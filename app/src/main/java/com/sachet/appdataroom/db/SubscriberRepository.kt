package com.sachet.appdataroom.db

class SubscriberRepository(private val dao: SubscriberDAO) {

    val subscribers = dao.getAll()

    suspend fun insert(subscriber: Subscriber){
        dao.insert(subscriber)
    }

    suspend fun update(subscriber: Subscriber){
        dao.update(subscriber)
    }

    suspend fun delete(subscriber: Subscriber){
        dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(){
        dao.deleteAll()
    }

}