package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val contactsAdapter = ContactsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        setListeners()
    }


    override fun onResume() {
        super.onResume()
        contactsAdapter.setContacts(ContactRepository.getContacts())
    }

    private fun initRecyclerView() {
        binding.recycleView.adapter = contactsAdapter

    }

    private fun setListeners() = with(binding) {
        addContactButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, AddContactActivity::class.java))
        }
        contactsAdapter.onDeleteClick = { contact ->
            ContactRepository.removeContact(contact)
            contactsAdapter.setContacts(ContactRepository.getContacts())
        }
        contactsAdapter.onItemClick = { contact ->
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(contact.name)
            builder.setMessage(contact.contactNumber.toString())

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.yes, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}
