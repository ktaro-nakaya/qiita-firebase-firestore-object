package jp.co.casareal.firestoreobjectandreference.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import jp.co.casareal.firestoreobjectandreference.entity.Detail
import jp.co.casareal.firestoreobjectandreference.entity.User

class DetailViewModel(
    val user:MutableLiveData<User> = MutableLiveData(),
    val detail:MutableLiveData<Detail> = MutableLiveData()
):ViewModel()