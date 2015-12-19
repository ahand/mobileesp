/* *******************************************
// Copyright 2010-2015, Anthony Hand
//
//
// File version 2015.12.19 (December 19, 2015)
// Updates:
//	- Moved MobileESP to GitHub. https://github.com/ahand/mobileesp
//	- Opera Mobile/Mini browser has the same UA string on multiple platforms and doesn't differentiate phone vs. tablet.
//		- Removed DetectOperaAndroidPhone(). This method is no longer reliable.
//		- Removed DetectOperaAndroidTablet(). This method is no longer reliable.
//	- Added support for Windows Phone 10: variable and DetectWindowsPhone10()
//	- Updated DetectWindowsPhone() to include WP10.
//	- Added support for Firefox OS.
//		- A variable plus DetectFirefoxOS(), DetectFirefoxOSPhone(), DetectFirefoxOSTablet()
//		- NOTE: Firefox doesn't add UA tokens to definitively identify Firefox OS vs. their browsers on other mobile platforms.
//	- Added support for Sailfish OS. Not enough info to add a tablet detection method at this time.
//		- A variable plus DetectSailfish(), DetectSailfishPhone()
//	- Added support for Ubuntu Mobile OS.
//		- DetectUbuntu(), DetectUbuntuPhone(), DetectUbuntuTablet()
//	- Added support for 2 smart TV OSes. They lack browsers but do have WebViews for use by HTML apps.
//		- One variable for Samsung Tizen TVs, plus DetectTizenTV()
//		- One variable for LG WebOS TVs, plus DetectWebOSTV()
//	- Updated DetectTizen(). Now tests for "mobile" to disambiguate from Samsung Smart TVs
//	- Removed variables for obsolete devices: deviceHtcFlyer, deviceXoom.
//	- Updated DetectAndroid(). No longer has a special test case for the HTC Flyer tablet.
//	- Updated DetectAndroidPhone().
//		- Updated internal detection code for Android.
//		- No longer has a special test case for the HTC Flyer tablet.
//		- Checks against DetectOperaMobile() on Android and reports here if relevant.
//	- Updated DetectAndroidTablet().
//		- No longer has a special test case for the HTC Flyer tablet.
//		- Checks against DetectOperaMobile() on Android to exclude it from here.
//	- DetectMeego(): Changed definition for this method. Now detects any Meego OS device, not just phones.
//	- DetectMeegoPhone(): NEW. For Meego phones. Ought to detect Opera browsers on Meego, as well.
//	- DetectTierIphone(): Added support for phones running Sailfish, Ubuntu and Firefox Mobile.
//	- DetectTierTablet(): Added support for tablets running Ubuntu and Firefox Mobile.
//	- DetectSmartphone(): Added support for Meego phones.
//	- Refactored the detection logic in DetectMobileQuick() and DetectMobileLong().
//		- Moved a few detection tests for older browsers to Long.
//	- Refactorings.
//
//
//
// LICENSE INFORMATION
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//        http://www.apache.org/licenses/LICENSE-2.0
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
// either express or implied. See the License for the specific
// language governing permissions and limitations under the License.
//
//
// ABOUT THIS PROJECT
//   Project Owner: Anthony Hand
//   Email: anthony.hand@gmail.com
//   Web Site: http://www.mobileesp.com
//   Source Files: https://github.com/ahand/mobileesp
//
//   Versions of this code are available for:
//      PHP, JavaScript, Java, ASP.NET (C#), and Ruby
//
// *******************************************
 */

package com.handinteractive.mobile;
/**
 * The DetectSmartPhone class encapsulates information about
 *   a browser's connection to your web site.
 *   You can use it to find out whether the browser asking for
 *   your site's content is probably running on a mobile device.
 *   The methods were written so you can be as granular as you want.
 *   For example, enquiring whether it's as specific as an iPod Touch or
 *   as general as a smartphone class device.
 *   The object's methods return true, or false.
 */
public class UAgentInfo {
    // User-Agent and Accept HTTP request headers

    private String userAgent = "";
    private String httpAccept = "";

    // Let's store values for quickly accessing the same info multiple times.
    public boolean initCompleted = false;
    public boolean isWebkit = false; //Stores the result of DetectWebkit()
    public boolean isMobilePhone = false; //Stores the result of DetectMobileQuick()
    public boolean isIphone = false; //Stores the result of DetectIphone()
    public boolean isAndroid = false; //Stores the result of DetectAndroid()
    public boolean isAndroidPhone = false; //Stores the result of DetectAndroidPhone()
    public boolean isTierTablet = false; //Stores the result of DetectTierTablet()
    public boolean isTierIphone = false; //Stores the result of DetectTierIphone()
    public boolean isTierRichCss = false; //Stores the result of DetectTierRichCss()
    public boolean isTierGenericMobile = false; //Stores the result of DetectTierOtherPhones()

