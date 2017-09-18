package com.test.manager;

public class UploadConstant {
	public static enum Systype {
		CLS("cls", "新车贷系统");

		private String key;
		private String desc;

		private Systype(String key, String desc) {
			this.key = key;
			this.desc = desc;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}

	public static enum FileNamespace {
		CUSTOMER("customer", "客户管理"),
		CREDIT("credit", "个人征信"),
		LOAN("loan", "贷款管理"),
		CAR_DEALER("car_dealer", "车商管理"),
		SECONDHAND_CAR("secondhand_car", "二手车管理"),
		DELIVER("deliver", "文档传递"),
		FEEDBACK("feedback", "在线反馈"),
		CAR_PLEDGE("car_pledge", "抵押登记") ,
		LOAN_TEMPLATE("loan_template", "模版管理"),
		LAWSUIT("lawsuit","诉讼管理"),
		DRAGCAR("dragcar","拖车管理管理"),
		SETTLEMENT("settlement","结清处理"),
		LOAN_PATCH("loan_patch","补件管理"),
		ASSET_PACKAGE("asset_package","资产分发"),
		INSURANCE_DISTRIBUTION("insurance_distribution","保险分发"),
		COMMON_QUESTION("common_question","常见问题");

		private String key;
		private String desc;

		private FileNamespace(String key, String desc) {
			this.key = key;
			this.desc = desc;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public static FileNamespace getEnum(String key) {
			for(FileNamespace fileNamespace: FileNamespace.values()){
				if(fileNamespace.getKey().equals(key))
					return fileNamespace;
			}
			return null;
		}
	}
}
