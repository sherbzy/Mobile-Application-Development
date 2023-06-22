1. How did you handle prepending the $ symbol to the total cost?
	I handled prepending the $ symbol to the total cost with string concatenation: "$$output"
	where output represents the variable holding the total cost.
2. How did you initially hide the views and then make them visible again?
	I initially hid the views in the xml file where they reside. I added an android:visibility
	portion and set it equal to "invisible". Then, in the kotlin file, I added an if statement in
	the calculate button's onClickListener that checked if the elements were invisible and needed
	their visibility changed to View.VISIBLE.

Notes: Some of the formatting that I used in this project differed slightly from the original layout
       image that was provided in the assignment write up. I had a lot of difficulty trying to get
       the spacing of elements to look the same as well as the size of the text.