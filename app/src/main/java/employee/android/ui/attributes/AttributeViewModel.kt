package employee.android.ui.attributes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AttributeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is attribute Fragment"
    }
    val text: LiveData<String> = _text
}