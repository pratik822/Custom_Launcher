package ui

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.custom_launcher.R
import com.example.custom_launcher.ui.Adapter
import com.example.custom_launcher.ui.DataHandler
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val mainActivity=this;
    lateinit  var rv_adapter:Adapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        search.setOnQueryTextListener(object:SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
              return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                rv_adapter?.filter.filter(newText);
                return false;
            }

        });


        GlobalScope.launch(Dispatchers.Main){
            if(DataHandler.getInstalledApps(mainActivity)?.size!! >0){
                rv_list.apply {
                    layoutManager=LinearLayoutManager(mainActivity);
                    rv_adapter= Adapter(DataHandler.getInstalledApps(mainActivity)!!,mainActivity);
                    rv_list.adapter=rv_adapter;
                }
            }

        }



    }




}