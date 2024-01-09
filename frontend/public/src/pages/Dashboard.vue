<template>
  <v-container fluid grid-list-xl>  
        <v-card class="animated flipInX flashcard">
          <div style="background-color: #001d80;">
            <v-card-title class="pl-5 pt-5">
              <div class="display-1 pl-5 pt-5 card-pic-font" style="color: white">学位外语预报考</div>
            </v-card-title>
          </div>
        <v-card-text>
          <v-layout row-wrap v-if="!isSiteOpen">
             现在为非报名时间，报名通道关闭，请留意关注官网报名通知。
          </v-layout>
          <v-layout row-wrap v-if="isSiteOpen">
                <v-flex xs24 md12>
                    <v-text-field 
                      label="姓名" 
                      v-model="form.studentName" 
                      clearable 
                      clear-icon="cancel"
                      hint="姓名不能包含空格和特殊字符并且长度小于10个汉字"
                      :disabled="!formSubmitted"
                    ></v-text-field>
                    <v-select
                      v-model="form.studentSex"
                      :items="sexList"
                      label="性别"
                      :disabled="!formSubmitted"
                    ></v-select>
                    <v-text-field label="自考考号" 
                      v-model="form.studentStudyNum" 
                      clearable 
                      clear-icon="cancel"
                      hint="自考考号长度12位数字"
                      :disabled="!formSubmitted"
                    ></v-text-field>
                    <v-text-field 
                      label="年级" 
                      v-model="form.studentGrade" 
                      clearable 
                      clear-icon="cancel"
                      hint="如不清楚自己的年级，以自学考试第一科本科合格成绩取得年份为准, 长度4位，例如2015"
                      :disabled="!formSubmitted"
                    ></v-text-field>
                    <v-select
                      v-model="form.studentMajor"
                      :items="majorList"
                      outlined
                      label="专业"
                      :disabled="!formSubmitted"
                    ></v-select>
                    <v-select
                      v-model="form.language"
                      :items="languageList"
                      outlined
                      label="报考语种"
                      :disabled="!formSubmitted"
                    ></v-select>
                    <v-text-field 
                      label="身份证号码" 
                      v-model="form.studentIdentityNum" 
                      clearable 
                      clear-icon="cancel"
                      hint="18位身份证号码"
                      :disabled="!formSubmitted"
                    ></v-text-field>
                    <v-text-field 
                      label="联系电话" 
                      v-model="form.studentTel" 
                      clearable 
                      clear-icon="cancel"
                      hint="11位手机号码"
                      :disabled="!formSubmitted"
                    ></v-text-field>
                    <v-btn 
                      color="#001d80" 
                      @click="submitInfo" 
                      large 
                      style="margin-right:10ox" 
                      :disabled="!formSubmitted">
                      <div style="color: white">提交报名信息</div>
                      <v-progress-circular :size="22" :width="5" indeterminate v-if="!formSubmitted"></v-progress-circular>
                    </v-btn>
                </v-flex>
              <v-flex xs24 md12>
                <v-layout justify-center>
                  <label for="fileInput" slot="upload-label">
                      <figure>
                        <svg
                          xmlns="http://www.w3.org/2000/svg"
                          width="32"
                          height="32"
                          viewBox="0 0 32 32"
                        >
                          <path
                            class="path1"
                            d="M9.5 19c0 3.59 2.91 6.5 6.5 6.5s6.5-2.91 6.5-6.5-2.91-6.5-6.5-6.5-6.5 2.91-6.5 6.5zM30 8h-7c-0.5-2-1-4-3-4h-8c-2 0-2.5 2-3 4h-7c-1.1 0-2 0.9-2 2v18c0 1.1 0.9 2 2 2h28c1.1 0 2-0.9 2-2v-18c0-1.1-0.9-2-2-2zM16 27.875c-4.902 0-8.875-3.973-8.875-8.875s3.973-8.875 8.875-8.875c4.902 0 8.875 3.973 8.875 8.875s-3.973 8.875-8.875 8.875zM30 14h-4v-2h4v2z"
                          ></path>
                        </svg>
                      </figure>
                      <span class="upload-caption">上传照片</span>
                      <span class="upload-caption">格式：jpg, 不小于20kb，不大于40kb</span>
                    </label>
                </v-layout>
                 <v-layout justify-center>
                    <image-uploader
                      :preview="true"
                      outputFormat="file"
                      accept="image/jpeg"
                      @input="setImage"
                    >
                    </image-uploader>
                 </v-layout>
              </v-flex>
          </v-layout>
        </v-card-text>
      </v-card>
      <v-dialog :value="isShowDialog" v-if="isShowDialog" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">提交报名信息</v-card-title>
          <v-card-text>请确认报名信息的准确性，一经提交无法更改，确定提交吗.</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#001d80"  text @click="onConfirm"><div style="color: white">是</div></v-btn>
            <v-btn color="#001d80"  text @click="onCancel"><div style="color: white">否</div></v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog width="1500px" :value="isShoowReadConcent" v-if="isShoowReadConcent&&isSiteOpen">
        <v-card class="animated flipInX flashcard preview">
          <v-card-text>
            <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formQuerySubmitting"></v-progress-circular>
            <div class="output ql-snow" v-if="!formQuerySubmitting">
                <div class="ql-editor editor" v-html="content" ></div>
            </div>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#001d80" text @click="onConfirmConcent"><div style="color: white">已阅读，了解报名须知</div></v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
  </v-container>
