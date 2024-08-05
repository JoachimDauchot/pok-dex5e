package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pokdex.model.Move
import com.example.pokdex.ui.converters.typeToIconConverter
import com.example.pokdex.ui.theme.TransparentBrush
import com.example.pokdex.ui.theme.convertTypeToColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailMoveSet(title: String, moves: List<Move>) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f,
        label = "",
    )
    val openMoveDialog = remember { mutableStateOf(false) }
    val moveDetails = remember { mutableStateOf(Move()) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing,
                ),
            )
            .padding(5.dp),
        shape = RoundedCornerShape(25.dp),
        onClick = {
            expandedState = !expandedState
        },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(modifier = Modifier.width(20.dp))
                Text(
                    modifier = Modifier
                        .weight(6f)
                        .align(Alignment.CenterVertically),
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(0.2f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow",
                    )
                }
            }
            if (expandedState) {
                for (move in moves) {
                    Button(onClick = { moveDetails.value = move; openMoveDialog.value = true }, modifier = Modifier.fillMaxWidth().padding(2.dp), colors = ButtonDefaults.buttonColors(containerColor = convertTypeToColor(move.type))) {
                        Text(text = move.name, color = Color.Black, textAlign = TextAlign.Left, modifier = Modifier.width(250.dp), fontSize = 18.sp)
                        Image(painter = painterResource(id = typeToIconConverter(move.type)), contentDescription = null, Modifier.size(20.dp))
                    }
                }
            }
        }

        when {
            openMoveDialog.value -> {
                MoveDialog(
                    onDismissRequest = { openMoveDialog.value = false },
                    move = moveDetails.value,
                )
            }
        }
    }
}

@Composable
fun MoveDialog(
    onDismissRequest: () -> Unit,
    move: Move,

) {
    Dialog(

        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
        ),

    ) {
        Card(modifier = Modifier.fillMaxWidth().border(3.dp, color = convertTypeToColor(move.type), RoundedCornerShape(10.dp))) {
            Column(Modifier.fillMaxWidth().padding(8.dp).verticalScroll(rememberScrollState())) {
                Text(move.name, fontSize = 25.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.size(8.dp))
                Box(modifier = Modifier.width(100.dp).padding(PaddingValues(end = 5.dp)).background(color = convertTypeToColor(move.type), shape = RoundedCornerShape(12.dp))) {
                    Row(modifier = Modifier.padding(PaddingValues(start = 8.dp))) {
                        Image(
                            painter = painterResource(id = typeToIconConverter(move.type)),
                            "type",
                            Modifier.size(15.dp).align(Alignment.CenterVertically),
                        )
                        Text(
                            text = move.type,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                            modifier = Modifier.background(color = TransparentBrush).height(25.dp).align(Alignment.Bottom).padding(
                                PaddingValues(top = 1.dp, start = 3.dp),
                            ),
                            textAlign = TextAlign.Left,
                        )
                    }
                }
                Spacer(Modifier.size(16.dp))
                Text(move.description, textAlign = TextAlign.Justify)
                Spacer(Modifier.size(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column() {
                        Row() {
                            Text("Save: ", fontWeight = FontWeight.Bold)
                            Text(move.save.ifBlank { "None" })
                        }
                        Row() {
                            Text("Time: ", fontWeight = FontWeight.Bold)
                            Text(move.time.ifBlank { "N/A" })
                        }
                        Column() {
                            Text("Duration: ", fontWeight = FontWeight.Bold)
                            Text(move.duration.ifBlank { "N/A" })
                        }
                        if (move.damage.isNotEmpty()) {
                            Row() {
                                Text("Damage: ", fontWeight = FontWeight.Bold)
                            }
                            for (damage in move.damage.toSortedMap()) {
                                Row() {
                                    Text(text = "Level ${damage.key}: ")
                                    Text(text = "${damage.value.diceAmount}d${damage.value.diceMax}")
                                }
                            }
                        }
                    }
                    Column(Modifier.width(4.dp)) {
//
                    }

                    Column() {
                        Row() {
                            Text("Range: ", fontWeight = FontWeight.Bold)
                            Text(move.range)
                        }
                        Row() {
                            Text("PP: ", fontWeight = FontWeight.Bold)
                            Text(move.powerPoints.toString())
                        }
                        if (move.power.isNotEmpty()) {
                            Row() {
                                Text(text = "Power: ", fontWeight = FontWeight.Bold)
                                for (power in move.power) {
                                    Text(text = "$power ")
                                }
                            }
                        }
                    }
                }

                Spacer(Modifier.size(8.dp))
                Row(Modifier.fillMaxWidth()) {
                    TextButton(onClick = onDismissRequest) {
                        Text("CLOSE", fontWeight = FontWeight.Bold, fontSize = 20.sp, color = MaterialTheme.colorScheme.onPrimary, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}
