package controller

import modal.Entry
import util.AppUtils
import view.ContentEditView

class ContentEditPresenter(private val arguments: Map<String,String>, private val view: ContentEditView):
    AppBasePresenter<ContentEditView>(arguments, view) {

    override fun onCreate() {
        super.onCreate()
        view.entry = AppUtils.getEntry(arguments["id"])
    }

    fun handleEntry(entry: Entry){
        if(entry.name.isEmpty()){
            view.errorMessage = "Required field, make sure you type entry name"
            return
        }

        view.showUpdatedEntry(entry)
    }

}