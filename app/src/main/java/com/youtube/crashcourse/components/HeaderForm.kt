package com.youtube.crashcourse.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderForm(listSize: Int, removeAllAction: () -> Unit, addNameAction: (name: String) -> Unit) {
    val context = LocalContext.current
    var txtName by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(
            start = 10.dp,
            top = 10.dp,
            end = 10.dp,
            bottom = 20.dp
        ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "İsim Ekle",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black
            )
            if (listSize > 0) {
                var isClickedRemoveAllButton by remember {
                    mutableStateOf(false)
                }
                FilledIconButton(onClick = {
                    isClickedRemoveAllButton = true
                }, modifier = Modifier.size(20.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                }
                if (isClickedRemoveAllButton) {
                    MyDialog(
                        onDismissRequest = {
                            isClickedRemoveAllButton = false
                        },
                        confirmButton = {
                            removeAllAction()
                            isClickedRemoveAllButton = false
                        },
                        dismissButton = {
                            isClickedRemoveAllButton = false
                        },
                        message = "Tümünü silmek istediğinize emin misin?",
                    )
                }
            }
        }
        //Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedTextField(
                label = {
                    Text(text = "Ad ve Soyad")
                },
                value = txtName,
                onValueChange = { changedValue ->
                    txtName = changedValue
                },
                modifier = Modifier.weight(1f)
            )
            //Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                if (txtName.length >= 3) {
                    addNameAction(txtName)
                    txtName = ""
                } else {
                    Toast.makeText(context, "İsim en az 3 karater olmalı", Toast.LENGTH_SHORT)
                        .show()
                }
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                Text(text = "Ekle")
            }
        }
    }
    HorizontalDivider()
}