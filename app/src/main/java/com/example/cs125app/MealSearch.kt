package com.example.cs125app

import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Place{
    lateinit var myName: String
    lateinit var myFoods: Array<String>
    var myPos: Int = -1
    var myPop: Int = -1
    var isBreakfast: Int = -1
    var myScore: Int = 0

    fun createPlace(name: String, foods: Array<String>, pos: Int, pop: Int, breakfast: Int)
    {
        myName = name
        myFoods = foods
        myPos = pos
        myPop = pop
        isBreakfast = breakfast
    }
}
lateinit var buttonReturn: Button
lateinit var buttonCalculate: Button
lateinit var buttonAddMeal: Button
lateinit var button1: RadioButton
lateinit var button2: RadioButton
lateinit var button3: RadioButton
lateinit var cbPastries: CheckBox
lateinit var cbCoffee: CheckBox
lateinit var cbTea: CheckBox
lateinit var cbPancakes: CheckBox
lateinit var cbEggs: CheckBox
lateinit var cbMeats: CheckBox
lateinit var cbSandwich: CheckBox
lateinit var cbBurger: CheckBox
lateinit var cbPizza: CheckBox
lateinit var cbSoup: CheckBox
lateinit var cbSalad: CheckBox
lateinit var cbTaco: CheckBox
lateinit var cbBeef: CheckBox
lateinit var cbChicken: CheckBox
lateinit var cbFish: CheckBox
lateinit var cbDessert: CheckBox
lateinit var imageView1: ImageView
lateinit var imageView2: ImageView
lateinit var imageView3: ImageView
lateinit var textFirst: TextView
lateinit var textSecond: TextView
lateinit var textThird: TextView

