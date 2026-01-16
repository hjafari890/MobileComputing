package com.example.hw1
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hw1.ui.theme.Hw1Theme
import com.example.hw1.ui.theme.SampleData
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

data class Message(val author: String, val body: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Hw1Theme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main") {

                    composable("main") {
                        MainScreen(onNavigateToHw1 = {
                            navController.navigate("hw1") {
                                popUpTo("main") { saveState = true }
                                launchSingleTop = true
                            }
                        })
                    }

                    composable("hw1") {
                        Hw1Screen {
                            navController.navigate(route = "main") {
                                popUpTo("main") { inclusive = true }
                            }

                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Hw1Screen(onBack: () -> Unit) {
        Column(modifier = Modifier.fillMaxSize()) {
            androidx.compose.material3.Button(
                onClick = onBack,
                modifier = Modifier.padding(16.dp)
            ) {
                androidx.compose.material3.Text("← Back to Home")
            }

            // This calls your existing list function
            Conversation(com.example.hw1.ui.theme.SampleData.conversationSample)
        }
    }

    @Composable
    fun MessageCard(msg: Message) {
        var isExpanded by remember { mutableStateOf(false) }

        Row(modifier = Modifier.padding(all = 15.dp)) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Contact profile picture",
                modifier = Modifier

                    .size(70.dp)
                    .clip(CircleShape)
                    .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            val surfaceColor by animateColorAsState(
                if (isExpanded) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
            )

            Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
                Text(
                    text = msg.author,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.titleSmall
                )

                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                    shadowElevation = 5.dp,
                    color = surfaceColor,
                    modifier = Modifier.animateContentSize().padding(1.dp)
                )
                {
                    Text(
                        text = msg.body,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }


            }
        }
    }


    @Composable
    fun Conversation(messages: List<Message>) {
        LazyColumn {
            items(messages) { message ->
                MessageCard(msg = message)
            }
        }
    }

    @Preview(name = "Light Mode")
    @Preview(
        uiMode = Configuration.UI_MODE_NIGHT_YES,
        showBackground = true,
        name = "Dark Mode"
    )
    @Composable
    fun PreviewConversation() {
        Hw1Theme {
            Conversation(SampleData.conversationSample)
        }
    }

    @Composable
    fun PreviewMessageCard() {
        Hw1Theme() {
            Surface {
                MessageCard(
                    msg = Message("HELLLOOO", "Have a look at this, great!")
                )

            }
        }
    }
}

/// test


