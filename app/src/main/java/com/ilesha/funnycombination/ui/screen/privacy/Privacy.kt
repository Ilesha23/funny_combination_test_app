package com.ilesha.funnycombination.ui.screen.privacy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ilesha.funnycombination.R
import com.ilesha.funnycombination.ui.components.MenuButton

@Composable
fun Privacy(
    onBack: () -> Unit
) {

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Text(
            text = stringResource(R.string.privacy_header),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = stringResource(R.string.privacy_placeholder),
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        )
        MenuButton(
            action = {
                onBack()
            },
            text = stringResource(R.string.privacy_button_main_menu)
        )
    }

}