</template>

<script>
export default {
  components: {},
  created: function() {
    this.loadSiteInfo();
  },
  data() {
    return {
      formQuerySubmitting: false,
      siteManageQuerySubmitting: false,
      isSiteOpen: false,
      form:{
        studentName:"",
        studentSex:"",
        studentStudyNum:"",
        studentGrade:"",
        studentMajor:"",
        studentIdentityNum:"",
        studentTel:"",
        language:"",
        imageContent:""
      },
      sexList:["男","女"],
      majorList:["会计学","金融学","人力资源管理","工商管理"],
      languageList:["英语","日语"],
      formSubmitted:true,
      isShowDialog:false,
      imageMaxSize: this.$constants.registryForm.imageMaxSize,
      imageScaleRatio: this.$constants.registryForm.imageScaleRatio,
      image: null,
      allowImageUpload:true,
      isShoowReadConcent:true,
      content:""
    };
  },
  methods: {
    loadSiteInfo: function(){
      this.siteManageQuerySubmitting = true;
      var requestObj = {
          url: this.$constants.interface.backend.endpoint + "/student/registry/site/manage",
          successCallback: this.onSuccessSubmitSiteManageForm,
          failureCallback: this.onFailuredSubmitSiteManageForm,
          ins: this,
          hideLoading: true
      };
      this.$commons.sendGatewayGet(requestObj);
    },
    onSuccessSubmitSiteManageForm: function(response,callbackParam) {
        this.siteManageQuerySubmitting = false;
        if(response.data.siteManageData && response.data.siteManageData.pk){
          this.isSiteOpen = response.data.siteManageData.isSiteOpen;
          if(this.isSiteOpen){
            this.loadBulletin();
          }
        }
    },
    onFailuredSubmitSiteManageForm: function(error,callbackParam) {
        //if not handled by interceptor
        if(!error.handled){
          console.error(JSON.stringify(error));
          this.siteManageQuerySubmitting = false;
          var resResult = error.response;
          if (resResult && resResult.data) {
              if(this.$constants.exception[resResult.data.reasonCode]){
                this.$commons.showError(this.$constants.exception[resResult.data.reasonCode], this);
              }else{
                this.$commons.showError(resResult.data.msg, this);
              }
          }else{
            this.$commons.showError("出现无法预料的错误，请联系系统管理员", this);
          }
        }
    },
    loadBulletin: function(){
      this.formQuerySubmitting = true;
      var requestObj = {
          url: this.$constants.interface.backend.endpoint + "/student/registry/bulletin",
          successCallback: this.onSuccessSubmitQueryForm,
          failureCallback: this.onFailuredSubmitQueryForm,
          ins: this,
          hideLoading: true
      };
      this.$commons.sendGatewayGet(requestObj);
    },
    onSuccessSubmitQueryForm: function(response,callbackParam) {
        this.formQuerySubmitting = false;
        if(response.data.bulletinData && response.data.bulletinData.pk){
          this.content = response.data.bulletinData.content;
        }
    },
    onFailuredSubmitQueryForm: function(error,callbackParam) {
        //if not handled by interceptor
        if(!error.handled){
          console.error(JSON.stringify(error));
          this.formQuerySubmitting = false;
          var resResult = error.response;
          if (resResult && resResult.data) {
              if(this.$constants.exception[resResult.data.reasonCode]){
                this.$commons.showError(this.$constants.exception[resResult.data.reasonCode], this);
              }else{
                this.$commons.showError(resResult.data.msg, this);
              }
          }else{
            this.$commons.showError("出现无法预料的错误，请联系系统管理员", this);
          }
        }
    },
    onConfirmConcent:function(){
      this.isShoowReadConcent = false;
    },
    submitInfo:function(){
      if(this.validateForm()){
        this.isShowDialog = true;
      }
    },
    validateForm:function(){
        if(!this.form.studentName
						||this.form.studentName.length>this.$constants.registryForm.validator.registry_student_name_length
            ||!/^[\u4E00-\u9FA5]*$/g.test(this.form.studentName)){
            this.$commons.showError("姓名不能为包含空格和特殊字符并且长度小于" + this.$constants.registryForm.validator.registry_student_name_length + "个汉字", this);
					return false;
				}
				if(!this.form.studentSex
						||((this.form.studentSex !== this.$constants.registryForm.validator.registry_student_sex_male)
							&&(this.form.studentSex !== this.$constants.registryForm.validator.registry_student_sex_female))){
              this.$commons.showError("性别不能为空", this);
					return false;
				}
				if(!this.form.studentStudyNum
            ||isNaN(this.form.studentStudyNum)
						||this.form.studentStudyNum.length!==this.$constants.registryForm.validator.registry_student_study_num_length){
            this.$commons.showError("自考考号不能为空并且长度应为" + this.$constants.registryForm.validator.registry_student_study_num_length + "位", this);
					return false;
				}
				if(!this.form.studentGrade
            ||isNaN(this.form.studentGrade)
						||this.form.studentGrade.length>this.$constants.registryForm.validator.registry_student_grade_length){
            this.$commons.showError("年级不能为空并且长度应小于" + this.$constants.registryForm.validator.registry_student_grade_length + "位", this);
					return false;
				}
				if(!this.form.studentMajor
						||this.form.studentMajor.length>this.$constants.registryForm.validator.registry_student_major_length){
            this.$commons.showError("专业不能为空并且长度应小于" + this.$constants.registryForm.validator.registry_student_major_length + "位", this);
					return false;
				}
				if(!this.form.studentIdentityNum
						||this.form.studentIdentityNum.length!==this.$constants.registryForm.validator.registry_identity_length){
            this.$commons.showError("身份证号码不能为空并且长度应为" + this.$constants.registryForm.validator.registry_identity_length + "位", this);
					return false;
				}
				if(!this.form.studentTel
            ||isNaN(this.form.studentTel)
						||this.form.studentTel.length!==this.$constants.registryForm.validator.registry_tel_length){
            this.$commons.showError("联系电话不能为空并且长度应为" + this.$constants.registryForm.validator.registry_tel_length + "位", this);
					return false;
				}
				if(!this.form.imageContent){
          this.$commons.showError("请单击右侧照相机上传照片", this);
					return false;
				}
        if(!this.allowImageUpload){
          this.$commons.showError("请调整图片格式或大小:只允许jpg格式文件，文件应大于20kb且小于40kb", this);
					return false;
				}


        return true;
    },
    onConfirm:function(){
      this.isShowDialog = false;
      this.submitFormToBackend();
    },
    onCancel:function(){
      this.isShowDialog = false;
    },
    submitFormToBackend: function() {
        this.formSubmitted = false;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/student/registry/submit",
            successCallback: this.onSuccessSubmitForm,
            failureCallback: this.onFailuredSubmitForm,
            postData:this.form,
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayPost(requestObj);
    },
    onSuccessSubmitForm: function(response,callbackParam) {
        this.$commons.showSuccess("成功提交报名信息！", this);
        this.formSubmitted = true;
    },
    onFailuredSubmitForm: function(error,callbackParam) {
        console.error(JSON.stringify(error));
        this.formSubmitted = true;
        var resResult = error.response;
        if (resResult && resResult.data && resResult.data.reasonCode) {
            if(this.$constants.exception[resResult.data.reasonCode]){
              this.$commons.showError(this.$constants.exception[resResult.data.reasonCode], this);
            }else{
              this.$commons.showError(resResult.data.msg, this);
            }
        }else{
          this.$commons.showError("出现无法预料的错误，请联系系统管理员", this);
        }
    },
    setImage: function(output) {
      this.loadImageFileAsURL(output);
      if(output.type!==this.$constants.registryForm.imageType){
        this.$commons.showError("只允许jp(e)g图片格式", this);
        this.allowImageUpload = false;
        return;
      }
      if(output.size<this.$constants.registryForm.imageMinSize || output.size>this.$constants.registryForm.imageMaxSize){
        this.$commons.showError("图片大小必须介于20kb与40KB之间", this);
        this.allowImageUpload = false;
        return;
      }

      this.allowImageUpload = true;
    },
    loadImageFileAsURL: function(file) {
      var reader = new FileReader();
      var ins = this;
      reader.onloadend = function() {
        ins.form.imageContent = reader.result;
      }
      reader.readAsDataURL(file);
    }
  }
};
</script>

<style>

 #fileInput{
    display: none;
  }

  .v-messages__message {
    font-size: 15px !important;
    color: red !important;
  }
</style>
