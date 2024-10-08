package com.nino.codilitytask3tmdb.ui.filter.option

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoAdultContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.nino.codilitytask3tmdb.R
import com.nino.codilitytask3tmdb.ui.filter.FilterSectionTitle
import com.nino.codilitytask3tmdb.ui.filter.FilterState

data class IncludeAdultOption(override val defaultValue: Boolean) : FilterOption<Boolean> {
    override var currentValue: Boolean = defaultValue

    override fun modifyFilterState(filterState: FilterState) = filterState.copy(includeAdult = currentValue)

    @Composable
    override fun Render(onChanged: () -> Unit) {
        val isChecked = remember(defaultValue) { mutableStateOf(currentValue) }
        val onClick = {
            currentValue = currentValue.not()
            isChecked.value = currentValue
            onChanged()
        }
        Row(
            Modifier
                .clickable(onClick = onClick)
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            FilterSectionTitle(
                painter = rememberVectorPainter(Icons.Default.NoAdultContent),
                title = R.string.include_adult,
            )
            Switch(checked = isChecked.value, onCheckedChange = { onClick() })
        }
    }
}
