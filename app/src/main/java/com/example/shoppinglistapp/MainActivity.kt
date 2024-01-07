package com.example.shoppinglistapp

import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.shoppinglistapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val itemlist = arrayListOf<String>()
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itemlist)

        binding.goMap.setOnClickListener{
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        binding.add.setOnClickListener {
            itemlist.add(binding.editText.text.toString())
            binding.listView.adapter = adapter
            adapter.notifyDataSetChanged()
            binding.editText.text.clear()
        }

        binding.clear.setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
        }

        binding.listView.setOnItemClickListener { _, _, i, _ ->
            android.widget.Toast.makeText(this, "You Selected the item --> " + itemlist[i], android.widget.Toast.LENGTH_SHORT).show()
        }

        binding.delete.setOnClickListener {
            val position: SparseBooleanArray = binding.listView.checkedItemPositions
            val count = binding.listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist[item])
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}