package com.example.learning.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.example.learning.R
import com.example.learning.presentation.TestTags
import com.example.learning.presentation.ui.theme.IconSizeMedium

@Composable
fun StandardTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    maxLength: Int = 40,
    error: String = "",
    style: TextStyle = TextStyle(MaterialTheme.colors.onBackground),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    leadingIcon: ImageVector? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    isPasswordVisible: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    onValueChangeHandle: (String) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .semantics { testTag = TestTags.PASSWORD_TOGGLE },
            value = text,
            onValueChange = { if (it.length <= maxLength) onValueChangeHandle(it) },
            maxLines = maxLines,
            textStyle = style,
            placeholder = { Text(text = hint, style = MaterialTheme.typography.body1) },
            isError = error.isNotBlank(),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = visualTransform(isPasswordVisible, isPasswordToggleDisplayed),
            singleLine = singleLine,
            leadingIcon = if (leadingIcon != null) {
                val icon: @Composable () -> Unit = {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.size(IconSizeMedium)
                    )
                }
                icon
            } else null,
            trailingIcon = if (isPasswordToggleDisplayed) {
                val icon: @Composable () -> Unit = {
                    IconButton(
                        onClick = {
                            onPasswordToggleClick(!isPasswordVisible)
                        },
                        modifier = Modifier
                            .semantics {
                                testTag = TestTags.PASSWORD_TOGGLE
                            }
                    ) {
                        Icon(
                            imageVector = if (isPasswordVisible) {
                                Icons.Filled.VisibilityOff
                            } else {
                                Icons.Filled.Visibility
                            },
                            tint = Color.White,
                            contentDescription = if (isPasswordVisible) {
                                stringResource(id = R.string.password_visible_content_description)
                            } else {
                                stringResource(id = R.string.password_hidden_content_description)
                            }
                        )
                    }
                }
                icon
            } else null,
        )
        if (error.isNotBlank()) {
            Text(
                text = error,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private fun visualTransform(
    isPasswordVisible: Boolean,
    isPasswordToggleDisplayed: Boolean
): VisualTransformation {
    return if (!isPasswordVisible && isPasswordToggleDisplayed) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
}
