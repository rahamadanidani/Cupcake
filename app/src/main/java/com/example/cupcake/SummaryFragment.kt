
package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cupcake.databinding.FragmentSummaryBinding
import com.example.cupcake.model.OrderViewModel

/**
 * [SummaryFragment] berisi ringkasan detail pesanan dengan tombol untuk membagikan pesanan
 * melalui aplikasi lain.
 */
class SummaryFragment : Fragment() {

    // Binding objek instance yang sesuai dengan layout fragment_summary.xml
    //Properti ini bukan nol antara callback siklus hidup onCreateView() dan onDestroyView(),
    // ketika hierarki tampilan dilampirkan ke fragmen.
    private var binding: FragmentSummaryBinding? = null

    // Gunakan delegasi properti Kotlin 'by activityViewModels()' dari artefak fragmen-ktx
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            summaryFragment = this@SummaryFragment
        }
    }

    //Kirim pesanan dengan membagikan detail pesanan ke aplikasi lain melalui maksud implisit.
    fun sendOrder() {
        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

    //Metode siklus hidup fragmen ini dipanggil saat hierarki tampilan terkait dengan fragmen
    // sedang dihapus. Akibatnya, bersihkan objek yang mengikat.
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}