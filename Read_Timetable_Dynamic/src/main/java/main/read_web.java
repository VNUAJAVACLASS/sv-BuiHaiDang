package main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.WaitForSelectorState;

public class read_web {

    private static LocalDate startDate;

    public static class WebData {
        public String tableHtml;
        public LocalDate startDate;

        public WebData(String tableHtml, LocalDate startDate) {
            this.tableHtml = tableHtml;
            this.startDate = startDate;
        }
    }

    public static WebData fetchData() throws Exception {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            Page page = browser.newPage();

            // Mở trang web
            page.navigate("https://daotao.vnua.edu.vn/#/home");

            // Nhập thông tin đăng nhập bằng JOptionPane
            String[] credentials = readCredentialsWithSwing();
            String msv = credentials[0];
            String mk = credentials[1];

            // Kiểm tra nếu người dùng hủy nhập liệu
            if (msv == null || mk == null) {
                System.out.println("Đã hủy nhập thông tin đăng nhập.");
                browser.close();
                return new WebData(null, null);
            }

            // Điền thông tin đăng nhập
            page.locator("//input[@name='username']").fill(msv);
            page.locator("//input[@name='password']").fill(mk);

            // Nhấn Enter để đăng nhập
            page.keyboard().press("Enter");

            // Vào thời khóa biểu tuần
            page.locator("//a[@id='WEB_TKB_1TUAN']").click();
            page.waitForTimeout(5000);

            // Chọn tuần học
            page.locator(
                    "#fullScreen > div.card-body.p-0 > div.row.text-nowrap.px-1.pb-1 > div.d-inline-block.col-lg-7.col-md-12.col-sm-12.mb-1 > ng-select > div > div > div.ng-input")
                    .click();
            page.waitForTimeout(2000);

            // Cuộn lên trên
            page.evaluate("() => document.querySelector('.ng-dropdown-panel-items.scroll-host').scrollTo(0, 0)");
            page.waitForTimeout(1000); // Chờ cuộn hoàn tất

            // Lấy text của phần tử đầu tiên trong danh sách bằng XPath
            String weekText = page.locator(
                    "//div[@class='ng-dropdown-panel-items scroll-host']//div[contains(@class, 'ng-option')][1]")
                    .textContent().trim();
            System.out.println("Thông tin tuần: " + weekText);

            // Trích xuất ngày bắt đầu dưới dạng chuỗi
            String dateString = weekText.split("\\[từ ngày")[1].split("đến ngày")[0].trim();
            System.out.println("Ngày bắt đầu (chuỗi): " + dateString);

            // Chuyển đổi chuỗi ngày thành LocalDate
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            startDate = LocalDate.parse(dateString, formatter);
            System.out.println("Ngày bắt đầu (LocalDate): " + startDate);

            // Chọn thời khóa biểu học kỳ
            page.locator("//a[@id='WEB_TKB_HK']").click();
            page.waitForTimeout(5000);

            // Mở dropdown học kỳ
            String semesterDropdownSelector = "body > app-root:nth-child(1) > div > div > div > div.contentshow.ng-star-inserted > div > div > div.px-md-0.frame_left > app-tkb-hocky > div > div.card-body.p-2 > div:nth-child(1) > div > ng-select > div";
            page.locator(semesterDropdownSelector).click();
            page.waitForTimeout(2000);

            // Chọn học kỳ 2
            String semesterOptionSelector = "//div[contains(@class, 'ng-option') and contains(text(), 'Học kỳ 2 - Năm học 2024 - 2025')]";
            page.locator(semesterOptionSelector).click();
            System.out.println("Đã chọn học kỳ 2 - Năm học 2024 - 2025");

            // Tăng thời gian chờ để bảng cập nhật
            page.waitForTimeout(7000); // Tăng lên 7000ms để đảm bảo dữ liệu tải

            // Chọn table học kỳ và lấy dữ liệu
            String tableSelector = "//*[@id=\"printArea\"]/div[2]/table";
            page.waitForSelector(tableSelector,
                    new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(60000));

            // Sử dụng evaluate để lấy outerHTML
            String tableHtml = (String) page.evaluate(
                    "selector => document.evaluate(selector, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.outerHTML",
                    tableSelector);

            // Kiểm tra nếu bảng không có dữ liệu
            if (tableHtml.contains("Không tìm thấy dữ liệu")) {
                System.out.println(
                        "Học kỳ 2 - Năm học 2024 - 2025 không có dữ liệu thời khóa biểu. Vui lòng thử học kỳ khác.");
                browser.close();
                return new WebData(null, startDate);
            }

            System.out.println("Đã lấy dữ liệu thành công!");
            browser.close();
            return new WebData(tableHtml, startDate);
        }
    }

    private static String[] readCredentialsWithSwing() {
        // Tạo panel để chứa các trường nhập liệu
        JPanel panel = new JPanel();
        panel.setLayout(new java.awt.GridLayout(2, 2));

        // Tạo nhãn và trường nhập cho mã sinh viên
        JLabel userLabel = new JLabel("Mã sinh viên:");
        JTextField userField = new JTextField(15);
        panel.add(userLabel);
        panel.add(userField);

        // Tạo nhãn và trường nhập cho mật khẩu
        JLabel passLabel = new JLabel("Mật khẩu:");
        JPasswordField passField = new JPasswordField(15);
        panel.add(passLabel);
        panel.add(passField);

        // Hiển thị dialog để nhập thông tin
        int result = JOptionPane.showConfirmDialog(null, panel, "Nhập thông tin đăng nhập",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String username = userField.getText();
            char[] password = passField.getPassword();
            return new String[] { username, new String(password) };
        } else {
            return new String[] { null, null }; // Trả về null nếu người dùng hủy
        }
    }

    public static LocalDate getStartDate() {
        return startDate;
    }
}