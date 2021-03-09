package jp.co.casareal.firestoreobjectandreference.viewmodel

import androidx.lifecycle.*

class RegisterUserViewModel(
    var name: MutableLiveData<String> = MutableLiveData(""),
    var age: MutableLiveData<String> = MutableLiveData(""),
    var introduction: MutableLiveData<String> = MutableLiveData(""),
    var flg: MediatorLiveData<Boolean> = MediatorLiveData<Boolean>().apply { value = false },
) : ViewModel() {
    init {
        val observer = Observer<String> {
            if (!name.value.isNullOrBlank() && !introduction.value.isNullOrBlank() && !age.value.isNullOrBlank()) {
                age.value!!.toIntOrNull()?.let {
                    flg.postValue(true)
                } ?: flg.postValue(false)
            } else {
                flg.postValue(false)
            }
        }

        flg.addSource(name, observer)
        flg.addSource(age, observer)
        flg.addSource(introduction,observer)

    }
}