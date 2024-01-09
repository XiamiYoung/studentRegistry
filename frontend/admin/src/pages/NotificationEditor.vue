<template>
  <v-container fluid grid-list-xl>
    <v-card class="animated flipInX flashcard">
      <div style="background-color: #001d80;">
        <v-card-title class="pl-5 pt-5">
          <div class="display-1 pl-5 pt-5 card-pic-font" style="color: white">编辑通知内容</div>
        </v-card-title>
      </div>
      <v-card-text>
        <v-layout justify-center>	
          <v-flex xs12 md10 >
            <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formQuerySubmitting"></v-progress-circular>
            <v-card class="animated flipInX flashcard">
              <editor
                class="editor"
                v-model="content"
                :options="editorOption"
              />
            </v-card>
          </v-flex>
          <v-flex xs12>
             <div class="output ql-snow">
              <v-card class="animated flipInX flashcard preview">
                  <v-card-text>
                    <div class="ql-editor editor" v-html="content"></div>
                  </v-card-text>
              </v-card>
            </div>
          </v-flex>
        </v-layout>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
         <v-btn 
              color="#001d80"
              @click="submitEdit" 
              large 
              style="margin-right:10ox">
              <div style="color: white">提交</div>
              <v-progress-circular :size="22" :width="5" color="primary" indeterminate v-if="formEditSubmitting"></v-progress-circular>
          </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>

export default {
  components: {},
  created: function() {
    this.loadBulletin();
  },
  data() {
    return {
      formQuerySubmitting:false,
      formEditSubmitting:false,
      contentPk: null,
      content: '',
      editorOption: {
        // Some Quill options...
      }
    };
  },
  methods: {
    loadBulletin: function(){
      this.formQuerySubmitting = true;
      var requestObj = {
          url: this.$constants.interface.backend.endpoint + "/admin/bulletin",
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
          this.contentPk = response.data.bulletinData.pk;
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
            url: this.$constants.interface.backend.endpoint + "/admin/bulletin",
            successCallback: this.onSuccessSubmitEditForm,
            failureCallback: this.onFailuredSubmitEditForm,
            postData:{
              pk: this.contentPk,
              content: this.content
            },
            ins: this,
            hideLoading: true
        };
        this.$commons.sendGatewayPost(requestObj);
    },
    onSuccessSubmitEditForm: function(response,callbackParam) {
        this.formEditSubmitting = false;
        if(response.data && response.data.pk){
          this.contentPk = response.data.pk;
        }
        this.$commons.showSuccess("保存成功！", this);
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
        height:700px !important;
  }

  .preview {
    height:740px;
  }

</style>
