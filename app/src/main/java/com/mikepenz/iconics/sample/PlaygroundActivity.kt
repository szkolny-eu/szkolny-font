/*
 * Copyright (c) 2019 Mike Penz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mikepenz.iconics.sample

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.StateListDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.mikepenz.aboutlibraries.util.getThemeColor
import com.mikepenz.iconics.Iconics
import com.mikepenz.iconics.IconicsArrayBuilder
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.IconicsSize
import com.mikepenz.iconics.utils.*
import eu.szkolny.font.SzkolnyFont
import eu.szkolny.font.sample.R
import eu.szkolny.font.sample.databinding.ActivityPlaygroundBinding

class PlaygroundActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityPlaygroundBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_playground)

        // Handle Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val themeValue1 = getThemeColor(android.R.attr.textColorPrimary)
        val themeValue2 = getThemeColor(android.R.attr.textColorPrimaryInverse)

        //Show how to style the text of an existing TextView
        Iconics.Builder()
                .style(
                    ForegroundColorSpan(themeValue2),
                    BackgroundColorSpan(themeValue1),
                    RelativeSizeSpan(2f)
                )
                .styleFor(
                    "faw-adjust",
                    BackgroundColorSpan(Color.RED),
                    ForegroundColorSpan(Color.parseColor("#33000000")),
                    RelativeSizeSpan(2f)
                )
                .on(binding.test1)
                .build()

        //You can also do some advanced stuff like setting an image within a text
        val sb = SpannableString(binding.test5.text)
        val d = IconicsDrawable(this, SzkolnyFont.Icon.szf_delete_empty_outline).apply {
            sizeDp = 48
            paddingDp = 4
            colorInt = themeValue1
        }

        sb.setSpan(
            ImageSpan(d, DynamicDrawableSpan.ALIGN_BOTTOM),
            1,
            2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.test5.text = sb

        //Set the icon of an ImageView (or something else) as drawable
        binding.test2.setImageDrawable(
            IconicsDrawable(this, SzkolnyFont.Icon.szf_file_code_outline).apply {
                sizeDp = 48
                colorString = "#aaFF0000"
                contourWidthDp = 1
            }
        )

        //Set the icon of an ImageView (or something else) as bitmap
        binding.test3.setImageBitmap(
            IconicsDrawable(this, SzkolnyFont.Icon.szf_file_image_outline).apply {
                sizeX = IconicsSize.dp(48)
                sizeY = IconicsSize.dp(32)
                paddingDp = 4
                roundedCornersDp = 8
                colorString = "#deFF0000"
            }.toBitmap()
        )

        //Show how to style the text of an existing button
        Iconics.Builder()
                .style(BackgroundColorSpan(themeValue1))
                .style(RelativeSizeSpan(2f))
                .style(ForegroundColorSpan(themeValue2))
                .on(binding.test4)
                .build()

        //Show how to style the text of an existing button
        val iconStateListDrawable = StateListDrawable()
        iconStateListDrawable.addState(
            intArrayOf(android.R.attr.state_pressed),
            IconicsDrawable(this, SzkolnyFont.Icon.szf_file_pdf_outline).apply {
                sizeDp = 48
                colorString = "#aaFF0000"
                contourWidthDp = 1
            }
        )
        iconStateListDrawable.addState(
            intArrayOf(),
            IconicsDrawable(this, SzkolnyFont.Icon.szf_message_processing_outline).apply {
                sizeDp = 48
                colorString = "#aa00FF00"
                contourWidthDp = 2
            }
        )
        binding.test6.setImageDrawable(iconStateListDrawable)

        val iconicsDrawableBase = IconicsDrawable(this).apply {
            actionBar()
            colorInt = Color.GREEN
            backgroundColorInt = Color.RED
        }
        val array = IconicsArrayBuilder(iconicsDrawableBase)
                .add(SzkolnyFont.Icon.szf_file_excel_outline)
                .add(SzkolnyFont.Icon.szf_clipboard_list_outline)
                .add("Hallo")
                .add('A')
                .add(";)")
                .build()
        binding.list.adapter = IconsAdapter(this, array)

        // Create icons for menu_navigation
        val planningIcon = IconicsDrawable(this, SzkolnyFont.Icon.szf_notebook_outline)
        val homeIcon = IconicsDrawable(this, SzkolnyFont.Icon.szf_alarm_bell_outline)
        val calendarIcon = IconicsDrawable(this, SzkolnyFont.Icon.szf_calendar_today_outline)

        // Set icons
        binding.navigation.menu.apply {
            findItem(R.id.navigation_home).icon = planningIcon
            findItem(R.id.navigation_dashboard).icon = homeIcon
            findItem(R.id.navigation_notifications).icon = calendarIcon
        }

        // Automatically process all icons in menu
        binding.navigationAuto.menu.parseXmlAndSetIconicsDrawables(this, R.menu.menu_playground)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_playground, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private inner class IconsAdapter internal constructor(
        context: Context,
        objects: Array<IconicsDrawable>
    ) : ArrayAdapter<IconicsDrawable>(context, 0, objects) {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        @SuppressLint("ViewHolder")
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val v = inflater.inflate(R.layout.row_icon_array, parent, false)
            val icon = v.findViewById<ImageView>(android.R.id.icon)
            icon.setImageDrawable(getItem(position))
            return v
        }
    }

    companion object {
        @BindingAdapter("iconicsSrc", "iconicsColor")
        @JvmStatic
        fun loadIconicsImage(view: ImageView, name: String, color: Int?) {
            view.setImageDrawable(IconicsDrawable(view.context, name).apply {
                if (color != null) {
                    colorInt = color
                }
            })
        }
    }
}