    // Initialize some initial smartphone string variables.
    public static final String engineWebKit = "webkit";

    public static final String deviceIphone = "iphone";
    public static final String deviceIpod = "ipod";
    public static final String deviceIpad = "ipad";
    public static final String deviceMacPpc = "macintosh"; //Used for disambiguation

    public static final String deviceAndroid = "android";
    public static final String deviceGoogleTV = "googletv";

    public static final String deviceWinPhone7 = "windows phone os 7";
    public static final String deviceWinPhone8 = "windows phone 8";
    public static final String deviceWinPhone10 = "windows phone 10";
    public static final String deviceWinMob = "windows ce";
    public static final String deviceWindows = "windows";
    public static final String deviceIeMob = "iemobile";
    public static final String devicePpc = "ppc"; //Stands for PocketPC
    public static final String enginePie = "wm5 pie"; //An old Windows Mobile

    public static final String deviceBB = "blackberry";
    public static final String deviceBB10 = "bb10"; //For the new BB 10 OS
    public static final String vndRIM = "vnd.rim"; //Detectable when BB devices emulate IE or Firefox
    public static final String deviceBBStorm = "blackberry95";  //Storm 1 and 2
    public static final String deviceBBBold = "blackberry97";  //Bold 97x0 (non-touch)
    public static final String deviceBBBoldTouch = "blackberry 99";  //Bold 99x0 (touchscreen)
    public static final String deviceBBTour = "blackberry96";  //Tour
    public static final String deviceBBCurve = "blackberry89";  //Curve 2
    public static final String deviceBBCurveTouch = "blackberry 938";  //Curve Touch 9380
    public static final String deviceBBTorch = "blackberry 98";  //Torch
    public static final String deviceBBPlaybook = "playbook"; //PlayBook tablet

    public static final String deviceSymbian = "symbian";
    public static final String deviceS60 = "series60";
    public static final String deviceS70 = "series70";
    public static final String deviceS80 = "series80";
    public static final String deviceS90 = "series90";

    public static final String devicePalm = "palm";
    public static final String deviceWebOS = "webos"; //For Palm devices
    public static final String deviceWebOStv = "web0s"; //For LG TVs
    public static final String deviceWebOShp = "hpwos"; //For HP's line of WebOS devices

    public static final String deviceNuvifone = "nuvifone";  //Garmin Nuvifone
    public static final String deviceBada = "bada";  //Samsung's Bada OS
    public static final String deviceTizen = "tizen";  //Tizen OS
    public static final String deviceMeego = "meego";  //Meego OS
    public static final String deviceSailfish = "sailfish"; //Sailfish OS
    public static final String deviceUbuntu = "ubuntu"; //Ubuntu Mobile OS

    public static final String deviceKindle = "kindle";  //Amazon Kindle, eInk one
    public static final String engineSilk = "silk-accelerated";  //Amazon's accelerated Silk browser for Kindle Fire

    public static final String engineBlazer = "blazer"; //Old Palm
    public static final String engineXiino = "xiino"; //Another old Palm

    //Initialize variables for mobile-specific content.
    public static final String vndwap = "vnd.wap";
    public static final String wml = "wml";

    //Initialize variables for other random devices and mobile browsers.
    public static final String deviceTablet = "tablet"; //Generic term for slate and tablet devices
    public static final String deviceBrew = "brew";
    public static final String deviceDanger = "danger";
    public static final String deviceHiptop = "hiptop";
    public static final String devicePlaystation = "playstation";
    public static final String devicePlaystationVita = "vita";
    public static final String deviceNintendoDs = "nitro";
    public static final String deviceNintendo = "nintendo";
    public static final String deviceWii = "wii";
    public static final String deviceXbox = "xbox";
    public static final String deviceArchos = "archos";

    public static final String engineFirefox = "firefox"; //For Firefox OS
    public static final String engineOpera = "opera"; //Popular browser
    public static final String engineNetfront = "netfront"; //Common embedded OS browser
    public static final String engineUpBrowser = "up.browser"; //common on some phones
    public static final String engineOpenWeb = "openweb"; //Transcoding by OpenWave server
    public static final String deviceMidp = "midp"; //a mobile Java technology
    public static final String uplink = "up.link";
    public static final String engineTelecaQ = "teleca q"; //a modern feature phone browser
    public static final String devicePda = "pda"; //some devices report themselves as PDAs
    public static final String mini = "mini";  //Some mobile browsers put "mini" in their names.
    public static final String mobile = "mobile"; //Some mobile browsers put "mobile" in their user agent strings.
    public static final String mobi = "mobi"; //Some mobile browsers put "mobi" in their user agent strings.

