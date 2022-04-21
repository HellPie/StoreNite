package dev.hellpie.storenite.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Beenhere
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.accompanist.insets.ui.LocalScaffoldPadding
import dev.hellpie.storenite.models.Cosmetics
import dev.hellpie.storenite.network.FortniteApiService
import timber.log.Timber

enum class NavigationRoutes {
    SHOP_ALL,
    SHOP_DAILY,
    SHOP_FAVOURITES
}

data class NavigationItem(val id: NavigationRoutes, val name: String, val icon: ImageVector)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun StoreNiteApp() {
    val colorScheme = when {
        isDynamicThemingSupported && isSystemInDarkTheme() -> dynamicDarkColorScheme(LocalContext.current)
        isDynamicThemingSupported && !isSystemInDarkTheme() -> dynamicLightColorScheme(LocalContext.current)
        isSystemInDarkTheme() -> darkTheme
        else -> lighTheme
    }
    
    val navigation = rememberNavController()
    val navigationBarItems = listOf(
        NavigationItem(NavigationRoutes.SHOP_DAILY, "Daily", Icons.Outlined.CalendarToday),
        NavigationItem(NavigationRoutes.SHOP_FAVOURITES, "Favourites", Icons.Outlined.Beenhere),
        NavigationItem(NavigationRoutes.SHOP_ALL, "All", Icons.Outlined.Apps)
    )

    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = typography,
//        shapes = cornerShapes
    ) {
        Scaffold(
            bottomBar = {
                NavigationBar {
                    val backStack by navigation.currentBackStackEntryAsState()
                    val destination = backStack?.destination

                    navigationBarItems.forEach { item ->
                        NavigationBarItem(
                            label = { Text(text = item.name) },
                            icon = { Icon(imageVector = item.icon, contentDescription = null) },
                            selected = destination?.route == item.id.name,
                            onClick = {
                                Timber.d("Navigating to ${item.id.name}")
                                navigation.navigate(item.id.name) {
                                    popUpTo(navigation.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = navigation,
                startDestination = NavigationRoutes.SHOP_DAILY.name,
                modifier = Modifier.padding(it)
            ) {
                composable(NavigationRoutes.SHOP_DAILY.name) { ShopDaily() }
                composable(NavigationRoutes.SHOP_FAVOURITES.name) { Text(text = "Favourite Cosmetics") }
                composable(NavigationRoutes.SHOP_ALL.name) { ShopAll() }
            }
        }
    }
}

@Composable
fun ShopDaily() {
    val cosmetics = produceState(initialValue = emptyList(), producer = {
        val shop = FortniteApiService.create().getShop()?.daily
        if (shop != null)
            value = shop.entries.flatMap { it.items ?: emptyList() }
        else
            Timber.w("Failed to load shop, or no daily shop available.")
    })

    CosmeticsList(cosmetics = cosmetics.value)
}

@Composable
fun ShopAll() {
    val cosmetics = produceState(initialValue = emptyList<Cosmetics>(), producer = {
        value = FortniteApiService.create().getAllCosmetics()
    })

    CosmeticsList(cosmetics = cosmetics.value)
}

@Composable
fun CosmeticsList(
    cosmetics: List<Cosmetics>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(cosmetics.size) {
            CosmeticCard(
                cosmetic = cosmetics[it],
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CosmeticCard(
    cosmetic: Cosmetics
) {
    OutlinedCard(
        modifier = Modifier.padding(4.dp)
    ) {
        AsyncImage(
            model = cosmetic.images?.featured?.toString() ?: cosmetic.images?.icon?.toString(),
            contentDescription = "Item Icon",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .background(Brush.radialGradient(
                    0f to Color(red = 0xfd, green = 0xd6, blue = 0x49, alpha = 0xff),
                    1f to Color(red = 0xa3, green = 0x30, blue = 0x25, alpha = 0xff),
                ))
                .background(Brush.linearGradient(
                    0.4f to Color(red = 0xff, green = 0x77, blue = 0x00, alpha = 0x00),
                    1f to Color(red = 0xff, green = 0x77, blue = 0x00, alpha = 0xff),
                    start = Offset(50f, 0f),
                    end = Offset(50f, Float.POSITIVE_INFINITY)
                ))
                .padding(8.dp)
                .fillMaxWidth()
                .defaultMinSize(minHeight = 100.dp, minWidth = 100.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = cosmetic.name ?: "No name",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 2.dp)
                .align(CenterHorizontally)
        )

        Text(
            text = cosmetic.description ?: "No description",
            modifier = Modifier
                .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 16.dp)
                .align(CenterHorizontally)
        )
    }
}
