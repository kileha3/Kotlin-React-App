package view

import modal.Person

interface MainComponentView: AppBaseView {

    fun updateDrawerState()

    fun updatePerson(person: Person)

    fun onThemeChange()

    fun onLocaleChange(locale: String)
}