    //Smart TV strings
    public static final String smartTV1 = "smart-tv"; //Samsung Tizen smart TVs
    public static final String smartTV2 = "smarttv"; //LG WebOS smart TVs

    //Use Maemo, Tablet, and Linux to test for Nokia"s Internet Tablets.
    public static final String maemo = "maemo";
    public static final String linux = "linux";
    public static final String qtembedded = "qt embedded"; //for Sony Mylo
    public static final String mylocom2 = "com2"; //for Sony Mylo also

    //In some UserAgents, the only clue is the manufacturer.
    public static final String manuSonyEricsson = "sonyericsson";
    public static final String manuericsson = "ericsson";
    public static final String manuSamsung1 = "sec-sgh";
    public static final String manuSony = "sony";
    public static final String manuHtc = "htc"; //Popular Android and WinMo manufacturer

    //In some UserAgents, the only clue is the operator.
    public static final String svcDocomo = "docomo";
    public static final String svcKddi = "kddi";
    public static final String svcVodafone = "vodafone";

    //Disambiguation strings.
    public static final String disUpdate = "update"; //pda vs. update


    /**
     * Initialize the userAgent and httpAccept variables
     *
     * @param userAgent the User-Agent header
     * @param httpAccept the Accept header
     */
    public UAgentInfo(final String userAgent, final String httpAccept) {
        if (userAgent != null) {
            this.userAgent = userAgent.toLowerCase();
        }
        if (httpAccept != null) {
            this.httpAccept = httpAccept.toLowerCase();
        }

        //Intialize key stored values.
        initDeviceScan();
    }

    /**
     * Return the lower case HTTP_USER_AGENT
     * @return userAgent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Return the lower case HTTP_ACCEPT
     * @return httpAccept
     */
    public String getHttpAccept() {
        return httpAccept;
    }

    /**
     * Return whether the device is an Iphone or iPod Touch
     * @return isIphone
     */
    public boolean getIsIphone() {
        return isIphone;
    }

    /**
     * Return whether the device is in the Tablet Tier.
     * @return isTierTablet
     */
    public boolean getIsTierTablet() {
        return isTierTablet;
    }

    /**
     * Return whether the device is in the Iphone Tier.
     * @return isTierIphone
     */
    public boolean getIsTierIphone() {
        return isTierIphone;
    }

    /**
     * Return whether the device is in the 'Rich CSS' tier of mobile devices.
     * @return isTierRichCss
     */
    public boolean getIsTierRichCss() {
        return isTierRichCss;
    }

    /**
     * Return whether the device is a generic, less-capable mobile device.
     * @return isTierGenericMobile
     */
    public boolean getIsTierGenericMobile() {
        return isTierGenericMobile;
    }

    /**
     * Initialize Key Stored Values.
     */
    public final void initDeviceScan() {
        //Save these properties to speed processing
        this.isWebkit = detectWebkit();
        this.isIphone = detectIphone();
        this.isAndroid = detectAndroid();
        this.isAndroidPhone = detectAndroidPhone();

        //Generally, these tiers are the most useful for web development
        this.isMobilePhone = detectMobileQuick();
        this.isTierTablet = detectTierTablet();
        this.isTierIphone = detectTierIphone();

        //Optional: Comment these out if you NEVER use them
        this.isTierRichCss = detectTierRichCss();
        this.isTierGenericMobile = detectTierOtherPhones();

        this.initCompleted = true;
    }

    /**
     * Detects if the current device is an iPhone.
     * @return detection of an iPhone
     */
    public final boolean detectIphone() {
        // The iPad and iPod touch say they're an iPhone! So let's disambiguate.
        return userAgent.contains(deviceIphone) &&
            !detectIpad() &&
            !detectIpod();
    }

    /**
     * Detects if the current device is an iPod Touch.
     * @return detection of an iPod Touch
     */
    public final boolean detectIpod() {
        return userAgent.contains(deviceIpod);
    }

    /**
     * Detects if the current device is an iPad tablet.
     * @return detection of an iPad
     */
    public final boolean detectIpad() {
        return userAgent.contains(deviceIpad) && detectWebkit();
    }

