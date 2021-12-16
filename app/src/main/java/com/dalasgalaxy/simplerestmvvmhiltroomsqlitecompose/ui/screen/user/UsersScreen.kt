package com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.screen.user

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.R
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.model.user.User
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.navigation.Screen
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel.SettingsViewModel
import com.dalasgalaxy.simplerestmvvmhiltroomsqlitecompose.ui.viewmodel.UserViewModel
import com.valentinilk.shimmer.shimmer

@ExperimentalCoilApi
@Composable
fun Users(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController,
    settingsViewModel: SettingsViewModel
) {
    val users by viewModel.users.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
    MyApp(
        onAddClicked = { viewModel.addUser() },
        onDeleteClicked = { viewModel.deleteUser(it) },
        users = users,
        isLoading = isLoading,
        navController = navController
    )
}

@ExperimentalCoilApi
@Composable
fun MyApp(
    onAddClicked: (() -> Unit)? = null,
    onDeleteClicked: ((userToDelete: User) -> Unit)? = null,
    users: List<User>,
    isLoading: Boolean,
    navController: NavController
) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Simple Modern App by Feryael Justice") },
            actions = {
                IconButton(onClick = { onAddClicked?.invoke() }) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
                }
            })
    }) {
        LazyColumn {
            var itemCount = users.size
            if (isLoading) itemCount++
            items(count = itemCount) { index ->
                var auxIndex = index
                if (isLoading) {
                    if (auxIndex == 0) {
                        return@items LoadingCard()
                    }
                    auxIndex--
                }
                val user = users[auxIndex]
                Surface(modifier = Modifier.clickable {
                    navController.navigate(
                        Screen.UserDetailScreen.withArgs(
                            user.id
                        )
                    ) {
                        launchSingleTop = true
                    }
                }) {
                    UserCard(
                        user = user,
                        onDeleteClicked = onDeleteClicked,
                        shape = RoundedCornerShape(8.dp),
                        elevation = 1.dp,
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }

    }
}

@ExperimentalCoilApi
@Composable
fun UserCard(
    user: User,
    onDeleteClicked: ((userToDelete: User) -> Unit)?,
    shape: Shape,
    elevation: Dp,
    modifier: Modifier
) {
    Card(shape = shape, elevation = elevation, modifier = modifier) {
        Row(modifier = Modifier.padding(8.dp)) {
            Image(
                modifier = Modifier.size(50.dp),
                painter = rememberImagePainter(data = user.thumbnail, builder = {
                    placeholder(R.drawable.ic_baseline_image_24)
                    error(R.drawable.ic_baseline_image_24)
                }),
                contentDescription = "User image",
                contentScale = ContentScale.FillHeight
            )
            Spacer()
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${user.name} ${user.lastName}")
                Text(text = user.city)
            }
            Spacer()
            IconButton(onClick = {
                onDeleteClicked?.invoke(user)
            }) {
                Icon(Icons.Filled.Delete, "Remove")
            }
        }
    }
}

@Composable
fun LoadingCard() {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 1.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ImageLoading()
            Spacer()
            Column {
                Spacer()
                Box(modifier = Modifier.shimmer()) {
                    Column {
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                        Spacer()
                        Box(
                            modifier = Modifier
                                .height(15.dp)
                                .fillMaxWidth()
                                .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageLoading() {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
fun Spacer(size: Int = 8) = Spacer(modifier = Modifier.size(size.dp))