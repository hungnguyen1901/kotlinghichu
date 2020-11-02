package com.example.kotlin_ghichu

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log
import java.util.ArrayList

class DatabaseHandler : SQLiteOpenHelper {


    companion object {

        val Tag = "DatabaseHandler"
        val DB = "Database"
        val DBVersion = 1

        val Table_TT = "tableghichu"
        val ID_TT = "idd"
        val Tieude_TT = "tieudett"
        val Noidung_TT = "noidungtt"
    }

    var context: Context? = null
    var sqlObj: SQLiteDatabase


    constructor(context: Context) : super(context, DB, null, DBVersion) {

        this.context = context;
        sqlObj = this.writableDatabase;


    }

    override fun onCreate(p0: SQLiteDatabase?) {

        //SQL for creating table
        var sql1: String = "CREATE TABLE IF NOT EXISTS " + Table_TT + " " +
                "(" + ID_TT + " INTEGER PRIMARY KEY," +
                Tieude_TT + " TEXT, " +  " TEXT, " + Noidung_TT +
                " TEXT );"

        p0!!.execSQL(sql1);
        Log.d(Tag, "Database created!!!")


    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        p0!!.execSQL("Drop table IF EXISTS " + Table_TT)
        onCreate(p0)


    }

    fun AddContact(values: ContentValues): String {

        var Msg: String = "error";

        val ID = sqlObj!!.insert(Table_TT, "", values)

        if (ID > 0) {

            Msg = "ok"
        }

        return Msg
    }

    fun FetchContacts(keyword: String): ArrayList<ContactData> {


        var arraylist = ArrayList<ContactData>()


        val sqb = SQLiteQueryBuilder()
        sqb.tables = Table_TT
        val cols = arrayOf("idd", "tieudett", "noidungtt")
        val rowSelArg = arrayOf(keyword)

        val cur = sqb.query(sqlObj, cols, "idd like ?", rowSelArg, null, null, "idd")

        if (cur.moveToFirst()) {

            do {
                val id = cur.getInt(cur.getColumnIndex("idd"))
                val tieude1 = cur.getString(cur.getColumnIndex("tieudett"))
                val noidung1 = cur.getString(cur.getColumnIndex("noidungtt"))

                arraylist.add(ContactData(id, tieude1, noidung1))

            } while (cur.moveToNext())
        }

        var count: Int = arraylist.size

        return arraylist
    }
    fun FetchContacts2(keyword: String): ArrayList<ContactData> {


        var arraylist = ArrayList<ContactData>()


        val sqb = SQLiteQueryBuilder()
        sqb.tables = Table_TT
        val cols = arrayOf("idd", "tieudett", "noidungtt")
        val rowSelArg = arrayOf(keyword)

        val cur = sqb.query(sqlObj, cols, "tieudett like ?", rowSelArg, null, null, "tieudett")

        if (cur.moveToFirst()) {

            do {
                val id = cur.getInt(cur.getColumnIndex("idd"))
                val fname = cur.getString(cur.getColumnIndex("tieudett"))
                val email = cur.getString(cur.getColumnIndex("noidungtt"))

                arraylist.add(ContactData(id, fname, email))

            } while (cur.moveToNext())
        }

        var count: Int = arraylist.size

        return arraylist
    }


    fun UpdateContact(values: ContentValues, id: Int): String {

        var selectionArs = arrayOf(id.toString())

        val i = sqlObj!!.update(Table_TT, values, "idd=?", selectionArs)
        if (i > 0) {
            return "ok";
        } else {

            return "error";
        }
    }

    fun RemoveContact(id: Int): String {

        var selectionArs = arrayOf(id.toString())

        val i = sqlObj!!.delete(Table_TT, "idd=?", selectionArs)
        if (i > 0) {
            return "ok";
        } else {

            return "error";
        }
    }


}