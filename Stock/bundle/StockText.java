package bundle;

import java.util.ArrayList;
import java.util.ListResourceBundle;

public class StockText extends ListResourceBundle {

	String[][] texts = new String[][] {

			{ "ok", "تایید" },
			{ "StoreToStock", "ذخیره جنس در انبار" },
			{ "SoldDate", "تاریخچه برداشت " },
			{ "StoreDate", "تاریخچه ذخیره " },
			{ "PringList", "چاپ لیست" }, 
			{ "search", "جستجوی محصول" },
			{ "Title", "لیست موجودی انبار" },
			{ "Please select a row", "لطفا یک ردیف را انتخاب کنید" },
			{ "Information", "معلومات" },
			{ "cancel", "انصراف" },
			{ "edit", "ویرایش" },
			{ "TotalStore", "مجموع ذخیره :" },
			{ "TotalSold", "مجموع برداشت :" },
			{ "edit Selected Store", "ویرایش مقدار و تاریخ" },
			{ "NumberFormatException", "لطفا قیمت عددی را وارد نمائید " },
			{ "NullPointerException", "لطفا تمام قیمت ها را وارد نمائید" },
			{ "ok", "تایید" }
			
			
	};

	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return texts;
	}

}
