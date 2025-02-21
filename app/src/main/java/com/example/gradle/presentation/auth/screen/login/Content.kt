package com.example.gradle.presentation.auth.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gradle.R
import com.example.gradle.presentation.composable.LotkaButton
import com.example.gradle.presentation.composable.LotkaTextField
import com.example.gradle.presentation.composable.TextFieldType
import com.example.gradle.presentation.composable.TrailingIconVisibility
import com.example.gradle.ui.theme.LotkaButtonStyle
import com.example.gradle.ui.theme.LotkaTheme


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()


    // Observe login state and trigger navigation or any other action
    LaunchedEffect(uiState.isLoggedIn) {
        if (uiState.isLoggedIn) {
            navigateToHome() // Call function when user is logged in
        }
    }


    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {

        item {
            Spacer(modifier = Modifier.size(64.dp))
        }
        item {
            Column(
                modifier = Modifier,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "company logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(28.dp)
                )

                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.wellcome),
                    style = LotkaTheme.typography.sectionTitle,
                    color = LotkaTheme.color.textPrimary,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = stringResource(R.string.login_to_access),
                    style = LotkaTheme.typography.ultraSmallText,
                    color = LotkaTheme.color.textPrimary,
                )
            }
        }

        item {
            Spacer(modifier = Modifier.size(64.dp))
        }

        item {
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color = Color.White)
                    .fillMaxWidth()
                    .padding(vertical = 32.dp, horizontal = 16.dp),
            ) {
                LotkaTextField(
                    modifier = Modifier,
                    onTextChanged = { viewModel.onUsernameChanged(it) },
                    value = uiState.username,
                    label = R.string.username,
                    onTrailingIconClicked = {},
                    trailingIconVisibility = TrailingIconVisibility.VISIBLE,
                    footnote = "",
                    footnoteFontSize = 10.sp,
                    shape = RoundedCornerShape(8.dp),
                    keyboardType = KeyboardType.Text,
                    imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                    textFieldType = TextFieldType.Outlined,
                )

                Spacer(modifier = Modifier.size(16.dp))

                LotkaTextField(
                    modifier = Modifier,
                    onTextChanged = { viewModel.onPasswordChanged(it) },
                    value = uiState.password,
                    label = R.string.password,
                    onTrailingIconClicked = {},
                    trailingIconVisibility = TrailingIconVisibility.VISIBLE,
                    footnote = "",
                    footnoteFontSize = 10.sp,
                    shape = RoundedCornerShape(8.dp),
                    keyboardType = KeyboardType.Password,
                    imeAction = androidx.compose.ui.text.input.ImeAction.Done,
                    textFieldType = TextFieldType.Outlined,
                )
                Spacer(modifier = Modifier.size(32.dp))

                if (uiState.errorMessage != null) {
                    Text(
                        text = uiState.errorMessage.orEmpty(),
                        color = LotkaTheme.color.textSecondary,
                        style = LotkaTheme.typography.ultraSmallText
                    )
                    Spacer(modifier = Modifier.size(16.dp))
                }

                LotkaButton(
                    modifier = Modifier,
                    text = stringResource(R.string.login),
                    buttonType = LotkaButtonStyle.primary,
                    isLoading = uiState.isLoading,
                    onClick = { viewModel.login() },
                )


//                CustomSnackbar(
//                    title = "Error",
//                    message = "Error message",
//                    errorType = SnackbarErrorType.ERROR,
//                    onDismiss = {
//
//                    }
//                )
            }
        }
    }
}
