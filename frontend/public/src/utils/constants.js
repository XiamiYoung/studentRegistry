var baseContext = {
}
module.exports = {
	interface:{
		backend:{
			endpoint:"http://39.100.64.122:9090/dufe"
		}
	},
	registryForm: {
		validator: {
			registry_student_sex_male: "男",
			registry_student_sex_female: "女",
			registry_student_name_length: 10,
			registry_student_grade_length: 4,
			registry_student_major_length: 30,
			registry_identity_length: 18,
			registry_student_study_num_length: 12,
			registry_tel_length: 11
		},
		imageMinSize:20480, //20kb
		imageMaxSize:40960, //40kb
		imageType:"image/jpeg"
	},
	exception:{
		"DSR1001":"提交参数错误,请检查提交数据的格式",
		"DSR4001":"此考生已经提交过信息，请不要重复提交",
		"DSR7002":"现在为非报名时间，报名通道关闭，请留意关注官网报名通知。"
	}
}
