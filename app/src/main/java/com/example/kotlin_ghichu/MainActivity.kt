package com.example.kotlin_ghichu

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var ContactList = ArrayList<ContactData>()
    var adapter: ContactAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    override fun onResume() {
        super.onResume()
        var DB:DatabaseHandler = DatabaseHandler(this);
        ContactList = DB.FetchContacts("%");


        switch1.setOnClickListener(){
            if (switch1.isChecked) {
                ContactList = DB.FetchContacts("%");
                var ContactAdapterObj = ContactAdapter(this, ContactList)

                contact_list.adapter = ContactAdapterObj
                Toast.makeText(this, "Sắp xếp theo thời gian !", Toast.LENGTH_SHORT).show()
            }else{
                ContactList = DB.FetchContacts2("%");
                var ContactAdapterObj = ContactAdapter(this, ContactList)

                contact_list.adapter = ContactAdapterObj
                Toast.makeText(this, "sắp xếp theo a-b-c !", Toast.LENGTH_SHORT).show()
            }

        }

        if(ContactList.size>0) {
            var ContactAdapterObj = ContactAdapter(this, ContactList)

            contact_list.adapter = ContactAdapterObj
            contact_list.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->


                var tieude = ContactList[position].Tieude;
                var noidung = ContactList[position].Noidung;
                var id = ContactList[position].conID

                var intent = Intent(this, ContactManager::class.java)
                intent.putExtra("idd", id)
                intent.putExtra("tieudett", tieude)
                intent.putExtra("noidungtt", noidung)
                intent.putExtra("action", "edit")
                startActivity(intent)
            }
        }else{

            Toast.makeText(this, "Ghi chú đang trống !", Toast.LENGTH_SHORT).show()

        }

//        add_contact_btn.setOnClickListener(){
//
//            var intent= Intent(this,ContactManager::class.java)
//            startActivity(intent)
//
//        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.menu_them, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.contact_btn) {
            var intent= Intent(this,ContactManager::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }



}

