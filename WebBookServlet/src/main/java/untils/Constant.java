package untils;

public class Constant {

    // Thông báo lỗi
    public static final String BOOK_ID_INVALID_VALIDATE_MSG = "Mã sách không hợp lệ.";
    public static final String BOOK_QUANTITY_IN_STOCK_INVALID_VALIDATE_MSG = "Số lượng mua vượt quá tồn kho.";
    public static final String ADD_ORDER_ERROR_MSG = "Đã xảy ra lỗi khi tạo đơn hàng. Vui lòng thử lại.";
    public static final String NOT_ENOUGHT_ORDER_ERROR_MSG = "Một số mặt hàng không còn đủ số lượng trong kho.";
    public static final String ORDER_ID_INVALID_VALIDATE_MSG = "Mã đơn hàng không hợp lệ.";
    public static final String VALUE_INVALID_VALIDATE_MSG = "Giá trị xác nhận không hợp lệ.";
    public static final String UPDATE_ORDER_SUCCESS = "Cập nhật trạng thái đơn hàng thành công.";
    public static final String UPDATE_ORDER_FAIL = "Cập nhật trạng thái đơn hàng thất bại.";

    // Thông điệp validate
    public static final String DELIVERY_ADDRESS_EMPTY_VALIDATE_MSG = "Vui lòng nhập địa chỉ nhận sách.";
    public static final String TRANSFER_IMAGE_EMPTY_VALIDATE_MSG = "Vui lòng chọn ảnh xác nhận chuyển khoản.";

    // Hành động
    public static final String ADD_TO_CART_ACTION = "addToCart";
    public static final String REMOVE_FROM_CART_ACTION = "removeFromCart";
    public static final String CART_OF_CUSTOMER = "cartOfCustomer";
    public static final String ORDER_OF_CUSTOMER = "orderOfCustomer";
    public static final String ORDER_LIST_OF_CUSTOMER = "orderListOfCustomer";

    // Trạng thái đơn hàng (byte)
    public static final byte WAITING_CONFIRM_ORDER_STATUS = 0;
    public static final byte DELIVERING_ORDER_STATUS = 1;
    public static final byte DELIVERED_ORDER_STATUS = 2;
    public static final byte CANCEL_ORDER_STATUS = 3;
    public static final byte REJECT_ORDER_STATUS = 4;
    public static final byte NOT_AVAIABLE_ORDER_STATUS = 5;

    // Trạng thái thanh toán
    public static final String PAYMENTED_STATUS = "Đã thanh toán";
    public static final String UNPAYMENT_STATUS = "Chưa thanh toán";

    // Đường dẫn tương ứng với trạng thái đơn hàng
    public static final String WAITING_APPROVE_ACTION = "waiting";
    public static final String DELIVERING_ACTION = "delivering";
    public static final String DELIVERED_ACTION = "delivered";
    public static final String REJECT_ACTION = "reject";

    // Phương thức thanh toán
    public static final String CASH_PAYMENT_MODE = "cash";
    public static final String TRANSFER_PAYMENT_MODE = "transfer";

    // Login
    public static final String LOGINED_USER = "user";
}