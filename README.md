
# JNotify [![Build Status](https://travis-ci.org/Albocoder/JNotify.svg?branch=master)](https://travis-ci.org/Albocoder/JNotify)
This library aims to help making notifications like popups easy. <br>
It has only 2 classes at the moment and unfortunately no developer documentation.

# Features 
  1) Support for "danger", "warning" and "success" notifications
  2) Support for all the 4 corners of the screen (relative to any system tray)
  3) "fading" animation only :(
  4) Automatic killing of the notifications
  5) Automatically stacking notifications to keep a nice and tidy look.
  6) Automatic reordering of what is showing when a notification is killed.
  7) (coming up) beeping and custom sound plays

# How to use
To use this you need to follow the following steps:
<br>

0) (advanced) <i>git clone https://github.com/Albocoder/JNotify.git</i>
1) (advanced) <i>cd JNotify && mvn install</i>
3) (advanced) now the jar file will be in the target folder


4) (easy) Download and add <a href="https://oss.sonatype.org/service/local/repositories/comgithubalbocoder-1006/content/com/github/albocoder/jnotify/1.0.2/jnotify-1.0.2.jar">JNotify-1.0.2.jar</a> in your build path.
5) To use in your code just: <i>import com.github.albocoder.jnotify.*;</i>
6) Create <b>NotificationManager(String position,String animation,int xpadding,int ypadding)</b> instance
7) Call <b>notify(String type,String title, String msg,String animation,int duration)</b> or <b>notifyNoAutokill(String type,String title, String msg,String animation)</b> method of NotificationManager

# Screenshot

![In Action](http://erin.avllazagaj.ug.bilkent.edu.tr/JNotify/ss1.png)

# Used by

<a href="https://github.com/Albocoder/CoinTracker">CoinTracker</a>

# Repository

Open in the <a href="http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.github.albocoder%22">Central Sonatype repository</a>
<br>Latest release version: 1.0.2

# Documentation

Javadoc in jar file in sonatype repository

# Open to contribution

This is open source initiative. Please contribute to this repository by adding new features and refactoring the code. :)<br>
If you can't contribute with code please consider dropping a star. It really helps a lot :) 

# Licensing

Use at your own will just please link this repository at the code comments and/or any place you mention used libraries.

## Support on Beerpay
Hey dude! Help me out for a couple of :beers:!

[![Beerpay](https://beerpay.io/Albocoder/JNotify/badge.svg?style=beer-square)](https://beerpay.io/Albocoder/JNotify)  [![Beerpay](https://beerpay.io/Albocoder/JNotify/make-wish.svg?style=flat-square)](https://beerpay.io/Albocoder/JNotify?focus=wish)