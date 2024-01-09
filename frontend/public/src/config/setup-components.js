// Core Components
import Toolbar from '../components/core/Toolbar.vue';
import Breadcrumbs from '../components/core/Breadcrumbs.vue';
import PageFooter from '../components/core/PageFooter.vue';
import ImageUploader from 'vue-image-upload-resize'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

function setupComponents(Vue) {

  Vue.component('toolbar', Toolbar);
  Vue.component('breadcrumbs', Breadcrumbs);
  Vue.component('page-footer', PageFooter);
  Vue.component('ImageUploader', ImageUploader);

}


export {
  setupComponents
}
