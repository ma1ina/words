package com.polsl.words.ui.tile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.PopupProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldWithDropdown(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    setValue: (TextFieldValue) -> Unit,
    onDismissRequest: () -> Unit,
    dropDownExpanded: Boolean,
    list: List<String>,
    label: String = ""
) {
    Box(modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    if (!focusState.isFocused)
                        onDismissRequest()
                },
            value = value,
            onValueChange = setValue,
            label = { Text(label) },
            colors = OutlinedTextFieldDefaults.colors(
            )
        )
        DropdownMenu(
            expanded = dropDownExpanded,
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            ),
            onDismissRequest = onDismissRequest
        ) {
            list.forEach { text ->
                DropdownMenuItem(onClick = {
                    setValue(
                        TextFieldValue(
                            text,
                            TextRange(text.length)
                        )
                    )
                }, text = { Text(text = text) })
            }
        }
    }
}