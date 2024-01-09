<template>
  <v-container fluid grid-list-xl>
    <v-card class="animated flipInX flashcard">
      <div style="background-color: #001d80;">
        <v-card-title class="pl-5 pt-5">
          <div class="display-1 pl-5 pt-5 card-pic-font" style="color: white">网站全局管理</div>
        </v-card-title>
      </div>
      <v-card-text>
        <v-layout justify-center>	
             <v-switch
              v-model="isSiteOpen"
              :label="siteOpenLabl"
              @change="submitEdit"
              color="blue"
            ></v-switch>
            <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formEditSubmitting"></v-progress-circular>
        </v-layout>
        <v-layout>
            <v-btn 
                color="#001d80"
                @click="submitDelete" 
                large 
                style="margin-right:10ox">
                <div style="color: white">永久清除所有报名数据</div>
                <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="siteClearSubmitting"></v-progress-circular>
            </v-btn>
        </v-layout>
      </v-card-text>
      <v-dialog :value="isShowDialog" v-if="isShowDialog" persistent max-width="290">
        <v-card>
          <v-card-title class="headline">永久清除所有报名数据</v-card-title>
          <v-card-text>请确认将要永久清除所有报名数据，包括报名信息和照片，一经删除无法恢复，请确保已导出数据，确定删除吗?</v-card-text>
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
    this.loadSwitch();
  },
  data() {
    return {
      formQuerySubmitting:false,
      formEditSubmitting:false,
      siteClearSubmitting:false,
      siteManagePk: null,
      isSiteOpen: true,
      siteOpenLabl:"",
      isShowDialog: false
    };
  },
  methods: {
    loadSwitch: function(){
      this.formQuerySubmitting = true;
      var requestObj = {
          url: this.$constants.interface.backend.endpoint + "/admin/site/manage",
          successCallback: this.onSuccessSubmitQueryForm,
          failureCallback: this.onFailuredSubmitQueryForm,
          ins: this,
          hideLoading: true
      };
      this.$commons.sendGatewayGet(requestObj);
    },
    onSuccessSubmitQueryForm: function(response,callbackParam) {
        this.formQuerySubmitting = false;
        if(response.data.siteManageData && response.data.siteManageData.pk){
          this.isSiteOpen = response.data.siteManageData.isSiteOpen;
          this.siteManagePk = response.data.siteManageData.pk;
          if(this.isSiteOpen){
            this.siteOpenLabl="网站已开放"
          }else{
            this.siteOpenLabl="网站已关闭"
          }
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
    submitEdit: function() {
        this.formEditSubmitting = true;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/site/manage",
            successCallback: this.onSuccessSubmitEditForm,
            failureCallback: this.onFailuredSubmitEditForm,
            postData:{
              isSiteOpen: this.isSiteOpen,
              pk: this.siteManagePk
            },
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayPost(requestObj);
    },
    onSuccessSubmitEditForm: function(response,callbackParam) {
        this.formEditSubmitting = false;
        if(response.data && response.data.pk){
          this.siteManagePk = response.data.pk;
        }
        if(this.isSiteOpen){
          this.siteOpenLabl="网站已开放"
          this.$commons.showSuccess("网站已开放！", this);
        }else{
          this.siteOpenLabl="网站已关闭"
          this.$commons.showSuccess("网站已关闭！", this);
        }
    },
    onFailuredSubmitEditForm: function(error,callbackParam) {
        //if not handled by interceptor
        if(!error.handled){
          console.error(JSON.stringify(error));
          this.formEditSubmitting = false;
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
        this.siteClearSubmitting = true;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/site/clear",
            successCallback: this.onSuccessSubmitDeleteForm,
            failureCallback: this.onFailuredSubmitDeleteForm,
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayDelete(requestObj);
    },
    onSuccessSubmitDeleteForm: function(response,callbackParam) {
        this.$commons.showSuccess("成功清除所有报名数据！", this);
        this.siteClearSubmitting = false;
    },
    onFailuredSubmitDeleteForm: function(error,callbackParam) {
        console.error(JSON.stringify(error));
        this.siteClearSubmitting = false;
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
}
</script>

<style>
  .student-pic{
    margin-top:80px
  }
  .v-btn__content{
    color: white;
  }

  .editor {
    width: 100%;
    height: 100%;
  }

  .edit_container {
      font-family: 'Avenir', Helvetica, Arial, sans-serif;
      -webkit-font-smoothing: antialiased;
      -moz-osx-font-smoothing: grayscale;
      text-align: center;
      color: #2c3e50;
      margin-top: 60px;
  }
  .ql-editor {
        height:700px;
  }

  .preview {
    height:740px;
  }

</style>
