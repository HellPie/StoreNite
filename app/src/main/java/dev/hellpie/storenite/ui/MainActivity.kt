package dev.hellpie.storenite.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.hellpie.storenite.BuildConfig
import dev.hellpie.storenite.models.Cosmetics
import dev.hellpie.storenite.network.FortniteApiService
import dev.hellpie.storenite.ui.compose.Locals
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            StoreNiteApp()
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun StoreNiteApp(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavController = rememberAnimatedNavController()
) {
    CompositionLocalProvider(
        Locals.localScaffoldState provides scaffoldState,
        Locals.localVersionState provides BuildConfig.VERSION_NAME
    ) {
        CosmeticsShopView()
    }
}

@Composable
@Preview
fun CosmeticsShopView() {
    val cosmetics = produceState(initialValue = emptyList<Cosmetics>(), producer = {
        //val shop = FortniteApiService.create().getShop()?.daily
        //if (shop != null)
        //    value = shop.entries.flatMap { it.items ?: emptyList() }
        //else
        //    Timber.w("Failed to load shop, or no daily shop available.")
        value = FortniteApiService.create().getAllCosmetics()
    })

    LazyColumn {
        items(cosmetics.value) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = it.images?.featured?.toString() ?: it.images?.icon?.toString(),
                        contentDescription = "Item Icon",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .defaultMinSize(minHeight = 100.dp, minWidth = 100.dp)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = it.name ?: "No name", fontWeight = FontWeight.Bold)
                    Text(text = it.description ?: "No description")
                }
            }
        }
    }
}
