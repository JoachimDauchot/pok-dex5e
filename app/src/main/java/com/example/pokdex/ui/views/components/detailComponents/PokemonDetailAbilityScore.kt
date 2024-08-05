package com.example.pokdex.ui.views.components.detailComponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokdex.model.Attributes
import com.example.pokdex.ui.converters.AttributesStringConverter
import com.example.pokdex.ui.theme.BugBrush
import com.example.pokdex.ui.theme.DragonBrush
import com.example.pokdex.ui.theme.ElectricBrush
import com.example.pokdex.ui.theme.FireBrush
import com.example.pokdex.ui.theme.FlyingBrush
import com.example.pokdex.ui.theme.GrassBrush
import com.example.pokdex.ui.theme.Gray100
import com.example.pokdex.ui.theme.PoisonBrush
import com.example.pokdex.ui.theme.WaterBrush

@Composable
fun PokemonDetailAbilityScore(attributes: Attributes, hp: Int, ac: Int, skills: List<String>, savingThrow: List<String>) {
    Column(modifier = Modifier.fillMaxWidth().padding(5.dp)) {
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            if (skills.isNotEmpty()) {
                Text("Skills: ", fontWeight = FontWeight.ExtraBold)
                for (skill in skills) {
                    Text(text = "$skill ")
                }
            }
        }
        Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxWidth().padding(4.dp)) {
            if (savingThrow.isNotEmpty()) {
                Text("Saving throws: ", fontWeight = FontWeight.ExtraBold)
                for (save in savingThrow) {
                    Text(text = "${AttributesStringConverter(save)} ")
                }
            }
        }
        Spacer(modifier = Modifier.size(10.dp))
        val modifier = Modifier.align(Alignment.CenterHorizontally)
        AttributeRow(text = "HP", maxValue = 160f, attribute = hp, color = GrassBrush, modifier)
        AttributeRow(text = "AC", maxValue = 30f, attribute = ac, color = WaterBrush, modifier)
        Spacer(modifier = Modifier.size(10.dp))
        Divider(color = Gray100, thickness = 2.dp, modifier = modifier.width(300.dp))
        Spacer(modifier = Modifier.size(10.dp))
        AttributeRow(text = "STR", maxValue = 25f, attribute = attributes.strength, color = FireBrush, modifier)
        AttributeRow(text = "DEX", maxValue = 25f, attribute = attributes.dexterity, color = FlyingBrush, modifier)
        AttributeRow(text = "CON", maxValue = 25f, attribute = attributes.constitution, color = BugBrush, modifier)
        AttributeRow(text = "INT", maxValue = 25f, attribute = attributes.intelligence, color = DragonBrush, modifier)
        AttributeRow(text = "WIS", maxValue = 25f, attribute = attributes.wisdom, color = PoisonBrush, modifier)
        AttributeRow(text = "CHA", maxValue = 25f, attribute = attributes.charisma, color = ElectricBrush, modifier)
    }
}

@Composable
private fun AttributeRow(text: String, maxValue: Float, attribute: Int, color: Color, modifier: Modifier) {
    Row(modifier = modifier.padding(5.dp).width(400.dp).height(18.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(text = text, fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.width(43.dp))
        LinearProgressIndicator(progress = attribute / maxValue, color = color, modifier = Modifier.fillMaxHeight())
        Text(text = attribute.toString(), fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, modifier = Modifier.width(43.dp))
    }
}
