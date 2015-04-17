#MobileESP
[![License](https://img.shields.io/hexpm/l/plug.svg?style=flat)](http://blog.mobileesp.com/?page_id=13)

[![Platform](https://img.shields.io/badge/platform-PHP-orange.svg?style=flat)](http://blog.mobileesp.com/?page_id=53)
[![Platform](https://img.shields.io/badge/platform-Java-orange.svg?style=flat)](http://blog.mobileesp.com/?page_id=53)
[![Platform](https://img.shields.io/badge/platform-JavaScript-orange.svg?style=flat)](http://blog.mobileesp.com/?page_id=53)
[![Platform](https://img.shields.io/badge/platform-ASP.NET-orange.svg?style=flat)](http://blog.mobileesp.com/?page_id=53)
[![Platform](https://img.shields.io/badge/platform-Python-orange.svg?style=flat)](http://blog.mobileesp.com/?page_id=53)
[![Platform](https://img.shields.io/badge/platform-C++-orange.svg?style=flat)](http://blog.mobileesp.com/?page_id=53)

At last, a dead simple way for web site publishers to detect visitors on mobile web sites! 

MobileESP is free, open source and lightweight. MobileESP has a simple API for detecting mobile devices. The API returns the simple Boolean result of TRUE if the device is the specified type, or FALSE if it isn't. For example, want to know if your visitor is on an iPhone, Android or Windows Phone device? Simply use the method DetectTierIphone(). It's that easy.

##Core Principles
MobileESP believes in making it dead easy for a website publisher to detect mobile visitors. As a result, the API follows the DetectXXX() pattern and returns a simple Boolean (true or false) for the type of mobile device or platform desired. The API DetectXXX() methods are consistent by design across supported platforms. 

In addition, a second core principle is that the code is easy for the user to read and understand. Therefore, we believe it's easier for the user to change, update or extend the library, if desired. 

The code is also purposefully written to be modular so that it is not only easier to maintain, but also easier to extend. 


##Server Side Platforms
MobileESP started with PHP (and JavaScript) and has been extended by the community to a bunch of other platforms:
- PHP
- Java
- ASP.NET (C#) 
- Python
- C++

Also available in separate repositories: 
- <a href="https://github.com/eimermusic/mobileesp">Ruby</a>: This project was ported by Martin, who separately maintains the Ruby codebase on GitHub.
- <a href="https://bitbucket.org/mbarrero/mobileesp-classic-asp">Classic ASP (VBscript)</a>: This project was ported by Miguel Barrero, who separately maintains the codebase on BitBucket.