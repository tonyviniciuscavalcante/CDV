package br.edu.ifsp.scl.ads.pdm.cdv

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.EditText
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.scl.ads.pdm.cdv.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private companion object {
        const val VALOR_TELEFONE_UM = "VALOR_TELEFONE_UM"
        const val VALOR_TELEFONE_DOIS = "VALOR_TELEFONE_DOIS"

    }

    private val telefoneDoisEt: EditText by lazy {
        EditText(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)

        telefoneDoisEt.hint = "Segundo telefone"
        telefoneDoisEt.layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        amb.root.addView(telefoneDoisEt)

        savedInstanceState?.apply {
            getString(VALOR_TELEFONE_UM)?.let {
                amb.telefoneUmEt.setText(it)
            }
            getString(VALOR_TELEFONE_DOIS, "0000000000").let {
                telefoneDoisEt.setText(it)
            }
            Log.v(getString(R.string.app_name),"onSaveRestoreState: Restaurando dados de instancia")
        }

        amb.abraBt.setOnClickListener{
            Intent(this, AnotherActivity::class.java).also {
                startActivity(it)
            }

        }

        Log.v(getString(R.string.app_name), "onCreate: iniciando ciclo completo")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(VALOR_TELEFONE_UM, amb.telefoneUmEt.text.toString())
        outState.putString(VALOR_TELEFONE_DOIS, telefoneDoisEt.text.toString())
        Log.v(getString(R.string.app_name),"onSaveInstaceState: Salvando dados de instancia")
    }

    /*override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.apply {
            getString(VALOR_TELEFONE_UM)?.let {
                amb.telefoneUmEt.setText(it)
            }
            getString(VALOR_TELEFONE_DOIS, "0000000000").let {
                telefoneDoisEt.setText(it)
            }
        }
        Log.v(getString(R.string.app_name),"onSaveRestoreState: Restaurando dados de instancia")
    }*/

    override fun onStart() {
        super.onStart()
        Log.v(getString(R.string.app_name), "onStart: iniciando ciclo visivel")
    }

    override fun onResume() {
        super.onResume()
        Log.v(getString(R.string.app_name), "onResume: iniciando ciclo foreground")
    }

    override fun onPause() {
        super.onPause()
        Log.v(getString(R.string.app_name), "onPause: finalizandos ciclo foreground")
    }

    override fun onStop() {
        super.onStop()
        Log.v(getString(R.string.app_name), "onStop: finalizando ciclo visivel")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(getString(R.string.app_name), "onDestroy: finalizando ciclo completo")
    }

    override fun onRestart() {
        super.onRestart()
        Log.v(getString(R.string.app_name), "onRestart: preparando onStart")
    }
}
