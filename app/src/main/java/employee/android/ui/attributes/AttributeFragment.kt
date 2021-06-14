package employee.android.ui.attributes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import employee.android.R


class AttributeFragment : Fragment() {

    private lateinit var attributeViewModel: AttributeViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        attributeViewModel = ViewModelProvider(this).get(AttributeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_attribute, container, false)
       /* val textView: TextView = root.findViewById(R.id.text_gallery)
        attributeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        //Button to move to add attribute fragment
        val button: Button = root.findViewById(R.id.add_attribute_button)
        button.setOnClickListener(View.OnClickListener {
            val fragment: Fragment = AddAttributeFragment()
            val fragmentManager: FragmentManager = requireActivity().supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.add_fra, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        })

        return root
    }
}