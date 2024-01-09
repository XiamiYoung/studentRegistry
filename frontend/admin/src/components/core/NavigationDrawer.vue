<template>
  <v-navigation-drawer
    v-model="toggle"
    fixed app>
    <v-toolbar flat dark :color="$root.themeColor" class="toolbar">
      <router-link :to="{ name: 'Dashboard' }">
      </router-link>
      <router-link :to="{ name: 'Dashboard' }" class="text">
         东北财经大学
      </router-link>
    </v-toolbar>
    <v-list>
      <v-list-tile @click="changeRoute('Dashboard', 1)">
        <v-list-tile-action>
          <v-icon :color="iconColor">dashboard</v-icon>
        </v-list-tile-action>
        <v-list-tile-title :class="[{'active': selectedIndex === 1}, 'item-title' ]" >报考数据管理</v-list-tile-title>
      </v-list-tile>
    </v-list>
    <v-list>
      <v-list-tile @click="changeRoute('DataExport', 2)">
        <v-list-tile-action>
          <v-icon :color="iconColor">dashboard</v-icon>
        </v-list-tile-action>
        <v-list-tile-title :class="[{'active': selectedIndex === 2}, 'item-title' ]" >报考数据导出</v-list-tile-title>
      </v-list-tile>
    </v-list>
    <v-list>
      <v-list-tile @click="changeRoute('NotificationEditor', 3)">
        <v-list-tile-action>
          <v-icon :color="iconColor">dashboard</v-icon>
        </v-list-tile-action>
        <v-list-tile-title :class="[{'active': selectedIndex === 3}, 'item-title' ]" >编辑通知内容</v-list-tile-title>
      </v-list-tile>
    </v-list>
    <v-list>
      <v-list-tile @click="changeRoute('SiteManagement', 4)">
        <v-list-tile-action>
          <v-icon :color="iconColor">dashboard</v-icon>
        </v-list-tile-action>
        <v-list-tile-title :class="[{'active': selectedIndex === 4}, 'item-title' ]" >网站全局管理</v-list-tile-title>
      </v-list-tile>
    </v-list>
  </v-navigation-drawer>
</template>

<script>
export default {
  props: {
    toggle: {
      type: Boolean,
      required: false,
      default: true
    }
  },

  data() {
    return {
      selectedIndex: 1,
      iconColor: "#457cb3"
    };
  },
  methods: {
    changeRoute: function(routeName, selectedIndex, isExternalLink) {
      if (!isExternalLink) {
        if(this.selectedIndex == selectedIndex){
          return;
        }else{
          this.selectedIndex = selectedIndex;
          return this.$router.push({ name: routeName });
        }
      } else {
        this.openExternal(routeName);
      }
    },
    openExternal: function(routeName) {
      window.open(this.$constants.interface.external[routeName].link);
    }
  }
};
</script>

<style scoped>
</style>
