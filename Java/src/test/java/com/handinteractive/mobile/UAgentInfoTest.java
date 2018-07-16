package com.handinteractive.mobile;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UAgentInfoTest {

    @Test
    public void shouldDetectChromeAndroidMobile() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Linux; Android 8.1.0; Nexus 5X Build/OPM3.171019.013) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.111 Mobile Safari/537.36", null);

        assertThat(uAgentInfo.detectAndroidPhone(), is(true));
        assertThat(uAgentInfo.detectAndroidTablet(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectAndroid(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectFirefoxAndroidMobile() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Android 7.0; Mobile; rv:57.0) Gecko/57.0 Firefox/57.0", null);

        assertThat(uAgentInfo.detectAndroidPhone(), is(true));
        assertThat(uAgentInfo.detectAndroidTablet(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectAndroid(), is(true));

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldDetectAndroidBrowserMobile() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Linux; U; Android 4.4.4; en-gb; SM-G357FZ Build/KTU84P) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30", null);

        assertThat(uAgentInfo.detectAndroidPhone(), is(true));
        assertThat(uAgentInfo.detectAndroidTablet(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectAndroid(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectChromeAndroidTablet() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Linux; Android 8.0.0; Pixel C Build/OPR1.170623.027) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.111 Safari/537.36", null);

        assertThat(uAgentInfo.detectAndroidTablet(), is(true));
        assertThat(uAgentInfo.detectAndroidPhone(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsTabletDevice(uAgentInfo);

        assertThat(uAgentInfo.detectAndroid(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectFirefoxAndroidTablet() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Android 7.0; Tablet; rv:57.0) Gecko/57.0 Firefox/57.0", null);

        assertThat(uAgentInfo.detectAndroidTablet(), is(true));
        assertThat(uAgentInfo.detectAndroidPhone(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsTabletDevice(uAgentInfo);

        assertThat(uAgentInfo.detectAndroid(), is(true));

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldDetectAndroidBrowserTablet() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Linux; U; Android 4.4.2; en-gb; SM-T310 Build/KOT49H) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Safari/534.30", null);

        assertThat(uAgentInfo.detectAndroidTablet(), is(true));
        assertThat(uAgentInfo.detectAndroidPhone(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsTabletDevice(uAgentInfo);

        assertThat(uAgentInfo.detectAndroid(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectChromeOS() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (X11; CrOS x86_64 10032.75.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.116 Safari/537.36", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectBlackberryMobile() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (BlackBerry; U; BlackBerry 9900; en) AppleWebKit/534.11+ (KHTML, like Gecko) Version/7.1.0.346 Mobile Safari/534.11+", null);

        assertThat(uAgentInfo.detectBlackBerry(), is(true));
        assertThat(uAgentInfo.detectBlackBerry10Phone(), is(false));
        assertThat(uAgentInfo.detectBlackBerryHigh(), is(false));
        assertThat(uAgentInfo.detectBlackBerryTouch(), is(true));
        assertThat(uAgentInfo.detectBlackBerryTablet(), is(false));
        assertThat(uAgentInfo.detectBlackBerryLow(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectBlackBerryWebKit(), is(true));
        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectSafariIPad() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (iPad; CPU OS 11_2_5 like Mac OS X) AppleWebKit/604.5.3 (KHTML, like Gecko) Version/11.0 Mobile/15D5049a Safari/604.1", null);

        assertThat(uAgentInfo.detectIpad(), is(true));
        assertThat(uAgentInfo.detectIpod(), is(false));
        assertThat(uAgentInfo.getIsIphone(), is(false));
        assertThat(uAgentInfo.detectIphoneOrIpod(), is(false));
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsTabletDevice(uAgentInfo);

        assertThat(uAgentInfo.detectIos(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectSafariIPhone() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (iPhone; CPU iPhone OS 11_2_5 like Mac OS X) AppleWebKit/604.5.3 (KHTML, like Gecko) Version/11.0 Mobile/15D5049a Safari/604.1", null);

        assertThat(uAgentInfo.getIsIphone(), is(true));
        assertThat(uAgentInfo.detectIpod(), is(false));
        assertThat(uAgentInfo.detectIpad(), is(false));
        assertThat(uAgentInfo.detectIphoneOrIpod(), is(true));
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectIos(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectSafariIpod() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (iPod touch; CPU iPhone OS 11_1_2 like Mac OS X) AppleWebKit/604.3.5 (KHTML, like Gecko) Version/11.0 Mobile/15B202 Safari/604.1", null);

        assertThat(uAgentInfo.detectIpod(), is(true));
        assertThat(uAgentInfo.getIsIphone(), is(false));
        assertThat(uAgentInfo.detectIpad(), is(false));
        assertThat(uAgentInfo.detectIphoneOrIpod(), is(true));
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectIos(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectSafariMacintosh() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectChromeMacintosh() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectFirefoxMacintosh() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.13; rv:57.0) Gecko/20100101 Firefox/57.0", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldDetectEdgeWindowsPhone() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Windows Phone 10.0; Android 6.0.1; Microsoft; Lumia 640 LTE) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/52.0.2743.116 Mobile Safari/537.36 Edge/15.15063", null);

        assertThat(uAgentInfo.detectWindowsPhone10(), is(true));
        assertThat(uAgentInfo.detectWindowsPhone8(), is(false));
        assertThat(uAgentInfo.detectWindowsPhone7(), is(false));
        assertThat(uAgentInfo.detectWindowsMobile(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectWindowsPhone(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldDetectInternetExplorerWindowsPhone() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Mobile; Windows Phone 8.1; Android 4.0; ARM; Trident/7.0; Touch; rv:11.0; IEMobile/11.0; Microsoft; Lumia 640 LTE) like iPhone OS 7_0_3 Mac OS X AppleWebKit/537 (KHTML, like Gecko) Mobile Safari/537", null);

        assertThat(uAgentInfo.detectWindowsPhone8(), is(true));
        assertThat(uAgentInfo.detectWindowsPhone7(), is(false));
        assertThat(uAgentInfo.detectWindowsPhone10(), is(false));
        assertThat(uAgentInfo.detectWindowsMobile(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);

        assertIsMobileDevice(uAgentInfo);

        assertThat(uAgentInfo.detectWindowsPhone(), is(true));

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectInternetExplorerWindows() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; Touch; rv:11.0) like Gecko", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldNotDetectEdgeWindows() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectChromeWindows() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.71 Safari/537.36", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsWebkit(uAgentInfo);
    }

    @Test
    public void shouldNotDetectFirefoxWindows() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldNotDetectFirefoxUbuntu() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:57.0) Gecko/20100101 Firefox/57.0", null);

        assertThat(uAgentInfo.detectUbuntu(), is(false));
        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldDetectFirefoxLinux() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (X11; Linux x86_64; rv:57.0) Gecko/20100101 Firefox/57.0", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsNotWebkitNorRichCss(uAgentInfo);
    }

    @Test
    public void shouldDetectOperaWindows() {
        UAgentInfo uAgentInfo = new UAgentInfo("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36 OPR/50.0.2762.45", null);

        assertIsNotAnyIDevice(uAgentInfo);
        assertIsNotAnyAndroidDevice(uAgentInfo);
        assertIsNotAnyWindowsDevice(uAgentInfo);

        assertIsNotSupportedDevice(uAgentInfo);

        assertIsWebkit(uAgentInfo);
    }

    private void assertIsNotAnyIDevice(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectIpod(), is(false));
        assertThat(uAgentInfo.getIsIphone(), is(false));
        assertThat(uAgentInfo.detectIpad(), is(false));
        assertThat(uAgentInfo.detectIphoneOrIpod(), is(false));
        assertThat(uAgentInfo.detectIos(), is(false));
    }

    private void assertIsNotAnyAndroidDevice(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectAndroid(), is(false));
        assertThat(uAgentInfo.detectAndroidPhone(), is(false));
        assertThat(uAgentInfo.detectAndroidTablet(), is(false));
        assertThat(uAgentInfo.detectAndroidWebKit(), is(false));
    }

    private void assertIsNotAnyWindowsDevice(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectWindowsPhone10(), is(false));
        assertThat(uAgentInfo.detectWindowsPhone8(), is(false));
        assertThat(uAgentInfo.detectWindowsPhone7(), is(false));
        assertThat(uAgentInfo.detectWindowsMobile(), is(false));
        assertThat(uAgentInfo.detectWindowsPhone(), is(false));
    }

    private void assertIsNotSupportedDevice(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectMobileQuick(), is(false));
        assertThat(uAgentInfo.detectMobileLong(), is(false));
        assertThat(uAgentInfo.getIsTierIphone(), is(false));
        assertThat(uAgentInfo.getIsTierTablet(), is(false));
        assertThat(uAgentInfo.detectGameConsole(), is(false));
        assertThat(uAgentInfo.getIsTierGenericMobile(), is(false));
    }

    private void assertIsMobileDevice(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectMobileQuick(), is(true));
        assertThat(uAgentInfo.detectMobileLong(), is(true));
        assertThat(uAgentInfo.getIsTierIphone(), is(true));
        assertThat(uAgentInfo.getIsTierTablet(), is(false));
        assertThat(uAgentInfo.detectGameConsole(), is(false));
        assertThat(uAgentInfo.getIsTierGenericMobile(), is(false));
    }

    private void assertIsTabletDevice(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.getIsTierTablet(), is(true));
        assertThat(uAgentInfo.detectMobileQuick(), is(false));
        assertThat(uAgentInfo.detectMobileLong(), is(false));
        assertThat(uAgentInfo.getIsTierIphone(), is(false));
        assertThat(uAgentInfo.detectGameConsole(), is(false));
        assertThat(uAgentInfo.getIsTierGenericMobile(), is(false));
    }

    private void assertIsWebkit(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectWebkit(), is(true));
        assertThat(uAgentInfo.getIsTierRichCss(), is(false));
    }

    private void assertIsNotWebkitNorRichCss(UAgentInfo uAgentInfo) {
        assertThat(uAgentInfo.detectWebkit(), is(false));
        assertThat(uAgentInfo.getIsTierRichCss(), is(false));
    }
}