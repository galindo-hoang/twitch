package com.example.learning.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.learning.R
import com.example.learning.domain.models.User
import com.example.learning.presentation.ui.theme.*

@ExperimentalMaterialApi
@Composable
fun UserProfileItem(
    user: User,
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    onItemClick: () -> Unit = {},
    onActionItemClick: () -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onItemClick,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = SpaceSmall,
                    horizontal = SpaceMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                modifier = Modifier
                    .size(ProfilePictureSizeSmall)
                    .clip(CircleShape)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
                    .padding(horizontal = SpaceSmall)
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = user.description,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            IconButton(
                onClick = onActionItemClick,
                modifier = Modifier.size(IconSizeMedium)

            ) {
                actionIcon()
            }
        }
    }
}