package bundle;

import java.util.ListResourceBundle;

public class ExpenceText extends ListResourceBundle {

	private String[][] texts = new String[][] {
			{ "ok", "تایید" },
			{ "cancel", "انصراف" },
			{ "add", "افزودن مورد" },
			{ "edit", "ویرایش مورد" },
			{ "remove", "پاک کردن مورد" },
			{ "print", "چاپ لیست" },
			{ "selectType", "انتخاب نوع مصرف" },
			{ "search", "جستجوی فاکتور" },
			{ "addType", "افزودن نوع مصرف" },
			{ "total", "کل مبالغ تادیه شده" },
			{ "eidtingSelectRow", "لطفا برای ویرایش یک ردیف انتخاب کنید!" },
			{ "editingSelectType", "لطفا نوع مصرف را برای ویرایش انتخاب کنید!" },
			{ "addingSelectType",
					"رای افزودن مورد، لطفا بک نوع مصرف را انتخاب کنید." },
			{ "SignIn", "ورود" }, { "Accounter", "حسابدار" },
			{ "Manager", "حسابرس" }, { "selectUser", "انتخاب کاربر" },
			{ "Window", "پنجره ورودی" }, { "userName", "نام کاربری" },
			{ "password", "کلمه عبور" }, { "dataYear", "حساب سال" },
			{ "SeeInvoice", "مشاهده فاکتور" }, { "name", "نام" }, { "Address", "آدرس" },
			{ "Tell", "شماره تماس" }, { "fax", "فکس" }, { "webSite", "وب سایت" }, { "email", "ایمیل" }, { "logo", "لوگو" },
			{ "select", "انتخاب" },{"date","تاریخ"},{"cancel","انصراف"},{"addYear","ایجاد حساب سال"},{"deleteYear","حذف حساب سال"}};

	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return texts;
	}

}
