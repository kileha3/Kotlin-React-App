package view

import modal.Entry

interface ContentEditView: AppBaseView {

    var entry: Entry?

    var errorMessage: String?

    fun showUpdatedEntry(entry: Entry?)
}