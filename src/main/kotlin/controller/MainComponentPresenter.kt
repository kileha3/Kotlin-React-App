package controller

import modal.Person
import view.MainComponentView

class MainComponentPresenter(private val arguments: Map<String,String>, private val view: MainComponentView):
    AppBasePresenter<MainComponentView>(arguments, view) {

    override fun onCreate() {
        super.onCreate()
        view.updatePerson(
            Person(
                "https://www.openhost.co.za/download/bootmin/img/avatar_lg.jpg",
                "example@example.com", "John Doe"
            )
        )
    }

    fun handleThemeChange(){
        view.onThemeChange()
    }

    fun handleUpdateDrawerState(){
        view.updateDrawerState()
    }
}