package com.youtube.crashcourse.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.youtube.crashcourse.User

@Composable
fun ListNames(list: List<User>, deleteNameAction: (item: User) -> Unit) {
    LazyColumn {
        if (list.isEmpty()) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Herhangi bir isim bulunamadı",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
        items(list) { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp, horizontal = 10.dp),
            ) {
                Text(text = "#${item.id} ${item.name}")
                var isClickedDeleteButton by remember {
                    mutableStateOf(false)
                }
                Button(onClick = {
                    isClickedDeleteButton = true
                }) {
                    Text(text = "Sil")
                }
                if (isClickedDeleteButton) {
                    MyDialog(
                        onDismissRequest = {
                            isClickedDeleteButton = false
                        },
                        confirmButton = {
                            deleteNameAction(item)
                            isClickedDeleteButton = false
                        },
                        dismissButton = {
                            isClickedDeleteButton = false
                        },
                        message = "${item.name} adlı kullanıcıyı silmek istediğinize emin misin?",
                    )
                }
            }
            HorizontalDivider()
        }
    }
}