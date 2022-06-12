package com.aliosmanarslan.itunessearchapi.utils.widgets

import android.content.Context
import android.content.res.TypedArray
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.aliosmanarslan.itunessearchapi.R
import kotlinx.android.synthetic.main.search_bar_item.view.*

class SearchBar: ConstraintLayout {

    fun setHint(value : String?){
        this.etSearchBar.hint = value
    }

    fun setHeaderImage(headerImage : Int){
        this.ivHeader.setImageResource(headerImage)
    }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs){
        inflate(context, R.layout.search_bar_item, this)
        init(context,attrs)
    }
    constructor(context: Context, attrs: AttributeSet?,
                @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private fun init(context: Context, attrs: AttributeSet?) {
        val typedArray : TypedArray = context.obtainStyledAttributes(attrs, R.styleable.cSearchBar,0 ,0)
        setHint(typedArray.getString(R.styleable.cSearchBar_hint))
        setHeaderImage(typedArray.getResourceId(R.styleable.cSearchBar_header_image,0))
    }

    fun addTextChangedListener(textWatcher: TextWatcher){
        this.etSearchBar.addTextChangedListener(textWatcher)
    }

    fun getSearchedText(): String{
        return this.etSearchBar.text.toString()
    }
}