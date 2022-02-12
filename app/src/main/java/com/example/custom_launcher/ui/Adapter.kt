package com.example.custom_launcher.ui

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.custom_launcher.R
import data.AppData
import java.lang.Exception

class Adapter(var installedApps: java.util.ArrayList<AppData>, var ctx:Context) : RecyclerView.Adapter<Adapter.CustomViewHolder>(),Filterable {

    lateinit var orignalList:ArrayList<AppData>;
    init {
        orignalList= arrayListOf();
        orignalList.addAll(installedApps)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
      val inflator= LayoutInflater.from(parent.context);
       val view= inflator.inflate(R.layout.row,parent,false);
        return CustomViewHolder(view)
    }
    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.tv.text=installedApps[position].app_name;
        holder.tv2.text=installedApps[position].version_name;
        holder.iv_img.setImageDrawable(installedApps[position].icon)
        holder.iv_img.setOnClickListener {
          onClick(installedApps[position],ctx);
        }

    }

    override fun getItemCount(): Int {
       return installedApps.size
    }
    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv:TextView=itemView.findViewById(R.id.tv_1)
        val tv2:TextView=itemView.findViewById(R.id.tv_2)
        val iv_img:ImageView=itemView.findViewById(R.id.iv_img);
    }

    override fun getFilter(): Filter {
      return object :Filter(){
          override fun performFiltering(p0: CharSequence?): FilterResults {
              val charString = p0?.toString() ?: ""
              if(!charString.isEmpty()){
                  var tempList= arrayListOf<AppData>();
                  for (row in installedApps) {
                     if(row.app_name.lowercase().contains(charString)){
                         tempList.add(row);
                     }
                  }
                  installedApps=tempList;

              }else{
                  installedApps=orignalList;
              }
              val filterResults = FilterResults()
              filterResults.values = installedApps
              return filterResults
          }

          override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
              installedApps=p1?.values as ArrayList<AppData>;
              notifyDataSetChanged()

          }

      }
    }
    fun onClick(appData: AppData,act:Context) {
        try {
            val launchIntent = act.packageManager.getLaunchIntentForPackage(appData.package_Name)
            if(launchIntent!=null){
                act.startActivity(launchIntent)
            }

        }catch (e:Exception){
            Toast.makeText(ctx, "Package not found", Toast.LENGTH_SHORT).show();

        }
    }


}