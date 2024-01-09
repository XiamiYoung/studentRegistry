<template>
  <div>
    <template v-if="!$route.meta.allowAnonymous">
      <v-app id="inspire">
        <div class="app-container">
          <toolbar @toggleNavigationBar="drawer = !drawer"/>
          <navigation :toggle="drawer"/>
          <v-content>
            <breadcrumbs />
            <router-view/>
            <page-footer />
          </v-content>
        </div>
      </v-app>
    </template>
    <template v-else>
      <transition>
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </transition>
    </template>
  </div>
</template>

<script>
export default {
  name: "App",
  created: function() {

  },
  data() {
    return {
      drawer: true
    };
  }
};
</script>

<style>
#calendar {
  height: 100%;
}

.flashcard {
  border-radius: 10px;
  margin: 0px;
  padding: 0px;
  box-shadow: 0 0px 10px rgba(0, 0, 0, 0.4);
}
.flashcard:hover {
  box-shadow: 0 0px 25px rgba(0, 0, 0, 0.8);
}
.animated {
  animation-duration: 1s;
  animation-fill-mode: both;
}
@keyframes flipInX {
  from {
    transform: perspective(400px) rotate3d(1, 0, 0, 90deg);
    animation-timing-function: ease-in;
    opacity: 0;
  }
  40% {
    transform: perspective(400px) rotate3d(1, 0, 0, -20deg);
    animation-timing-function: ease-in;
  }
  60% {
    transform: perspective(400px) rotate3d(1, 0, 0, 10deg);
    opacity: 1;
  }
  80% {
    transform: perspective(400px) rotate3d(1, 0, 0, -5deg);
  }
  to {
    transform: perspective(400px);
  }
}
.flipInX {
  backface-visibility: visible !important;
  animation-name: flipInX;
}

.toolbar {
  font-weight: bold;
  font-size: 18px;
}

.toolbar .text {
  padding-left: 15px;
  color: white;
  text-decoration: none;
}

.item-title {
  font-size: 17px;
  font-weight: 500;
}
.item-sub-title {
  font-size: 15px;
  font-weight: 500;
}

.active {
  font-weight: bold;
}

.theme--light.v-icon {
  color: #457cb3;
}

.v-list__group__header--active .v-list__group__header__prepend-icon .v-icon {
  color: #e03e3e;
}

.v-list__group__items {
  margin-left: 20px;
}

.toolbar-page {
  background-color: rgba(49, 56, 62, 0.73) !important;
  color: white !important;
}

.v-btn--floating .v-icon {
  height: auto !important;
  width: inherit;
}

.v-progress-circular {
  margin: 1rem;
}

.bau-rota-name {
  font-size: 15px !important;
  line-height: 32px !important;
}

.theme--light.v-label {
  font-size: 20px !important;
  color: #f44336 !important;
}

.theme-default .cv-header-day {
  background-color: rgba(2, 188, 119, 0.15);
}

.simple-calendar {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  color: #2c3e50;
  height: 80vh !important;
  width: 60vw;
  margin-left: auto;
  margin-right: auto;
}

.first-header-item {
  margin-left: 240px;
}

.calendar-title {
  font-size: 2em !important;
  margin-top: 15px !important;
}

.event-item-backup {
  background-color: #82b1ff !important;
}

.event-item-region-1 {
  background-color: #f6756c !important;
}

.event-item-region-2 {
  background-color: #82b1ff !important;
}

.event-item-region-3 {
  background-color: #e4e41f !important;
}

.short-desc {
  white-space: normal;
  word-wrap: break-word;
  font-size: 1.2em;
  padding: 10px; 
}
</style>
