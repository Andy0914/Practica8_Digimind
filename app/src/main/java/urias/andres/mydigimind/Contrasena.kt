package urias.andres.mydigimind

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_contrasena.*

class Contrasena : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contrasena)
        auth = FirebaseAuth.getInstance()
        btn_restablecer.setOnClickListener {
            validaCamposContrasena()
        }
    }
    fun validaCamposContrasena(){
        var correo = et_correo_contr.text.toString()
        if (!correo.isNullOrBlank()){
            auth.sendPasswordResetEmail(correo)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Se envio un correo al $correo", Toast.LENGTH_SHORT)
                            .show()
                    }else{
                        Toast.makeText(this,"Error al enviar correo", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
        }else{
            Toast.makeText(this,"Ingresa correo de recuperación", Toast.LENGTH_SHORT)
                .show()
        }

    }
}