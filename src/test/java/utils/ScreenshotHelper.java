package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for capturing screenshots during test execution.
 * Screenshots are saved to the 'screenshots' directory in the project root.
 *
 * Usage:
 *   ScreenshotHelper screenshot = new ScreenshotHelper(page);
 *   screenshot.capture("my-screenshot");
 *   screenshot.captureOnFailure("LoginTest", "testLogin");
 */
public class ScreenshotHelper {

    private final Page page;
    private static final String SCREENSHOT_DIR = "screenshots";
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public ScreenshotHelper(Page page) {
        this.page = page;
        createScreenshotDirectory();
    }

    /**
     * Creates the screenshot directory if it doesn't exist
     */
    private void createScreenshotDirectory() {
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Capture screenshot with a custom name
     * @param name Name for the screenshot file (without extension)
     * @return Path to the saved screenshot
     */
    public Path capture(String name) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("%s_%s.png", name, timestamp);
        Path path = Paths.get(SCREENSHOT_DIR, fileName);

        page.screenshot(new Page.ScreenshotOptions().setPath(path));
        System.out.println("Screenshot saved: " + path.toAbsolutePath());

        return path;
    }

    /**
     * Capture screenshot on test failure with class and method name
     * @param className Test class name
     * @param methodName Test method name
     * @return Path to the saved screenshot
     */
    public Path captureOnFailure(String className, String methodName) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("FAILED_%s_%s_%s.png", className, methodName, timestamp);
        Path path = Paths.get(SCREENSHOT_DIR, fileName);

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(path)
                .setFullPage(true));

        System.out.println("Failure screenshot saved: " + path.toAbsolutePath());
        return path;
    }

    /**
     * Capture full page screenshot (scrolls to capture entire page)
     * @param name Name for the screenshot file (without extension)
     * @return Path to the saved screenshot
     */
    public Path captureFullPage(String name) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("%s_fullpage_%s.png", name, timestamp);
        Path path = Paths.get(SCREENSHOT_DIR, fileName);

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(path)
                .setFullPage(true));

        System.out.println("Full page screenshot saved: " + path.toAbsolutePath());
        return path;
    }

    /**
     * Capture screenshot of a specific element
     * @param selector CSS/XPath selector for the element
     * @param name Name for the screenshot file (without extension)
     * @return Path to the saved screenshot
     */
    public Path captureElement(String selector, String name) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("%s_element_%s.png", name, timestamp);
        Path path = Paths.get(SCREENSHOT_DIR, fileName);

        page.locator(selector).screenshot(new Locator.ScreenshotOptions().setPath(path));
        System.out.println("Element screenshot saved: " + path.toAbsolutePath());

        return path;
    }

    /**
     * Capture screenshot of a specific element using Locator
     * @param locator Playwright Locator for the element
     * @param name Name for the screenshot file (without extension)
     * @return Path to the saved screenshot
     */
    public Path captureElement(Locator locator, String name) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("%s_element_%s.png", name, timestamp);
        Path path = Paths.get(SCREENSHOT_DIR, fileName);

        locator.screenshot(new Locator.ScreenshotOptions().setPath(path));
        System.out.println("Element screenshot saved: " + path.toAbsolutePath());

        return path;
    }

    /**
     * Capture screenshot with custom subdirectory
     * @param subDir Subdirectory name within screenshots folder
     * @param name Name for the screenshot file (without extension)
     * @return Path to the saved screenshot
     */
    public Path captureToSubDir(String subDir, String name) {
        String timestamp = LocalDateTime.now().format(TIMESTAMP_FORMAT);
        String fileName = String.format("%s_%s.png", name, timestamp);
        Path dirPath = Paths.get(SCREENSHOT_DIR, subDir);

        // Create subdirectory if it doesn't exist
        File dir = dirPath.toFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = dirPath.resolve(fileName);
        page.screenshot(new Page.ScreenshotOptions().setPath(path));
        System.out.println("Screenshot saved: " + path.toAbsolutePath());

        return path;
    }

    /**
     * Get the screenshot directory path
     * @return Path to screenshot directory
     */
    public static String getScreenshotDirectory() {
        return SCREENSHOT_DIR;
    }
}
