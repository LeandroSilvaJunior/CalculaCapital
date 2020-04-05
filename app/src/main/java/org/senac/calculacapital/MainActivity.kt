package org.senac.calculacapital

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var quantidadeMesesComponent : SeekBar
    private lateinit var taxaJurosMensalComponent : EditText
    private lateinit var capitalAtualComponent : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quantidadeMesesComponent = findViewById<SeekBar>(R.id.sb_quantidade_meses)
        taxaJurosMensalComponent = findViewById<EditText>(R.id.et_taxa_juros_mensal)
        capitalAtualComponent = findViewById<EditText>(R.id.et_capital_autal)
    }

    fun calcularCapitalfuturo(view : View) {
        if (validate()) {
            var quantidadeMeses = quantidadeMesesComponent.progress.toDouble()
            var taxaJuros = taxaJurosMensalComponent.text.toString().toDouble()
            var capitalAtual = capitalAtualComponent.text.toString().toDouble()
            var capitalFuturo = capitalAtual * Math.pow((1.toDouble() + taxaJuros), quantidadeMeses)

            val alertDialogBuilder = AlertDialog.Builder(this@MainActivity)
            alertDialogBuilder.setTitle("Capital futuro")
            alertDialogBuilder.setMessage("Valor calculado para o capital futuro: R$${"%.2f".format(capitalFuturo)}")
            alertDialogBuilder.setNeutralButton("Ok") { _, _->}

            alertDialogBuilder.create().show()

        }
    }

    private fun validate(): Boolean {
        var validate = true

        if (taxaJurosMensalComponent.text.trim().length == 0) {
            taxaJurosMensalComponent.setError("Você deve informar ataxa de juros!")
            validate= false
        }

        if (capitalAtualComponent.text.trim().length == 0) {
            capitalAtualComponent.setError("Você deve informar o capital atual!")
            validate = false
        }

        return validate
    }
}