    /**
     * Detects if the current device is an iPhone or iPod Touch.
     * @return detection of an iPhone or iPod Touch
     */
    public final boolean detectIphoneOrIpod() {
        //We repeat the searches here because some iPods may report themselves as an iPhone, which would be okay.
        return userAgent.contains(deviceIphone) || userAgent.contains(deviceIpod);
    }

    /**
     * Detects *any* iOS device: iPhone, iPod Touch, iPad.
     * @return detection of an Apple iOS device
     */
    public final boolean detectIos() {
        return detectIphoneOrIpod() || detectIpad();
    }


    /**
     * Detects *any* Android OS-based device: phone, tablet, and multi-media player.
     * Also detects Google TV.
     * @return detection of an Android device
     */
    public final boolean detectAndroid() {
        return userAgent.contains(deviceAndroid) || detectGoogleTV();

    }

    /**
     * Detects if the current device is a (small-ish) Android OS-based device
     * used for calling and/or multi-media (like a Samsung Galaxy Player).
     * Google says these devices will have 'Android' AND 'mobile' in user agent.
     * Ignores tablets (Honeycomb and later).
     * @return  detection of an Android phone
     */
    public final boolean detectAndroidPhone() {
        //First, let's make sure we're on an Android device.
        if (!detectAndroid())
            return false;

        //If it's Android and has 'mobile' in it, Google says it's a phone.
        if (userAgent.contains(mobile))
            return true;

        //Special check for Android devices with Opera Mobile/Mini. They should report here.
        return detectOperaMobile();

    }

    /**
     * Detects if the current device is a (self-reported) Android tablet.
     * Google says these devices will have 'Android' and NOT 'mobile' in their user agent.
     * @return detection of an Android tablet
     */
    public final boolean detectAndroidTablet() {
        //First, let's make sure we're on an Android device.
        if (!detectAndroid())
            return false;

        //Special check for Android devices with Opera Mobile/Mini. They should NOT report here.
        if (detectOperaMobile())
            return false;

        //Otherwise, if it's Android and does NOT have 'mobile' in it, Google says it's a tablet.
        return !userAgent.contains(mobile);
    }

    /**
     * Detects if the current device is an Android OS-based device and
     * the browser is based on WebKit.
     * @return detection of an Android WebKit browser
     */
    public boolean detectAndroidWebKit() {
        return detectAndroid() && detectWebkit();
    }

    /**
     * Detects if the current device is a GoogleTV.
     * @return detection of GoogleTV
     */
    public final boolean detectGoogleTV() {
        return userAgent.contains(deviceGoogleTV);
    }

    /**
     * Detects if the current browser is based on WebKit.
     * @return detection of a WebKit browser
     */
    public final boolean detectWebkit() {
        return userAgent.contains(engineWebKit);
    }

    /**
     * Detects if the current browser is the Symbian S60 Open Source Browser.
     * @return detection of Symbian S60 Browser
     */
    public final boolean detectS60OssBrowser() {
        //First, test for WebKit, then make sure it's either Symbian or S60.
        return detectWebkit() && (userAgent.contains(deviceSymbian) || userAgent.contains(deviceS60));
    }

    /**
     *
     * Detects if the current device is any Symbian OS-based device,
     *   including older S60, Series 70, Series 80, Series 90, and UIQ,
     *   or other browsers running on these devices.
     * @return detection of SymbianOS
     */
    public final boolean detectSymbianOS() {
        return userAgent.contains(deviceSymbian)
            || userAgent.contains(deviceS60)
            || userAgent.contains(deviceS70)
            || userAgent.contains(deviceS80)
            || userAgent.contains(deviceS90);
    }

    /**
     * Detects if the current browser is a Windows Phone 7.x, 8, or 10 device
     * @return detection of Windows Phone 7.x OR 8
     */
    public final boolean detectWindowsPhone() {
        return detectWindowsPhone7() || detectWindowsPhone8() || detectWindowsPhone10();
    }

    /**
     * Detects a Windows Phone 7 device (in mobile browsing mode).
     * @return detection of Windows Phone 7
     */
    public final boolean detectWindowsPhone7() {
        return userAgent.contains(deviceWinPhone7);
    }

    /**
     * Detects a Windows Phone 8 device (in mobile browsing mode).
     * @return detection of Windows Phone 8
     */
    public final boolean detectWindowsPhone8() {
        return userAgent.contains(deviceWinPhone8);
    }

    /**
     * Detects a Windows Phone 10 device (in mobile browsing mode).
     * @return detection of Windows Phone 10
     */
    public final boolean detectWindowsPhone10() {
        return userAgent.contains(deviceWinPhone10);
    }

