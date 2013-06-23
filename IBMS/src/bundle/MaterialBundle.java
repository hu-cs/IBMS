package bundle;

import java.util.ListResourceBundle;
import java.util.Properties;

public class MaterialBundle extends ListResourceBundle {

	String[][] materialBundle = new String[][] { { "ok", "تایید" },
			{ "cancel", "انصراف" },
			{ "addMaterial", "افزودن محصول" },
			{ "deleteMaterial", "حذف محصول" },
			{ "editMaterial", "ویرایش محصول" },
			{ "print", "چاپ لیست" },
			{ "search", "جستجو" }, 
			{ "askForDelete", "آیا مطمئن هستید؟" },
			{ "enterNumber", "لطفا در جای مناسب عدد وارد کنید!" },
			{ "enterCorrect", "لطفا اطلاعات درست وارد کنید!" }
	// ,{"",""}

	};

	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return materialBundle;
	}

}
