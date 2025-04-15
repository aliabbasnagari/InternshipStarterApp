package com.cloudcare.internshipstarterapp.data

class Quotes {
    companion object {
        private val motivationalQuotes = arrayOf(
            "The only way to do great work is to love what you do. - Steve Jobs",
            "It does not matter how slowly you go as long as you do not stop. - Confucius",
            "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
            "Success is not final, failure is not fatal: It is the courage to continue that counts. - Winston Churchill",
            "Believe you can and you're halfway there. - Theodore Roosevelt",
            "You miss 100% of the shots you don't take. - Wayne Gretzky",
            "The only limit to our realization of tomorrow is our doubts of today. - Franklin D. Roosevelt",
            "The harder you work for something, the greater you'll feel when you achieve it. - Unknown",
            "It always seems impossible until it's done. - Nelson Mandela",
            "Don’t watch the clock; do what it does. Keep going. - Sam Levenson",
            "Success usually comes to those who are too busy to be looking for it. - Henry David Thoreau",
            "Opportunities don't happen, you create them. - Chris Grosser",
            "The way to get started is to quit talking and begin doing. - Walt Disney",
            "Don’t wait. The time will never be just right. - Napoleon Hill",
            "Dream big and dare to fail. - Norman Vaughan",
            "One often meets his destiny on the road he takes to avoid it. - Master Oogway",
            "The past is history, the future is a mystery, but today is a gift. That’s why it’s called the present. - Master Oogway",
            "You are too concerned with what was and what will be. - Master Oogway",
            "There is no secret ingredient. It’s just you. - Master Oogway",
            "You must believe in yourself.  - Master Shifu",
            "Your mind is like this water, my friend. When it is agitated, it becomes difficult to see. But if you allow it to settle, the answer becomes clear.  - Master Shifu",
            "There is no such thing as 'impossible.' It’s just a word. There is always a way.  - Master Shifu"
        )

        fun getRandomQuote(): String {
            return motivationalQuotes.random()
        }
    }
}