    /**
     * Detects if the current browser is a Windows Mobile device.
     * Excludes Windows Phone 7.x and 8 devices.
     * Focuses on Windows Mobile 6.xx and earlier.
     * @return detection of Windows Mobile
     */
    public final boolean detectWindowsMobile() {
        if (detectWindowsPhone()) {
            return false;
        }
        //Most devices use 'Windows CE', but some report 'iemobile'
        //  and some older ones report as 'PIE' for Pocket IE.
        //  We also look for instances of HTC and Windows for many of their WinMo devices.
        if (userAgent.contains(deviceWinMob)
            || userAgent.contains(deviceIeMob)
            || userAgent.contains(enginePie)
            || (userAgent.contains(manuHtc) && userAgent.contains(deviceWindows))
            || (detectWapWml() && userAgent.contains(deviceWindows))) {
            return true;
        }

        //Test for Windows Mobile PPC but not old Macintosh PowerPC.
        return userAgent.contains(devicePpc) && !(userAgent.contains(deviceMacPpc));

    }

    /**
     * Detects if the current browser is any BlackBerry.
     * Includes BB10 OS, but excludes the PlayBook.
     * @return detection of Blackberry
     */
    public final boolean detectBlackBerry()
    {
        return userAgent.contains(deviceBB) || httpAccept.contains(vndRIM) || detectBlackBerry10Phone();

    }

    /**
     * Detects if the current browser is a BlackBerry 10 OS phone.
     * Excludes tablets.
     * @return detection of a Blackberry 10 device
     */
    public final boolean detectBlackBerry10Phone() {
        return userAgent.contains(deviceBB10) && userAgent.contains(mobile);
    }

    /**
     * Detects if the current browser is on a BlackBerry tablet device.
     *    Example: PlayBook
     * @return detection of a Blackberry Tablet
     */
    public final boolean detectBlackBerryTablet() {
        return userAgent.contains(deviceBBPlaybook);
    }

    /**
     * Detects if the current browser is a BlackBerry device AND uses a
     *    WebKit-based browser. These are signatures for the new BlackBerry OS 6.
     *    Examples: Torch. Includes the Playbook.
     * @return detection of a Blackberry device with WebKit browser
     */
    public final boolean detectBlackBerryWebKit() {
        return detectBlackBerry() && userAgent.contains(engineWebKit);
    }

    /**
     * Detects if the current browser is a BlackBerry Touch
     * device, such as the Storm, Torch, and Bold Touch. Excludes the Playbook.
     * @return detection of a Blackberry touchscreen device
     */
    public final boolean detectBlackBerryTouch() {
        return detectBlackBerry() && (userAgent.contains(deviceBBStorm) ||
            userAgent.contains(deviceBBTorch) ||
            userAgent.contains(deviceBBBoldTouch) ||
            userAgent.contains(deviceBBCurveTouch));
    }

    /**
     * Detects if the current browser is a BlackBerry device AND
     *   has a more capable recent browser. Excludes the Playbook.
     *   Examples, Storm, Bold, Tour, Curve2
     *   Excludes the new BlackBerry OS 6 and 7 browser!!
     * @return detection of a Blackberry device with a better browser
     */
    public final boolean detectBlackBerryHigh()
    {
        //Disambiguate for BlackBerry OS 6 or 7 (WebKit) browser
        return !detectBlackBerryWebKit()
            && detectBlackBerry()
            && (detectBlackBerryTouch()
                || userAgent.contains(deviceBBBold)
                || userAgent.contains(deviceBBTour)
                || userAgent.contains(deviceBBCurve));
    }

    /**
     * Detects if the current browser is a BlackBerry device AND
     *   has an older, less capable browser.
     *   Examples: Pearl, 8800, Curve1
     * @return detection of a Blackberry device with a poorer browser
     */
    public boolean detectBlackBerryLow()
    {
        //Assume that if it's not in the High tier, then it's Low
        return detectBlackBerry() && !(detectBlackBerryHigh() || detectBlackBerryWebKit());
    }

    /**
     * Detects if the current browser is on a PalmOS device.
     * @return detection of a PalmOS device
     */
    public final boolean detectPalmOS()
    {
        //Make sure it's not WebOS first
        return !detectPalmWebOS()
            //Most devices nowadays report as 'Palm', but some older ones reported as Blazer or Xiino.
            && (userAgent.contains(devicePalm)
            || userAgent.contains(engineBlazer)
            || userAgent.contains(engineXiino));
    }

    /**
     * Detects if the current browser is on a Palm device
     *    running the new WebOS.
     * @return detection of a Palm WebOS device
     */
    public final boolean detectPalmWebOS() {
        return userAgent.contains(deviceWebOS);
    }

