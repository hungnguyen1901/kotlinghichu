package com.example.kotlin_ghichu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.list_book.view.*

class ContactAdapter(con: Context, arrList:ArrayList<ContactData>): BaseAdapter() {

    var arrayList = ArrayList<ContactData>()
    var context: Context? = null
    var myInflater: LayoutInflater? = null

    init {

        this.context    = con
        this.myInflater = LayoutInflater.from(context)
        this.arrayList  = arrList
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var myView = myInflater!!.inflate(R.layout.list_book,null)
        var ConObj = arrayList[p0]

       /* var TG : String = ConObj.Thoigian.toString()*/
        var CV : String =ConObj.Tieude.toString()


        /*myView.contact_name.text = TG*/
        myView.contact_tieude.text = CV


        return myView
    }

    override fun getItem(p0: Int): Any {
        return arrayList[p0]

    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()

    }

    override fun getCount(): Int {
        return arrayList.size

    }



}