package com.leiber.listup.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.leiber.listup.ui.theme.ListUpTheme
import kotlinx.coroutines.launch

@Composable
fun HelloWorld(
    modifier: Modifier = Modifier
) {
    Text(
        text = "Pepe World",
        modifier = modifier // The operations are applied in that order.
            .background(Color.Red)
            .padding(16.dp),
        fontWeight = FontWeight.Bold,
        color = Color.Blue
    )
}

// Help preview changes
@Preview(
    showBackground = true
)
@Composable
fun HelloWorldPreview() {
    // Define that the composable is displayed within my theme settings.
    ListUpTheme {
        HelloWorld(modifier = Modifier)
    }
}


@Composable
fun ClickableTextExample(
    modifier: Modifier = Modifier
) {
    // Status management (when the state changes, it triggers a recomposition of the composable)
    var text by remember { mutableStateOf("Click me!") }

    Text(
        text = text,
        fontSize = 16.sp,
        modifier = modifier
            .clickable {
                text = "This is a new text!"
            }
    )
}

@Preview(
    showBackground = true
)
@Composable
fun ClickableTextExamplePreview() {
    ListUpTheme {
        ClickableTextExample()
    }
}


@Composable
fun IconExample(
    modifier: Modifier = Modifier,
    iconContainer: IconContainer
) {
    Icon(
        imageVector = iconContainer.icon,
        contentDescription = "Favorite icon",
        modifier = modifier
            .size(48.dp)
            .border(
                BorderStroke(2.dp, Color.Gray),
                shape = CircleShape
            )
            .padding(8.dp)
    )
}

@Preview(
    showBackground = true
)
@Composable
fun IconExamplePreview(
    @PreviewParameter(IconProvider::class) icon: IconContainer
) {
    ListUpTheme {
        IconExample(
            iconContainer = icon
        )
    }
}


@Composable
fun RowView(
) {
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    )
    {
        HelloWorld()
        IconExample(iconContainer = IconContainer(Icons.Default.Favorite))
    }
}

@Preview(
    showBackground = true
)
@Composable
fun RowViewPreview() {
    ListUpTheme {
        RowView()
    }
}


@Composable
fun ColumnView() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelloWorld()
        IconExample(iconContainer = IconContainer(Icons.Default.Favorite))
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ColumnViewPreview() {
    ListUpTheme {
        ColumnView()
    }
}

@Composable
fun PageExampleView() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { /* Drawer content */ }
        },
    ) {
        Scaffold(
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text("Show drawer") },
                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                    onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
                )
            }
        ) { contentPadding ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Screen content", modifier = Modifier.padding(contentPadding))
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun MenuViewPreview() {
    ListUpTheme {
        PageExampleView()
    }
}