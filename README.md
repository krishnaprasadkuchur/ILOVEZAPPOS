# ILOVEZAPPOS
Android Application for the Zappos Family
. 
The application consists of 3 different activities. They are:
1. The splash screen, which appears for 5 seconds when the application launches.
2. Following this the search Product screen (MainActivity) launches.
3. When the Oncreate for MainActivity is created, a query with blank search query (term?=) is sent using the retrofit library.
4. Following, this the first element retrieved is displayed in a card view to the user.
5. When the user queries, for a product, that query text is sent using retrofit, and the first product is displayed in the card view.
6. When the card is clicked, the application navigates to the ProductDetails screen, where details of the product are displayed, along with a
    a floating button to add the item to the cart.
 7. When the button is clicked, the animation is executed on the button, and the cart in the main menu is updated.
 8. To remove the item, the floating button is clicked again.
