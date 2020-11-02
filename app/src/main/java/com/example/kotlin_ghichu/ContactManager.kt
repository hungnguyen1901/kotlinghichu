package com.example.kotlin_ghichu

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.them_sua.*

class ContactManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.them_sua)

        var record_id = intent.getIntExtra("idd",0)

        if (record_id == 0) {

            save_btn.text = "THÊM"
            txtHTag.text = "Nhập nội dung"

        } else {
            save_btn.text = "SỬA"
            txtHTag.text = "Nội dung"
            var _fname = intent.getStringExtra("tieudett")
            var _email = intent.getStringExtra("noidungtt")


            tieudetxt.setText(_fname)
            noidungtxt.setText(_email)


        }
        save_btn.setOnClickListener() {

            var a = tieudetxt.text.toString();
            var c = noidungtxt.text.toString();




            if (a == "") {

                Toast.makeText(this, "Vui lòng nhập tiêu đề", Toast.LENGTH_SHORT).show()

            } else {

                var values = ContentValues()
                values.put("tieudett", a)
                values.put("noidungtt", c)

                if (record_id == 0) {

                    var DB: DatabaseHandler = DatabaseHandler(this);


                    var response = DB.AddContact(values);
                    if (response == "ok") {

                        Toast.makeText(this, "Đã thêm thành công", Toast.LENGTH_SHORT).show()

                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        Toast.makeText(this, "Thêm thất bại", Toast.LENGTH_SHORT).show()
                    }

                } else {

                    var DB: DatabaseHandler = DatabaseHandler(this);

                    var res: String = DB.UpdateContact(values, record_id)

                    if (res == "ok") {
                        Toast.makeText(this, "Sửa thành công", Toast.LENGTH_SHORT).show()

                        var intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()


                    } else {
                        Toast.makeText(this, "Sửa thất bại", Toast.LENGTH_SHORT).show()

                    }

                }

            }

        }

       if (record_id == 0){
           delete_btn.text ="QUAY LẠI"
           delete_btn.setOnClickListener() {
               var intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
               finish()
           }
       } else{
           delete_btn.text = "XÓA"
           delete_btn.setOnClickListener() {

               var DB: DatabaseHandler = DatabaseHandler(this);
               var res: String = DB.RemoveContact(record_id)

               if (res == "ok") {

                   Toast.makeText(this, "Xóa Thành Công",
                       Toast.LENGTH_SHORT).show()
               } else {
                   Toast.makeText(this, "Xóa thất bại",
                       Toast.LENGTH_SHORT).show()
               }

               var intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
               finish()

           }
       }


    }
}
