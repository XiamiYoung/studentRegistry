<template>
  <v-app id="login" class="secondary">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4 lg4>
            <v-card class="elevation-1 pa-3">
              <v-card-text>
                <div class="layout column align-center">
                  <h1 class="flex my-4 primary--text">外语考试预报名管理系统登录</h1>
                </div>
                <v-form>
                  <v-text-field
                    append-icon="person"
                    name="login"
                    label="登录名"
                    type="text"
                    v-model="username"
                    :error="error"
                    :rules="[rules.required]"/>
                  <v-text-field
                    :type="hidePassword ? 'password' : 'text'"
                    :append-icon="hidePassword ? 'visibility_off' : 'visibility'"
                    name="password"
                    label="密码"
                    id="password"
                    :rules="[rules.required]"
                    v-model="password"
                    :error="error"
                    @click:append="hidePassword = !hidePassword"/>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn block color="primary" @click="login" :loading="loading">登录</v-btn>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
      <v-snackbar
        v-model="showResult"
        :timeout="2000"
        top>
        {{ result }}
      </v-snackbar>
    </v-content>
  </v-app>
</template>

<script>
export default {
  created: function() {
  },
  data() {
    return {
      loading: false,
      username: '',
      password: '',
      hidePassword: true,
      error: false,
      showResult: false,
      result: '',
      rules: {
        required: value => !!value || '不能为空.'
      }
    }
  },
  methods: {
    login:function(){
      if(this.validateForm()){
        this.submitLogin();
      }
    },
    validateForm:function(){
        if(!this.username){
            this.$commons.showError("登录名不能为空", this);
					return false;
				}
				if(!this.password){
              this.$commons.showError("密码不能为空", this);
					return false;
				}

        return true;
    },
    submitLogin: function() {
        var requestObj = {
            url: this.$constants.interface.backend.endpoint + "/admin/login",
            successCallback: this.onSuccessLogin,
            failureCallback: this.onFailuredLogin,
            postData:{
              "userName":this.username,
              "password":this.password
            },
            ins: this,
            hideLoading: false
        };
        this.$commons.sendGatewayPost(requestObj);
    },
    onSuccessLogin: function(response,callbackParam) {
        this.$router.push({ name: 'Dashboard' });
        this.password = "";
    },
    onFailuredLogin: function(error,callbackParam) {
        console.error(JSON.stringify(error));
        this.formSubmitted = true;
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
  }
}
</script>

<style scoped lang="css">
  #login {
    height: 50%;
    width: 100%;
    position: absolute;
    top: 0;
    left: 0;
    content: "";
    z-index: 0;
  }

  .v-btn__content{
    color: white;
  }
</style>
