package urias.andres.mydigimind

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.btn_registrar
import kotlinx.android.synthetic.main.activity_login.et_correo
import kotlinx.android.synthetic.main.activity_registro.*

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
// ...
// Initialize Firebase Auth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)
        auth = FirebaseAuth.getInstance()

        btn_registrar.setOnClickListener {
            validaDatos()
        }
    }

    fun validaDatos(){
        var correo = et_correo.text.toString().trim()
        var contra1 = et_contra_reg.text.toString()
        var contra2 = et_contra_reg_conf.text.toString()
        println(correo)
        if (!correo.isNullOrBlank() && !contra1.isNullOrBlank() && !contra2.isNullOrBlank()){
            if (contra1 == contra2){
                registrar(correo,contra1);
            }else{
                Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            Toast.makeText(this,"Ingrese datos en los campos de texto", Toast.LENGTH_SHORT)
                .show()
        }
    }
    private fun registrar(email:String,password:String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "${user?.email} creado exitosamente.", Toast.LENGTH_SHORT).show()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Error de registro",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
}