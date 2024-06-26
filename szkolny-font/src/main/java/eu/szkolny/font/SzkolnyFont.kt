/*
 * Copyright 2019 Mike Penz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.szkolny.font

import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.ITypeface
import java.util.LinkedList

@Suppress("EnumEntryName")
object SzkolnyFont : ITypeface {

    override val fontRes: Int
        get() = R.font.szkolnyfont_font_v1_5

    override val characters: Map<String, Char> by lazy {
        Icon.values().associate { it.name to it.character }
    }
    
    override val mappingPrefix: String
        get() = "szf"

    override val fontName: String
        get() = "SzkolnyFont"

    override val version: String
        get() = "1.5"

    override val iconCount: Int
        get() = characters.size

    override val icons: List<String>
        get() = characters.keys.toCollection(LinkedList())

    override val author: String
        get() = "Kuba Szczodrzyński"

    override val url: String
        get() = ""

    override val description: String
        get() = ""

    override val license: String
        get() = ""

    override val licenseUrl: String
        get() = ""

    override fun getIcon(key: String): IIcon = Icon.valueOf(key)

    enum class Icon constructor(override val character: Char) : IIcon {
        szf_alarm_bell_outline('\ue800'),
		szf_archive('\ue801'),
		szf_calendar('\ue802'),
		szf_calendar_plus_outline('\ue803'),
		szf_calendar_today_outline('\ue804'),
		szf_clipboard_list_outline('\ue805'),
		szf_delete_empty_outline('\ue806'),
		szf_discord_outline('\ue807'),
		szf_file_code_outline('\ue808'),
		szf_file_excel_outline('\ue809'),
		szf_file_image_outline('\ue80a'),
		szf_file_music_outline('\ue80b'),
		szf_file_pdf_outline('\ue80c'),
		szf_file_percent_outline('\ue80d'),
		szf_file_powerpoint_outline('\ue80e'),
		szf_file_video_outline('\ue80f'),
		szf_file_word_outline('\ue810'),
		szf_forward('\ue811'),
		szf_github_face('\ue812'),
		szf_image_plus_outline('\ue813'),
		szf_message_processing_outline('\ue814'),
		szf_notebook_outline('\ue815'),
		szf_reply('\ue816'),
		szf_sync('\ue817'),
		szf_sync_error('\ue818'),
		szf_timetable('\ue819'),
		szf_umbrella_beach_outline('\ue81a'),
		szf_zip_box_outline('\ue81b');

        override val typeface: ITypeface by lazy { SzkolnyFont }
    }
}
