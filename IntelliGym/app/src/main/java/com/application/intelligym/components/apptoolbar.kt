import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.application.intelligym.R // <-- Change mo to sa actual path ng R mo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    isLoggedIn: Boolean,
    onLoginClick: () -> Unit,
    onLogoutClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    TopAppBar(
        title = { Text("IntelliGym") },
        actions = {
            Box(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .clickable { expanded = true }
            ) {
                // Profile Picture / Icon
                Image(
                    painter = painterResource(id = R.drawable.profile), // <-- replace mo ng profile icon mo!
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(4.dp)
                )

                // Dropdown menu
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Profile") },
                        onClick = {
                            expanded = false
                            // TODO: Navigate to Profile page if you want
                        }
                    )

                    if (isLoggedIn) {
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {
                                expanded = false
                                onLogoutClick()
                            }
                        )
                    } else {
                        DropdownMenuItem(
                            text = { Text("Login") },
                            onClick = {
                                expanded = false
                                onLoginClick()
                            }
                        )
                    }
                }
            }
        }
    )
}