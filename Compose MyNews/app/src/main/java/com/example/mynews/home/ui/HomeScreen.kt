package com.example.mynews.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mynews.home.HomeViewModel
import com.kwabenaberko.newsapilib.models.Article

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val articles by homeViewModel.articles.observeAsState(emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(articles) { article ->
                ArticleItem(article)
            }
        }
    }
}

@Composable
fun ArticleItem(article: Article) {
    val noImageUrl = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQwMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQwNGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3N//AABEIAJQAngMBIgACEQEDEQH/xAAbAAEAAwEBAQEAAAAAAAAAAAAAAwQFBgIBB//EADwQAAEDAgQCBgcFCAMAAAAAAAEAAgMEEQUSITETQQYiUXGBkRQyM0JhocEVJENSsSNicnSy0eHwJTZT/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/AP11ERAREQEREBERAREQEREBERAREQEREBERAREQEREBERARF9QfEXvNHDC+eUXawKgMYgcevTOA7WkXQXEUTK+if+I9h/fCnaYpfZTRu7nIPKKQwvHu37l4II5HyQfEREBERAREQEREBERARF4lmji9d2vYN0HtfHuawXcQB8SqMta46Rty/Hmqzi556zi4nTUoL2LzNGHMa38RwPgocEoIatkkk7S5oOUAGyix1wa6CEe6y/0+i18AZkw2M83ku+aCOXAqZ3s3yM8bqnJgEzfZTMd/ELLokQcuaPFab1GyafkfceS8/atdA7JNqebZGWXVLnelD28eBltQwknx0/QoLuHVba6J+aMNez1rHQ9y9FU+j7ctJNKfedYeA/yraAiIgIiICIiAiIg8TX4T8u+XRZOuvzWysuqj4cxHJ2oQRKWlbnqIxyvcqJXMLbedzvytugzcalz4hIB7oDfkuso4+DSQx/lYAuLZ98xQDfizfIla2I9IXlzoqJuUAkF7tz3IOlRYHR7Es8UkVXMMzesHvPLsupazpDTxXbTtMz+29m+aDZvouQ6QTcTFJANmAN+V/qjMQq8TrYYpZC2N7wCxmgte5VCrl49bM8e/IbW+J0+iDp8NZwsJhbzd1vNSKR7eHFFENmMA8go0BERAREQEREBERAVaujzR5hu39FZQgEWI0O6DGVuB3Bw+pmGhym3l/dV5WGOQsPLbuVpkIq8LlpWOyvI8je6DnaGp9Fq4p3NzNYdRe3+7rT/4KoHVkmpie0XAWdLhdbCSXU7z+8zrBU3tdGbSNLTt1hZBu/Y8U2tFiFPN8HaFQTYPXw6mnc5vawhyyLhTw1tTB7GolZ3PKDSwuN8E088jHN4ED3jMLa2t9VUwqPj4jTRkXu8E+Gp/ReajFq2ph4M85cw7iwF+9XeisfExMv5Rxk+eiDpKg3kUa+uN3E9q+ICIiAiIgIiICIiAiIgq1sLn5XsBJGhAUMNHISC7qd+60EQfIgYho9x/iN1K6UvGWRrHt7HC6jRBDLQYfN7SkYCebOr+ipydHqB/sZZoz8SHLSRBhS9GZvwKqJ/wddv91qYLhjsMhkdM9jpZLaM2AHJWkug+IiICIiAiIgIiICIiAqmH4hBXSTRxFzXxPyua7fv7lcG4XNUVNJ6PJXUY+9QVMvV/9GZtWlBrNxWm9DdVvLmRiQxgEXcXA2sAvkOKxvmjhmgnp3SepxWWDvHtWLRuth9FWGNzoYayR8jQLloJOvgr+J1lPiMcFJQyCaV0rHXZ7gBuSexBp1tUyipnVEjS5jbXA+JsojiVOMT+zyHibKHAnY6Xt5KDpLf7Hnvvmb/UFRxCJ0mK4hNEP2tNHDNHbc23HldBquxKBmJMoLOMzhe9tBpfXySoxOngr4qN+biSAG42FyQL+Sx6Vrm1+HVlQQ2SpfLM+/uty6DyUf3mvp6yaOikk9JeHxShzRla31bX7ig36yuipHMY5r5JpL5Io23c7/C8QYlFI2bPFNFJCzO+ORtjb4cisyCuZ6bFiVR1IammEYktcRvB1B7F8ZVzTPqohWCqgbTPLpGx5QHdl0GhTYr6TwyyiqgyS1nloygHnutFc7hE8IipGfbD8+Vo9HAFr/l2XQ80BERAREQEREBERAUMVTTyCXgzRu4R/aZT6veo8UqvQqCaces1tmfFx0CxMKmpqSvpGwvcRPFw58zHAcTcHXe9yEHRQyxSwCSBzTE4EhzdrKEVNGymNU2aJsB3kGgOtliumfQ01ZhUZPFdKGU455ZOzu1UmIupoJsPw2VxFNCM8oa0uvYWA0+Nyg2pJ4G0/HkkYIbA53Hq25FfRLC6Z8TXsMoaHOaNwDsVzZqWydGaynzEmneGNJFiWZhlOq1ab/sNZ/Lx/VBoTyRQxGSdzGRtGrnaALyaiFtMZ+I0QZc2e+gCy8dqYTUUlJOTwXP4k1ml1wNhYdpVJlTG7AcTpWOJbATw7gg5CQRv4hBtw19BUOEMNTA9ztmNI18FPDJC7PHEWHhuyva0aNPZ81hVInacOfWx0zYGzMs6n9cm2l78lfwnSrxX+aI+SCaavw+nmMc08Ecg3adCFZhmjnibLC9skbtQ5uxWLlqjjdf6LHTONo83Gvp1dLWW1AJBCwTNjbJbrCP1fBB7REQEREBERAREQRVFPFUBgmZnDHBwBPMbL7UU8VSwNnbmDXBw1tYjYqREEMlJTyVTKl8QM8Ys1/ML6yniZPJO1lpZLB7r722UqIK09BSzuldLEHGVobIbkZgNl5qcMo6qYzTw5pCACcxGg8VbRBXgoqenfnhjyuyBl7k9Uckmoqad8j5Yg50jOG83IzN3srCIKUGE0EErZY6Zoew3aSSbHxXyXCKCaV8slOC95u45iLnzV5EFKfCqGeUyywXkIALsxF7Cw5q1BDHTwtihblY3YXuvaICIiAiIgIiICIiAiIgIiICIiAiIgIiICIiAiIgIiIP/2Q=="

    Card(
        modifier = Modifier.padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = article.urlToImage?:noImageUrl,
                contentDescription = "News image",
                modifier = Modifier.size(80.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = article.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 3
                )

                Text(
                    text = article.source.name,
                    maxLines = 1,
                    fontSize = 14.sp
                )
            }
        }
    }
}
