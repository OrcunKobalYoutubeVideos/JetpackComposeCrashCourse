package com.youtube.crashcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.youtube.crashcourse.components.HeaderForm
import com.youtube.crashcourse.components.ListNames
import com.youtube.crashcourse.components.MyDialog
import com.youtube.crashcourse.ui.theme.YoutubeCrashCourseTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            YoutubeCrashCourseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        App()
                    }
                }
            }
        }
    }

    @Composable
    fun App(vm: MyViewModel = viewModel()) {
        val list by vm.list.collectAsState()
        HeaderForm(
            listSize = list.size,
            removeAllAction = {
                vm.removeAll()
            },
            addNameAction = { txtName ->
                vm.addName(txtName)
            }
        )
        ListNames(list = list, deleteNameAction = { item->
            vm.removeName(item)
        })
    }

}

data class User(val id: String, val name: String)

class MyViewModel : ViewModel() {
    private val _list = MutableStateFlow<List<User>>(listOf())
    val list = _list.asStateFlow()

    fun addName(name: String) {
        _list.value += User(id = System.currentTimeMillis().toString().takeLast(4), name = name)
    }

    fun removeName(user: User) {
        _list.value -= user
    }

    fun removeAll() {
        _list.value = listOf()
    }

}



