package com.bignerdranch.android.course.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.bignerdranch.android.course.ui.model.entity.NavigationItem

@Composable
fun MainFrame() {
    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "任务", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person),
    )
    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }
    Scaffold(bottomBar = {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.surface,
        ) {
            navigationItems.forEachIndexed { index, navigationItem ->
                BottomNavigationItem(
                    selected = currentNavigationIndex == index,
                    onClick = {
                        currentNavigationIndex = index
                    },
                    icon = {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = navigationItem.title)
                    },
                    selectedContentColor = Color(0xFF149EE4),
                    unselectedContentColor = Color(0xFF999999)
                )
            }
        }
    }) {
//        Text(text = "current navigation item: $currentNavigationIndex")
        when (currentNavigationIndex) {
0->Stu
        }
    }
}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}