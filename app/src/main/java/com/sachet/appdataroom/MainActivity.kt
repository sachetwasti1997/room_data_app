package com.sachet.appdataroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sachet.appdataroom.databinding.ActivityMainBinding
import com.sachet.appdataroom.db.Subscriber
import com.sachet.appdataroom.db.SubscriberDatabase
import com.sachet.appdataroom.db.SubscriberRepository
import com.sachet.appdataroom.recycler_view.SubscriberRecyclerViewAdapter
import com.sachet.appdataroom.view_model.SubscriberViewModel
import com.sachet.appdataroom.view_model.SubscriberViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subscriberViewModal: SubscriberViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModal = ViewModelProvider(this, factory)[SubscriberViewModel::class.java]
        binding.myViewModal = subscriberViewModal
        binding.lifecycleOwner = this
        Log.i("TAG", "onCreate: ${binding.nameInput.text}")
        initRecyclerView()
    }

    private fun initRecyclerView(){
        binding.subscriberRecyclerView.layoutManager = LinearLayoutManager(this)
        displayList()
    }

    private fun displayList(){
        subscriberViewModal.subscribersList.observe(this, Observer { it ->
            Log.i("TAGRECYCLER", "displayList: ${it.toString()}")
            binding.subscriberRecyclerView.adapter = SubscriberRecyclerViewAdapter(it){subscriber ->
                clickListener(subscriber)
            }
        })
    }

    private fun clickListener(subscriber: Subscriber){
        Toast.makeText(this, "selected name is ${subscriber.name}", Toast.LENGTH_LONG).show()
        subscriberViewModal.setUpdateDeleteTextView(subscriber)
    }

}