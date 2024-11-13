package com.example.contacts

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contacts.databinding.ActivityAddContactBinding

class AddContactActivity : AppCompatActivity() {
    private val binding by lazy { ActivityAddContactBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() = with(binding) {


        createButton.setOnClickListener {
            val contactNameString = contactName.text.toString()
            val contactNumberString = contactNumber.text.toString().toInt()
            val contact = Contact(contactNameString, contactNumberString)
            ContactRepository.addContact(contact)
            finish()
        }
    }


}

