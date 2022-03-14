package urias.andres.mydigimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_dashboard.*
import urias.andres.mydigimind.R
import urias.andres.mydigimind.Recordatorio
import urias.andres.mydigimind.Tiempo

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard,container,false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTiempo.setOnClickListener {
            showTimePickerDialog()
        }
        btnGuardar.setOnClickListener {
            var nombre = editTextInputRemember.text
            var dias = ""
            var tiempo = txtHoras.text.toString()
            if( check_Lunes.isChecked) {
                dias+= "Lun"
            }
            if(check_Martes.isChecked) {
                dias += if (dias.isNotEmpty()){
                    ",Mar"
                }else
                    "Mar"
            }
            if(check_Miercoles.isChecked) {
                dias+= if(dias.isNotEmpty()){
                    ",Mier"
                }else "Mier"
            }
            if(check_Jueves.isChecked) {
                dias+= if(dias.isNotEmpty()){
                    ",Jue"
                }else
                    "Jue"
            }
            if(check_viernes.isChecked) {
                dias+= if(dias.isNotEmpty()){
                    ",Vier"
                }else "Vier"
            }
            if(check_sabado.isChecked) {
                dias+= if(dias.isNotEmpty()){
                    ",Sab"
                }else "Sab"
            }
            if(check_Domingo.isChecked){
                dias+= if(dias.isNotEmpty()){
                    ",Dom"
                }else "Dom"
            }
            if(check_Lunes.isChecked && check_Martes.isChecked && check_Miercoles.isChecked && check_Jueves.isChecked && check_viernes.isChecked &&
                check_sabado.isChecked && check_Domingo.isChecked) {
                dias="Todo el dia"
            }

            var recordatorio = Recordatorio(dias,tiempo,nombre)
            val bundle = Bundle()
            bundle.putSerializable("recordatorio",recordatorio)
            clean()
            setFragmentResult("key",bundle)

        }
    }
    private fun showTimePickerDialog(){
        val timePicker = Tiempo{onTimeSelected(it)}
        timePicker.show(parentFragmentManager,"time")
    }
    private fun onTimeSelected(time:String){
        txtHoras.text = time
    }
    private fun clean(){
        editTextInputRemember.setText("")
        check_Lunes.isChecked =false
        check_Martes.isChecked =false
        check_Miercoles.isChecked =false
        check_Jueves.isChecked =false
        check_viernes.isChecked =false
        check_sabado.isChecked =false
        check_Domingo.isChecked =false
        txtHoras.text = ""
    }
}