package com.example.gradle.presentation.composable

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gradle.presentation.util.ErrorText
import com.example.gradle.ui.theme.LotkaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LotkaTextField(
  modifier: Modifier = Modifier,
  onTextChanged: (String) -> Unit,
  value: String,
  @StringRes label: Int,
  isError: Boolean = false,
  @StringRes error: Int? = null,
  singleLine: Boolean = true,
  leadingIcon: Painter? = null,
  leadingIconDescription: String? = null,
  trailingIcon: ImageVector? = null,
  trailingIconDescription: String? = null,
  onTrailingIconClicked: () -> Unit = {},
  trailingIconVisibility: TrailingIconVisibility = TrailingIconVisibility.VISIBLE,
  footnote: String = "",
  footnoteFontSize: TextUnit = 10.sp,
  shape: Shape = RoundedCornerShape(8.dp),
  visualTransformation: VisualTransformation = VisualTransformation.None,
  keyboardType: KeyboardType,
  imeAction: ImeAction,
  onImeActionClicked: () -> Unit = {},
  isRequired: Boolean = false,
  textFieldType: TextFieldType = TextFieldType.Outlined,
  enabled: Boolean = true,
  color: TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = LotkaTheme.color.primary,
    unfocusedBorderColor = Color(0xFFEDF1F3),
    cursorColor = Blue,
    focusedLabelColor = LotkaTheme.color.primary,
    unfocusedLabelColor =Color(0xFF6C7278),
    disabledLabelColor = Color.Cyan,
    focusedTextColor = Color(0xFF222222),
    unfocusedTextColor = Color(0xFF6C7278),
  ),
  textStyle: TextStyle = LotkaTheme.typography.contentText
) {



    when (textFieldType) {
      TextFieldType.Outlined -> {
        androidx.compose.material3.OutlinedTextField(
          modifier = Modifier.fillMaxWidth(),
          value = value,
          onValueChange = onTextChanged,
          colors = color,
          keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
          keyboardActions = KeyboardActions { onImeActionClicked.invoke() },
          singleLine = singleLine,
          label = {
            Text(
              text = stringResource(id = label) + if (isRequired) "" else "",
              style = LotkaTheme.typography.contentText.copy(
                fontSize = 14.sp,
                color = if (label == 0) LotkaTheme.color.error else LotkaTheme.color.primary
              )
            )
          },
          shape = shape,
          textStyle = textStyle.copy( fontSize = 14.sp),
          isError = isError,
          enabled = enabled,
          visualTransformation = visualTransformation,
          leadingIcon = leadingIcon?.let {
            {
              IconButton(onClick = {}) {
                Icon(painter = leadingIcon, contentDescription = leadingIconDescription)
              }
            }
          },
          trailingIcon = trailingIcon?.let {
            when (trailingIconVisibility) {
              TrailingIconVisibility.VISIBLE -> {
                {
                  IconButton(onClick = onTrailingIconClicked) {
                    Icon(imageVector = it, contentDescription = trailingIconDescription)
                  }
                }
              }
              TrailingIconVisibility.ONLY_WHEN_INPUT_IS_NOT_NULL -> {
                if (!value.isNullOrBlank()) {
                  {
                    IconButton(onClick = onTrailingIconClicked) {
                      Icon(imageVector = it, contentDescription = trailingIconDescription)
                    }
                  }
                } else null
              }
            }
          }
        )
      }
      TextFieldType.Filled -> {
        Column(modifier = modifier.fillMaxWidth()) {
          Text(
            text = stringResource(id = label) + if (isRequired) "" else "",
            style = LotkaTheme.typography.sectionTitle.copy(
              fontSize = 14.sp,
              color = if (value.isEmpty()) LotkaTheme.color.error else LotkaTheme.color.primary
            ),
            modifier = Modifier.padding(bottom = 4.dp)
          )
          BasicTextField(
            modifier = Modifier
              .fillMaxWidth()
              .background(LotkaTheme.color.surface, shape),
            value = value,
            onValueChange = onTextChanged,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = KeyboardActions { onImeActionClicked.invoke() },
            singleLine = singleLine,
            textStyle = textStyle.copy(fontSize = 14.sp, textAlign = TextAlign.Start),
            enabled = enabled,
            visualTransformation = visualTransformation
          )
        }
        when (error) {
          null -> if (footnote.isNotBlank()) {
            Text(
              modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
              text = footnote,
              style = LotkaTheme.typography.contentText,
              fontSize = footnoteFontSize,
              textAlign = TextAlign.Start
            )
          }
          else -> ErrorText(resource = error, textAlign = TextAlign.Start)
        }
    }


  }
}

sealed class TextFieldType {
  object Outlined : TextFieldType()
  object Filled : TextFieldType()
}



// format long to 123,456,789,9


enum class TrailingIconVisibility {
  VISIBLE,
  ONLY_WHEN_INPUT_IS_NOT_NULL
}