    /**
     * Detects if the current browser is on an HP tablet running WebOS.
     * @return detection of an HP WebOS tablet
     */
    public final boolean detectWebOSTablet() {
        return userAgent.contains(deviceWebOShp) && userAgent.contains(deviceTablet);
    }

    /**
     * Detects if the current browser is on a WebOS smart TV.
     * @return detection of a WebOS smart TV
     */
    public boolean detectWebOSTV() {
        return userAgent.contains(deviceWebOStv) && userAgent.contains(smartTV2);
    }

    /**
     * Detects Opera Mobile or Opera Mini.
     * @return detection of an Opera browser for a mobile device
     */
    public final boolean detectOperaMobile() {
        return userAgent.contains(engineOpera) && (userAgent.contains(mini) || userAgent.contains(mobi));
    }

    /**
     * Detects if the current device is an Amazon Kindle (eInk devices only).
     * Note: For the Kindle Fire, use the normal Android methods.
     * @return detection of a Kindle
     */
    public final boolean detectKindle() {
        return userAgent.contains(deviceKindle) && !detectAndroid();
    }

    /**
     * Detects if the current Amazon device is using the Silk Browser.
     * Note: Typically used by the the Kindle Fire.
     * @return detection of an Amazon Kindle Fire in Silk mode.
     */
    public final boolean detectAmazonSilk() {
        return userAgent.contains(engineSilk);
    }

    /**
     * Detects if the current browser is a
     *    Garmin Nuvifone.
     * @return detection of a Garmin Nuvifone
     */
    public boolean detectGarminNuvifone() {
        return userAgent.contains(deviceNuvifone);
    }

    /**
     * Detects a device running the Bada OS from Samsung.
     * @return detection of a Bada device
     */
    public final boolean detectBada() {
        return userAgent.contains(deviceBada);
    }

    /**
     * Detects a device running the Tizen smartphone OS.
     * @return detection of a Tizen device
     */
    public final boolean detectTizen() {
        return userAgent.contains(deviceTizen) && userAgent.contains(mobile);
    }

    /**
     * Detects if the current browser is on a Tizen smart TV.
     * @return detection of a Tizen smart TV
     */
    public boolean detectTizenTV() {
        return userAgent.contains(deviceTizen) && userAgent.contains(smartTV1);
    }

    /**
     * Detects a device running the Meego OS.
     * @return detection of a Meego device
     */
    public boolean detectMeego() {
        return userAgent.contains(deviceMeego);
    }

    /**
     * Detects a phone running the Meego OS.
     * @return detection of a Meego phone
     */
    public final boolean detectMeegoPhone() {
        return userAgent.contains(deviceMeego) && userAgent.contains(mobi);
    }

    /**
     * Detects a mobile device (probably) running the Firefox OS.
     * @return detection of a Firefox OS mobile device
     */
    public boolean detectFirefoxOS() {
        return detectFirefoxOSPhone() || detectFirefoxOSTablet();

    }

    /**
     * Detects a phone (probably) running the Firefox OS.
     * @return detection of a Firefox OS phone
     */
    public final boolean detectFirefoxOSPhone()
    {
        //First, let's make sure we're NOT on another major mobile OS.
        return !(detectIos() || detectAndroid() || detectSailfish())
            && userAgent.contains(engineFirefox)
            && userAgent.contains(mobile);

    }

    /**
     * Detects a tablet (probably) running the Firefox OS.
     * @return detection of a Firefox OS tablet
     */
    public final boolean detectFirefoxOSTablet()
    {
        //First, let's make sure we're NOT on another major mobile OS.
        return !(detectIos() || detectAndroid() || detectSailfish())
            && userAgent.contains(engineFirefox)
            && userAgent.contains(deviceTablet);

    }

    /**
     * Detects a device running the Sailfish OS.
     * @return detection of a Sailfish device
     */
    public final boolean detectSailfish() {
        return userAgent.contains(deviceSailfish);
    }

    /**
     * Detects a phone running the Sailfish OS.
     * @return detection of a Sailfish phone
     */
    public final boolean detectSailfishPhone() {
        return detectSailfish() && userAgent.contains(mobile);

    }

    /**
     * Detects a mobile device running the Ubuntu Mobile OS.
     * @return detection of an Ubuntu Mobile OS mobile device
     */
    public boolean detectUbuntu() {
        return detectUbuntuPhone() || detectUbuntuTablet();

    }

    /**
     * Detects a phone running the Ubuntu Mobile OS.
     * @return detection of an Ubuntu Mobile OS phone
     */
    public final boolean detectUbuntuPhone() {
        return userAgent.contains(deviceUbuntu) && userAgent.contains(mobile);

    }

