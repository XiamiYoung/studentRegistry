<template>
  <v-container fluid grid-list-xl>
    <v-card class="animated flipInX flashcard">
      <div style="background-color: #001d80;">
        <v-card-title class="pl-5 pt-5">
          <div class="display-1 pl-5 pt-5 card-pic-font" style="color: white">报考数据导出</div>
        </v-card-title>
      </div>
      <v-card-text>
        <v-layout justify-center>	
            <v-btn 
              color="#001d80"
              @click="submitDownload" 
              large 
              style="margin-right:10ox">
              <div style="color: white">生成Excel</div>
              <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formDownloadSubmitting"></v-progress-circular>
            </v-btn>
            <v-btn 
              color="#001d80"
              @click="submitZipGenerate" 
              large 
              style="margin-right:10ox">
              <div style="color: white">生成下载压缩包</div>
              <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formZipGenerateSubmitting"></v-progress-circular>
            </v-btn>
        </v-layout>
        <v-layout justify-center>	
            <div v-for="(zipFileName,index) in zipFileNames" :key="index">
                <v-flex>
                  <v-chip  class="ma-2 animated flipInX flashcard" color="teal" text-color="white" @click="submitDownloadZipFile(zipFileName)" >
                     {{zipFileName}}.zip
                  </v-chip>
                  <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formDownloadZipFileSubmitting"></v-progress-circular>
                </v-flex>
            </div>
        </v-layout>
      </v-card-text>
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
      formDownloadSubmitting:false,
      formDownloadSubmitted:false,
      formZipGenerateSubmitting:false,
      formZipGenerateSubmitted:false,
      formDownloadZipFileSubmitted:false,
      formDownloadZipFileSubmitting:false,
      zipFileNames:[]
    };
  },
  methods: {
    submitDownload: function() {
        this.formDownloadSubmitted = false;
        this.formDownloadSubmitting = true;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/registry/export",
            successCallback: this.onSuccessSubmitDownloadForm,
            failureCallback: this.onFailuredSubmitDownloadForm,
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayDownload(requestObj);
    },
    onSuccessSubmitDownloadForm: function(response,callbackParam) {
        this.formDownloadSubmitted = true;
        this.formDownloadSubmitting = false;
        this.downloadFile(this.$constants.export.excelName,response.data,response.headers["content-type"] );
    },
    onFailuredSubmitDownloadForm: function(error,callbackParam) {
        //if not handled by interceptor
        if(!error.handled){
          console.error(JSON.stringify(error));
          this.formDownloadSubmitted = true;
          this.formDownloadSubmitting = false;
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
    submitZipGenerate: function() {
      this.formZipGenerateSubmitted = false;
      this.formZipGenerateSubmitting = true;
      var requestObj = {
        url: this.$constants.interface.backend.endpoint + "/admin/registry/zip/generate",
        successCallback: this.onSuccessSubmitZipGenerateForm,
        failureCallback: this.onFailuredSubmitZipGenerateForm,
        ins: this,
        hideLoading: true
      };
      this.$commons.sendGatewayGet(requestObj);
    },
    onSuccessSubmitZipGenerateForm: function(response,callbackParam) {
      this.formZipGenerateSubmitted = true;
      this.formZipGenerateSubmitting = false;
      this.zipFileNames = response.data.zipFileNames;
    },
    onFailuredSubmitZipGenerateForm: function(error,callbackParam) {
      //if not handled by interceptor
      if(!error.handled){
        console.error(JSON.stringify(error));
        this.formZipGenerateSubmitted = true;
        this.formZipGenerateSubmitting = false;
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
    submitDownloadZipFile: function(zipFileName) {
        this.formDownloadZipFileSubmitted = false;
        this.formDownloadZipFileSubmitting = true;
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/registry/zip/download/" + encodeURIComponent(zipFileName),
            successCallback: this.onSuccessSubmitDownloadZipFileForm,
            failureCallback: this.onFailuredSubmitDownloadZipFileForm,
            ins: this,
            hideLoading: true,
            successCallbackParamObj:{
              "zipFileName":zipFileName
            }
        };
        this.$commons.sendGatewayDownload(requestObj);
    },
    onSuccessSubmitDownloadZipFileForm: function(response,callbackParam) {
        this.formDownloadZipFileSubmitted = true;
        this.formDownloadZipFileSubmitting = false;
        this.downloadFile(callbackParam.zipFileName + ".zip",response.data,response.headers["content-type"] );
    },
    onFailuredSubmitDownloadZipFileForm: function(error,callbackParam) {
        //if not handled by interceptor
        if(!error.handled){
          console.error(JSON.stringify(error));
          this.formDownloadZipFileSubmitted = true;
          this.formDownloadZipFileSubmitting = false;
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
    downloadFile :function (fileName, content, fileType) {
        var fileData = new Blob([content], { type: fileType });
        if (window.navigator && window.navigator.msSaveOrOpenBlob) { // for IE
            window.navigator.msSaveOrOpenBlob(fileData, fileName);
        } else { // for Non-IE (chrome, firefox etc.)
            var a = document.createElement("a");
            document.body.appendChild(a);
            a.style = "display: none";
            var fileUrl = URL.createObjectURL(fileData);
            a.href =  fileUrl;
            a.download = fileName;
            a.click();
            URL.revokeObjectURL(a.href)
            a.remove();
        }
    }
  }
}
</script>

<style scoped>
  .student-pic{
    margin-top:80px
  }
  .v-btn__content{
    color: white;
  }
</style>
