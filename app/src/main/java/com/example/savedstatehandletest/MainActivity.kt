@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.savedstatehandletest

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.savedstatehandletest.ui.theme.SavedStateHandleTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SavedStateHandleTestTheme {
                val viewModel = viewModel<MainViewModel>()
                val backgroundColor by viewModel.color.collectAsState()
                val name by viewModel.name.collectAsState()

                TestScreen(
                    backgroundColor = backgroundColor,
                    name = name,
                    onClickScreen = { viewModel.updateBackgroundColor() },
                    onTextChanged = viewModel::updateName
                )

            }
        }
    }
}

@Composable
fun TestScreen(backgroundColor: Long, name: String, onClickScreen: () -> Unit, onTextChanged: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(backgroundColor))
            .padding(16.dp)
            .clickable { onClickScreen() },
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            textStyle = MaterialTheme.typography.headlineLarge.merge(SpanStyle(color = Color.Black))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TestScreenPreView() {
    SavedStateHandleTestTheme {
        var name by remember {
            mutableStateOf("Test")
        }

        TestScreen(
            backgroundColor = 0xFF2B447F,
            name = name,
            onClickScreen = { Log.d("TAG", "onClickScreen") },
            onTextChanged = { name = it }
        )
    }
}