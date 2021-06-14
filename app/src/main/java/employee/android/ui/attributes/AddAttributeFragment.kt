package employee.android.ui.attributes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import employee.android.R

class AddAttributeFragment : Fragment() {

    private lateinit var addAttributeViewModel: AddAttributeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addAttributeViewModel = ViewModelProvider(this).get(AddAttributeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_add_attribute_layout, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_gallery)
        addAttributeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return root;
    }

}