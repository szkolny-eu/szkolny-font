package eu.szkolny.font

import android.content.Context
import com.mikepenz.iconics.typeface.ITypeface
import com.mikepenz.iconics.typeface.IconicsHolder
import com.mikepenz.iconics.typeface.IconicsInitializer

class Initializer : androidx.startup.Initializer<ITypeface> {
    override fun create(context: Context): ITypeface {
        IconicsHolder.registerFont(SzkolnyFont)
        return SzkolnyFont
    }

    override fun dependencies(): List<Class<out androidx.startup.Initializer<*>>> {
        return listOf(IconicsInitializer::class.java)
    }
}
