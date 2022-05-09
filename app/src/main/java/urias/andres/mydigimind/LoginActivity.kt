package urias.andres.mydigimind

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        btn_registrar.setOnClickListener {
            val intent: Intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

        btn_ingresar.setOnClickListener {
            validaIngreso()
        }

        btn_contrasena.setOnClickListener{
            val intent: Intent = Intent(this, Contrasena::class.java)
            startActivity(intent)
        }

    }

    fun validaIngreso(){
        var correo = et_correo.text.toString().trim()
        var password = et_contraseña.text.toString()
        if(!correo.isNullOrBlank() && !password.isNullOrBlank()){
            ingresar(correo, password)
        }else{
            Toast.makeText(this,"Ingrese datos en los campos de texto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun ingresar(email:String,password:String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intent: Intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("user",user)
                    startActivity(intent)


                } else {
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }
}