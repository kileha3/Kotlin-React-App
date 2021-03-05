package view

import com.ccfraser.muirwik.components.*
import com.ccfraser.muirwik.components.button.mFab
import com.ccfraser.muirwik.components.form.MFormControlVariant
import controller.ContentEditPresenter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.display
import kotlinx.css.flexDirection
import modal.Entry
import react.RBuilder
import react.RProps
import react.RState
import react.dom.br
import react.dom.h2
import react.dom.p
import react.setState
import styled.css
import styled.styledDiv
import util.AppUtils
import util.ComponentStyles
import util.ComponentStyles.textField


class ContentEditComponent(props: RProps): AppBaseComponent<RProps, RState>(props), ContentEditView {

    private var mPresenter: ContentEditPresenter = ContentEditPresenter(AppUtils.getParams(), this)

    private var currentEntry: Entry ? = null

    private var updatedEntry: Entry ? = null

    private var nameError: Boolean = false

    private var updated: Boolean = false

    init {
        GlobalScope.launch {
            withTimeout(3000){
                mPresenter.onCreate()
            }
        }
    }


    override fun RBuilder.render() {
        themeContext.Consumer { _ ->

            styledDiv {
                styledDiv {

                    css {
                        display = Display.flex
                        flexDirection = FlexDirection.column
                    }

                    mTextField(label = if(nameError) errorMessage.toString() else "Entry name", value = currentEntry?.name,
                        error = nameError, disabled = updatedEntry != null,
                        variant = MFormControlVariant.outlined, onChange = {
                                event -> event.persist()
                                setState { currentEntry?.name = event.targetInputValue }
                        }) {
                        css(textField)
                    }

                    mTextFieldMultiLine(label = "Entry description",
                        disabled = updatedEntry != null,
                        value = currentEntry?.description,
                        variant = MFormControlVariant.outlined, onChange = {
                                event -> event.persist()
                                setState { currentEntry?.description = event.targetInputValue }
                        }) {
                        css(textField)
                    }


                    if(updated){
                        styledDiv {
                            h2 { +"Edited Entry" }
                            br {  }
                            p{+"${updatedEntry?.name}"}
                            p{+"${updatedEntry?.description}"}
                        }
                    }
                }

                mFab("check", "  Save  ", color = MColor.secondary, onClick = {
                    currentEntry?.let { it1 -> mPresenter.handleEntry(it1) }
                }) { css(ComponentStyles.buttonMargin) }
            }


        }
    }


    override var entry: Entry ? = null
        get() = currentEntry
        set(value) {
            field = value
            setState {
                if(value != null){
                    currentEntry = value
                }
            }
        }
    override var errorMessage: String? = null
        set(value) {
            field = value
            setState{
                nameError = !errorMessage.isNullOrBlank()
            }
        }

    override fun showUpdatedEntry(entry: Entry?) {
        setState {
            updated = entry != null
            updatedEntry = entry
        }
    }

}