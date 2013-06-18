package bundle;

import java.util.ListResourceBundle;

public class CustomerTextbundle extends ListResourceBundle {

	public String [][] customerText = new String [][]{
			
			{"CustomerList","لیست مشتریان"},
			{"ok","تایید"},
			{"details","دیدن جزئیات"},
			{"AddCustomer","افزودن مشتری"},
			{"DeleteCustomer","حذف مشتری"},
			{"EditCustomer","ویرایش مشتری"},
			{"Print","چاپ لیست"},
			{"All","همه"},
			{"CustomerPaid","تصفیه شده"},
			{"DeptCustomer","بدهکاران"},
			{"Search","جستجو مشتری"},
			{"Name","نام"},
			{"LastName","تخلص"},
			{"Total","جـمـــــع کــــــــل"},
			{"Information","معلومات"},
			{"No","خیر"},
			{"Cancel","لغو"},
			{"CancelButton","انصراف"},
			{"Yes","بلی"},
			{"PleaseSelectOneRow","لطفا یک ردیف را انتخاب نمائید"},
			{"Payment","رسیده گی"},
			{"TotalPayment","کــــل رســـــیدگی"},
			{"Giveing","بـــردگــی"},
			{"TotalGiveing","کـــل بـــردگـی"},
			{"CompanyDemand","طــلب شـــــرکــت"},
			{"DeletePayment","حذف پرداخت"},
			{"EditPayment","ویرایش پرداخت"},
			{"AddToPaymentAccount","افزودن به حساب پرداخت"},
			{"SeeInvoice","دیدن فاکتور"},
			{"AddNewCustomer","افزودن مشتری جدید"},
			{"Date","تاریخ"},
			{"Cost","مبلغ"},
			{"EditCusotmerAccount","ویرایش حساب مشتری"},
			{"SelectMaterial","انتخاب جنس"},
			{"PrintInvoice","چاپ فاکتور"},
			{"Adress","آدرس"},
			{"PhoneNumber","شماره تماس"},
			{"Fax","فکس"},
			{"WebSite","وب سایت"},
			{"Email","ایمل"},
			{"InvoiceNumber","شماره فاکتور"},
			{"Customer","مشتری"},
			{"CustomerRegard","مشتری محترم"},
			{"PleaseInsertCorrectInformation","لطفا  معلومات را درست بیافزائید"},
			{"PleaseEnterAllInformation","لطفا تمام معلومات را بیافزائید"},
			{"IfyouDeleteCustomerFromListItwillBeDeleteAllInformation","در صورت پاک کردن، مشتری مورد نظر از لیست شما پاک خواهد شد، آیا هنوز مایل هستید ؟"},
			{"Alert","هشدار"},
			{"AreYouSure","آیامطءن هستید ؟" },
			{"Afghani","افغانی"},
			{"PlaceToStomp","محل مهر شرکت"},
			{"PlaceToAssignOrStomp","محل مهر یا امضا مشتری"},
			{"CustomersList","لیست مشتریان "},
			{"AtYear"," در سال "},
			{"Hour","    ساعت: "},
			{"Page","صفحه"}
					
			
			
			
			
			
			
			
			
	};
	
	
	
	@Override
	protected Object[][] getContents() {
		// TODO Auto-generated method stub
		return customerText;
	}

}
