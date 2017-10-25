# JNotify
This library aims to help making notifications like popups easy. <br>
It has only 2 classes at the moment and unfortunately no developer documentation.
# How to use
To use this you need to follow the following steps:
<br>

0) (optional) <i>git clone https://github.com/Albocoder/JNotify.git</i>
1) (optional) <i>cd JNotify && ant</i>
2) (recommended) Add <a href="erin.avllazagaj.ug.bilkent.edu.tr/JNotify/JNotify-1.0.jar">JNotify-1.0.jar</a> in your build path.
3) To use in your code just: <i>import albocoder.*;</i>
4) Create <b>NotificationManager(String position,String animation,int xpadding,int ypadding)</b> instance
5) Call <b>notify(String type,String title, String msg,String animation,int duration)</b> or <b>notifyNoAutokill(String type,String title, String msg,String animation)</b> method of NotificationManager

# Open to contribution

This is open source initiative. Please contribute to this repository by adding new features and refactoring the code. :)<br>
If you can't contribute with code please consider dropping a star. It really helps a lot :) 

# Used by

<a href="https://github.com/Albocoder/CoinTracker">CoinTracker</a>

# Licensing

Use at your own will just please link this repository at the code comments and/or any place you mention used libraries.