lateinit var buttonTypes: RadioGroup
class MealSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_search)


        buttonReturn = findViewById(R.id.buttonReturn)
        buttonCalculate = findViewById(R.id.buttonCalculate)
        buttonAddMeal = findViewById(R.id.buttonAddMeal)
        cbPastries = findViewById(R.id.cbPastries)
        cbCoffee = findViewById(R.id.cbCoffee)
        cbTea = findViewById(R.id.cbTea)
        cbPancakes = findViewById(R.id.cbPancakes)
        cbEggs = findViewById(R.id.cbEggs)
        cbMeats = findViewById(R.id.cbMeats)
        cbSandwich = findViewById(R.id.cbSandwich)
        cbBurger = findViewById(R.id.cbBurger)
        cbPizza = findViewById(R.id.cbPizza)
        cbSoup = findViewById(R.id.cbSoup)
        cbSalad = findViewById(R.id.cbSalad)
        cbTaco = findViewById(R.id.cbTaco)
        cbBeef = findViewById(R.id.cbBeef)
        cbChicken = findViewById(R.id.cbChicken)
        cbFish = findViewById(R.id.cbFish)
        cbDessert = findViewById(R.id.cbDessert)
        buttonTypes = findViewById(R.id.buttonsViews)
        button1 = findViewById(R.id.radioButton3)
        button2 = findViewById(R.id.radioButton4)
        button3 = findViewById(R.id.radioButton5)
        imageView1 = findViewById(R.id.imageView1)
        imageView2 = findViewById(R.id.imageView2)
        imageView3 = findViewById(R.id.imageView3)
        textFirst = findViewById(R.id.textFirst)
        textSecond = findViewById(R.id.textSecond)
        textThird = findViewById(R.id.textThird)


        val places = arrayOf("Starbucks", "Dunkin", "Breakfast Republic", "Peet's Coffee", "Le Diplomatic Cafe",
            "Flippoly", "Jersey Mike's", "Blaze Pizza", "Sgt. Pepperoni's Pizza", "Luna Grill", "Jack in the Box",
            "In n Out", "The Habit", "Chick-fil-A", "Chipotle", "Taco Bell", "Del Taco", "Tender Greens"
            , "KY Sushi", "Iro Sushi Stuff X Roll"
            , "The Buffalo Spot", "Yogurtland", "Insomnia Cookies", "Share Tea", "Cha for Tea")
        val foodTypes = arrayOf("Pastries", "Coffee", "Tea", "Pancakes", "Eggs", "Meats", "Sandwich", "Burger", "Pizza", "Soup", "Salad", "Taco", "Beef", "Chicken", "Fish", "Dessert")
        val settings = getSharedPreferences("UserInfo", 0)
        var x = 0
        var objectList = MutableList(0) {Place()}
        for(i in places)
        {
            var foodlist = arrayOf("")
            var isBreakfast = -1
            if (i == "Starbucks"){ foodlist = arrayOf("Pastries", "Coffee", "Tea", "Eggs", "Sandwich"); isBreakfast = 1}
            if (i == "Dunkin"){ foodlist = arrayOf("Pastries", "Coffee", "Tea", "Eggs", "Sandwich"); isBreakfast = 1}
            if (i == "Breakfast Republic"){ foodlist = arrayOf("Pancakes", "Coffee", "Eggs", "Meats", "Pastries", "Tea"); isBreakfast = 1}
            if (i == "Peet's Coffee"){ foodlist = arrayOf("Coffee", "Tea", "Eggs", "Pastries", "Sandwich"); isBreakfast = 1}
            if (i == "Le Diplomatic Cafe"){ foodlist = arrayOf("Coffee", "Tea", "Eggs", "Pastries", "Sandwich", "Meats", "Salad"); isBreakfast = 1}
            if (i == "Flippoly"){ foodlist = arrayOf("Sandwich", "Meats", "Eggs", "Coffee"); isBreakfast = 0}
            if (i == "Jersey Mike's"){ foodlist = arrayOf("Sandwich", "Meats", "Beef", "Chicken"); isBreakfast = 0}
            if (i == "Blaze Pizza"){ foodlist = arrayOf("Pizza", "Pastries", "Salad", "Dessert"); isBreakfast = 0}
            if (i == "Sgt. Pepperoni's Pizza"){ foodlist = arrayOf("Pizza", "Salad", "Sandwich", "Dessert"); isBreakfast = 0}
            if (i == "Luna Grill"){ foodlist = arrayOf("Salad", "Chicken", "Beef", "Fish", "Meats"); isBreakfast = 0}
            if (i == "Jack in the Box"){ foodlist = arrayOf("Burger", "Beef", "Chicken"); isBreakfast = 0}
            if (i == "In n Out"){ foodlist = arrayOf("Burger", "Beef"); isBreakfast = 0}
            if (i == "The Habit"){ foodlist = arrayOf("Burger", "Beef", "Chicken", "Salad"); isBreakfast = 0}
            if (i == "Chick-fil-A"){ foodlist = arrayOf("Chicken", "Sandwich", "Salad"); isBreakfast = 0}
            if (i == "Chipotle"){ foodlist = arrayOf("Taco", "Salad", "Chicken", "Beef"); isBreakfast = 0}
            if (i == "Taco Bell"){ foodlist = arrayOf("Taco", "Beef", "Chicken"); isBreakfast = 0}
            if (i == "Del Taco"){ foodlist = arrayOf("Taco", "Chicken", "Beef", "Salad"); isBreakfast = 0}
            if (i == "Tender Greens"){ foodlist = arrayOf("Salad", "Sandwich", "Chicken", "Beef", "Fish", "Soup"); isBreakfast = 0}
            if (i == "KY Sushi"){ foodlist = arrayOf("Fish", "Salad"); isBreakfast = 0}
            if (i == "Iro Sushi Stuff X Roll"){ foodlist = arrayOf("Fish"); isBreakfast = 0}
            if (i == "The Buffalo Spot"){ foodlist = arrayOf("Chicken", "Salad"); isBreakfast = 0}
            if (i == "Yogurtland"){ foodlist = arrayOf("Dessert"); isBreakfast = 0}
            if (i == "Insomnia Cookies"){ foodlist = arrayOf("Pastries", "Dessert"); isBreakfast = 0}
            if (i == "Share Tea"){ foodlist = arrayOf("Tea"); isBreakfast = 0}
            if (i == "Cha for Tea"){ foodlist = arrayOf("Tea"); isBreakfast = 0}

            var temp = Place()
            temp.createPlace(i, foodlist, x, settings.getInt(i,0), isBreakfast)
            objectList.add(objectList.size,temp)
            x++
        }
        println("objectList = " + objectList.size)
        for (i in objectList)
        {
            println("Object = "+ i.myName + ", " + i.myFoods.contentToString() + ", "+ i.myPos+", "+i.myPop+", "+i.isBreakfast)
        }

        buttonCalculate.setOnClickListener {
            var checkedList = mutableListOf<String>()
            if(cbPastries.isChecked){checkedList.add(checkedList.size,"Pastries")}
            if(cbCoffee.isChecked){checkedList.add(checkedList.size,"Coffee")}
            if(cbTea.isChecked){checkedList.add(checkedList.size,"Tea")}
            if(cbPancakes.isChecked){checkedList.add(checkedList.size,"Pancakes")}
            if(cbEggs.isChecked){checkedList.add(checkedList.size,"Eggs")}
            if(cbMeats.isChecked){checkedList.add(checkedList.size,"Meats")}
            if(cbSandwich.isChecked){checkedList.add(checkedList.size,"Sandwich")}
            if(cbBurger.isChecked){checkedList.add(checkedList.size,"Burger")}
            if(cbPizza.isChecked){checkedList.add(checkedList.size,"Pizza")}
            if(cbSoup.isChecked){checkedList.add(checkedList.size,"Soup")}
            if(cbSalad.isChecked){checkedList.add(checkedList.size,"Salad")}
            if(cbTaco.isChecked){checkedList.add(checkedList.size,"Taco")}
            if(cbBeef.isChecked){checkedList.add(checkedList.size,"Beef")}
            if(cbChicken.isChecked){checkedList.add(checkedList.size,"Chicken")}
            if(cbFish.isChecked){checkedList.add(checkedList.size,"Fish")}
            if(cbDessert.isChecked){checkedList.add(checkedList.size,"Dessert")}
            var first = -99
            var firstName = ""
            var second = -99
            var secondName = ""
            var third = -99
            var thirdName = ""
            for (i in objectList)
            {
                i.myScore = i.myPop
                for (food in i.myFoods)
                {
                    if(food in checkedList)
                    {
                        i.myScore += 1
                    }
                    else
                    {
                        //i.myScore -= 1
                    }
                }
                if (i.myScore > first)
                {
                    thirdName = secondName
                    third = second
                    secondName = firstName
                    second = first
                    firstName = i.myName
                    first = i.myScore
                }
                else if (i.myScore > second)
                {
                    thirdName = secondName
                    third = second
                    secondName = i.myName
                    second = i.myScore
                }
                else if (i.myScore > third)
                {
                    thirdName = i.myName
                    third = i.myScore
                }
            }
            var tempName = ""
            var tempScore = 0
            var tempText = textFirst
            var tempButton = button1
            for (img in arrayOf(imageView1, imageView2, imageView3))
            {
                if(img == imageView1){tempName = firstName; tempScore = first; tempText = textFirst; tempButton = button1}
                if(img == imageView2){tempName = secondName; tempScore = second; tempText = textSecond; tempButton = button2}
                if(img == imageView3){tempName = thirdName; tempScore = third; tempText = textThird; tempButton = button3}
                //val places = arrayOf("Starbucks", "Dunkin", "Breakfast Republic", "Peet's Coffee", "Le Diplomatic Cafe",
                //    "Flippoly", "Jersey Mike's", "Blaze Pizza", "Sgt. Pepperoni's Pizza", "Luna Grill", "Jack in the Box",
                //    "In n Out", "The Habit", "Chick-fil-A", "Chipotle", "Taco Bell", "Del Taco", "Tender Greens"
                //    , "KY Sushi", "Iro Sushi Stuff X Roll"
                //    , "The Buffalo Spot", "Yogurtland", "Insomnia Cookies", "Share Tea", "Cha for Tea")
                if(tempName == places[0]){img.setBackgroundResource(R.drawable.starbucks);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 300")}
                if(tempName == places[1]){img.setBackgroundResource(R.drawable.dunkin);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 700")}
                if(tempName == places[2]){img.setBackgroundResource(R.drawable.breakfastrepublic);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 570")}
                if(tempName == places[3]){img.setBackgroundResource(R.drawable.peetscoffee);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 300")}
                if(tempName == places[4]){img.setBackgroundResource(R.drawable.lediplocafe);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 500")}
                if(tempName == places[5]){img.setBackgroundResource(R.drawable.flippoly);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 750")}
                if(tempName == places[6]){img.setBackgroundResource(R.drawable.jerseymikes);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 900")}
                if(tempName == places[7]){img.setBackgroundResource(R.drawable.blazepizza);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 1250")}
                if(tempName == places[8]){img.setBackgroundResource(R.drawable.sgtpeppspizza);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 1250")}
                if(tempName == places[9]){img.setBackgroundResource(R.drawable.lunagrill);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 1000")}
                if(tempName == places[10]){img.setBackgroundResource(R.drawable.jackinthebox);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 800")}
                if(tempName == places[11]){img.setBackgroundResource(R.drawable.innout);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 880")}
                if(tempName == places[12]){img.setBackgroundResource(R.drawable.thehabit);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 950")}
                if(tempName == places[13]){img.setBackgroundResource(R.drawable.chickfila);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 850")}
                if(tempName == places[14]){img.setBackgroundResource(R.drawable.chipotle);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 1050")}
                if(tempName == places[15]){img.setBackgroundResource(R.drawable.tacobell);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 1000")}
                if(tempName == places[16]){img.setBackgroundResource(R.drawable.deltaco);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 800")}
                if(tempName == places[17]){img.setBackgroundResource(R.drawable.tendergreens);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 650")}
                if(tempName == places[18]){img.setBackgroundResource(R.drawable.kysushi);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 700")}
                if(tempName == places[19]){img.setBackgroundResource(R.drawable.irosushi);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 700")}
                if(tempName == places[20]){img.setBackgroundResource(R.drawable.buffalospot);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 1200")}
                if(tempName == places[21]){img.setBackgroundResource(R.drawable.yogurtland);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 800")}
                if(tempName == places[22]){img.setBackgroundResource(R.drawable.insombiacookies);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 300")}
                if(tempName == places[23]){img.setBackgroundResource(R.drawable.sharetea);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 450")}
                if(tempName == places[24]){img.setBackgroundResource(R.drawable.chatea);tempButton.text = tempName; tempText.text = (tempName + "\nScore: "+tempScore+"\nAverage Calories = 450")}
            }
        }

        buttonAddMeal.setOnClickListener{
            var tempName = ""
            if (button1.isChecked){tempName = button1.text.toString()}
            if (button2.isChecked){tempName = button2.text.toString()}
            if (button3.isChecked){tempName = button3.text.toString()}
            var kCal = settings.getInt("kCals", 0)
            if (tempName == places[0]){kCal+=300}
            if (tempName == places[1]){kCal+=700}
            if (tempName == places[2]){kCal+=570}
            if (tempName == places[3]){kCal+=300}
            if (tempName == places[4]){kCal+=500}
            if (tempName == places[5]){kCal+=750}
            if (tempName == places[6]){kCal+=900}
            if (tempName == places[7]){kCal+=1250}
            if (tempName == places[8]){kCal+=1250}
            if (tempName == places[9]){kCal+=1000}
            if (tempName == places[10]){kCal+=800}
            if (tempName == places[11]){kCal+=880}
            if (tempName == places[12]){kCal+=950}
            if (tempName == places[13]){kCal+=850}
            if (tempName == places[14]){kCal+=1050}
            if (tempName == places[15]){kCal+=1000}
            if (tempName == places[16]){kCal+=800}
            if (tempName == places[17]){kCal+=650}
            if (tempName == places[18]){kCal+=700}
            if (tempName == places[19]){kCal+=700}
            if (tempName == places[20]){kCal+=1200}
            if (tempName == places[21]){kCal+=800}
            if (tempName == places[22]){kCal+=300}
            if (tempName == places[23]){kCal+=450}
            if (tempName == places[24]){kCal+=450}

            val edit = settings.edit()
            var tempPop = settings.getInt(tempName, 0)
            tempPop+=1
            edit.putInt("kCals", kCal)
            edit.putInt(tempName,tempPop)
            edit.apply()
            startActivity(Intent(this, DietActivity::class.java))
            finish()
        }
        buttonReturn.setOnClickListener{
            startActivity(Intent(this, DietActivity::class.java))
            finish()
        }

    }
    fun storeToPng(name: String)
    {

    }
}