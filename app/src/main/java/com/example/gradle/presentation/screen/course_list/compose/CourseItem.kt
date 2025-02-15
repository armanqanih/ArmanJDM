package com.example.gradle.presentation.screen.course_list.compose

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gradle.R
import com.example.gradle.presentation.composable.StandardImage
import com.example.gradle.presentation.util.Dimens.SpaceMedium
import com.example.gradle.presentation.util.Dimens.SpaceSmall

@Composable
fun CourseItem (

){

    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(SpaceMedium)
        .clip(shape = RoundedCornerShape(30.dp))
        .height(150.dp)
        .background(androidx.compose.material.MaterialTheme.colors.onBackground)
    , horizontalArrangement = Arrangement.Center,

    ) {
        StandardImage(imageUrl = "",
           modifier = Modifier
               .fillMaxHeight()
               .width(width = 200.dp)
               .clip(shape = RoundedCornerShape(30.dp))
        )
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(SpaceMedium)
            ) {

            Text(text = "The Title",
                color = androidx.compose.material.MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1
                )
            Spacer(modifier = Modifier.height(SpaceSmall))
               Text(text = "Description Description DescriptionD escription ",
                color = androidx.compose.material.MaterialTheme.colors.onPrimary,
                style = MaterialTheme.typography.bodySmall,
                   fontWeight = FontWeight.Normal,
                maxLines = 2,
                   overflow = TextOverflow.Ellipsis
                )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(onClick = {

            }, modifier = Modifier.fillMaxWidth()
                .clip(shape = RoundedCornerShape(20.dp))

                ) {

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(SpaceSmall)
                ) {
                    Text(text = "Button",
                        color = androidx.compose.material.MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.bodyLarge,
                        maxLines = 1
                    )

                  IconButton(onClick = {

                  },

                      ) {
                      Icon(imageVector = Icons.Outlined.Send,
                          contentDescription = ""
                          )
                  }

                }

            }


        }


    }

}