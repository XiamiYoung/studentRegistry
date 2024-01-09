<template>
  <v-toolbar
    dark
    app
    :color="$root.themeColor">
    学士学位外语考试预报名管理系统
    <v-spacer></v-spacer>
    <v-menu offset-y origin="center center" :nudge-bottom="10" transition="scale-transition">
      <v-btn icon large flat slot="activator" :ripple="false">
        <v-avatar size="42px">
          <img src="https://avataaars.io"/>
        </v-avatar>
      </v-btn>
      <v-list>
        <v-list-tile
          v-for="(item,index) in items"
          :key="index"
          :to="!item.href ? { name: item.name } : null"
          :href="item.href"
          ripple="ripple"
          :disabled="item.disabled"
          :target="item.target"
          @click="item.click">
          <v-list-tile-action v-if="item.icon">
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-tile-action>
          <v-list-tile-content>
            <v-list-tile-title>{{ item.title }}</v-list-tile-title>
          </v-list-tile-content>
        </v-list-tile>
      </v-list>
    </v-menu>
  </v-toolbar>
</template>
<script>
export default {
  created: function() {
  },
  data() {
    return {
      items: [
        {
          icon: 'exit_to_app',
          href: '#',
          title: '退出',
          click: () => {
            this.logoutAdmin();
          }
        }
      ]
    };
  },

  methods: {
    logoutAdmin:function(){
      this.$store.commit("setUserName", "");
      this.$store.commit("setToken", "");
      const vm = this;
      vm.$router.push({ name: 'Login' })
    }
  }
};
</script>
<style scoped>
.header-img {
  margin-right: 20px;
}
.header-name {
  font-weight: bold;
}
</style>