    /**
     * Detects a tablet running the Ubuntu Mobile OS.
     * @return detection of an Ubuntu Mobile OS tablet
     */
    public final boolean detectUbuntuTablet() {
        return userAgent.contains(deviceUbuntu) && userAgent.contains(deviceTablet);

    }


    /**
     * Detects the Danger Hiptop device.
     * @return detection of a Danger Hiptop
     */
    public final boolean detectDangerHiptop() {
        return userAgent.contains(deviceDanger) || userAgent.contains(deviceHiptop);
    }

    /**
     * Detects if the current browser is a Sony Mylo device.
     * @return detection of a Sony Mylo device
     */
    public final boolean detectSonyMylo() {
        return userAgent.contains(manuSony) && (userAgent.contains(qtembedded) || userAgent.contains(mylocom2));
    }

    /**
     * Detects if the current device is on one of the Maemo-based Nokia Internet Tablets.
     * @return detection of a Maemo OS tablet
     */
    public final boolean detectMaemoTablet() {
        return userAgent.contains(maemo) || userAgent.contains(linux)
            && userAgent.contains(deviceTablet)
            && !detectWebOSTablet()
            && !detectAndroid();
    }

    /**
     * Detects if the current device is an Archos media player/Internet tablet.
     * @return detection of an Archos media player
     */
    public final boolean detectArchos() {
        return userAgent.contains(deviceArchos);
    }

    /**
     * Detects if the current device is an Internet-capable game console.
     * Includes many handheld consoles.
     * @return detection of any Game Console
     */
    public final boolean detectGameConsole() {
        return detectSonyPlaystation() || detectNintendo() || detectXbox();
    }

    /**
     * Detects if the current device is a Sony Playstation.
     * @return detection of Sony Playstation
     */
    public final boolean detectSonyPlaystation() {
        return userAgent.contains(devicePlaystation);
    }

    /**
     * Detects if the current device is a handheld gaming device with
     * a touchscreen and modern iPhone-class browser. Includes the Playstation Vita.
     * @return detection of a handheld gaming device
     */
    public final boolean detectGamingHandheld() {
        return userAgent.contains(devicePlaystation) && userAgent.contains(devicePlaystationVita);
    }

    /**
     * Detects if the current device is a Nintendo game device.
     * @return detection of Nintendo
     */
    public final boolean detectNintendo() {
        return userAgent.contains(deviceNintendo) || userAgent.contains(deviceWii) || userAgent.contains(deviceNintendoDs);
    }

    /**
     * Detects if the current device is a Microsoft Xbox.
     * @return detection of Xbox
     */
    public final boolean detectXbox() {
        return userAgent.contains(deviceXbox);
    }

    /**
     * Detects whether the device is a Brew-powered device.
     * @return detection of a Brew device
     */
    public final boolean detectBrewDevice() {
        return userAgent.contains(deviceBrew);
    }

    /**
     * Detects whether the device supports WAP or WML.
     * @return detection of a WAP- or WML-capable device
     */
    public final boolean detectWapWml() {
        return httpAccept.contains(vndwap) || httpAccept.contains(wml);
    }

    /**
     * Detects if the current device supports MIDP, a mobile Java technology.
     * @return detection of a MIDP mobile Java-capable device
     */
    public final boolean detectMidpCapable() {
        return userAgent.contains(deviceMidp) || httpAccept.contains(deviceMidp);
    }



    //*****************************
    // Device Classes
    //*****************************

    /**
     * Check to see whether the device is any device
     *   in the 'smartphone' category.
     * @return detection of a general smartphone device
     */
    public final boolean detectSmartphone() {
        //Exclude duplicates from TierIphone
        return (detectTierIphone()
            || detectS60OssBrowser()
            || detectSymbianOS()
            || detectWindowsMobile()
            || detectBlackBerry()
            || detectMeegoPhone()
            || detectPalmOS());
    }

    /**
     *	Detects if the current device is a mobile device.
     *  This method catches most of the popular modern devices.
     *  Excludes Apple iPads and other modern tablets.
     * @return detection of any mobile device using the quicker method
     */
    public final boolean detectMobileQuick() {
        //Let's exclude tablets
        if (isTierTablet) {
            return false;
        }
        //Most mobile browsing is done on smartphones
        if (detectSmartphone()) {
            return true;
        }

        //Catch-all for many mobile devices
        if (userAgent.contains(mobile)) {
            return true;
        }

        if (detectOperaMobile()) {
            return true;
        }

        //We also look for Kindle devices
        if (detectKindle()
            || detectAmazonSilk()) {
            return true;
        }

        if (detectWapWml()
            || detectMidpCapable()
            || detectBrewDevice()) {
            return true;
        }

        return (userAgent.contains(engineNetfront)) || (userAgent.contains(engineUpBrowser));

    }

