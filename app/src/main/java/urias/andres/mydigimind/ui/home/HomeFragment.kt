package urias.andres.mydigimind.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recordatorio.view.*
import urias.andres.mydigimind.Carrito
import urias.andres.mydigimind.R
import urias.andres.mydigimind.Recordatorio
import urias.andres.mydigimind.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    companion object {
        var recordatorio = ArrayList<Recordatorio>()
        var first = true
    }

    private lateinit var homeViewModel:HomeViewModel
    var carrito = Carrito()
    private var adapter: RecordatorioAdapter? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        if(first){
            fillTasks()
            first= false
        }

        adapter = RecordatorioAdapter(root.context, recordatorio)
        val table : GridView = root.findViewById(R.id.gridView)
        table.adapter = adapter
        return root
    }

    fun fillTasks(){
        recordatorio.add(Recordatorio( arrayListOf("Tuesday"), "17:30","Practice 1"))
        recordatorio.add(Recordatorio( arrayListOf("Monday","Saturday"), "17:00","Practice 2"))
        recordatorio.add(Recordatorio( arrayListOf("Wednesday"), "14:00","Practice 3"))
        recordatorio.add(Recordatorio( arrayListOf("Saturday"), "11:00","Practice 4"))
        recordatorio.add(Recordatorio( arrayListOf("Friday"), "13:00","Practice 5"))
        recordatorio.add(Recordatorio( arrayListOf("Thursday"), "10:40","Practice 6"))
        recordatorio.add(Recordatorio( arrayListOf("Monday"), "12:00","Practice 7"))
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var llave = "recordatorio"
        var bundle = Bundle()
        setFragmentResultListener("key") { recordatorio, bundle ->
            val resultado: Recordatorio = bundle.getSerializable("recordatorio") as Recordatorio
            carrito.agregar(resultado)
            adapter = RecordatorioAdapter(context,carrito.recordatorios)
            gridView.adapter = adapter
        }
    }

    class RecordatorioAdapter: BaseAdapter {
        var recordatorio = ArrayList<Recordatorio>()
        var context: Context? = null

        constructor(context: Context?, recordatorio: ArrayList<Recordatorio>){
            this.context = context
            this.recordatorio = recordatorio
        }
        override fun getCount(): Int {
            return recordatorio.size
        }

        override fun getItem(p0: Int): Any {
            return recordatorio[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            var rec = recordatorio[p0]
            var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.recordatorio, null)
            vista.txtDiasRecordatorio.text = rec.dias.toString()
            vista.txtNombreRecordatorio.text = rec.nombre.toString()
            vista.txtTiempoRecordatorio.text = rec.tiempo.toString()
            return vista
        }
    }
}