import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    monitorData: sessionStorage.getItem("userToken") == null ? {} : sessionStorage.getItem("userToken")
  },
  mutations: {
    setMonitorData(state, userToken) {
      sessionStorage.setItem("userToken", JSON.stringify(userToken));
      state.userToken = userToken;
    }
  }
});
export default store;
