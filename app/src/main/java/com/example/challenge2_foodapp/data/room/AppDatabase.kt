package com.example.challenge2_FoodEntityapp.data.room

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.challenge2_foodapp.R
import com.example.challenge2_foodapp.data.entity.CartEntity
import com.example.challenge2_foodapp.data.entity.FoodEntity
import com.example.challenge2_foodapp.data.entity.FoodEntityConverter
import com.example.challenge2_foodapp.data.room.CartDao
import com.example.challenge2_foodapp.data.room.FoodDao

@Database(entities = [FoodEntity::class, CartEntity::class], version = 2, exportSchema = false)
@TypeConverters(FoodEntityConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CartDao(): CartDao
    abstract fun FoodDao(): FoodDao

    companion object {
        private const val DATABASE_NAME = "db_FoodEntity.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        insertFoodEntityDummyData(db)
                    }
                })
                .build()
        }

        private fun insertFoodEntityDummyData(db: SupportSQLiteDatabase) {
            val FoodEntityDummy = listOf(
                FoodEntity(
                    id = 1,
                    name = "Ayam Bakar",
                    price = "20000.0",
                    description = "Restoran ayam bakar adalah tempat makan yang populer yang mengkhususkan diri dalam menyajikan ayam yang dipanggang dengan berbagai bumbu dan saus khas yang menghasilkan cita rasa yang kaya dan lezat.",
                    location = "Tropodo, Kec. Krian, Kabupaten Sidoarjo, Jawa Timur 61262",
                    locationLink = "https://www.google.com/maps/place/ayam+bakar+TOMBO+ROSO/@-7.4193177,112.4914834,12z/data=!4m10!1m2!2m1!1sayam+bakar!3m6!1s0x2e7809fa72353a0b:0x70024ead54330b5d!8m2!3d-7.425008!4d112.5756041!15sCgpheWFtIGJha2FyWgwiCmF5YW0gYmFrYXKSARJjaGlja2VuX3Jlc3RhdXJhbnTgAQA!16s%2Fg%2F11khz068y9?entry=ttu",
                    image = R.drawable.ayam_bakar
                ),
                FoodEntity(
                    id = 2,
                    name = "Cappucino",
                    price = "15000.0",
                    description = "Cappucino sangat peduli tentang kualitas kopi mereka. Mereka mungkin menggunakan biji kopi berkualitas tinggi yang dipanggang dengan cermat untuk menghasilkan rasa kopi yang khas dan nikmat.\n",
                    location = "Jl. Wonokromo Tangkis No.52, Wonokromo, Kec. Wonokromo, Surabaya, Jawa Timur 60243",
                    locationLink = "https://www.google.com/maps/place/Brain+Coffee+Surabaya/@-7.3030023,112.5795561,12z/data=!4m10!1m2!2m1!1scafe+surabaya!3m6!1s0x2dd7fbe4a7ffdb87:0x8a817c8c06e14e25!8m2!3d-7.3030023!4d112.7319914!15sCg1jYWZlIHN1cmFiYXlhWg8iDWNhZmUgc3VyYWJheWGSAQtjb2ZmZWVfc2hvcOABAA!16s%2Fg%2F11f64cflhq?entry=ttu",
                    image = R.drawable.cappucino
                ),
                FoodEntity(
                    id = 3,
                    name = "Burger",
                    price = "25000.0",
                    description = "Burger adalah makanan cepat saji yang terdiri dari sepotong daging, biasanya sapi, yang diolah dan dibentuk menjadi patty. Patty daging ini kemudian dipanggang, digoreng, atau dibakar, lalu ditempatkan di dalam sepotong roti bulat yang sering disebut sebagai bun.",
                    location = "Pakuwon Mall, Unit 2 Mezz. 40, Jl. Puncak Indah Jl. Raya Lontar No.2, Surabaya, Jawa Timur 60227",
                    locationLink = "https://www.google.com/maps/place/E%26W+Burgers+-+Pakuwon+Mall/@-7.2890718,112.5233194,12z/data=!4m9!1m2!2m1!1sburger!3m5!1s0x2dd7fb0dfc686c6b:0xc04f05e3242b6916!8m2!3d-7.2890718!4d112.6757547!16s%2Fg%2F11qh1_dsdr?entry=ttu",
                    image = R.drawable.burger
                ),
                FoodEntity(
                    id = 4,
                    name = "Spaghetti",
                    price = "10000.0",
                    description = "Spaghetti merupakan hidangan yang sering ditemukan dalam masakan Italia dan telah menjadi salah satu makanan favorit di banyak negara di seluruh dunia.",
                    location = "Jl. Raya Darmo Permai I No. 1, Pradahkalikendal, Kec. Dukuhpakis, Kota SBY, Jawa Timur 60226",
                    locationLink = "https://www.google.com/maps/place/Kei's+Spaghetti/@-7.2890569,112.5233191,12z/data=!4m10!1m2!2m1!1sspaghetti!3m6!1s0x2dd7e551de21fdbf:0xc694c1b3c270c28!8m2!3d-7.3556433!4d112.7549684!15sCglzcGFnaGV0dGlaCyIJc3BhZ2hldHRpkgEKcmVzdGF1cmFudOABAA!16s%2Fg%2F11h_vh1kgj?entry=ttu",
                    image = R.drawable.spaghetti
                ),
                FoodEntity(
                    id = 5,
                    name = "Dimsum",
                    price = "30000.0",
                    description = "Dimsum adalah sejenis hidangan kecil dari masakan Tiongkok yang terdiri dari berbagai jenis makanan seperti dumpling, pangsit, pao (bakpao), dan hidangan-hidangan lainnya yang sering disajikan dalam porsi kecil",
                    location = "JH6W+XG4, Sidogolong, Watugolong, Kec. Krian, Kabupaten Sidoarjo, Jawa Timur 61262",
                    locationLink = "https://www.google.com/maps/place/DIMSUM+CHOIE/@-7.289042,112.5233189,12z/data=!4m10!1m2!2m1!1sdimsum!3m6!1s0x2e7809371c86dfc9:0x5408f293769a435a!8m2!3d-7.387607!4d112.5963141!15sCgZkaW1zdW1aCCIGZGltc3VtkgESZGltX3N1bV9yZXN0YXVyYW504AEA!16s%2Fg%2F11fk3fdkm3?entry=ttu",
                    image = R.drawable.dimsum
                ),
                FoodEntity(
                    id = 6,
                    name = "Chicken Wing",
                    price = "40000.0",
                    description = "Chicken wing adalah hidangan yang terbuat dari sayap ayam yang biasanya dipotong menjadi tiga bagian: drumette, flat, dan tip. Bagian sayap ini biasanya digoreng hingga cokelat keemasan dan kemudian dilumuri dengan berbagai jenis saus atau bumbu untuk memberikan rasa yang khas.",
                    location = "Jl. Raya Kertajaya Indah No.29, Manyar Sabrangan, Kec. Mulyorejo, Surabaya, Jawa Timur 60116",
                    locationLink = "https://www.google.com/maps/place/Wingstop+Kertajaya/@-7.2890271,112.5233187,12z/data=!4m10!1m2!2m1!1schicken+wing!3m6!1s0x2dd7fb0e775faed3:0x299983465f6689b5!8m2!3d-7.2801968!4d112.7746129!15sCgxjaGlja2VuIHdpbmdaDiIMY2hpY2tlbiB3aW5nkgEYY2hpY2tlbl93aW5nc19yZXN0YXVyYW50mgEkQ2hkRFNVaE5NRzluUzBWSlEwRm5TVVF0ZFdZeU9HeG5SUkFC4AEA!16s%2Fg%2F11rq1v818w?entry=ttu",
                    image = R.drawable.chicken_wings
                ),
                FoodEntity(
                    id = 7,
                    name = "French Fries",
                    price = "20000.0",
                    description = "French fries adalah sejenis hidangan yang terbuat dari potongan-potongan kentang yang dipotong tipis, dicelupkan dalam minyak panas, dan digoreng hingga berwarna keemasan dan renyah. ",
                    location = "Loop, Graha Festival, Booth H, Pradahkalikendal, Kec. Dukuhpakis, Surabaya, Jawa Timur 60227",
                    locationLink = "https://www.google.com/maps/place/CHI+FRY+CRISPY+SNACK/@-7.2890121,112.5233184,12z/data=!4m10!1m2!2m1!1sFrench+Fries!3m6!1s0x2dd7fc49040acacf:0xf15688895bfe1288!8m2!3d-7.293319!4d112.6763032!15sCgxGcmVuY2ggRnJpZXNaDiIMZnJlbmNoIGZyaWVzkgESY2hpY2tlbl9yZXN0YXVyYW50mgEjQ2haRFNVaE5NRzluUzBWSlEwRm5TVU5GZUhFdE1FUjNFQUXgAQA!16s%2Fg%2F11f15hkpls?entry=ttu",
                    image = R.drawable.french_fries
                ),
                FoodEntity(
                    id = 8,
                    name = "Fried Chicken",
                    price = "35000.0",
                    description = "FoodEntity fried chicken adalah hidangan yang terdiri dari potongan-potongan daging ayam yang dicelupkan ke dalam adonan tepung dan kemudian digoreng hingga menjadi berwarna kecokelatan dan renyah di luar serta juicy di dalam. ",
                    location = "Supermall Pakuwon Lantai B1, Jl. Puncak Indah Lontar No. 2, Babatan, Wiyung, Kota SBY, Babatan, Kec. Bubutan, Surabaya, Jawa Timur 60174",
                    locationLink = "https://www.google.com/maps/place/KFC+-+Pakuwon+Mall/@-7.2889823,112.5233179,12z/data=!4m10!1m2!2m1!1skfc!3m6!1s0x2dd7fa0933de20fd:0x15643a1ea53890a6!8m2!3d-7.2883215!4d112.6758181!15sCgNrZmMiA4gBAVoFIgNrZmOSARZmcmllZF9jaGlja2VuX3Rha2Vhd2F54AEA!16s%2Fg%2F12ml2yksh?entry=ttu",
                    image = R.drawable.fried_chicken
                ),
                FoodEntity(
                    id = 9,
                    name = "Ayam Panggang",
                    price = "50000.0",
                    description = "FoodEntity ayam panggang adalah hidangan yang terbuat dari potongan-potongan daging ayam yang dipanggang dengan berbagai bumbu dan saus. ",
                    location = "Jl. Raya Perning, Perning, Kec. Jetis, Kabupaten Mojokerto, Jawa Timur 61352",
                    locationLink = "https://www.google.com/maps/place/Ayam+Panggang+Perning/@-7.4067379,112.3388186,12z/data=!4m10!1m2!2m1!1sayam+panggang!3m6!1s0x2e780ee485beda99:0x9347a20329bfc7c8!8m2!3d-7.4067379!4d112.4912539!15sCg1heWFtIHBhbmdnYW5nWg8iDWF5YW0gcGFuZ2dhbmeSARJjaGlja2VuX3Jlc3RhdXJhbnTgAQA!16s%2Fg%2F11c6dfs_qz?entry=ttu",
                    image = R.drawable.ayam_panggang
                ),
                FoodEntity(
                    id = 10,
                    name = "Sate Ayam",
                    price = "30000.0",
                    description = "Sate ayam adalah hidangan khas Indonesia yang terdiri dari potongan-potongan daging ayam yang ditusuk dengan bambu atau tusukan logam, kemudian dipanggang atau dibakar dengan menggunakan arang atau panggangan hingga matang.",
                    location = "HHVJ+CQ6, Jl. Jagalan Tengah, Bibis, Tambak Kemerakan, Kec. Krian, Kabupaten Sidoarjo, Jawa Timur 61262",
                    locationLink = "https://www.google.com/maps/place/sate+ayam+ponorogo/@-7.4067228,112.3388183,12z/data=!4m10!1m2!2m1!1ssate+ayam!3m6!1s0x2e7809af87fa0213:0x9c2edfead90a1fce!8m2!3d-7.4064607!4d112.5818793!15sCglzYXRlIGF5YW1aCyIJc2F0ZSBheWFtkgEQc2F0YXlfcmVzdGF1cmFudOABAA!16s%2Fg%2F11f5dxrn81?entry=ttu",
                    image = R.drawable.sate_ayam
                ),
                FoodEntity(
                    id = 11,
                    name = "Ramen",
                    price = "60000.0",
                    description = "Ramen adalah hidangan mie populer asal Jepang yang terdiri dari mie gandum yang tebal dan kenyal yang disajikan dalam kuah kaldu yang kaya dan beraroma. ",
                    location = "Pakuwon Mall, Jl. Mayjend. Jonosewojo No.2, Babatan, Kec. Wiyung, Surabaya, Jawa Timur 60227",
                    locationLink = "https://www.google.com/maps/place/Marugame+Udon,+Pakuwon+Mall/@-7.4065939,112.091555,10z/data=!4m10!1m2!2m1!1smarugame+udon!3m6!1s0x2dd7fc365875c8a1:0xe4921d582e0cffe!8m2!3d-7.2880285!4d112.6759646!15sCg1tYXJ1Z2FtZSB1ZG9uIgOIAQFaDyINbWFydWdhbWUgdWRvbpIBEHVkb25fbm9vZGxlX3Nob3DgAQA!16s%2Fg%2F11csqw73dr?entry=ttu",
                    image = R.drawable.ramen
                ),
                FoodEntity(
                    id = 12,
                    name = "Pizza",
                    price = "70000.0",
                    description = "Pizza adalah hidangan yang terdiri dari adonan yang tipis dan pipih yang biasanya dibuat dari tepung terigu, air, ragi, garam, dan minyak. Adonan ini kemudian diolesi dengan saus tomat, lapisan keju, dan berbagai topping sesuai selera.",
                    location = "Pakuwon Mall Lantai B1 Unit 42 – 45, jalan Puncak Indah Jl. Raya Lontar No.2, Babatan, Kec. Wiyung, Surabaya, Jawa Timur 60123",
                    locationLink = "https://www.google.com/maps/place/Pizza+Hut+Restoran+-+Supermall+Pakuwon+Indah/@-7.3697112,112.5166281,12z/data=!4m10!1m2!2m1!1spizza+hut!3m6!1s0x2dd7fc3640f204a3:0xacd9ab809daa91ae!8m2!3d-7.289313!4d112.6752187!15sCglwaXp6YSBodXQiA4gBAVoLIglwaXp6YSBodXSSARBwaXp6YV9yZXN0YXVyYW504AEA!16s%2Fg%2F1tcx4fcf?entry=ttu",
                    image = R.drawable.pizza
                ),
                FoodEntity(
                    id = 13,
                    name = "Sushi",
                    price = "30000.0",
                    description = "Sushi adalah hidangan Jepang yang terkenal di seluruh dunia. Ini adalah makanan yang terbuat dari nasi yang diberi tambahan bahan-bahan seperti potongan ikan mentah, makanan laut, sayuran, atau telur.l",
                    location = "Jl. Manyar Kertoarjo No.71, Mojo, Kec. Gubeng, Surabaya, Jawa Timur 60285",
                    locationLink = "https://www.google.com/maps/place/Sushi+Hiro+-+Manyar+Kertoarjo/@-7.280028,112.7675883,17z/data=!3m1!4b1!4m6!3m5!1s0x2dd7fb6b4f38b9df:0xff3008f27216a1c6!8m2!3d-7.280028!4d112.7701632!16s%2Fg%2F11v06dzddj?entry=ttu",
                    image = R.drawable.sushi
                ),
                FoodEntity(
                    id = 14,
                    name = "Strawberry Milkshake",
                    price = "25000.0",
                    description = "Strawberry milkshake adalah minuman yang lezat dan segar yang terbuat dari campuran stroberi segar atau sirup stroberi dengan susu dan es krim.",
                    location = "Jl. Bratang Gede No.24-26, Ngagelrejo, Kec. Wonokromo, Surabaya, Jawa Timur 60245",
                    locationLink = "https://www.google.com/maps/place/Schuim+Milkshake/@-7.298317,112.7459304,17z/data=!4m10!1m2!2m1!1sstrawberry+milkshake!3m6!1s0x2dd7fb32d68d814f:0x7a6172a11fe97e06!8m2!3d-7.298317!4d112.750694!15sChRzdHJhd2JlcnJ5IG1pbGtzaGFrZVoWIhRzdHJhd2JlcnJ5IG1pbGtzaGFrZZIBBGNhZmXgAQA!16s%2Fg%2F11j7h5qjtb?entry=ttu",
                    image = R.drawable.strawberry_milkshake,
                )
            )

            val contentValues = ContentValues()

            for (food in FoodEntityDummy) {
                contentValues.clear()
                contentValues.put("food_id", food.id)
                contentValues.put("food_name", food.name)
                contentValues.put("food_price", food.price)
                contentValues.put("food_description", food.description)
                contentValues.put("food_location", food.location)
                contentValues.put("food_location_link", food.locationLink)
                contentValues.put("food_image", food.image)

                db.insert("food_entity", SQLiteDatabase.CONFLICT_REPLACE, contentValues)
            }
        }
    }
}