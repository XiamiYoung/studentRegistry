<template>
  <v-container fluid grid-list-xl>
    <v-card class="animated flipInX flashcard">
      <div style="background-color: #001d80;">
        <v-card-title class="pl-5 pt-5">
          <div class="display-1 pl-5 pt-5 card-pic-font" style="color: white">报考数据管理</div>
        </v-card-title>
      </div>
      <v-card-text>
        <v-layout justify-center>	
          <v-flex xs20 md10 >
            <v-text-field 
              label="身份证号码" 
              v-model="queryIdentityNum" 
              clearable 
              clear-icon="cancel"
              hint="输入考生身份证号码"
            ></v-text-field>
          </v-flex>
          <v-flex xs4>
            <v-btn 
              color="#001d80"
              @click="submitQuery" 
              large 
              style="margin-right:10ox">
              <div style="color: white">查询</div>
              <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formQuerySubmitting"></v-progress-circular>
            </v-btn>
            <v-btn 
              color="#001d80"
              @click="submitDelete" 
              large 
              :disabled="!(formQuerySubmitted&&dataQueryByIdFound)"
              style="margin-right:10ox">
              <div style="color: white">删除</div>
              <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formDeleteSubmitting"></v-progress-circular>
            </v-btn>
          </v-flex>
        </v-layout>
          <v-layout row-wrap v-if="formQuerySubmitted&&dataQueryByIdFound">
                <v-flex xs24 md12>
                    <v-text-field 
                      label="姓名" 
                      v-model="form.studentName" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                    <v-text-field 
                      label="性别" 
                      v-model="form.studentSex" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                    <v-text-field label="自考考号" 
                      v-model="form.studentStudyNum" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                    <v-text-field 
                      label="年级" 
                      v-model="form.studentGrade" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                    <v-text-field 
                      label="专业" 
                      v-model="form.studentMajor" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                    <v-text-field 
                      label="报考语种" 
                      v-model="form.language" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                    <v-text-field 
                      label="联系电话" 
                      v-model="form.studentTel" 
                      :disabled="fieldDisabled"
                    ></v-text-field>
                </v-flex>
              <v-flex xs6 md6>
                 <v-layout justify-center>
                    <v-img :src="form.imageContent" height="100%" weight="100%">
                  </v-img>
                 </v-layout>
              </v-flex>
          </v-layout>
          <v-layout row-wrap v-if="!dataQueryByIdFound">
              <v-flex xs24 md12>
                  没有查询到相关信息
              </v-flex>
          </v-layout>
      </v-card-text>
      <v-dialog :value="isShowDialog" v-if="isShowDialog" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">删除报名信息</v-card-title>
          <v-card-text>请确认将要删除报名信息，一经删除无法恢复，确定删除吗.</v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="#001d80"  text @click="onConfirm"><div style="color: white">是</div></v-btn>
            <v-btn color="#001d80"  text @click="onCancel"><div style="color: white">否</div></v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-card>
  </v-container>
</template>

<script>
export default {
  components: {},
  created: function() {
  },
  data() {
    return {
      queryIdentityNum:"",
      form:{},
      formQuerySubmitting:false,
      formQuerySubmitted:false,
      formDeleteSubmitting:false,
      formDeleteSubmitted:false,
      fieldDisabled:true,
      dataQueryByIdFound:true,
      isShowDialog:false
    };
  },
  methods: {
    submitQuery:function(){
      if(this.validateForm()){
        this.submitQueryFormToBackend();
      }
    },
    validateForm:function(){
				if(!this.queryIdentityNum
						||this.queryIdentityNum.length!==this.$constants.registryForm.validator.registry_identity_length){
            this.$commons.showError("身份证号码不能为空并且长度应为" + this.$constants.registryForm.validator.registry_identity_length + "位", this);
					return false;
				}
        return true;
    },
    submitQueryFormToBackend: function() {
        this.formQuerySubmitted = false;
        this.formQuerySubmitting = true;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/registry/" + this.queryIdentityNum,
            successCallback: this.onSuccessSubmitQueryForm,
            failureCallback: this.onFailuredSubmitQueryForm,
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayGet(requestObj);
    },
    onSuccessSubmitQueryForm: function(response,callbackParam) {
        this.formQuerySubmitted = true;
        this.formQuerySubmitting = false;
        if(response.data.studentRegistryData && response.data.studentRegistryData.pk){
          this.form = response.data.studentRegistryData;
          this.dataQueryByIdFound = true;
        }else{
          this.dataQueryByIdFound = false;
        }
    },
    onFailuredSubmitQueryForm: function(error,callbackParam) {
        //if not handled by interceptor
        if(!error.handled){
          console.error(JSON.stringify(error));
          this.formQuerySubmitted = true;
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
    submitDelete:function(){
      this.isShowDialog = true;
    },
    onConfirm:function(){
      this.isShowDialog = false;
      this.submitDeleteToBackend();
    },
    onCancel:function(){
      this.isShowDialog = false;
    },
    submitDeleteToBackend: function() {
        this.formDeleteSubmitted = false;
        this.formDeleteSubmitting = true;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/registry/" + this.queryIdentityNum,
            successCallback: this.onSuccessSubmitDeleteForm,
            failureCallback: this.onFailuredSubmitDeleteForm,
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayDelete(requestObj);
    },
    onSuccessSubmitDeleteForm: function(response,callbackParam) {
        this.$commons.showSuccess("成功删除报名信息！", this);
        this.formQuerySubmitted = false;
        this.formDeleteSubmitted = true;
        this.formDeleteSubmitting = false;
    },
    onFailuredSubmitDeleteForm: function(error,callbackParam) {
        console.error(JSON.stringify(error));
        this.formDeleteSubmitted = true;
        this.formDeleteSubmitting = false;
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
    }
  }
};
</script>

<style scoped>
  .student-pic{
    margin-top:80px
  }
</style>
