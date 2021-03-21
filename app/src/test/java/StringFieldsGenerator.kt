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

import eu.szkolny.font.SzkolnyFont
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import ru.ztrap.iconics.IconicsStringGenerator

/**
 * @author pa.gulko zTrap (29.10.2017)
 */
@Ignore
@RunWith(JUnit4::class)
class StringFieldsGenerator : IconicsStringGenerator() {

    @Test fun generateSzkolnyFont() {
        generateIconsFrom(SzkolnyFont)
    }

    override val fileCreationStrategy: IconicsStringGenerator.FileCreationStrategy
        get() = IconicsStringGenerator.FileCreationStrategy.SAVE_ONLY_CURRENT

    override val modifierCurrent: String get() = ""
}
