package employee.android.ui.attributes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddAttributeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is add attribute Fragment"
    }
    val text: LiveData<String> = _text
}