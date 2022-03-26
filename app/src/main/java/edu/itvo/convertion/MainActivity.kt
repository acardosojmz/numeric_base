package edu.itvo.convertion


import android.os.Bundle
import android.text.InputFilter
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import edu.itvo.convertion.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var listOfItems =listOf(2,8,10,16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item, listOfItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnFrom.adapter = adapter
        binding.spnTo.adapter = adapter
        val base = binding.spnFrom.selectedItem.toString().toInt()
        this.applyFilter(base)

        binding.spnFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                binding.edtData.setText("")

                applyFilter(binding.spnFrom.selectedItem.toString().toInt())
            }
        }

        binding.btnConvert.setOnClickListener {
            val from = binding.spnFrom.selectedItem.toString().toLong()
            val to = binding.spnTo.selectedItem.toString().toLong()
            val data = binding.edtData.text
            when (from){
                2L->{
                    val converted = when(to) {
                        8L-> Convert.binaryToOctal(binding.edtResult.text.toString())
                        10L -> Convert.binaryToDecimal(binding.edtResult.text.toString()).toString()
                        16L -> Convert.binaryToHexadecimal(binding.edtResult.text.toString())
                        else -> "No está implementada esta funcion"
                    }
                    binding.edtResult.text = converted
                }
                8L->{
                    val binary=Convert.octalToBinary(data.toString())
                    val converted = when(to){
                        10L->Convert.binaryToDecimal(binary).toString()
                        16L->Convert.binaryToHexadecimal(binary)
                        else-> binary

                    }
                    binding.edtResult.text = converted
                }
                10L-> binding.edtResult.text  = Convert.decimalToBaseX(data.toString().toLong(), to)

                16L->{
                    val binary=Convert.hexadecimalToBinary(data.toString().uppercase())
                    val converted = when(to) {
                        8L-> Convert.binaryToOctal(binary)
                        10L -> Convert.binaryToDecimal(binary).toString()
                        16L -> Convert.binaryToHexadecimal(binary)
                        else -> binary
                    }
                    binding.edtResult.text = converted
                }
                else -> {}
            }
        }
    }
    private fun applyFilter(base:Int){
        binding.edtData.filters = arrayOf<InputFilter>(ValidateFilter(base))
    }

}




