import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'
import constants from '@/utils/constants.js'

Vue.use(VueAxios, axios);
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

    if (!requestObj.headers) {
      headers = {
        'Content-Type': 'application/json'
      };
    } else {
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
  }
}

export default commonsJS;
