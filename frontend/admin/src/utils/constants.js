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
			registry_identity_length: 18
		}
	},
	export:{
		excelName:"报名信息表.xlsx"
	},
	exception:{
		"DSR1001":"提交参数错误,请检查提交数据的格式",
		"DSR3001":"用户名或密码错误",
		"DSR5002":"无法读取用户图片，请确认图片是否存在",
		"DSR5003":"无法删除用户图片，请联系管理员",
		"DSR5004":"无法找到用户图片，请联系管理员",
		"DSR5005":"无法创建压缩包，请联系管理员",
		"DSR5006":"无法找到压缩包，请联系管理员",
		"DSR5007":"无法下载压缩包，请联系管理员",
		"DSR2002":"会话超时，请重新登录",
		"DSR0001":"请登录系统"
	}
}