    /**
     * The longer and more thorough way to detect for a mobile device.
     *   Will probably detect most feature phones,
     *   smartphone-class devices, Internet Tablets,
     *   Internet-enabled game consoles, etc.
     *   This ought to catch a lot of the more obscure and older devices, also --
     *   but no promises on thoroughness!
     * @return detection of any mobile device using the more thorough method
     */
    public final boolean detectMobileLong() {
        if (detectMobileQuick()
            || detectGameConsole()) {
            return true;
        }

        if (detectDangerHiptop()
            || detectMaemoTablet()
            || detectSonyMylo()
            || detectArchos()) {
            return true;
        }

        if ((userAgent.contains(devicePda)) &&
            (!userAgent.contains(disUpdate))) //no index found
        {
            return true;
        }

        //Detect older phones from certain manufacturers and operators.
        return (userAgent.contains(uplink)) || (userAgent.contains(engineOpenWeb)) || (userAgent.contains(manuSamsung1)) || (userAgent
            .contains(manuSonyEricsson)) || (userAgent.contains(manuericsson)) || (userAgent.contains(svcDocomo)) || (userAgent
            .contains(svcKddi)) || (userAgent.contains(svcVodafone));

    }

    //*****************************
    // For Mobile Web Site Design
    //*****************************

    /**
     * The quick way to detect for a tier of devices.
     *   This method detects for the new generation of
     *   HTML 5 capable, larger screen tablets.
     *   Includes iPad, Android (e.g., Xoom), BB Playbook, WebOS, etc.
     * @return detection of any device in the Tablet Tier
     */
    public final boolean detectTierTablet() {
        return detectIpad()
            || detectAndroidTablet()
            || detectBlackBerryTablet()
            || detectFirefoxOSTablet()
            || detectUbuntuTablet()
            || detectWebOSTablet();
    }

    /**
     * The quick way to detect for a tier of devices.
     *   This method detects for devices which can
     *   display iPhone-optimized web content.
     *   Includes iPhone, iPod Touch, Android, Windows Phone 7 and 8, BB10, WebOS, Playstation Vita, etc.
     * @return detection of any device in the iPhone/Android/Windows Phone/BlackBerry/WebOS Tier
     */
    public final boolean detectTierIphone() {
        return detectIphoneOrIpod()
            || detectAndroidPhone()
            || detectWindowsPhone()
            || detectBlackBerry10Phone()
            || (detectBlackBerryWebKit()
            && detectBlackBerryTouch())
            || detectPalmWebOS()
            || detectBada()
            || detectTizen()
            || detectFirefoxOSPhone()
            || detectSailfishPhone()
            || detectUbuntuPhone()
            || detectGamingHandheld();
    }

    /**
     * The quick way to detect for a tier of devices.
     *   This method detects for devices which are likely to be capable
     *   of viewing CSS content optimized for the iPhone,
     *   but may not necessarily support JavaScript.
     *   Excludes all iPhone Tier devices.
     * @return detection of any device in the 'Rich CSS' Tier
     */
    public final boolean detectTierRichCss() {
        boolean result = false;
        //The following devices are explicitly ok.
        //Note: 'High' BlackBerry devices ONLY
        if (detectMobileQuick()) {

            //Exclude iPhone Tier and e-Ink Kindle devices.
            if (!detectTierIphone() && !detectKindle()) {

                //The following devices are explicitly ok.
                //Note: 'High' BlackBerry devices ONLY
                //Older Windows 'Mobile' isn't good enough for iPhone Tier.
                if (detectWebkit()
                    || detectS60OssBrowser()
                    || detectBlackBerryHigh()
                    || detectWindowsMobile()
                    || userAgent.contains(engineTelecaQ)) {
                    result= true;
                } // if detectWebkit()
            } //if !detectTierIphone()
        } //if detectMobileQuick()
        return result;
    }

    /**
     * The quick way to detect for a tier of devices.
     *   This method detects for all other types of phones,
     *   but excludes the iPhone and RichCSS Tier devices.
     * @return detection of a mobile device in the less capable tier
     */
    public final boolean detectTierOtherPhones() {
        //Exclude devices in the other 2 categories
        return detectMobileLong() && !detectTierIphone() && !detectTierRichCss();
    }
}
