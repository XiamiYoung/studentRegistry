import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    userName: sessionStorage.getItem("userName"),
    token: sessionStorage.getItem("token")
  },
  mutations: {
    setUserName(state,userName) {
        sessionStorage.setItem("userName",userName);
        state.userName = userName;
    },
    setToken(state,token) {
        sessionStorage.setItem("token",token);
        state.token = token;
    }
  }
});
export default store;
