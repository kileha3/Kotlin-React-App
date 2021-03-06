package controller

import view.AppBaseView

abstract class AppBasePresenter<V: AppBaseView>(arguments: Map<String,String>, view: V) {

    open fun onCreate(){}

}