package kz.axelrod.cryptofun.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kz.axelrod.cryptofun.ui.theme.backgroundBtnNotEnabled
import kz.axelrod.cryptofun.ui.theme.green3
import kz.axelrod.cryptofun.ui.theme.white

@Composable
fun CustomYellowButton(
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = green3,
            contentColor = white,
            disabledContainerColor = backgroundBtnNotEnabled
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        content()
    }
}
