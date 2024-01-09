import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
var store = require('@/store/store')

Vue.use(VueAxios, axios);

axios.interceptors.request.use(
    config => {
        if(!config.url.endsWith("/admin/login")){
          if (store.default.state.token) {
              config.headers['Authorization'] = 'Bearer ' + store.default.state.token;
              config.headers['Logged-In-User'] = store.default.state.userName;
          }
        }
        return config;
    }
);

axios.interceptors.response.use(
  response => {
    if(response.headers
        &&response.headers['auth-token']
        &&response.headers['logged-in-user']){
            store.default.commit("setUserName", response.headers['logged-in-user']);
            store.default.commit("setToken", response.headers['auth-token']);
    }
    return response
  },
  error => {
    var httpCode = error.response.status;
    if (httpCode) {
      var errMsg = "Unexpected Error";
      var reasonCode = "";
      var springSecurityStatus = "";
      var springSecurityError = "";
      if (error.response.data) {
        //custom error body -- session expired
        if(error.response.data.msg){
          errMsg = error.response.data.msg;
        }
        reasonCode = error.response.data.reasonCode;
        //default spring security access denied body
        springSecurityStatus = error.response.data.status;
        springSecurityError = error.response.data.error;
        if (reasonCode === 'DSR2002'){
          error.handled = true;
          commonsJS.handleSessionInvalid();
        }else if(springSecurityStatus===403&&springSecurityError==="Forbidden"){
          error.handled = true;
          commonsJS.handleSessionInvalid();
        }
      }
    }
    return Promise.reject(error)
  }
)

var commonsJS = {
  sendGatewayPost: function (requestObj) {
    var headers = {};
    var loader = "";
    if (!requestObj.hideLoading) {
      var curr = this;
      loader = curr.showLoading(requestObj.ins);
    }

    if (!requestObj.headers) {
      headers = {
        'Content-Type': 'application/json'
      };
    } else {
      headers = requestObj.headers;
    }

    axios({
      method: "post",
      headers: headers,
      url: requestObj.url,
      data: requestObj.postData,
      withCredentials: true
    }).then(function (response) {
      if (!requestObj.hideLoading) {
        curr.closeLoading(loader);
      }
      requestObj.successCallback(response, requestObj.successCallbackParamObj);
    })
      .catch(function (error) {
        if (!requestObj.hideLoading) {
          curr.closeLoading(loader);
        }
        requestObj.failureCallback(error, requestObj.failureCallbackParamObj);
      });
  },
  sendGatewayGet: function (requestObj) {
    var headers = {};
    var loader = "";
    if (!requestObj.hideLoading) {
      var curr = this;
      loader = curr.showLoading(requestObj.ins);
    }

    if (requestObj.headers) {
      headers = requestObj.headers;
    }

    axios({
      method: "get",
      url: requestObj.url,
      headers: headers,
      withCredentials: true
    }).then(function (response) {
      if (!requestObj.hideLoading) {
        curr.closeLoading(loader);
      }
      requestObj.successCallback(response, requestObj.successCallbackParamObj);
    })
      .catch(function (error) {
        if (!requestObj.hideLoading) {
          curr.closeLoading(loader);
        }
        requestObj.failureCallback(error, requestObj.failureCallbackParamObj);
      });
  },
  sendGatewayDelete: function (requestObj) {
    var headers = {};
    var loader = "";
    if (!requestObj.hideLoading) {
      var curr = this;
      loader = curr.showLoading(requestObj.ins);
    }

    if (requestObj.headers) {
      headers = requestObj.headers;
    }

    axios({
      method: "delete",
      url: requestObj.url,
      headers: headers,
      withCredentials: true
    }).then(function (response) {
      if (!requestObj.hideLoading) {
        curr.closeLoading(loader);
      }
      requestObj.successCallback(response, requestObj.successCallbackParamObj);
    })
      .catch(function (error) {
        if (!requestObj.hideLoading) {
          curr.closeLoading(loader);
        }
        requestObj.failureCallback(error, requestObj.failureCallbackParamObj);
      });
  },
  sendGatewayDownload: function (requestObj) {
    var headers = {};
    var loader = "";
    if (!requestObj.hideLoading) {
      var curr = this;
      loader = curr.showLoading(requestObj.ins);
    }

    if (requestObj.headers) {
      headers = requestObj.headers;
    }

    axios({
      method: "get",
      url: requestObj.url,
      headers: headers,
      responseType: "arraybuffer",
      withCredentials: true
    }).then(function (response) {
      if (!requestObj.hideLoading) {
        curr.closeLoading(loader);
      }
      requestObj.successCallback(response, requestObj.successCallbackParamObj);
    })
      .catch(function (error) {
        if (!requestObj.hideLoading) {
          curr.closeLoading(loader);
        }
        requestObj.failureCallback(error, requestObj.failureCallbackParamObj);
      });
  },
  showLoading: function (ins) {
    let loader = ins.$loading.show({
      loader: 'spinner'
    });
    return loader;
  },
  closeLoading: function (loader) {
    loader.hide();
  },
  showSuccess: function (msg, ins) {
    ins.$toast.success(msg);
  },
  showMessage: function (msg, ins) {
    ins.$toast.info(msg);
  },
  showError: function (msg, ins) {
    ins.$toast.error(msg);
  },
  randomIntFromInterval: function (min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
  },
  navTo: function (ins, navName, queryObj) {
    if (queryObj) {
      ins.$router.push({
        name: navName,
        query: queryObj
      });
    } else {
      ins.$router.push({
        name: navName
      });
    }
  },
  handleSessionInvalid:function(){
    //clear session if any, and return to login page
    store.default.commit("setUserName", '');
    store.default.commit("setToken", '');
    window.location.href = "/";
  },
  getUrlParam: function(parameter){
    var urlparameter = null;
      if(window.location.href.indexOf(parameter) > -1){
          urlparameter = this.getUrlVars()[parameter];
      }
      return urlparameter;
  },
  getUrlVars:function() {
    var vars = {};
    window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
        vars[key] = value;
    });
    return vars;
}
}

export default commonsJS;
