package com.example.explora2025
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val catBreeds = listOf(
        CatBreed(1, "Siamese", R.drawable.siamese, "Elegant and affectionate. Relaxed and chill.", "Siamese cats are known for being highly intelligent, curious, and active. They are often described as being \"dog-like\" in their behavior, as they enjoy playing fetch, following their owners around, and walking on a leash. Many Siamese cats even enjoy swimming or playing in the water."),
        CatBreed(2, "Persian", R.drawable.persian, "Charming and fluffy. Always look miserable and exhausted.", "With their snub noses, chubby cheeks, and long hair, the Persian cat is quite an exquisite breed. They're also typically quiet and affectionate cats who enjoy being held, but they're content just lounging around too. They make a perfect, purring lap warmer!"),
        CatBreed(3, "Oggy", R.drawable.oggy, "My cat Oggy. Won't stop beating his brother Puszkin up.", "The Scottish fold is a rare breed of cat which came about due to a genetic mutation in the 1960s. They are known for their rounded faces and bodies and unique folds on their ears, which give them what is often referred to as an ‘owl-like’ appearance. Their coats can range in colour from white to black or anything in-between, and can be with markings or solid colours. The long-haired version of the Scottish fold cat is known as a Highland Fold. There has been speculation over the years that these unusually gorgeous cats actually have ancestry from the Orient. This is because a 1796 issue of the Universal Magazine of Knowledge and Pleasure described wild folded-ear cats in China. ")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CatBreedAdapter(
            catBreeds,
            onItemClick = { catBreed ->
                val intent = Intent(this, CatDetailsActivity::class.java).apply {
                    putExtra("imageResId", catBreed.imageResId)
                    putExtra("name", catBreed.name)
                    putExtra("extraDetails", catBreed.extraDetails)
                }
                startActivity(intent)
            },
            onLikeClick = { catBreed ->
                Toast.makeText(this, "Liked: ${catBreed.name}", Toast.LENGTH_SHORT).show()
            },
            onShareClick = { catBreed ->
                Toast.makeText(this, "Shared: ${catBreed.name}", Toast.LENGTH_SHORT).show()
            }
        )
    }
}